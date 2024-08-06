package org.snehanshu.notification_service.dao;

import org.snehanshu.notification_service.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationServiceDAO extends JpaRepository<NotificationEntity, Integer> {
}
