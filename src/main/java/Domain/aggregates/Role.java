package Domain.aggregates;

import Domain.states.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private  UUID id;
    private RoleType roleType;
    private String description;


}