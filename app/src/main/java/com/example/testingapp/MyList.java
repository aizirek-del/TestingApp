package com.example.testingapp;

public class MyList {

     public int id;
     public String title;
     public String body;

    public String getTitle() {

        return title;
    }

    public String getBody() {

        return body;
    }

    public  int getId(){

        return id;
   }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setBody(String body) {

        this.body = body;
    }

    public void setId(int id){

        this.id = id;
   }
}
