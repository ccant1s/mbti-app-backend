package com.mbtiapp.mbtiservice.service;

import com.mbtiapp.mbticommon.Res.TestDeptModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DepartmentService {
     TestDeptModel addDepartment(TestDeptModel dept);

     TestDeptModel findById(TestDeptModel id);

     List<TestDeptModel> findAll();
}
