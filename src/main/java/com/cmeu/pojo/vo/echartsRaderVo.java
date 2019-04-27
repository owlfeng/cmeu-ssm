package com.cmeu.pojo.vo;

import java.util.List;

public class echartsRaderVo {
    private String  name;
    private float[] value;
    private String text;
    private int max;
    private int min;

    public echartsRaderVo() {
    }

    public echartsRaderVo(String name, float[] value, String text, int max, int min) {
        this.name = name;
        this.value = value;
        this.text = text;
        this.max = max;
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float[] getValue() {
        return value;
    }

    public void setValue(float[] value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
