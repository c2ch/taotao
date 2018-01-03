package com.c2c.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by CC on 2017/9/6.
 */
public class ReadTxt {
    public static void main(String[] args) throws IOException {
        File file = new File(ReadTxt.class.getResource("/cc.txt").getFile());
        FileReader fileReader = new FileReader(file);
        //bufferreader知识进行了一下封装，是读取的效率更高
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //定义一个空字符串
        String str;
        //此处判断切记不能用if
        //bufferedReader.readLine()是读取文件的一行，如果有多行，会逐行读取。
        //当每一行不为空时则把内容打印到控制台中，也可以存到写入的流中，把内容写到文本里
        while((str = bufferedReader.readLine())!= null) {
            System.out.println(str);
        }
        //最后不要忘记关流
        fileReader.close();

    }
}
