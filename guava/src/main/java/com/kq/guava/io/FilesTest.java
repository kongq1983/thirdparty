package com.kq.guava.io;

import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * Created by qikong on 2019/3/23.
 */
public class FilesTest {

    @Before
    public void befofe() throws Exception{
        File targetFile = new File(IOConfig.TARGET_FILE);
        File srcFile = new File(IOConfig.SRC_FILE);
        File moveFile = new File(IOConfig.MOVE_FILE);
        File moveToFile = new File(IOConfig.MOVE_TO_FILE);

        if(targetFile.exists()) {
            targetFile.delete();
        }

        if(moveToFile.exists()) {
            moveToFile.delete();
        }


        if(!moveFile.exists()) {
            Files.copy(srcFile,moveFile);
        }



    }


    /**
     * 拷贝文件
     * @throws Exception
     */
    @Test
    public void testCopyFileWithGuava() throws Exception{

        File srcFile = new File(IOConfig.SRC_FILE);
        File targetFile = new File(IOConfig.TARGET_FILE);

        assert srcFile.exists() == true;
        assert targetFile.exists()==false;

        Files.copy(srcFile,targetFile);

        assertTrue(targetFile.exists());

        //Files.move
        //Files.readLines
    }

    /**
     * 移动文件 或 重命名
     * @throws Exception
     */
    @Test
    public void testMoveFile() throws Exception {
        File moveFile = new File(IOConfig.MOVE_FILE);
        File moveToFile = new File(IOConfig.MOVE_TO_FILE);

        Files.move(moveFile,moveToFile);


    }


    /**
     * 读文件内容
     * @throws Exception
     */
    @Test
    public void readline() throws Exception {

        File srcFile = new File(IOConfig.SRC_FILE);

        List<String> list = Files.readLines(srcFile, Charset.defaultCharset());

        list.stream().forEach(System.out::println);



    }

}
