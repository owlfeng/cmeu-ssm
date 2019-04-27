package com.cmeu.service;

import com.cmeu.pojo.vo.ktestvo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderTestService {
    List<ktestvo> testlist();

}
