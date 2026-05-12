package application.services.role;

import application.exceptions.EntityNotFoundException;
import application.ports.out.RoleRepositoryPort;
import domain.aggregates.Role;
import domain.aggregates.Team;
import domain.valueObjects.TeamRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleCommandService {

    private final RoleRepositoryPort roleRepositoryPort;


    @Transactional
    public addRoleToTeam()
    public void addTeamRole (UUID teamId, UUID role, int quantity){

        Team team= teamRepositoryPort.findById(teamId).orElseThrow(()-> new EntityNotFoundException("Team not found"));

        Role role= roleRepositoryPort.findById(role).orElseThrow(()-> new EntityNotFoundException("Role not found"));

        TeamRole teamRole=

    }

}
