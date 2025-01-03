package com.example.realestate.offre.repository;

import com.example.realestate.offre.entity.Immobilier;
import com.example.realestate.offre.entity.Offre;
import com.example.realestate.offre.response.OffreResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;


@Repository
public interface OffreRepository extends MongoRepository<Offre, String> {
    //here username is like userId
    List<Offre> findOffreByUserId(String userId);


    // Vous ne pouvez pas directement accéder à immobilier.city, mais vous pouvez accéder à l'ID de l'immobilier
    List<Offre> findByImmobilier_Id(String immobilierId);


}
