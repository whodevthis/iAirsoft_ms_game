package application.services.cammo;

import application.dtos.cammo.CammoDetailsDto;
import application.exceptions.EntityNotFoundException;
import application.mappers.CammoMapper;
import application.ports.in.cammo.query.GetAllCammoUseCase;
import application.ports.in.cammo.query.GetCammoByIdUseCase;
import application.ports.in.cammo.query.SearchCammoUseCase;
import application.ports.out.CammoRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Cammo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CammoQueryService implements GetAllCammoUseCase, GetCammoByIdUseCase, SearchCammoUseCase {

    private final CammoMapper cammoMapper;
    private final CammoRepositoryPort cammoRepositoryPort;

    @Override
    public List<CammoDetailsDto> getAll() {
        return cammoRepositoryPort.findAll().stream().map(cammoMapper::toDetailsDTO).toList();
    }

    @Override
    public CammoDetailsDto getById(UUID id) {
        return cammoMapper.toDetailsDTO(cammoRepositoryPort.findById(id).orElseThrow(()-> new EntityNotFoundException("Cammo not found")));
    }

    @Override
    public List<CammoDetailsDto> search(String data) {
        List<Cammo> cammos = cammoRepositoryPort.search(GenericUtils.search(data, Cammo.class));

        if (cammos.isEmpty()) throw new EntityNotFoundException("No cammos found for: " + data);

        return cammos.stream().map(cammoMapper::toDetailsDTO).toList();
    }
}
