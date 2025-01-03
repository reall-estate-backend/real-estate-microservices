package com.example.realestate.offre;


public record OffreRequest(
        String id,
        ImmobilierRequest immobilierRequest,
        String userId
) {
}
