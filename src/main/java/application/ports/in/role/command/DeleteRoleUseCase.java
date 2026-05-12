package application.ports.in.role.command;

import java.util.UUID;

public interface DeleteRoleUseCase {
    void delete (UUID id);
}
