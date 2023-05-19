package com.mbtiapp.mbticommon.Res;


import lombok.Data;

import java.util.*;
import java.util.ArrayList;

@Data
public class TestDeptModel {

private String id;

private String name;

private String position;

private List<TestDeptModel> emp  = new ArrayList<>();




}
