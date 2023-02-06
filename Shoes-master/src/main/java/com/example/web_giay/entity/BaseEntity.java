package com.example.web_giay.entity;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

public class BaseEntity {
    @CreatedDate
    private Date createAt;
    @UpdateTimestamp
    private Date updateAt;
}
