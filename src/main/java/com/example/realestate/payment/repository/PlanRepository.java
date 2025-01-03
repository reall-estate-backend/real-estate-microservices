package com.example.realestate.payment.repository;

import com.example.realestate.payment.entity.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends MongoRepository<Plan, String> {
    Optional<Plan> findPlanByName(String name);
}