package org.example.binancetradebotsimulator.kama;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KAMA {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Double result;

    private Date closeDate;
}
