package com.example.web_giay.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String categoryname;
    private Long categoryid;
    private int statusId;
    private Date modifiedDate;
    private String urlImage;
}
