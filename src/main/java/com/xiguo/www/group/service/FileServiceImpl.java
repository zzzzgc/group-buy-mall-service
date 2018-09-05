package com.xiguo.www.group.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/9/3 下午 3:54
 */
@Service("fileService")
public class FileServiceImpl implements  FileService{
    @Override
    public boolean deleteFileByUrl(List<String> fileHttpUrls) {
        
        return false;
    }
}
