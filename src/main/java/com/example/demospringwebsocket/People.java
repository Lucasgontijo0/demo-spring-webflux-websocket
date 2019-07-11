package com.example.demospringwebsocket;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class People {
    private String name;
    private String height;
    private String mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;
    private String homeWorld;
    List<String> films = new ArrayList<>();
    List<String> species = new ArrayList<>();
    List<String> vehicles = new ArrayList<>();
    List<String> starShips = new ArrayList<>();
    private String created;
    private String edited;
    private String url;
}