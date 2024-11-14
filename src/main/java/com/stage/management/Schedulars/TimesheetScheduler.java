package com.stage.management.Schedulars;

import com.stage.management.DAO.Entities.OurUsers;
import com.stage.management.DAO.Entities.Timesheet;
import com.stage.management.Services.ITimesheetService;
import com.stage.management.Services.security.OurUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class TimesheetScheduler {
    @Autowired
    private ITimesheetService timesheetService;

    @Autowired
    private OurUserDetailsService userDetailsService; // Service to get all users

    // Schedule to run at the start of every week (Sunday at 00:00)
  //  @Scheduled(cron = "0 * * * * *") // Every minute
    @Scheduled(cron = "0 0 0 * * SUN")

    public void createWeeklyTimesheets() {
        List<OurUsers> users = userDetailsService.getAllCollaborators();
        for (OurUsers user : users) {
            createTimesheetForUser(user);
        }
    }

    private void createTimesheetForUser(OurUsers user) {
        Timesheet timesheet = new Timesheet();
        timesheet.setUtilisateur(user);

        // Set the start and end date to the current week
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        timesheet.setDateDebut(calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, 6);
        timesheet.setDateFin(calendar.getTime());

        timesheetService.createTimesheet(timesheet);
    }

}
