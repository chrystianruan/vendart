package com.api.vendart.services;

import com.api.vendart.dtos.ProductDTO;
import com.api.vendart.entities.Product;
import com.api.vendart.repositories.CategoryRepository;
import com.api.vendart.repositories.ProductRepository;
import com.api.vendart.repositories.SellerRepository;
import com.api.vendart.utils.ListUtil;
import com.api.vendart.utils.ResponseUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ListUtil listUtil;
    @Autowired
    private ResponseUtil responseUtil;

    private Map<String, String> response;

    public List<?> getAll() {
        if (productRepository.findAll().isEmpty()) {
            return listUtil.getEmptyListWithMessage("Nenhum produto encontrado");
        }
        List<ProductDTO> products = new ArrayList<>();
        try {
            for (Product product : productRepository.findAll()) {
                products.add(product.parseToDTO());
            }
            return products;

        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException);
        }

    }

    public ResponseEntity<?> show(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            String[][] message = {{"response"}, {"Nenhum produto encontrado"}};
            return new ResponseEntity<>(responseUtil.getResponse(message, 1), HttpStatus.NOT_FOUND);
        }
        try {
            ProductDTO productDTO = productRepository.findOne(id).parseToDTO();
            return new ResponseEntity<>(productDTO, HttpStatus.OK);

        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException);
        }


    }

    @Transactional
    public ResponseEntity<?> save(ProductDTO dto) {
        try {
            Map<String, String> categoryOrSellerExists = verifyCategoryOrSellerExists(dto.getCategoryId(), dto.getSellerId());

            if (categoryOrSellerExists != null) {
                return new ResponseEntity<>(categoryOrSellerExists, HttpStatus.METHOD_NOT_ALLOWED);
            }

            Product product = new Product();
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setImage(dto.getImage());
            product.setCategory(categoryRepository.findOne(dto.getCategoryId()));
            product.setSeller(sellerRepository.findOne(dto.getSellerId()));
            product.setValue(dto.getValue());
            product.setCreatedAt(new Date());
            product.setUpdatedAt(new Date());
            productRepository.save(product);

            response.clear();
            response.put("response", "Produto "+dto.getName()+" cadastrado com sucesso!");

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException);
        }
    }

    public Map<String, String> verifyCategoryOrSellerExists(Long categoryId, Long sellerId) {
        if (sellerRepository.findById(sellerId).isEmpty() || categoryRepository.findById(categoryId).isEmpty()) {
            if (sellerRepository.findById(sellerId).isEmpty()) {
                response.clear();
                response.put("response", "O vendedor não existe");
                return response;
            }
            if (categoryRepository.findById(categoryId).isEmpty()) {
                response.clear();
                response.put("response", "A categoria não existe");
                return response;
            }
        }
        return null;
    }


}
