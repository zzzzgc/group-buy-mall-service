package com.xiguo.www.group.controller;

import com.xiguo.www.group.enums.FileUploadUrl;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.service.FileService;
import com.xiguo.www.group.utils.FileKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: ZGC
 * @date Created in 2018/8/30 下午 2:27
 */
@Api(value = "/file", tags = "文件服务模块")
@RestController
@RequestMapping("/file")
public class FileController {
    /**
     * 系统域名 或 系统域名加端口
     */
    @Value("${system.web.domain.url}")
    private String domainUrl;

    @Autowired
    FileService fileService;

    /**
     * 文件保存路径
     */
    @Value("${system.web.file.upload.path}")
    private String saveRootPath;


    /**
     * 保存文件
     * <p>
     * 用 form-data(表单数据)的方式传递过来
     * 文件件统一用file作为key,value为文件
     * <p>
     * 附带 fileType来区分存放路径 默认规则  0图片 1媒体 2数据 3文件 优先级从左到右
     *
     * @param multipartFiles 文件 集合
     * @param fileType       文件类型(多文件需要统一一个类型)
     * @return 文件转url的集合
     */
    @PostMapping
    @ApiOperation("文件上传接口")
    public ResponseEntity uploadFiles(@RequestParam("file") List<MultipartFile> multipartFiles, @RequestParam(value = "fileType", defaultValue = "3") int fileType) {
        List<String> fileHttpUrls = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.isEmpty()) {
                return new ResponseEntity<>("文件为空,请正确上传文件", HttpStatus.FORBIDDEN);
            }
            String fileHttpUrl = _fileClassify(fileType, multipartFile);
            if (fileHttpUrl == null) {
                return RETemplate.failure("文件上传失败,请稍后重试");
            }
            fileHttpUrls.add(fileHttpUrl);
        }
        return RETemplate.ok(fileHttpUrls);
    }

    /**
     * 删除文件
     * <p>
     * 地址: http://groupBuy.52xiguo.com/f/file/asdfadsflajsdflj.jpg
     * 删除路径: file/asdfadsflajsdflj.jpg
     */
    @PutMapping
    @ApiOperation("文件删除接口")
    public ResponseEntity deleteFile(@RequestBody List<String> fileHttpUrls) {
        for (String fileHttpUrl : fileHttpUrls) {
            boolean b = fileService.deleteFileByUrl(fileHttpUrl);
        }
        return RETemplate.ok();
    }


    /**
     * 文件分类放置在不同文件夹
     *
     * @param fileType      文件类型
     * @param multipartFile 文件
     * @return 文件的url
     */
    private String _fileClassify(int fileType, MultipartFile multipartFile) {
        String fileRelativePath = null;
        switch (fileType) {
            case 0:
                fileRelativePath = FileUploadUrl.IMAGE_FILE_PATH.getValue();
                break;
            case 1:
                fileRelativePath = FileUploadUrl.VIDEO_FILE_PATH.getValue();
                break;
            case 2:
                fileRelativePath = FileUploadUrl.DATA_FILE_PATH.getValue();
                break;
            default:
                fileRelativePath = FileUploadUrl.COMMON_FILE_PATH.getValue();
        }
        return fileService.saveFile(multipartFile, fileRelativePath);
    }
}
