package site.currychoco.hrmanager.myInformation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.currychoco.hrmanager.myInformation.domain.MyInformation;
import site.currychoco.hrmanager.myInformation.domain.MyInformationDto;
import site.currychoco.hrmanager.myInformation.repository.MyInformationRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyInformationService {

    private final MyInformationRepository myInformationRepository;

    /**
     * 정보 수정 신청 저장
     */
    public void saveOwnInfo(MyInformationDto dto){
        MyInformation ownInfo = new MyInformation(dto);
        myInformationRepository.save(ownInfo);
    }

    /**
     * 수정 신청 리스트 출력
     */
    public List<MyInformationDto> getAllowList(){
        List<MyInformationDto> dtoList = new ArrayList<>();
        List<MyInformation> list = myInformationRepository.findAll();
        for(MyInformation info : list){
            dtoList.add(MyInformationDto.fromEntity(info));
        }

        return dtoList;
    }

    /**
     * 고유번호를 통한 정보 출력
     */
    public MyInformationDto getMyInformation(Long no){
        MyInformation myInfo = myInformationRepository.findById(no).orElseThrow();
        MyInformationDto myInfoDto = MyInformationDto.fromEntity(myInfo);
        return myInfoDto;
    }

    /**
     * 정보 수정 승인
     */
    @Transactional
    public void allowModify(MyInformationDto dto){
        MyInformation myInformation = myInformationRepository.findById(dto.getNo()).orElseThrow();
        myInformation.modify(dto);
    }
}
