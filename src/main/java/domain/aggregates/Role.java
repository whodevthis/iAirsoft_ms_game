package domain.aggregates;

import domain.states.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private  UUID id;
    private RoleType roleType;
    private String description;


}