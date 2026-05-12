package application.ports.in.role.query;

import application.dtos.role.RoleDetailsDto;

import java.util.List;

public interface SearchRoleUseCase {

     List<RoleDetailsDto> search(String data);

}
