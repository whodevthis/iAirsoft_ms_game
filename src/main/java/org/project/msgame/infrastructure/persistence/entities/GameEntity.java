package org.project.msgame.infrastructure.persistence.entities;

import org.project.msgame.domain.states.GameStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "games")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String imagePath;

    @Column
    private ZonedDateTime initGame;

    @Column
    private ZonedDateTime endGame;

    @Column(nullable = false)
    private int maxPlayers;

    @ManyToMany
    @JoinTable(
            name = "game_teams",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<TeamEntity> teams = new ArrayList<>();

    @Column(nullable = false)
    private int bleedingTimeSeconds;

    @Column(nullable = false)
    private int healingTimeSeconds;

    @Column
    private ZonedDateTime recruitingTimeEnd;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status;
}