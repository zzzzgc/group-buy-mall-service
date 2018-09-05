package com.xiguo.www.group.service;

import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/9/3 下午 3:54
 */
public interface FileService {

    boolean deleteFileByUrl(List<String> fileHttpUrls);
}
