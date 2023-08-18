package com.byteteacher.library.util;

import java.io.File;

public class FileUtil {


    /**
     * 判断File是否是文件，已增加判空处理
     * @param file
     * @return 是否是文件
     */
    public static boolean isFile(File file) {
        if (file == null) {
            return false;
        }
        return file.isFile();
    }
}
