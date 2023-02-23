package gov.edu.anm.presenter.api.avaliation;

import gov.edu.anm.presenter.api.avaliation.dtos.AddAvaliationRequestDto;
import gov.edu.anm.presenter.api.avaliation.dtos.AvaliationOutputDto;

import java.util.List;

public interface AvaliationService {
    AvaliationOutputDto findById(Long userId, Long teamId);
    List<AvaliationOutputDto> findAll();
    AvaliationOutputDto addAvaliationToTeam(AddAvaliationRequestDto addAvaliationRequestDto);
    void deleteAvaliation(Long userId, Long teamId);

}
