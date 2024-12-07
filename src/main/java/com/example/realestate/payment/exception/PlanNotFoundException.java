package com.example.realestate.payment.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class PlanNotFoundException extends RuntimeException {

    private final String message;

}
