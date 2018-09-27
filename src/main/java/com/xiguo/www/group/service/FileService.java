package com.xiguo.www.group.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: ZGC
 * @date Created in 2018/9/3 下午 3:54
 */
public interface FileService {

    boolean deleteFileByUrl(String fileHttpUrl);

    /**
     * 保存文件获取访问文件的url
     *
     * @param multipartFile 文件
     * @param fileSavePath  保存的相对路径
     * @return 文件 URL
     */
    String saveFile(MultipartFile multipartFile, String fileSavePath);

    /**
     * 保存文件获取访问文件的url
     *
     * @param data             文件
     * @param fileNameSuffix   文件后缀
     * @param fileRelativePath 保存的相对路径
     * @return 文件 URL
     */
    String saveFile(byte[] data, String fileNameSuffix, String fileRelativePath);
}
