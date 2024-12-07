package com.example.realestate.offre.response;

import java.time.LocalDate;

public record OffreResponse(
        String id,
        ImmobilierResponse immobilierResponse,
        String userId,
        LocalDate dateDePublication,
        LocalDate dateDeUpdate
) {
}
