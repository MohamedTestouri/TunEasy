/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuneasy.entities;

/**
 *
 * @author weixin
 */
public class Product {
    private int id;
    private String name;
    private String image;
    private String subtitle;
    private String description;
    private double prix;
    private int idCategory;

    public Product() {
    }

    public Product(String name, String image, String subtitle, String description, double prix, int idCategory) {
        this.name = name;
        this.image = image;
        this.subtitle = subtitle;
        this.description = description;
        this.prix = prix;
        this.idCategory = idCategory;
    }

    public Product(int id, String name, String image, String subtitle, String description, double prix, int idCategory) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.subtitle = subtitle;
        this.description = description;
        this.prix = prix;
        this.idCategory = idCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    
}
