package com.cmeu.mapper;

import com.cmeu.pojo.FRMModel;

import java.util.List;

/**
 * @author fudada
 * @date 2019/4/30 - 17:16
 */
public interface FRMMapper {
    List<FRMModel> list();
    void save(FRMModel frmModel);

}
