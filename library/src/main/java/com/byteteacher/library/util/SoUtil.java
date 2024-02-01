package com.byteteacher.library.util;

import java.io.File;
import java.io.RandomAccessFile;

public class SoUtil {

    /**
     * 判断so文件是否为64位
     *
     * @param soFile so文件
     * @return so文件为64位返回true，反之返回false
     */
    public static boolean isSo64BitAbi(File soFile) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(soFile, "r");
            randomAccessFile.seek(4);
            int label = randomAccessFile.read();
            // so文件的第5个字节用于标识Abi位数，为1代表32位，为2代表64位
            return label == 2;
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                randomAccessFile.close();
            } catch (Exception x) {
            }
        }
        return false;
    }


}
