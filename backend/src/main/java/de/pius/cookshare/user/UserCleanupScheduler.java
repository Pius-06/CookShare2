package de.pius.cookshare.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserCleanupScheduler {

    private static final Logger log = LoggerFactory.getLogger(UserCleanupScheduler.class);
    private UserCleanupService userCleanupService;

    @Autowired
    public UserCleanupScheduler(UserCleanupService userCleanupService) {
        this.userCleanupService = userCleanupService;
    }

    @Scheduled(cron = "0 0 3 * * ?", // Sekunde | Minute | Stunde | Tag | Monat | Wochentag
            zone = "Europe/Berlin")
    public void deleteExpiredUnverifiedUsers() {
        int deletedUsers = userCleanupService.deleteExpiredUsers();
        log.info("Deleted {} users with expired verification token", deletedUsers);
    }
}
