package org.snehanshu.notification_service.service;

import org.snehanshu.notification_service.model.CreateNotification;
import org.snehanshu.notification_service.model.NotificationModel;

import java.util.List;

public interface NotificationService {
    List<NotificationModel> getAll();
    NotificationModel getById(int id);
    NotificationModel create(CreateNotification createNotification);
    NotificationModel update(int id, NotificationModel notificationModel);
    void delete(int id);
}