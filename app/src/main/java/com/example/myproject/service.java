package com.example.myproject;

public class service {
    private String name, description, time;
    private int price;

    public service(String name, String description, int price, String time) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.time = time;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public String getTime() { return time; }
}
