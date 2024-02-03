package com.api.vendart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sellers")
@SQLDelete(sql = "UPDATE sellers SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at is null")
@Getter @Setter
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "location")
    private String location;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @OneToMany(mappedBy = "seller")
    private List<Product> products;

    public Seller() {}
}
