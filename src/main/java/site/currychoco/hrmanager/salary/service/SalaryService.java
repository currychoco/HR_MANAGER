package site.currychoco.hrmanager.salary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.currychoco.hrmanager.salary.domain.Salary;
import site.currychoco.hrmanager.salary.domain.SalaryDto;
import site.currychoco.hrmanager.salary.repository.SalaryRepository;

@RequiredArgsConstructor
@Service
public class SalaryService {

    private final SalaryRepository salaryRepository;

    /**
     * 사번을 통한 SalaryDto 출력
     */
    public SalaryDto getSalaryByEmpNo(Long empNo){
        Salary salary = salaryRepository.findById(empNo).orElseThrow();
        SalaryDto dto = SalaryDto.fromEntity(salary);
        return dto;
    }

    /**
     * 연봉 정보 수정
     */
    @Transactional
    public void modifySalary(SalaryDto salaryDto){
        Salary salary = salaryRepository.findById(salaryDto.getEmpNo()).orElseThrow();
        salary.modify(salaryDto);
    }
}
