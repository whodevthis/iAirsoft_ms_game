package org.project.msgame.application.ports.in.role.query;

import org.project.msgame.application.dtos.role.RoleDetailsDto;

import java.util.List;

public interface SearchRoleUseCase {

     List<RoleDetailsDto> search(String data);

}
