package com.mastercard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Component
public class CronJobs {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    // Cron job that runs every minute
    @Scheduled(cron = "0 * * * * *")
    public void runJobEveryMinute() {
        taskExecutor.execute(() -> {
            System.out.println("Job running every minute on thread: " + Thread.currentThread().getName());
            performTask1();  // Task logic
        });
    }

    // Cron job that runs at 2:00 AM every day
    @Scheduled(cron = "0 0 2 * * *")
    public void runJobAt2AM() {
        taskExecutor.execute(() -> {
            System.out.println("Job running at 2:00 AM on thread: " + Thread.currentThread().getName());
            performTask2();  // Task logic
        });
    }

    // Cron job that runs every hour
    @Scheduled(cron = "0 0 * * * *")
    public void runJobEveryHour() {
        taskExecutor.execute(() -> {
            System.out.println("Job running every hour on thread: " + Thread.currentThread().getName());
            performTask3();  // Task logic
        });
    }

    // Task logic for Job 1
    private void performTask1() {
        try {
            System.out.println("Performing Task 1...");
            Thread.sleep(3000);  // Simulate task
            System.out.println("Task 1 completed!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Task logic for Job 2
    private void performTask2() {
        try {
            System.out.println("Performing Task 2...");
            Thread.sleep(5000);  // Simulate task
            System.out.println("Task 2 completed!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Task logic for Job 3
    private void performTask3() {
        try {
            System.out.println("Performing Task 3...");
            Thread.sleep(4000);  // Simulate task
            System.out.println("Task 3 completed!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

