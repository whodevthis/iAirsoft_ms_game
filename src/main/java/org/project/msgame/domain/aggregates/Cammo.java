package org.project.msgame.domain.aggregates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cammo {

    private  UUID id;
    private String name;
    private String  imagePath;

}