package com.example.realestate.payment.repository;

import com.example.realestate.payment.entity.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    Optional<Subscription> findSubscriptionByUserId(String userId);
}
