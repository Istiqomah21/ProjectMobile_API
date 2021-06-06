package com.example.simplemovieapps.model;

public class SaveModel {
    public Integer getId() {
        return id;
    }



    public Integer getNum() {
        return num;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public String getTags() {
        return tags;
    }

    public String getFavorite() {
        return favorite;
    }

    private Integer id, num;
    private String author, body, favorite, tags;


    public SaveModel(String author, String body, String tags, String favorite, Integer num) {
        this.author = author;
        this.body = body;
        this.tags = tags;
        this.favorite = favorite;
        this.num = num;
    }

    public SaveModel(Integer id, String author, String body,String tags, String favorite, Integer num) {
        this.id=id;
        this.author = author;
        this.body = body;
        this.tags = tags;
        this.favorite = favorite;
        this.num = num;
    }


}
