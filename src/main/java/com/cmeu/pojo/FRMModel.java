package com.cmeu.pojo;

import java.io.Serializable;

/**
 * @author fudada
 * @date 2019/4/26 - 15:46
 */
public class FRMModel implements Serializable, Comparable<FRMModel> {
    private  int id; //用户id
    private  Float initiation; //入户时常
    private  Float frequency;//频率
    private  Float monetary;//总金额
    private  Float recency;//最后一次购买时间
    private  Float weight ;//

    public FRMModel() {
    }

    public FRMModel(int id, Float initiation, Float frequency, Float monetary, Float recency) {
        this.id = id;
        this.initiation = initiation;
        this.frequency = frequency;
        this.monetary = monetary;
        this.recency = recency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getInitiation() {
        return initiation;
    }

    public void setInitiation(Float initiation) {
        this.initiation = initiation;
    }

    public Float getFrequency() {
        return frequency;
    }

    public void setFrequency(Float frequency) {
        this.frequency = frequency;
    }

    public Float getMonetary() {
        return monetary;
    }

    public void setMonetary(Float monetary) {
        this.monetary = monetary;
    }

    public Float getRecency() {
        return recency;
    }

    public void setRecency(Float recency) {
        this.recency = recency;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(FRMModel o) {
        //先按照年龄排序
        if((this.initiation >= o.getInitiation())){
            return this.getInitiation().compareTo(o.getInitiation());
        }
        if((this.frequency >= o.getFrequency())){
            return this.getFrequency().compareTo(o.getFrequency());
        }

        if((this.monetary >= o.getMonetary())){
            return this.getMonetary().compareTo(o.getMonetary());
        }

        if((this.recency >= o.getRecency())){
            return this.getRecency().compareTo(o.getRecency());
        }
        return 0;
    }

    @Override
    public String toString() {
        return "FRMModel{" +
                "id=" + id +
                ", initiation=" + initiation +
                ", frequency=" + frequency +
                ", monetary=" + monetary +
                ", recency=" + recency +
                ", weight=" + weight +
                '}';
    }
}


