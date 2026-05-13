package org.project.msgame.infrastructure.persistence.mapper;

import org.project.msgame.domain.aggregates.Team;
import org.project.msgame.infrastructure.persistence.entities.ObjectiveEntity;
import org.project.msgame.infrastructure.persistence.entities.TeamEntity;
import org.project.msgame.infrastructure.persistence.mirrorClasses.team.PlayerObjectiveJson;
import org.project.msgame.infrastructure.persistence.mirrorClasses.team.PlayerRoleJson;
import org.project.msgame.infrastructure.persistence.mirrorClasses.team.RespawnJson;
import org.project.msgame.infrastructure.persistence.mirrorClasses.team.TeamRoleJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.msgame.domain.valueObjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Mapper(componentModel = "spring")
public interface TeamEntityMapper {

    @Mapping(target = "cammo", ignore = true)
    @Mapping(target = "respawn", expression = "java(toRespawnJson(team.getRespawn()))")
    @Mapping(target = "players", expression = "java(toPlayerRoleJsons(team.getPlayers()))")
    @Mapping(target = "roles", expression = "java(toTeamRoleJsons(team.getRoles()))")
    @Mapping(target = "playerObjectives", expression = "java(toPlayerObjectiveJsons(team.getPlayerObjectives()))")
    @Mapping(target = "objectives", ignore = true)
    TeamEntity toEntity(Team team);

    default Team toDomain (TeamEntity entity){

        if(entity==null){ return null;}

        Team team = new Team(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImagePath(),
                toPlayerRoles(entity.getPlayers()),
                toTeamRoles(entity.getRoles()),
                toPlayerObjectives(entity.getPlayerObjectives()),
                toObjectiveIds(entity.getObjectives()),
                entity.getCammo().getId(),
                toRespawn(entity.getRespawn())
        );
        return team;
    }

    default Respawn toRespawn(RespawnJson json) {
        if (json == null) return null;
        return new Respawn(new Marker(json.getLat(), json.getLon(), json.getIconUrl()));
    }

    default List<PlayerRole> toPlayerRoles(List<PlayerRoleJson> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(p -> new PlayerRole(p.getPlayerId(), p.getRoleId())).toList();
    }

    default List<TeamRole> toTeamRoles(List<TeamRoleJson> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(r -> new TeamRole(r.getRole(), r.getQuantity())).toList();
    }

    default List<PlayerObjective> toPlayerObjectives(List<PlayerObjectiveJson> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(po -> new PlayerObjective(new PlayerRole(po.getPlayerId(), po.getRoleId()), po.getObjectiveId())).toList();
    }

    default List<UUID> toObjectiveIds(List<ObjectiveEntity> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(ObjectiveEntity::getId).toList();
    }

    default RespawnJson toRespawnJson(Respawn respawn) {
        if (respawn == null) return null;
        return new RespawnJson(respawn.marker().lat(), respawn.marker().lon(), respawn.marker().iconUrl());
    }

    default List<PlayerRoleJson> toPlayerRoleJsons(List<PlayerRole> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(p -> new PlayerRoleJson(p.playerId(), p.roleId())).toList();
    }

    default List<TeamRoleJson> toTeamRoleJsons(List<TeamRole> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(r -> new TeamRoleJson(r.role(), r.quantity())).toList();
    }

    default List<PlayerObjectiveJson> toPlayerObjectiveJsons(List<PlayerObjective> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(po -> new PlayerObjectiveJson(po.playerRole().playerId(), po.playerRole().roleId(), po.objectiveId())).toList();
    }
}