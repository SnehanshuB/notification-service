package org.snehanshu.notification_service.util;

import org.snehanshu.notification_service.entity.NotificationEntity;
import org.snehanshu.notification_service.model.CreateNotification;
import org.snehanshu.notification_service.model.NotificationModel;

public class NotificationMapper {

    public static NotificationEntity toEntity(CreateNotification createNotification) {
        return new NotificationEntity(
                0, // default id (0 or any default value as it's auto-generated)
                createNotification.getMessage(),
                createNotification.getRecipient(),
                createNotification.getStatus(),
                null // createdAt will be set automatically by @PrePersist
        );
    }

    public static NotificationModel toModel(NotificationEntity entity) {
        NotificationModel model = new NotificationModel();
        model.setId(entity.getId());
        model.setMessage(entity.getMessage());
        model.setRecipient(entity.getRecipient());
        model.setStatus(entity.getStatus());
        model.setCreatedAt(entity.getCreatedAt() != null ? entity.getCreatedAt().toString() : null);
        return model;
    }
}
