package com.mbtiapp.mbtiservice.client;

import com.mbtiapp.mbticommon.Res.TestEmpModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmpClient {

    @GetExchange("/employee/list")
    List<TestEmpModel> getEmpList();
}
