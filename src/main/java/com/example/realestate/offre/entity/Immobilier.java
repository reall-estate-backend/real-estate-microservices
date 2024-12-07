package com.example.realestate.offre.entity;


import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "immobiliers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Immobilier {
    @Id
    private String id;
    private String title;
    private List<String> images;
    private int bedroom;
    private int bathroom;
    private double price;
    private String address;
    private String city;
    private double latitude;
    private double longitude;
    private String description;
    private String type;
    private String property;
    private String utilities;
    private String petPolicy;
    private String incomePolicy;
    private double size;
    private double schoolDistance;
    private double busDistance;
    private double restaurantDistance;

}
