package com.bjpowernode.crm.mapper;

import java.util.List;

public interface CustomerMapper {
    List<String> getByName(String name);
}
