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
public class Order {
    private int id;
    private int idUser;
    private String description;
    private double total;
    private String status;

    public Order(int id, int idUser, String description, double total, String status) {
        this.id = id;
        this.idUser = idUser;
        this.description = description;
        this.total = total;
        this.status = status;
    }

    public Order(int idUser, String description, double total) {
        this.idUser = idUser;
        this.description = description;
        this.total = total;
    }
    

    public Order(int idUser, String description, double total, String status) {
        this.idUser = idUser;
        this.description = description;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
