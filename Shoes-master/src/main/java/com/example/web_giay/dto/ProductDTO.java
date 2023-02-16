package com.example.web_giay.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Data
public class ProductsDTO {
    private Long id;
    private String name;
    private Double price;
    private String categoryname;
    private Long categoryid;
    private int statusId;
    private Date modifiedDate;
    private String urlImage;
}
