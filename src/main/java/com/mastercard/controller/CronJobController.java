package com.mastercard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ams/")
public class CronJobController {

    private final CronJobs cronJobs;

    public CronJobController(CronJobs cronJobs) {
        this.cronJobs = cronJobs;
    }

    // Trigger Cron job manually for testing or monitoring
    @GetMapping("/trigger-job1")
    public String triggerJob1() {
        new Thread(() -> cronJobs.runJobEveryMinute()).start();
        return "Job 1 triggered manually.";
    }

    @GetMapping("/trigger-job2")
    public String triggerJob2() {
        new Thread(() -> cronJobs.runJobAt2AM()).start();
        return "Job 2 triggered manually.";
    }

    @GetMapping("/trigger-job3")
    public String triggerJob3() {
        new Thread(() -> cronJobs.runJobEveryHour()).start();
        return "Job 3 triggered manually.";
    }
}

