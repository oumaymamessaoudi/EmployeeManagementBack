package com.stage.management.DAO.Repositories;

import com.stage.management.DAO.Entities.TimeEntry;
import com.stage.management.DAO.Entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeEntryRepository extends JpaRepository<TimeEntry,Integer> {
  List<TimeEntry> findAllByTimesheet(Timesheet feuilleDeTemps);

}
