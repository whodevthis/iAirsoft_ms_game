package org.project.msgame.infrastructure.messagin.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.msgame.application.dtos.player.InputPlayerDto;
import org.project.msgame.application.services.player.PlayerCommandService;
import org.project.msgame.infrastructure.messagin.dto.UserEventDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerCreatedConsumer {

    private final PlayerCommandService playerCommandService;

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void handleUserCreated(UserEventDTO event) {
        if (event.getId() == null) {
            log.warn("Evento recibido sin UUID, ignorando.");
            return;
        }

        log.info("Evento recibido de msAuth - UUID: {}, userName: {}",
                event.getId(), event.getUserName());

        InputPlayerDto inputPlayerDto = new InputPlayerDto(
                event.getId(),
                event.getUserName(),
                null
        );

        playerCommandService.create(inputPlayerDto);
    }
}