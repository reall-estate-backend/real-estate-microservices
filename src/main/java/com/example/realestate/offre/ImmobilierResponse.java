package com.example.realestate.offre;


import java.util.List;

public record ImmobilierResponse(
        String id,
        String title ,
        List<String> images,
        int bedroom ,
        int bathroom ,
        double price ,
        String address ,
        String city ,
        double latitude ,
        double longitude,
        String description ,
        String type,             //Type (rent/buy)
        String property,         //Property (apartment, house, etc.)
        String utilities,        //Utilities Policy
        String petPolicy,        //Pet Policy
        String incomePolicy,     //Income Policy
        double size,             //Size in sqft
        double schoolDistance,   //School distance
        double busDistance,      //Bus stop distance
        double restaurantDistance //Restaurant distance
){
}

