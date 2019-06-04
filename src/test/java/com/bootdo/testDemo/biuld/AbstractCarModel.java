package com.bootdo.testDemo.biuld;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-04 10:00
 **/
public abstract class AbstractCarModel {
    protected List<String> sequences  = new ArrayList<>();
    abstract  void process1 ();
    abstract  void process2 ();
    abstract  void process3 ();
    abstract  void process4 ();
    public void run() {
        for (String seq : sequences) {
           if("process1".equalsIgnoreCase(seq)){
               this.process1();
               continue;
           }
           if("process2".equalsIgnoreCase(seq)){
               this.process2();
               continue;
           }
           if("process3".equalsIgnoreCase(seq)){
               this.process3();
               continue;
           }
           if("process4".equalsIgnoreCase(seq)){
               this.process4();
               continue;
           }
        }
    }
}
