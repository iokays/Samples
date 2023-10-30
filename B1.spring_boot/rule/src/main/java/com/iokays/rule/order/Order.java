package com.iokays.rule.order;

public class Order {
    private String customer;
    private double total;

    public Order(String customer, double total) {
        this.customer = customer;
        this.total = total;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCustomer() {
        return customer;
    }

}
