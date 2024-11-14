package com.stage.management.DAO.Repositories;

import com.stage.management.DAO.Entities.Activité;
import com.stage.management.DAO.Entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Integer> {
}
