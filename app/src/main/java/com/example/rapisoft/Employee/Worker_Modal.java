package com.example.rapisoft.Employee;

public class Worker_Modal {
    private int imageResId;
    private String name;
    private String category;
    private String email;
    private String phone;


    public Worker_Modal(String name, String category,String phone,  String email, int imageResId) {
        this.imageResId = imageResId;
        this.name = name;
        this.category = category;
        this.email = email;
        this.phone = phone;
    }


    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
