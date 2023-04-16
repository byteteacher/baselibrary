package com.byteteacher.library.util;

import java.io.File;

public class FileUtil {


    public static boolean isFile(File file) {
        if (file == null) {
            return false;
        }
        return file.isFile();
    }
}
