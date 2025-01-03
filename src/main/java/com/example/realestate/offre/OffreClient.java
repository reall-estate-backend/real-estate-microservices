package com.example.realestate.offre;


import com.example.realestate.prediction.PredictionRequest;
import com.example.realestate.prediction.PredictionResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "offre-service",
        url = "http://localhost:8050/api/v1/offres"
)
public interface OffreClient {


    @PostMapping("/addOffre")
    public String createOfferWithImmobilier(@RequestBody @Valid OffreRequest request);


    @PutMapping("/updateOffre/{offreId}")
    public String updateOfferWithImmobilier(@RequestBody OffreRequest request, @PathVariable String offreId);


    @DeleteMapping("/deleteOffre/{offreId}")
    public void deleteOfferWithImmobilier(@PathVariable String offreId);


    @GetMapping("/getOffre/{offerId}")
    public OffreResponse getOfferWithImmobilier(@PathVariable String offerId);

    @GetMapping("/allOffers")
    public List<OffreResponse> getAllOffers();


    @GetMapping("/allOffers/{userId}")
    public List<OffreResponse> getOffresByUserId(@PathVariable("userId") String userId);


    @GetMapping("/search")
    public List<OffreResponse> searchOffresByCity(@RequestParam String city);


    @PostMapping("/predictHousePrice")
    public PredictionResponse predictHousePrice(@RequestBody PredictionRequest request);


}
