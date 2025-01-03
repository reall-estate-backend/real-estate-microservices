package com.example.realestate.payment.request;

public record PlanRequest (
        String id,
        String name,
        String description,
        int maxPrediction
) {
}

