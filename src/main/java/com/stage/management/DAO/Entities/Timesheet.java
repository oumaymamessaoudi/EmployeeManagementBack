package com.stage.management.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Timesheet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="timesheet")
    private List<TimeEntry> TimeEntries;

 @JsonIgnore
   @ManyToOne
    OurUsers utilisateur;

    @Column
    private int totalHours;

    public int getTotalHours() {
        return TimeEntries.stream()
                .mapToInt(entry -> entry.getHeuresLundi() +
                        entry.getHeuresMardi() +
                        entry.getHeuresMercredi() +
                        entry.getHeuresJeudi() +
                        entry.getHeuresVendredi())
                .sum();
    }

    public void updateTotalHours() {
        this.totalHours = getTotalHours();
    }
}
