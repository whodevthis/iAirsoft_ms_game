package infrastructure.persistence.entities;


import infrastructure.persistence.converters.team.PlayerObjectiveListConverter;
import infrastructure.persistence.converters.team.PlayerRoleListConverter;
import infrastructure.persistence.converters.team.RespawnConverter;
import infrastructure.persistence.converters.team.TeamRoleListConverter;
import infrastructure.persistence.mirrorClass.team.PlayerObjectiveJson;
import infrastructure.persistence.mirrorClass.team.PlayerRoleJson;
import infrastructure.persistence.mirrorClass.team.RespawnJson;
import infrastructure.persistence.mirrorClass.team.TeamRoleJson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "teams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String imagePath;

    @Convert(converter = PlayerRoleListConverter.class)
    @Column(columnDefinition = "json")
    private List<PlayerRoleJson> players = new ArrayList<>();

    @Convert(converter = TeamRoleListConverter.class)
    @Column(columnDefinition = "json")
    private List<TeamRoleJson> roles = new ArrayList<>();

    @Convert(converter = PlayerObjectiveListConverter.class)
    @Column(columnDefinition = "json")
    private List<PlayerObjectiveJson> playerObjectives = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "team_objectives",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "objective_id")
    )
    private List<ObjectiveEntity> objectives = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cammo_id")
    private CammoEntity cammo;

    @Convert(converter = RespawnConverter.class)
    @Column(columnDefinition = "json")
    private RespawnJson respawn;
}