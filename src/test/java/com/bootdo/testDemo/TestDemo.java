package com.bootdo.testDemo;

import java.util.HashMap;

public class TestDemo {
   private static int num =100;

   public static void main(String[] args) {
       HashMap<Integer,Integer> zdMap = new HashMap<>();
       System.out.println(Integer.valueOf(zdMap.get(1)));;
       System.out.println(zdMap.get(1));
       zdMap.put(1,zdMap.get(1)+1);
   }
}
