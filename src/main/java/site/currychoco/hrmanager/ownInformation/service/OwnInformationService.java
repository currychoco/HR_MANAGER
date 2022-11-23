package site.currychoco.hrmanager.ownInformation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.ownInformation.domain.OwnInformation;
import site.currychoco.hrmanager.ownInformation.domain.OwnInformationDto;
import site.currychoco.hrmanager.ownInformation.repository.OwnInformationRepository;

@RequiredArgsConstructor
@Service
public class OwnInformationService {

    private final OwnInformationRepository ownInformationRepository;

    /**
     * 정보 수정 신청 저장
     */
    public void saveOwnInfo(OwnInformationDto dto){
        OwnInformation ownInfo = new OwnInformation(dto);
        ownInformationRepository.save(ownInfo);
    }
}
