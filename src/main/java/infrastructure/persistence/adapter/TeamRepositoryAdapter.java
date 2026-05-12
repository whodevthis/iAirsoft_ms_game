package infrastructure.persistence.adapter;

import application.ports.out.TeamRepositoryPort;
import domain.aggregates.Team;
import infrastructure.persistence.mapper.TeamEntityMapper;
import infrastructure.persistence.repository.TeamJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TeamRepositoryAdapter implements TeamRepositoryPort {

    private final TeamJpaRepository teamJpaRepository;
    private final TeamEntityMapper teamEntityMapper;

    @Override
    public Team save(Team team) {
        return teamEntityMapper.toDomain(teamJpaRepository.save(teamEntityMapper.toEntity(team)));
    }

    @Override
    public Optional<Team> findById(UUID id) {
        return teamJpaRepository.findById(id).map(teamEntityMapper::toDomain);
    }

    @Override
    public List<Team> findAll() {
        return teamJpaRepository.findAll().stream().map(teamEntityMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        teamJpaRepository.deleteById(id);
    }

    @Override
    public List<Team> search(Specification<Team> spec) {
        return teamJpaRepository.findAll((Sort) spec).stream().map(teamEntityMapper::toDomain).toList();
    }
}