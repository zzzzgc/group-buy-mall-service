package com.xiguo.www.group.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: ZGC
 * @date Created in 2018/8/30 下午 6:39
 */
public class FileKit {
    /**
     * 保存文件
     *
     * @param file     文件
     * @param filePath 文件保存路径
     * @param fileName 文件名(带文件格式后缀的 .jpg .text)
     * @throws IOException
     */
    public static void saveFile(byte[] file, String filePath, String fileName) throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            boolean mkdirs = targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * 保存文件
     *
     * @param file           文件
     * @param filePath       文件保存路径
     * @param fileNameSuffix 文件名(带文件格式后缀的 .jpg .text)
     * @throws IOException
     */
    public static String saveToUuidFile(byte[] file, String filePath, String fileNameSuffix) throws IOException {
        String fileName = uuidKit.getUuid() + "." + fileNameSuffix;
        saveFile(file, filePath, fileName);
        return fileName;
    }


    /**
     * 用uuid作为文件名的并保存文件,然后返回uuid文件名
     *
     * @param multipartFiles 文件
     * @param fileSavePath   保存路径
     * @return 文件名称
     * @throws IOException 文件io异常
     */
    public static String saveToUuidFile(MultipartFile multipartFiles, String fileSavePath) throws IOException {
        String fileName = multipartFiles.getOriginalFilename();
        fileName = FileNameKit.getUUIDFileName(fileName);
        FileKit.saveFile(multipartFiles.getBytes(), fileSavePath, fileName);
        return fileName;
    }


}
