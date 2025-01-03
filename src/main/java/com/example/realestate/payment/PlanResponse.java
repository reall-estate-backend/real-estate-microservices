package com.example.realestate.payment;

public record PlanResponse(
        String id,
        String name,
        String description,
        int maxPrediction
) {
}

