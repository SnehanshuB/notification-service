package org.snehanshu.notification_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.snehanshu.notification_service.model.CreateNotification;
import org.snehanshu.notification_service.model.NotificationModel;
import org.snehanshu.notification_service.service.NotificationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/getAllNotifications")
    public ResponseEntity<List<NotificationModel>> getNotifications() {
        try {
            List<NotificationModel> notifications = notificationService.getAll();
            log.info("Fetched all notifications successfully. Total notifications: {}", notifications.size());
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            log.error("Error fetching notifications: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } finally {
            getInfo();
        }
    }

    private static void getInfo() {
        log.info("Validation of API complete.");
    }

    @GetMapping("/getNotification/{id}")
    public ResponseEntity<NotificationModel> getNotificationById(@PathVariable int id) {
        try {
            NotificationModel notification = notificationService.getById(id);
            if (notification != null) {
                return ResponseEntity.ok(notification);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<NotificationModel> createNotification(@RequestBody CreateNotification createNotification) {
        try {
            NotificationModel createdNotification = notificationService.create(createNotification);
            log.info("Notification created successfully: {}", createdNotification);
            return ResponseEntity.ok(createdNotification);
        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity violation: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } finally {
            getInfo();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<NotificationModel> updateNotification(@PathVariable int id, @RequestBody NotificationModel notificationModel) {
        try {
            NotificationModel updatedNotification = notificationService.update(id, notificationModel);
            if (updatedNotification != null) {
                return ResponseEntity.ok(updatedNotification);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable int id) {
        try {
            notificationService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}