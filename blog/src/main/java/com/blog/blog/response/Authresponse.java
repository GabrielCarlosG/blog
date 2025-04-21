package com.blog.blog.response;

public class Authresponse {
    final String token;

    public Authresponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
