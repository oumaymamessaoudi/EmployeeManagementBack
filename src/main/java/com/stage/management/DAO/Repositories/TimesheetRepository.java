package com.stage.management.DAO.Repositories;
import com.stage.management.DAO.Entities.OurUsers;
import com.stage.management.DAO.Entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {
    List<Timesheet> findAllByUtilisateur(OurUsers utilisateur);
    List<Timesheet> findByUtilisateurId(Integer userId);

}
