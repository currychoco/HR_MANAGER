package site.currychoco.hrmanager.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.department.domain.Department;
import site.currychoco.hrmanager.department.domain.DepartmentDto;
import site.currychoco.hrmanager.department.repository.DepartmentRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // 모든 부서 리스트 가져오기
    public List<DepartmentDto> getAllDept(){
        List<DepartmentDto> dtoList = new ArrayList<>();

        List<Department> deptList = departmentRepository.findAll();
        for(Department dept : deptList){
            dtoList.add(DepartmentDto.fromEntity(dept));
        }
        return dtoList;
    }


    /**
     * 새로운 부서 생성
     */
    public void addDepartment(DepartmentDto dto){
        Department department = new Department(dto);
        departmentRepository.save(department);
    }

}
