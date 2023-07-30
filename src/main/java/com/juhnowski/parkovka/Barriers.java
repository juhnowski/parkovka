package com.juhnowski.parkovka;

import java.util.HashMap;

public class Barriers {
    public static HashMap<Integer, String> map = new  HashMap<Integer, String>();
    static{
        map.put(1, "Въезд 1");
        map.put(2, "Выезд 1");
        map.put(3, "Въезд 2");
        map.put(4, "Выезд 2");        
    };
}
