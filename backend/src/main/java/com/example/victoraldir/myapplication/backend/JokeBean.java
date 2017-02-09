package com.example.victoraldir.myapplication.backend;

/**
 * The object model for the Joke we are sending through endpoints
 */
public class JokeBean {

    private String joke;

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}