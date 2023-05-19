package com.mbtiapp.mbtiservice.repsitory;

import com.mbtiapp.mbticommon.Res.TestDeptModel;
import com.mbtiapp.mbtiservice.service.DepartmentService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class DepartmentRepository implements DepartmentService {

    private List<TestDeptModel> departments = new ArrayList<>();

    @Override
    public TestDeptModel addDepartment(TestDeptModel dept){
        departments.add(dept);
        return dept;
    }
    @Override
    public TestDeptModel findById(TestDeptModel param) throws NullPointerException{
        return departments.stream()
                .filter(o->o.getId().equals(param.getId())).findAny().orElseThrow();


    }
    @Override
    public List<TestDeptModel> findAll() {
        return departments;
    }
}
