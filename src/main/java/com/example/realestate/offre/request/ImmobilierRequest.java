package com.example.realestate.offre.request;


import java.util.List;

public record ImmobilierRequest (
        String id,
        String title ,
        List<String> images ,
        int bedroom ,
        int bathroom ,
        double price ,
        String address ,
        String city ,
        double latitude ,
        double longitude,
        String description,
        String type,             // Added: Type (rent/buy)
        String property,         // Added: Property (apartment, house, etc.)
        String utilities,        // Added: Utilities Policy
        String petPolicy,        // Added: Pet Policy
        String incomePolicy,     // Added: Income Policy
        double size,             // Added: Size in sqft
        double schoolDistance,   // Added: School distance
        double busDistance,      // Added: Bus stop distance
        double restaurantDistance // Added: Restaurant distance


){
}
