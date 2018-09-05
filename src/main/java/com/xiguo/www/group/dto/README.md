# DTO 规范

前言:该DTO是作为和entity的映射而存在的


1. 名称一致
1. 属性一致
    * 但是属性为其他entity时,则选择它对应DTO
1. 可以保存id
    * 因为id是否显示的控制权,是由entity决定的
1. 需要完整的javaBean架构
    * 包含getting setting方法和构造
1. beanConvert只能转换2层实体.
    * 超出部分需要手动再次使用beanConvert转换
