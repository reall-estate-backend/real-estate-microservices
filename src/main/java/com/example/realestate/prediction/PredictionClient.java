package com.example.realestate.prediction;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        name = "prediction-service",
        url = "http://localhost:8000"
)
public interface PredictionClient {

    @PostMapping("/api/predict/")
    ResponseEntity<PredictionResponse> predictHousePrice(@RequestBody PredictionRequest request);

}
