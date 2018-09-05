package com.xiguo.www.group.utils;

import java.util.UUID;

/**
 * @author: ZGC
 * @date Created in 2018/8/30 下午 3:56
 */
public class FileNameKit {
    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return 保留文件后缀的uuid文件名
     */
    public static String getUUIDFileName(String fileOriginName){
        return _getUUID() + _getSuffix(fileOriginName);
    }

    /**
     * 获取文件后缀
     * @param fileName 带有文件格式后缀的文件名
     * @return 文件格式后缀(带.) .jpg .png .elx .sql
     */
    private static String _getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }


    /**
     * 获取uuid
     * @return uuid
     */
    private static String _getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
