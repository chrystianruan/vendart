package com.api.vendart.services;

import com.api.vendart.dtos.CategoryDTO;
import com.api.vendart.entities.Category;
import com.api.vendart.entities.Product;
import com.api.vendart.enums.UserResponsesEnum;
import com.api.vendart.repositories.CategoryRepository;
import com.api.vendart.utils.ListUtil;
import com.api.vendart.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ListUtil listUtil;
    @Autowired
    private ResponseUtil responseUtil;
    public List<?> getAll() {
        try {
            List<String> emptyList = listUtil.getEmptyListWithMessage("Não há categorias disponíveis");
            if (categoryRepository.findAll().isEmpty()) {
                return emptyList;
            }
            List<CategoryDTO> categories = parseListObjectToDTO(categoryRepository.findAll());

            return categories;
        } catch (RuntimeException run) {
            throw new RuntimeException(run);
        }
    }
    public ResponseEntity<?> save(@RequestBody CategoryDTO categoryDTO) {
        try {
            Category category = new Category();
            category.setName(categoryDTO.getName());
            category.setCreatedAt(new Date());
            category.setUpdatedAt(new Date());
            categoryRepository.save(category);

            return new ResponseEntity<>("Categoria "+categoryDTO.getName()+" criada com sucesso", HttpStatus.CREATED);
        } catch (RuntimeException runEx) {
            throw new RuntimeException(runEx);
        }

    }

    public ResponseEntity<?> show(Long id) {
        try {
            if (categoryRepository.findById(id).isEmpty()) {
                String[][] listResponse = {{"response"},{"Nenhuma categoria encontrada"}};

                return new ResponseEntity<>(responseUtil.getResponse(listResponse, 1), HttpStatus.NOT_FOUND);
            }
            CategoryDTO category = categoryRepository.findOne(id).parseToDTO();

            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (RuntimeException runEx) {
            throw new RuntimeException(runEx);
        }

    }

    public ResponseEntity<?> update(Long id, CategoryDTO categoryDTO) {
        try {
            if (categoryRepository.findById(id).isEmpty()) {
                String[][] response = {{"response"}, {"Categoria não encontrada"}};
                return new ResponseEntity<>(responseUtil.getResponse(response, 1), HttpStatus.NOT_FOUND);
            }
            String[][] badResponse = {{"response"}, {"Erro ao atualizar categoria"}};
            String[][] goodResponse = {{"response"},{"Categoria atualizada com sucesso"}};
            categoryRepository.findById(id)
                    .map(categoria -> {
                        categoria.setName(categoryDTO.getName());
                        categoria.setUpdatedAt(new Date());

                        return new ResponseEntity<>(responseUtil.getResponse(goodResponse,1), HttpStatus.OK);
                    }).orElse(new ResponseEntity<>(responseUtil.getResponse(badResponse, 1), HttpStatus.INTERNAL_SERVER_ERROR));
            return null;
        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException);
        }
    }

    public ResponseEntity<?> delete(Long id, int userResponse) {
        try {
            if (categoryRepository.findById(id).isEmpty()) {
                String[][] response = {{"response"}, {"Categoria não encontrada"}};

                return new ResponseEntity<>(responseUtil.getResponse(response, 1), HttpStatus.NOT_FOUND);
            }
            if (userResponse == UserResponsesEnum.NAO.getValue()) {
                String[][] response = {{"response"}, {"Tem certeza que deseja apagar a categoria? (Todos os produtos que pertecem a essa categoria terão a categoria Outros"}};

                return new ResponseEntity<>(responseUtil.getResponse(response, 1), HttpStatus.METHOD_NOT_ALLOWED);
            }
            Category category = categoryRepository.findOne(id);
            category.getProducts();

            categoryRepository.deleteById(id);

            String[][] response = {{"response"}, {"A categoria foi deletada com sucesso!"}};

            return new ResponseEntity<>(responseUtil.getResponse(response, 1), HttpStatus.OK);
        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException);
        }

    }

    public List<CategoryDTO> parseListObjectToDTO(List<Category> categories) {
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        for (Category category : categories) {
            CategoryDTO categoryDTO = category.parseToDTO();
            categoriesDTO.add(categoryDTO);
        }

        return categoriesDTO;
    }

    public void removeAllProductsOfCategory() {

    }
}
