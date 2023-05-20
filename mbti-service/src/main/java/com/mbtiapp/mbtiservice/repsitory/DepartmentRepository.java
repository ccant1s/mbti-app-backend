package com.mbtiapp.mbtiservice.repsitory;

import com.mbtiapp.mbticommon.Res.TestDeptModel;
import com.mbtiapp.mbtiservice.client.EmpClient;
import com.mbtiapp.mbtiservice.service.DepartmentService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Repository
public class DepartmentRepository implements DepartmentService {

    @Autowired
    private EmpClient empClient;

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

    @Override
    public List<TestDeptModel> findWithEmp(long Id) {
        List<TestDeptModel> list = departments;
        list.stream().map(depart -> {
            depart.setEmp(empClient.getEmpList().stream().filter(o->o.getDepartmentId()==Id).toList());
            return depart;
        }).collect(toList());

        return list;
    }
}
