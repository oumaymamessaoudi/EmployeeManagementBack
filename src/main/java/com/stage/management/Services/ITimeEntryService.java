package com.stage.management.Services;

import com.stage.management.DAO.Entities.TimeEntry;
import com.stage.management.DAO.Entities.Timesheet;

import java.util.List;

public interface ITimeEntryService {
    public TimeEntry createTimeEntry(TimeEntry timeEntry) ;
    public List<TimeEntry> getTimeEntriesByTimesheet(Timesheet timesheet) ;
}
