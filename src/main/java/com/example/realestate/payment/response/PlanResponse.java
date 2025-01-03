package com.example.realestate.payment.response;

public record PlanResponse (
        String id,
        String name,
        String description,
        int maxPrediction
) {
}

