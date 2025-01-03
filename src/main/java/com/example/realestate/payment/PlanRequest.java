package com.example.realestate.payment;

public record PlanRequest(
        String id,
        String name,
        String description,
        int maxPrediction
) {
}

