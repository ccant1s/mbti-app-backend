package com.mbtiapp.mbtiserviceuser.controller;

import com.mbtiapp.mbticommon.Res.TestEmpModel;
import com.mbtiapp.mbtiserviceuser.service.TestEmpService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employee")
public class TestEmpController {

    @Resource
    TestEmpService testEmpService;

    @PostMapping("/add")
    public TestEmpModel addEmp (@RequestBody TestEmpModel param){
        return testEmpService.addEmp(param);
    }

    @GetMapping("/list")
    public List<TestEmpModel> getEmpList(){
        return testEmpService.listEmp();
    }

}
