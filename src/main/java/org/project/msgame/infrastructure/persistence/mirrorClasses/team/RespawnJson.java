package org.project.msgame.infrastructure.persistence.mirrorClasses.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespawnJson {
    private Double lat;
    private Double lon;
    private String iconUrl;
}