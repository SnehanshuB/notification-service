package org.snehanshu.notification_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationModel {
    private int id;
    private String message;
    private String recipient;
    private String status;
    private String createdAt;
}
