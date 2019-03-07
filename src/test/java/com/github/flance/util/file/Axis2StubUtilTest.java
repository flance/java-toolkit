package com.github.flance.util.file;

import java.io.File;

/**
 * @author zhangying
 * @date 2019/3/7 22:31
 */
public class Axis2StubUtilTest {
    public static void main(String[] args) {
        Axis2StubUtil.removeInvalidCodeLine(new File("D:/temp.java"),new File("D:/temp_clean.java"));
    }

    /**
     * 这是标准的多行注释
     *
     * @param args
     */
    public static void main1(String[] args) {
        System.out.println("理论上应该打印这一行内容的");
        java.lang.String s = new java.lang.String("hahahah");


        /***你们看吧这是的***/
        /*******************************************/
        /********************************************\
         *
         */
        java.lang.reflect.Method method = null;
        System.out.println("单行注释放在后边，这行不应该被删除");//这是一种单行注释
        /*
        这是标准的另一种多行注释a
        asdfasdfasdf
        asdf
        asdf
        as
         */
    }
}