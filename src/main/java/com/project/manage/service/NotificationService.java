package com.project.manage.service;

import com.project.manage.Dto.NotificationMessageDTO;
import com.project.manage.config.RabbitMQConfig;
import com.project.manage.model.Notification;
import com.project.manage.model.User;
import com.project.manage.model.WorkoutPlan;
import com.project.manage.repository.NotificationRepo;
import com.project.manage.repository.UserRepository;
import com.project.manage.repository.WorkoutPlanRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private EmailSenderService emailService;

    @Autowired
    private WorkoutPlanRepo workoutPlanRepo;

    @Autowired
    private UserRepository userRepository;

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void consume(NotificationMessageDTO dto) {
        // Convert DTO → Entity before saving
        Notification notification = new Notification();
        notification.setMessage(dto.getMessage());

        // if you want to attach relations:
        if (dto.getWorkoutId() != null) {
            WorkoutPlan plan = workoutPlanRepo.findById(dto.getWorkoutId()).orElse(null);
            notification.setWorkoutPlan(plan);
        }
        if (dto.getTrinerId() != null) {
            User trainer = userRepository.findById(dto.getTrinerId()).orElse(null);
            notification.setTrainer(trainer);
        }

        notificationRepo.save(notification);

        // Send email
        if (dto.getTrainerEmail() != null) {
            emailService.sendRegistrationEmail(
                    dto.getTrainerEmail(),
                    "New Enrollment Notification",
                    dto.getMessage()
            );
        }
    }

}
