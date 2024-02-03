package com.api.vendart.entities;

import com.api.vendart.dtos.PostDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
@Entity
@Table(name = "posts")
@SQLDelete(sql = "UPDATE posts SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at is null")
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "images")
    private String images;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    public Post() {}

    public PostDTO parseToDTO() {
        return null;
    }
}
