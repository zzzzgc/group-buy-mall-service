package com.xiguo.www.group.enums;

/**
 * @author: ZGC
 * @date Created in 2018/9/3 上午 9:38
 */
public enum FileUploadUrl {
    /**
     * 所有的文件路径都要添加到配置文件中的 spring.resources.static-locations 中
     * 图片分类路径
     */
    IMAGE_FILE_PATH("/images"),


    /**
     * 所有的文件路径都要添加到配置文件中的 spring.resources.static-locations 中
     * 视频分类路径
     */
    VIDEO_FILE_PATH("/video"),


    /**
     * 所有的文件路径都要添加到配置文件中的 spring.resources.static-locations 中
     * 数据分类路径
     */
    DATA_FILE_PATH("/data"),


    /**
     * 所有的文件路径都要添加到配置文件中的 spring.resources.static-locations 中
     * 默认文件路径
     */
    COMMON_FILE_PATH("/file");

    String value;


    FileUploadUrl(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
