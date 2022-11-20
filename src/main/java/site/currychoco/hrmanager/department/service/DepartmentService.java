package site.currychoco.hrmanager.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * 입력된 정보와 유사한 리스트 출력
     */
    public List<DepartmentDto> getSearchedDepartments(String data){
        List<DepartmentDto> dtoList = new ArrayList<>();

        List<Department> list = departmentRepository.findDepartmentByDeptCodeOrDeptName(data);

        for(Department dept : list){
            dtoList.add(DepartmentDto.fromEntity(dept));
        }

        return dtoList;
    }

    /**
     * 부서 정보
     */
    public DepartmentDto getDepartmentByDeptCode(String deptCode){
        Department department = departmentRepository.findById(deptCode).orElseThrow();
        DepartmentDto dto = DepartmentDto.fromEntity(department);
        return dto;
    }

    /**
     *  부서 정보 수정
     */
    @Transactional
    public void modifyDepartment(DepartmentDto dto){
        Department department = departmentRepository.findById(dto.getDeptCode()).orElseThrow();
        department.modify(dto);
    }
}
