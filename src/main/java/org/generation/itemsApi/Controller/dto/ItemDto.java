package org.generation.itemsApi.Controller.dto;

public class ItemDto {
    private String name;
    private String imageURL;
    private String description;
    private Double price;


    public ItemDto(String name, String imageURL, String description, Double price) {
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
        this.price = price;
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

