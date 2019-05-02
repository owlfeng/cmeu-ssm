package com.cmeu.service;

import com.cmeu.mapper.FRMMapper;
import com.cmeu.pojo.FRMModel;
import org.springframework.stereotype.Service;
import util.Cluster;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author fudada
 * @date 2019/4/30 - 17:16
 */
@Service
public interface FRMService {

    public List<FRMModel> list();
    public void save(FRMModel frmModel);
    public void save(List<FRMModel> frmModel);
    public void save( Set<Cluster> clusterSet);

}
