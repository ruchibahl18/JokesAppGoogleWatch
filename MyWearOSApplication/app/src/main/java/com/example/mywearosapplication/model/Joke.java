package com.example.mywearosapplication.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Joke implements Serializable {
    String id;
    String joke;
    int status;
}
