package com.example.realestate.offre;

import java.time.LocalDate;

public record OffreResponse(
        String id,
        ImmobilierResponse immobilierResponse,
        String userId,
        LocalDate dateDePublication,
        LocalDate dateDeUpdate
) {
}
