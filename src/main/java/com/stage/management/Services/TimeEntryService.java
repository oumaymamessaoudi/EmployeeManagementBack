package com.stage.management.Services;

import com.stage.management.DAO.Entities.TimeEntry;
import com.stage.management.DAO.Entities.Timesheet;
import com.stage.management.DAO.Repositories.TimeEntryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class TimeEntryService implements  ITimeEntryService{
    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntry createTimeEntry(TimeEntry timeEntry) {
        return timeEntryRepository.save(timeEntry);
    }

    public List<TimeEntry> getTimeEntriesByTimesheet(Timesheet timesheet) {
    return timeEntryRepository.findAllByTimesheet(timesheet);
     }
}
