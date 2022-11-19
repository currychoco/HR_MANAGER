package site.currychoco.hrmanager.position.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.position.domain.Position;
import site.currychoco.hrmanager.position.domain.PositionDto;
import site.currychoco.hrmanager.position.repository.PositionRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PositionService {

    // 모든 직위 정보 불러오기
    @Autowired
    private PositionRepository positionRepository;

    public List<PositionDto> getAllPosition(){
        List<PositionDto> positionDtoList = new ArrayList<>();

        List<Position> list = positionRepository.findAll();

        for(Position pos : list) {
            positionDtoList.add(PositionDto.fromEntity(pos));
        }

        return positionDtoList;
    }
}
