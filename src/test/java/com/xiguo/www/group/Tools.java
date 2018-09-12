package com.xiguo.www.group;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.User;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author: ZGC
 * @date Created in 2018/8/27 上午 11:45
 */
public class Tools {


    /**
     * 根据对象的 ->属性<- 生成get,set,new代码
     *
     * @param zlass
     */
    public static void createFunctionCode(Class<?> zlass, String[] ignoreFields, String[] ignoreTypes, Boolean returnSettingFunctionName, Boolean returnGettingFunctionName) {
        String simpleName = zlass.getSimpleName();
        System.out.println(simpleName + " " + toLowerCaseFirstOne(simpleName) + " = new " + simpleName + "();");
        Map<String, String> fieldsNameAndType = getFields(zlass, ignoreFields, ignoreTypes);
        if (returnSettingFunctionName) {
            for (String fieldName : fieldsNameAndType.keySet()) {
                System.out.println(fieldsNameAndType.get(fieldName) + " " + fieldName + " " + toLowerCaseFirstOne(fieldName) + ";");
            }
            for (String fieldName : fieldsNameAndType.keySet()) {
                System.out.println(toLowerCaseFirstOne(simpleName) + ".set" + toUpperCaseFirstOne(fieldName) + "(" + toLowerCaseFirstOne(fieldName) + ");");
            }
            System.out.println("以上是您需要的Setting方法");
        }

        if (returnGettingFunctionName) {
            for (String fieldName : fieldsNameAndType.keySet()) {
                System.out.println(fieldsNameAndType.get(fieldName) + " " + fieldName + " = " +toLowerCaseFirstOne(simpleName) + ".get" + toUpperCaseFirstOne(fieldName) + "();");
            }
            System.out.println("以上是您需要的Getting方法");
        }
        System.out.println();
    }


    public static Map<String, String> getFields(Class<?> zlass, String[] ignoreFields, String[] ignoreTypes) {
        Map<String, String> FieldsNameAndType = new HashMap<>();
        Field[] declaredFields = zlass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.isAccessible();
            String fieldName = declaredField.getName();
            String fieldType = declaredField.getType().getSimpleName();
            if (!useArraysBinarySearch(ignoreFields, fieldName)) if (!useArraysBinarySearch(ignoreTypes, fieldType)) FieldsNameAndType.put(fieldName, fieldType);
        }
        return FieldsNameAndType;
    }

    public static boolean useArraysBinarySearch(String[] arr, String targetValue) {
        int a = Arrays.binarySearch(arr, targetValue);
        if (a >= 0)
            return true;
        else
            return false;
    }

    //首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    //首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }


//    public static void main(String[] args) {
//        createFunctionCode(GroupBuy.class, new String[]{"serialVersionUID","createAt","updatedAt"},new String[]{"Set"}, true, false);
//    }
}
