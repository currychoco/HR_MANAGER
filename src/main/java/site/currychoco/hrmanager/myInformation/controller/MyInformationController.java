package site.currychoco.hrmanager.myInformation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.core.annotation.CheckAuthority;
import site.currychoco.hrmanager.emp.domain.EmployeeDto;
import site.currychoco.hrmanager.emp.service.EmployeeService;
import site.currychoco.hrmanager.myInformation.domain.MyInformationDto;
import site.currychoco.hrmanager.myInformation.service.MyInformationService;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MyInformationController {

    private final MyInformationService myInformationService;

    private final EmployeeService employeeService;

    @PostMapping("/my-info/create")
    public ResponseEntity<Void> createOwnInfo(@RequestBody MyInformationDto dto){
        dto.setRequestDate(new Timestamp(System.currentTimeMillis()));
        dto.setAllow("R");
        myInformationService.saveOwnInfo(dto);
        return ResponseEntity.ok(null);
    }

    @CheckAuthority(authCode = "g000015")
    @GetMapping("/manager/employee/{empNo}")
    public ResponseEntity<EmployeeDto> getEmployeeByNo(@PathVariable Long empNo) {
        return ResponseEntity.ok(employeeService.getEmployeeByEmpNo(empNo));
    }

    @CheckAuthority(authCode = "g000015")
    @GetMapping("/manager/my-information/{no}")
    public ResponseEntity<MyInformationDto> getMyInformationByNo(@PathVariable Long no) {
        return ResponseEntity.ok(myInformationService.getMyInformation(no));
    }

    @CheckAuthority(authCode = "g000015")
    @GetMapping("/allow/list")
    public ResponseEntity<List<MyInformationDto>> getAllowList(){
        return ResponseEntity.ok(myInformationService.getAllowList());
    }

    @CheckAuthority(authCode = "g000015")
    @PostMapping("/allow/update")
    public ResponseEntity<Void> updateAllow(@RequestBody MyInformationDto dto){
        MyInformationDto myInfoDto = myInformationService.getMyInformation(dto.getNo());
        dto.setAllow("Y");
        dto.setRequestDate(myInfoDto.getRequestDate());
        dto.setAllowDate(new Timestamp(System.currentTimeMillis()));
        myInformationService.allowModify(dto);

        EmployeeDto empDto = employeeService.getEmployeeByEmpNo(dto.getEmpNo());
        empDto.setEmpName(dto.getEmpName());
        empDto.setEmpNameEn(dto.getEmpNameEn());
        empDto.setGender(dto.getGender());
        empDto.setEmail(dto.getEmail());
        empDto.setPhone(dto.getPhone());
        empDto.setAddress1(dto.getAddress1());
        empDto.setAddress2(dto.getAddress2());
        employeeService.updateEmployee(empDto);

        return ResponseEntity.ok(null);
    }
}
