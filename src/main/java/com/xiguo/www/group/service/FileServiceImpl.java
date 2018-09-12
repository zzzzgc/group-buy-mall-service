package com.xiguo.www.group.service;

import com.xiguo.www.group.utils.FileKit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: ZGC
 * @date Created in 2018/9/3 下午 3:54
 */
@Service("fileService")
public class FileServiceImpl implements  FileService{

    /**
     * 文件保存路径
     */
    @Value("${system.web.file.upload.path}")
    private String saveRootPath;

    /**
     * 文件下载路径
     */
    @Value("${system.web.file.download.path}")
    private String readRootPath;

    @Override
    public boolean deleteFileByUrl(String fileHttpUrl) {
        String clippingPathPattern = "/[\\w]+/[\\w\\d]+.[\\w]+$";
            Matcher matcher = Pattern.compile(clippingPathPattern).matcher(fileHttpUrl);
            while (matcher.find()) {
                String fileRelativePath = matcher.group();
                String fileSavePath = saveRootPath + fileRelativePath;
                File file = new File(fileSavePath);
                if (!file.exists()) {
                    throw  new RuntimeException("文件不存在,文件:" + fileHttpUrl);
                }
                if (!file.delete()) {
                    throw  new RuntimeException("文件删除失败,文件:" + fileHttpUrl);
                }
        }
        return true;
    }

    /**
     *
     * @param multipartFile 文件
     * @param fileRelativePath
     * @return
     */
    @Override
    public String saveFile(MultipartFile multipartFile, String fileRelativePath) {
        String fileSavePath = saveRootPath + fileRelativePath;
        String fileHttpUrl = null;
        try {
            fileHttpUrl = readRootPath + fileRelativePath + "/" + FileKit.saveToUuidFile(multipartFile, fileSavePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileHttpUrl;
    }

    @Override
    public String saveFile(byte[] data, String fileNameSuffix, String fileRelativePath) {
        String fileSavePath = saveRootPath + fileRelativePath;
        String fileHttpUrl = null;
        try {
            String fileName = FileKit.saveToUuidFile(data, fileSavePath, fileNameSuffix);
            fileHttpUrl = readRootPath + fileRelativePath + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileHttpUrl;
    }
}
