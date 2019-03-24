package com.kq.guava.io;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;

/**
 * Created by qikong on 2019/3/24.
 */
public class IOConfig {

    public static final String getOSPath()  {
        if(SystemUtils.IS_OS_MAC) {
            return "/Users/qikong/temp";
        } else if(SystemUtils.IS_OS_WINDOWS) {
            return "c:\\temp\\myfile";
        } else {
            return "/temp/myfile";
        }
    }

    public static final String SRC_FILE = getOSPath()+ File.separator+"srcfile";
    public static final String MOVE_FILE = getOSPath()+ File.separator+"movefile";
    public static final String MOVE_TO_FILE = getOSPath()+ File.separator+"move_to_file";
    public static final String TARGET_FILE = getOSPath()+ File.separator+"targetfile";

}
