package infrastructure.persistence.mapper;

import domain.aggregates.Team;
import domain.valueObjects.*;
import infrastructure.persistence.entities.ObjectiveEntity;
import infrastructure.persistence.entities.TeamEntity;
import infrastructure.persistence.mirrorClass.team.PlayerObjectiveJson;
import infrastructure.persistence.mirrorClass.team.PlayerRoleJson;
import infrastructure.persistence.mirrorClass.team.RespawnJson;
import infrastructure.persistence.mirrorClass.team.TeamRoleJson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Mapper(componentModel = "spring")
public interface TeamEntityMapper {

    @Mapping(target = "cammoId", source = "cammo.id")
    @Mapping(target = "respawn", expression = "java(toRespawn(entity.getRespawn()))")
    @Mapping(target = "players", expression = "java(toPlayerRoles(entity.getPlayers()))")
    @Mapping(target = "roles", expression = "java(toTeamRoles(entity.getRoles()))")
    @Mapping(target = "playerObjectives", expression = "java(toPlayerObjectives(entity.getPlayerObjectives()))")
    @Mapping(target = "objectives", expression = "java(toObjectiveIds(entity.getObjectives()))")
    Team toDomain(TeamEntity entity);

    @Mapping(target = "cammo", ignore = true)
    @Mapping(target = "respawn", expression = "java(toRespawnJson(team.getRespawn()))")
    @Mapping(target = "players", expression = "java(toPlayerRoleJsons(team.getPlayers()))")
    @Mapping(target = "roles", expression = "java(toTeamRoleJsons(team.getRoles()))")
    @Mapping(target = "playerObjectives", expression = "java(toPlayerObjectiveJsons(team.getPlayerObjectives()))")
    @Mapping(target = "objectives", ignore = true)
    TeamEntity toEntity(Team team);

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