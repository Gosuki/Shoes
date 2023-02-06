package com.example.web_giay.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "description")
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Products products;

    @Column(name = "code")
    private String code;
    @Column(name = "description_detail")
    private String detailDescription;
    @Column(name = "material")
    private String material;
    @Column(name = "brands")
    private String design;
    @Column(name = "madein")
    private String madein;

}
