package com.example.simplemovieapps.model;

public class UserModel {
    private String nama, username,password;
    private int id;

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public UserModel(String nama, String username, String password){
        this.nama=nama;
        this.username=username;
        this.password=password;
    }
    public UserModel(Integer id, String nama, String username, String password){
        this.id=id;
        this.nama=nama;
        this.username=username;
        this.password=password;
    }
}
