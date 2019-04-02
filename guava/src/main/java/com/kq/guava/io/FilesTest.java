package com.kq.guava.io;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.assertTrue;
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
        File copyToFile = new File(IOConfig.COPY_FILE);
        File touchToFile = new File(IOConfig.TOUCH_FILE);

        if(!srcFile.exists()) {
            if(!srcFile.getParentFile().exists()) {
                Files.createParentDirs(srcFile);
            }

            Files.write("welcome to you!".getBytes(),srcFile);
        }

        if(targetFile.exists()) {
            targetFile.delete();
        }

        if(moveToFile.exists()) {
            moveToFile.delete();
        }


        if(!moveFile.exists()) {
            Files.copy(srcFile,moveFile);
        }

        Files.copy(srcFile,copyToFile);

        if(touchToFile.exists()) {
            touchToFile.deleteOnExit();
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

        List<String> list = Files.readLines(srcFile, Charsets.UTF_8);

        list.stream().forEach(System.out::println);

        List<String> list1 =Files.asCharSource(srcFile, Charsets.UTF_8).readLines();

        list1.stream().forEach(System.out::println);


        List<Integer> resultList = Files.asCharSource(srcFile, Charsets.UTF_8).readLines(new LineProcessor<List<Integer>>() {

            private List<Integer> list = new ArrayList<>();

            @Override
            public boolean processLine(String line) throws IOException {
                list.add(line.length());
                return true;
            }

            @Override
            public List<Integer> getResult() {
                return list;
            }
        });

        resultList.forEach(System.out::println);

    }

    @Test
    public void testFileMd5() throws Exception{
        File srcFile = new File(IOConfig.SRC_FILE);
        HashCode hashCode = Files.asByteSource(srcFile).hash(Hashing.sha256());

        File copyToFile = new File(IOConfig.COPY_FILE);
        HashCode moveFileHashCode = Files.asByteSource(copyToFile).hash(Hashing.sha256());

        System.out.println("hashCode="+hashCode);


        assertThat(moveFileHashCode.toString(),equalTo(hashCode.toString()));

    }

    @Test
    public void testWrite() throws Exception{
        File srcFile = new File(IOConfig.SRC_FILE);

        Files.asCharSink(srcFile,Charsets.UTF_8,FileWriteMode.APPEND)
                .write("-----------the end ----------");

    }


    @Test
    public void testTouch() throws Exception {
        File touchToFile = new File(IOConfig.TOUCH_FILE);

        Files.touch(touchToFile);

    }

    /**
     *目录遍历
     */
    @Test
    public void testIterAll(){
        File iterPath = new File(IOConfig.ITER_PATH);

        Iterable<File> iterable = Files.fileTraverser().depthFirstPreOrder(iterPath);

        for(File f : iterable) {
            System.out.println(f);
        }

    }


    /**
     * 深度优先（depth-first traversal）
     * 广度优先（breadth-first traversal）
     *目录遍历
     * PreOrder traversal：以“父节点-左子节点-右子节点”为顺序
     */
    @Test
    public void depthFirstPreOrder(){
        File iterPath = new File(IOConfig.ITER_PATH);

        Iterable<File> iterable = Files.fileTraverser().depthFirstPreOrder(iterPath);

        for(File f : iterable) {
            System.out.println(f);
        }

    }

    /**
     *目录遍历
     * PostOrder traversal 以“左子节点-右子节点-父节点”为顺序
     */
    @Test
    public void testdepthFirstPostOrder(){
        File iterPath = new File(IOConfig.ITER_PATH);

        Iterable<File> iterable = Files.fileTraverser().depthFirstPostOrder(iterPath);

        for(File f : iterable) {
            System.out.println(f);
        }

    }

    /**
     * 广度优先
     * 广度优先算法可以总结为，按层次来遍历的，先是根节点，然后是第二层子节点，依次是第三层子节点
     */
    @Test
    public void testBreadthFirst(){

        File iterPath = new File(IOConfig.ITER_PATH);

        Iterable<File> iterable = Files.fileTraverser().breadthFirst(iterPath);

        for(File f : iterable) {
            System.out.println(f);
        }

    }

}
