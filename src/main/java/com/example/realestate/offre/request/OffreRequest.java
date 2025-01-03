package com.example.realestate.offre.request;

import java.time.LocalDate;


public record OffreRequest(
        String id,
        ImmobilierRequest immobilierRequest,
        String userId
) {
}
