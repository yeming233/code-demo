package org.itourshare.test;

import java.io.File;

/**
 * @ClassName : TestRecursion
 * @Description : 递归
 * @Author : its
 * @Date: 2020-08-24 16:16
 */
public class TestRecursion {

    public static void main(String[] args) {
        File file = new File("D:\\home");
        listFile(file);
    }

    private static void listFile(File file) {
        File[] files = file.listFiles();
        for(File f: files){
            if(f.isDirectory()){
                System.out.println("direction=="+ f.getName());
                listFile(f);
            }else{
                System.out.println("file=="+ f.getName());
            }
        }
    }


}
