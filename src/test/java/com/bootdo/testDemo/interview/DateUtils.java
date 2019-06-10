package com.bootdo.testDemo.interview;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: bootdo
 * @description:
 * @author: yufeiafn@wondersgroup.com
 * @create: 2019-06-10 14:51
 **/
public class DateUtils {
    public static void main(String[] args) throws ParseException {
        List<String> daySjc = getDaySjc("20170901", "20180905");
        for (String s : daySjc) {
            System.out.println(s);
        }
    }
    /**
     * @Author yufeifan@wondersgroup.com
     * @Description 写出一个函数,求2个日期间的所有日期集合(面试题)
     * @Date 16:28 2019/6/10
     * @Param 
     * @return 
     **/
    public static List<String> getDaySjc(String date1 , String date2 ) throws ParseException {
        ArrayList<String> results = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parse1 = format.parse(date1);
        Date parse2 = format.parse(date2);
        Calendar instance1 = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance1.setTime(parse1);
        instance2.setTime(parse2);
        while (instance1.before(instance2)){
            results.add(format.format(instance1.getTime()));
            instance1.add(Calendar.DAY_OF_YEAR,1);
        }
        return results;
    }

}
