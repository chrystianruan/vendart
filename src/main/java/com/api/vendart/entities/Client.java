package com.api.vendart.entities;

import com.api.vendart.dtos.ClientDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
@Entity
@Table(name = "clients")
@SQLDelete(sql = "UPDATE clients SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at is null")
@Getter @Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "telephone")
    private String telephone;
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

    public Client() {}

    public ClientDTO parseToDTO() {
        return null;
    }
}
