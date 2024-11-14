package com.stage.management.Services;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.stage.management.DAO.Entities.*;
import com.stage.management.DAO.Repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TimesheetService implements ITimesheetService {
    @Autowired
    private UsersRepo ourUsersRepository;
    @Autowired
    private TimesheetRepository timesheetRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ActivitéRepository activiteRepository;
    @Autowired
    private ReleaseRepository  releaseRepository;
    @Override
    public Timesheet createTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }
    @Autowired
    private TimeEntryRepository timeEntryRepository;
    @Override
    public List<Timesheet> getAllTimesheetsByUser(OurUsers user) {
        return timesheetRepository.findAllByUtilisateur(user);
    }
    @Transactional
    public Timesheet saveTimesheet(Timesheet timesheet) {
        timesheet.updateTotalHours();
        return timesheetRepository.save(timesheet);
    }
    @JsonCreator

    public TimeEntry addTimeEntry(Integer timesheetId, TimeEntry timeEntry) {
        Timesheet timesheet = timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new RuntimeException("Timesheet not found for ID: " + timesheetId));

        // Ensure related entities are saved
        Projet projet = projetRepository.findById(timeEntry.getProjet().getId())
                .orElseThrow(() -> new RuntimeException("Project not found for ID: " + timeEntry.getProjet().getId()));
        Release release = releaseRepository.findById(timeEntry.getRelease().getIdRelease())
                .orElseThrow(() -> new RuntimeException("Release not found for ID: " + timeEntry.getRelease().getIdRelease()));
        Activité activite = activiteRepository.findById(timeEntry.getActivite().getIdActivité())
                .orElseThrow(() -> new RuntimeException("Activity not found for ID: " + timeEntry.getActivite().getIdActivité()));

        LocalDate date = LocalDate.of(2024, 8, 15);
        timeEntry.setDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        timeEntry.setHeuresTravaillees(8);
        timeEntry.setProjet(projet);
        timeEntry.setRelease(release);
        timeEntry.setActivite(activite);
        timesheet.updateTotalHours();

        timeEntry.setTimesheet(timesheet);

        return timeEntryRepository.save(timeEntry);
    }
    @Transactional
    public Timesheet updateTimeEntry(Integer timesheetId, TimeEntry updatedEntry) {
        Timesheet timesheet = timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid timesheet ID"));
        List<TimeEntry> entries = timesheet.getTimeEntries();
        for (TimeEntry entry : entries) {
            if (entry.getId().equals(updatedEntry.getId())) {
                entry.setHeuresLundi(updatedEntry.getHeuresLundi());
                entry.setHeuresMardi(updatedEntry.getHeuresMardi());
                entry.setHeuresMercredi(updatedEntry.getHeuresMercredi());
                entry.setHeuresJeudi(updatedEntry.getHeuresJeudi());
                entry.setHeuresVendredi(updatedEntry.getHeuresVendredi());
                break;
            }
        }
        timesheet.updateTotalHours();
        return timesheetRepository.save(timesheet);

    }

    @Transactional
    public void deleteTimeEntry(Integer timesheetId, Integer timeEntryId) {
        Timesheet timesheet = timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid timesheet ID"));
        List<TimeEntry> entries = timesheet.getTimeEntries();
        entries.removeIf(entry -> entry.getId().equals(timeEntryId));
        timesheet.updateTotalHours();
        timesheetRepository.save(timesheet);
    }
    @Override
    public List<OurUsers> getAllCollaborators() {
        return ourUsersRepository.findAllByRole(ERole.COLLABORATOR);
    }
    @Override

    public List<Timesheet> getTimesheetsByCollaboratorId(Integer userId) {
        return timesheetRepository.findByUtilisateurId(userId);
    }


}
