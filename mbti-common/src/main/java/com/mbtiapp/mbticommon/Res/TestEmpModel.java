package com.mbtiapp.mbticommon.Res;

import lombok.Data;

@Data
public class TestEmpModel {

    private long id;

    private long depatmentId;

    private String name;

    private String position;

    public TestEmpModel(long id, long depatmentId, String name, String position) {
        this.id = id;
        this.depatmentId = depatmentId;
        this.name = name;
        this.position = position;
    }
}
