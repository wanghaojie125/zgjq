package com.zhangongjx.infrastructure.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomHelper {
    public  static  String RandomNumber()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }
}
