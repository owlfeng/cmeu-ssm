package com.cmeu.pojo;

import java.io.Serializable;

/**
 * @author fudada
 * @date 2019/4/26 - 15:46
 */
public class FRMModel implements Serializable,Comparable<FRMModel> {
    private  int id; //用户id 用户组id
    private  Float initiation; //入户时常
    private  Float frequency;//频率
    private  Float monetary;//总金额
    private  Float recency;//最后一次购买时间
    private  int weight = 0 ;//
    private  float[] floats;
    private  int cid;




    public FRMModel() {
    }

    public FRMModel(Float initiation, Float frequency, Float monetary, Float recency,int cid) {
        this.initiation = initiation;
        this.frequency = frequency;
        this.monetary = monetary;
        this.recency = recency;
        this.cid = cid;
    }

    public FRMModel(int id, Float initiation, Float frequency, Float monetary, Float recency) {
        this.id = id;
        this.initiation = initiation;
        this.frequency = frequency;
        this.monetary = monetary;
        this.recency = recency;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setFloats(float[] floats) {
        this.floats = floats;
    }
    public float[] getFloats() {
        return floats;
    }

    public void setFloats(Float initiation,Float frequency,Float monetary, Float recency) {
        this.floats=new float[4];
        this.floats[0]=initiation;
        this.floats[1]=frequency;
        this.floats[2]=monetary;
        this.floats[3]=recency;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public int compare(FRMModel o) {
        System.out.println(o.getId());
        //
        if((this.initiation >= o.getInitiation())){
            this.weight=getWeight()+1;
        }
        if((this.frequency >= o.getFrequency())){
            this.weight=getWeight()+7;
        }

        if((this.monetary >= o.getMonetary())){
            this.weight=getWeight()+10;
        }

        if((this.recency >= o.getRecency())){
            this.weight=getWeight()+2;
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

    @Override
    public int compareTo(FRMModel o) {
        // compareTo 0 -1 1
        return  this.weight-o.getWeight();
    }
}


