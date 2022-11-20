package site.currychoco.hrmanager.position.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.currychoco.hrmanager.position.domain.Position;
import site.currychoco.hrmanager.position.domain.PositionDto;
import site.currychoco.hrmanager.position.repository.PositionRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PositionService {

    private final PositionRepository positionRepository;

    /**
     * 직위 생성
     */
    public void addPosition(PositionDto dto){
        Position position = new Position(dto);
        positionRepository.save(position);
    }

    /**
     * 모든 직위 출력
     */

    public List<PositionDto> getAllPosition(){
        List<PositionDto> positionDtoList = new ArrayList<>();

        List<Position> list = positionRepository.findAll();

        for(Position pos : list) {
            positionDtoList.add(PositionDto.fromEntity(pos));
        }

        return positionDtoList;
    }

    /**
     * 직책 정보 수정
     */
    @Transactional
    public void modifyPosition(PositionDto dto){
        Position position = positionRepository.findById(dto.getPositionCode()).orElseThrow();
        position.modify(dto);
    }

    /**
     * 직책 정보
     */
    public PositionDto getPositionByPositionCode(String positionCode){
        Position position = positionRepository.findById(positionCode).orElseThrow();
        PositionDto dto = PositionDto.fromEntity(position);
        return dto;
    }

    /**
     * 직책 검색
     */
    public List<PositionDto> getSearchedPosition(String data){
        List<PositionDto> dtoList = new ArrayList<>();

        List<Position> list = positionRepository.findPositionByPositionCodeOrPositionName(data);

        for(Position position : list){
            dtoList.add(PositionDto.fromEntity(position));
        }

        return dtoList;
    }
}
