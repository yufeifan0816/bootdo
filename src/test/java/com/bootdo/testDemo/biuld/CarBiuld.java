package com.bootdo.testDemo.biuld;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-04 10:28
 **/
public class CarBiuld {
    private List<String> sequences = new ArrayList<>();
    private CarModle carModle =  new CarModle();
    public void setSequence(String sequence){
        this.sequences.add(sequence);
    }
    public AbstractCarModel getModel(){
        carModle.sequences = this.sequences;
        return carModle;
    }
}
