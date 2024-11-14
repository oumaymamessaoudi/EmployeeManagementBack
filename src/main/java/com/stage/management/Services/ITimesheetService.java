package com.stage.management.Services;

import com.stage.management.DAO.Entities.OurUsers;
import com.stage.management.DAO.Entities.TimeEntry;
import com.stage.management.DAO.Entities.Timesheet;

import java.time.LocalDate;
import java.util.List;

public interface ITimesheetService {
    public Timesheet createTimesheet(Timesheet timesheet);
    public List<Timesheet> getAllTimesheetsByUser(OurUsers user);
    public TimeEntry addTimeEntry(Integer timesheetId, TimeEntry timeEntry) ;
    public List<Timesheet> getTimesheetsByCollaboratorId(Integer userId) ;
    public List<OurUsers> getAllCollaborators();
     }
