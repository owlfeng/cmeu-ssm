package com.cmeu.service.impl;

import com.cmeu.mapper.OrderTestMapper;
import com.cmeu.pojo.vo.ktestvo;
import com.cmeu.service.OrderTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTestServieImpl implements OrderTestService {
   @Autowired
    OrderTestMapper orderTestMapper;

    public List<ktestvo> testlist() {

        return orderTestMapper.testlist();
    }

}
