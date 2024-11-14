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
public class TimeEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int heuresTravaillees;



    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @ManyToOne
    @JoinColumn(name = "release_id")
    private Release release;

    @ManyToOne
    @JoinColumn(name = "activite_id")
    private Activité activite;
     @ManyToOne
     @JsonIgnore // Avoid circular references
     Timesheet timesheet;



    private int heuresLundi;
    private int heuresMardi;
    private int heuresMercredi;
    private int heuresJeudi;
    private int heuresVendredi;

}
