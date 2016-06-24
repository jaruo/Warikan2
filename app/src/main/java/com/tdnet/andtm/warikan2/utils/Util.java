package com.tdnet.andtm.warikan2.utils;

/**
 * Created by takahashi on 2016/06/22.
 */
public class Util {

//    数値チェック
    public static boolean isNum(String num){

        try {
            Integer.parseInt(num);
            return true;
        }catch(NumberFormatException e){
            return false;
        }

    }

}
