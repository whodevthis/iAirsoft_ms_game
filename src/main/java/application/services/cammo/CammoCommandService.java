package application.services.cammo;

import application.dtos.cammo.InputCammoDto;
import application.dtos.cammo.CammoDetailsDto;
import application.exceptions.EntityNotFoundException;
import application.ports.in.cammo.command.CreateCammoUseCase;
import application.ports.in.cammo.command.DeleteCammoUseCase;
import application.ports.in.cammo.command.UpdateCammoUseCase;
import application.ports.out.CammoRepositoryPort;
import application.utils.GenericUtils;
import domain.aggregates.Cammo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CammoCommandService implements CreateCammoUseCase, DeleteCammoUseCase, UpdateCammoUseCase {

    private final GenericUtils genericUtils;
    private final CammoRepositoryPort cammoRepositoryPort;

    @Transactional
    @Override
    public UUID create(InputCammoDto inputCammoDto) {
        Cammo cammo = new Cammo(null, inputCammoDto.name(), inputCammoDto.imagePath());
        return cammoRepositoryPort.save(cammo).getId();
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        cammoRepositoryPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Cammo not found: " + id));
        cammoRepositoryPort.deleteById(id);
    }

    @Transactional
    @Override
    public UUID update(CammoDetailsDto cammoDetailsDto) {
        Cammo oldCammo = cammoRepositoryPort.findById(cammoDetailsDto.id()).orElseThrow(() -> new EntityNotFoundException("Cammo not found"));

        Cammo updatedCammo = new Cammo(oldCammo.getId(),
                genericUtils.applyIfChanged(oldCammo.getName(),cammoDetailsDto.name()),
                genericUtils.applyIfChanged(oldCammo.getImagePath(),cammoDetailsDto.imagePath())
        );

        return cammoRepositoryPort.save(updatedCammo).getId();
    }


}
