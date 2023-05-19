package com.mbtiapp.mbtiservice.controller;

import com.mbtiapp.mbticommon.Res.TestDeptModel;
import com.mbtiapp.mbticommon.Res.TestEmpModel;
import com.mbtiapp.mbtiservice.repsitory.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;



    @PostMapping("/insert")
    public TestDeptModel add(@RequestBody TestDeptModel param) {
        return departmentRepository.addDepartment(param);
    }

    @PostMapping("/find")
    public TestDeptModel findById (@RequestBody TestDeptModel param){
        return departmentRepository.findById(param);
    }

    @GetMapping("/getAll")
    public List<TestDeptModel> getDepartments() {
        return departmentRepository.findAll();
    }




}
