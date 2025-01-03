package com.example.realestate.payment.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class SubscriptionNotFoundException extends RuntimeException {

    private final String message;

}

