package com.example.realestate.offre.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;


@Document(collection = "offres")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offre {
    @Id
    private String id;
    @DBRef
    private Immobilier immobilier;
    private String userId;
    private LocalDate dateDePublication;
    private LocalDate dateDeUpdate;
}
