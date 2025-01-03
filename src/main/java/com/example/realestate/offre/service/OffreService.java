package com.example.realestate.offre.service;

import com.example.realestate.User.UserClient;
import com.example.realestate.offre.entity.Immobilier;
import com.example.realestate.offre.entity.Offre;
import com.example.realestate.offre.exception.BusinessException;
import com.example.realestate.offre.mapper.ImmobilierMapper;
import com.example.realestate.offre.mapper.OffreMapper;
import com.example.realestate.offre.request.OffreRequest;
import com.example.realestate.offre.repository.ImmobilierRepository;
import com.example.realestate.offre.repository.OffreRepository;
import com.example.realestate.offre.response.OffreResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OffreService {

    private final OffreRepository offreRepository;
    private final ImmobilierRepository immobilierRepository;
    private final UserClient userClient;

    public OffreService(OffreRepository offreRepository,
                        ImmobilierRepository immobilierRepository,
                        ImmobilierMapper immobilierMapper,
                        OffreMapper offreMapper,
                        UserClient userClient) {
        this.offreRepository = offreRepository;
        this.immobilierRepository = immobilierRepository;
        this.userClient = userClient;
    }


    @Transactional(rollbackFor = Exception.class)
    public String createOfferWithImmobilier(OffreRequest offreRequest) {
        Immobilier immobilier = ImmobilierMapper.toImmobilier(offreRequest.immobilierRequest());
        Immobilier savedImmobilier = immobilierRepository.save(immobilier);

        Offre offre = OffreMapper.toOffre(offreRequest);
        offre.setUserId(offreRequest.userId());
        offre.setImmobilier(savedImmobilier);
        offre.setDateDePublication(LocalDate.now());
        offre.setDateDeUpdate(LocalDate.now());

        Offre savedOffre = offreRepository.save(offre);

        return savedOffre.getId();
    }


    @Transactional(rollbackFor = Exception.class)
    public String updateOfferWithImmobilier(OffreRequest offreRequest, String offreId) {

        Offre existingOffre = offreRepository.findById(offreId)
                .orElseThrow(() -> new RuntimeException("Offer not found with id: " + offreId));

        if (!existingOffre.getUserId().equals(offreRequest.userId())) {
            throw new RuntimeException("You are not authorized to update this offer.");
        }
        existingOffre.setDateDeUpdate(LocalDate.now());
        Immobilier updatedImmobilier = ImmobilierMapper.toImmobilier(offreRequest.immobilierRequest());
        if (updatedImmobilier != null) {
            updatedImmobilier.setId(existingOffre.getImmobilier().getId());
            immobilierRepository.save(updatedImmobilier);
            existingOffre.setImmobilier(updatedImmobilier);
        }
        Offre savedOffre = offreRepository.save(existingOffre);
        return savedOffre.getId();
    }


    public void deleteOfferWithImmobilier(String offreId) {
        Offre offer = offreRepository.findById(offreId)
                .orElseThrow(() -> new BusinessException("Offer not found with id: " + offreId));
        Immobilier immobilier = offer.getImmobilier();
        offreRepository.delete(offer);

        if (immobilier != null) {
            immobilierRepository.delete(immobilier);
        }
    }
    public OffreResponse getOfferWithImmobilier(String offerId) {
        Offre offre = offreRepository.findById(offerId)
                .orElseThrow(() -> new BusinessException("Offer not found with id: " + offerId));
        return OffreMapper.fromOffre(offre);
    }

//    public List<OffreResponse> getAllOffers() {
//        List<Offre> offreList = offreRepository.findAll();
//
//        return offreList.stream()
//                .map(OffreMapper::fromOffre)
//                .collect(Collectors.toList());
//    }


    public List<OffreResponse> getAllOffers() {
        List<Offre> offreList = offreRepository.findAll();

        if (offreList.isEmpty()) {
            log.warn("Aucune offre trouvée.");
        }

        return offreList.stream()
                .map(offre -> {
                    if (offre.getImmobilier() == null) {
                        log.warn("Offre {} sans immobilier associé", offre.getId());
                    }
                    return OffreMapper.fromOffre(offre);
                })
                .collect(Collectors.toList());
    }


    public List<OffreResponse> findOffersByUserId(String userId) {
        List<Offre> offers = offreRepository.findOffreByUserId(userId);

        if (offers.isEmpty()) {
            log.warn("No offers found for user ID: {}", userId);
            throw new BusinessException("Aucune offre trouvée pour l'utilisateur avec l'ID : " + userId);
        }

        return offers.stream()
                .map(OffreMapper::fromOffre)
                .collect(Collectors.toList());
    }

    public List<OffreResponse> searchByCity(String city) {
        // Recherchez tous les immobiliers dans la ville donnée
        List<Immobilier> immobiliers = immobilierRepository.findByCity(city);

        if (!immobiliers.isEmpty()) {
            // Créez une liste pour stocker les offres
            List<Offre> offres = new ArrayList<>();

            // Pour chaque immobilier trouvé, recherchez les offres associées
            for (Immobilier immobilier : immobiliers) {
                List<Offre> offresForImmobilier = offreRepository.findByImmobilier_Id(immobilier.getId());
                offres.addAll(offresForImmobilier); // Ajoutez les offres trouvées à la liste principale
            }
            return offres.stream()
                    .map(OffreMapper::fromOffre)
                    .collect(Collectors.toList());

        }

        return new ArrayList<>(); // Si aucun immobilier n'est trouvé, retourner une liste vide
    }

}
