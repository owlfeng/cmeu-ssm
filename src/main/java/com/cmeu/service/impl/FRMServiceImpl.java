package com.cmeu.service.impl;

import com.cmeu.mapper.FRMMapper;
import com.cmeu.pojo.FRMModel;
import com.cmeu.service.FRMService;
import org.springframework.stereotype.Service;
import util.Cluster;
import util.Point;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author fudada
 * @date 2019/4/30 - 17:16
 */
@Service
public class FRMServiceImpl implements FRMService {
    @Resource
    FRMMapper frmMapper;

    public List<FRMModel> list(){
        return frmMapper.list();

    }
    public void save(FRMModel frmModel){
        frmMapper.save(frmModel);
    }

    public void save(List<FRMModel> frmModel){
        for (FRMModel temp:frmModel)
        frmMapper.save(temp);
    }
    public void save(Set<Cluster> clusterSet){
        List<FRMModel> list = frmMapper.list();
        if (list.size()==0){
        for (Cluster cluster:clusterSet) {
            List<Point> members = cluster.getMembers();
            for (Point point:members) {
                float[] floats = point.getlocalArray();
                FRMModel frmModel = new FRMModel(floats[0], floats[1], floats[2], floats[3],point.getTitle());
                save(frmModel);
            }

        }

        }
    }
}
