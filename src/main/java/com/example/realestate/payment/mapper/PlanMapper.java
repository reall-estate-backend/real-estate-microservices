package com.example.realestate.payment.mapper;


import com.example.realestate.payment.entity.Plan;
import com.example.realestate.payment.request.PlanRequest;
import com.example.realestate.payment.response.PlanResponse;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {
    public Plan toPlan(PlanRequest request) {
        if (request == null) {
            return null;
        }
        return Plan.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .maxPrediction(request.maxPrediction())
                .build();
    }

    public PlanResponse fromPlan(Plan user) {

        return new PlanResponse(
                user.getId(),
                user.getName(),
                user.getDescription(),
                user.getMaxPrediction()
        );
    }
}
