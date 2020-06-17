package com.example.part1.lesson15.task1.Model;

import java.util.Date;

public class Order {
    private Integer id;
    private Client client;
    private Product product;
    private  java.sql.Date OrderDate;

    public Integer getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Product getProduct() {
        return product;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrderDate(java.sql.Date orderDate) {
        OrderDate = orderDate;
    }

    public Order(Integer id, Client client, Product product, java.sql.Date orderDate) {
        this.id = id;
        this.client = client;
        this.product = product;
        OrderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client.getName() +
                ", product=" + product.getName() +
                ", OrderDate=" + OrderDate +
                '}';
    }
}
