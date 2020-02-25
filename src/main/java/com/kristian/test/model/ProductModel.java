package com.kristian.test.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)

public class ProductModel {

    public ProductModel() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false, name = "name")
    @NotEmpty
    private String name;


    @Column(nullable = false, name = "price")
//    @NotEmpty
    private double price;


    @Column(nullable = false, name = "imagePath")
    @NotEmpty
    private String imagePath;


    @Column(nullable =  false, name = "description")
    @NotEmpty
    private String description;


    @Column(nullable = false, name = "quantityAvailable")
    private Integer quantityAvailable;


    @Column(name = "to_date")
    private Date toDate;


    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getQuantityAvailable() {

        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }


    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

}

