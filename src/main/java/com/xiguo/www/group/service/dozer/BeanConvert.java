package com.xiguo.www.group.service.dozer;

import java.util.List;
import java.util.Set;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 9:47
 */
public interface BeanConvert {
    /**
     * 单个对象的深度复制及类型转换，vo/domain , po
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return 数据对象
     */
    <T, S> T convert(S s, Class<T> clz);

//    /**
//     * 深度复制结果集(ResultSet为自定义的分页结果集)
//     * @param s 数据对象
//     * @param clz 复制目标类型
//     * @return 数据对象
//     */
//    <T, S> ResultSet<T> convert(ResultSet<S> s, Class<T> clz);

    /**
     * list的深度复制
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return 数据对象
     */
    <T, S> List<T> convert(List<S> s, Class<T> clz);

    /**
     * set深度复制
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return 数据对象
     */
    <T, S> Set<T> convert(Set<S> s, Class<T> clz);

    /**
     * 数组深度复制
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return 数据对象
     */
    <T, S> T[] convert(S[] s, Class<T> clz);
}
