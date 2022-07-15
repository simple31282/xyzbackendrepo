package org.generation.itemsApi.entity;

import org.generation.itemsApi.Controller.dto.ItemDto;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    private String name;
    private String imageURL;
    private Double price;
    private String description;

    public Item() {
    }

    public Item(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.imageURL = itemDto.getImageURL();
        this.description = itemDto.getDescription();
        this.price = itemDto.getPrice();


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
