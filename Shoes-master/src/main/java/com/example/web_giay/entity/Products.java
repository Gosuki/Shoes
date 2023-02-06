package com.example.web_giay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import static jakarta.persistence.CascadeType.PERSIST;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Products extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    @LastModifiedDate
    private Date modifiedDate;
    @ManyToOne
    @JoinColumn(name = "id_category" ,referencedColumnName = "id")
    private Category category;

    @Column(name = "id_status")
    private int statusId;
    @Column(name = "url_img")
    private String urlImage;

    @OneToOne(cascade = PERSIST, mappedBy = "products")
    private Description description;


}
