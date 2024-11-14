package com.stage.management.RestControllers;

import com.stage.management.DAO.Entities.*;
import com.stage.management.DAO.Repositories.ActivitéRepository;
import com.stage.management.DAO.Repositories.ProjetRepository;
import com.stage.management.DAO.Repositories.ReleaseRepository;
import com.stage.management.DAO.Repositories.UsersRepo;
import com.stage.management.Services.ITimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TimesheetController {
    @Autowired
    private ActivitéRepository activitéRepository;
    @Autowired
    private ReleaseRepository releaseRepository;


    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ITimesheetService timesheetService;
    @Autowired
    private UsersRepo ourUsersRepository;
    @GetMapping("/collaborator/{userId}")
    public List<Timesheet> getTimesheetsByUser(@PathVariable("userId") Integer userId) {
        OurUsers user = ourUsersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return timesheetService.getAllTimesheetsByUser(user);   }
    @GetMapping("/projects")
    public List<Projet> getProjects() {
        return projetRepository.findAll();
    }

    @GetMapping("/releases")
    public List<Release> getReleases() {
        return releaseRepository.findAll();
    }

    @GetMapping("/activities")
    public List<Activité> getActivities() {
        return activitéRepository.findAll();
    }
    @PostMapping("/{timesheetId}/timeEntries")
    public ResponseEntity<TimeEntry> addTimeEntry(@PathVariable Integer timesheetId, @RequestBody TimeEntry timeEntry) {
        TimeEntry savedEntry = timesheetService.addTimeEntry(timesheetId, timeEntry);
        return ResponseEntity.ok(savedEntry);
    }
    @GetMapping("/collaborators/eh")
    public List<OurUsers> getAllCollaborators() {
        return timesheetService.getAllCollaborators();
    }

    @GetMapping("/timesheet/{collaboratorId}")
    public List<Timesheet> getTimesheetsByCollaboratorId(@PathVariable Integer collaboratorId) {
        return timesheetService.getTimesheetsByCollaboratorId(collaboratorId);
    }

}
