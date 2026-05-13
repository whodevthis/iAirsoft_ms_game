
package org.project.msgame.infrastructure.messagin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventDTO {
    private UUID id;
    private String userName;
    private String userStatus;
    private String action;
}