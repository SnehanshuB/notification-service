package org.snehanshu.notification_service.service;

import jakarta.transaction.Transactional;
import org.snehanshu.notification_service.dao.NotificationServiceDAO;
import org.snehanshu.notification_service.entity.NotificationEntity;
import org.snehanshu.notification_service.model.CreateNotification;
import org.snehanshu.notification_service.model.NotificationModel;
import org.snehanshu.notification_service.util.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationServiceDAO notificationServiceDAO;

    @Override
    public List<NotificationModel> getAll() {
        List<NotificationEntity> entities = notificationServiceDAO.findAll();
        return entities.stream()
                .map(NotificationMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationModel getById(int id) {
        Optional<NotificationEntity> entity = notificationServiceDAO.findById(id);
        return entity.map(NotificationMapper::toModel).orElse(null);
    }

    @Override
    public NotificationModel create(CreateNotification createNotification) {
        NotificationEntity entity = NotificationMapper.toEntity(createNotification);
        NotificationEntity savedEntity = notificationServiceDAO.save(entity);
        return NotificationMapper.toModel(savedEntity);
    }

    @Override
    public NotificationModel update(int id, NotificationModel notificationModel) {
        Optional<NotificationEntity> entityOptional = notificationServiceDAO.findById(id);
        if (entityOptional.isPresent()) {
            NotificationEntity entity = entityOptional.get();
            entity.setMessage(notificationModel.getMessage());
            entity.setRecipient(notificationModel.getRecipient());
            entity.setStatus(notificationModel.getStatus());
            entity.setCreatedAt(LocalDateTime.parse(notificationModel.getCreatedAt()));
            NotificationEntity updatedEntity = notificationServiceDAO.save(entity);
            return NotificationMapper.toModel(updatedEntity);
        } else {
            // Handle entity not found scenario
            return null;
        }
    }

    @Override
    public void delete(int id) {
        notificationServiceDAO.deleteById(id);
    }
}