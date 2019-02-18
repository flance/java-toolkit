package com.github.flance.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 注解工具类
 *
 * @author zhangying
 * @date 2019/2/18 23:22
 */
public class AnnotationUtils {

    /**
     * 找出所有标注了该annotation的公共属性，循环遍历父类
     * <p>暂未支持Spring风格Annotation继承Annotation</p>
     *
     * @param clazz      指定类
     * @param annotation 注解
     * @param <T>        注解类型
     * @return 所有field set
     */
    public static <T extends Annotation> Set<Field> getAnnotatedPublicFields(Class<? extends Object> clazz,
                                                                             Class<T> annotation) {
        if (Object.class.equals(clazz)) {
            return Collections.emptySet();
        }

        Set<Field> annotatedFields = new HashSet<Field>();
        Field[] fields = clazz.getFields();

        for (Field field : fields) {
            if (field.getAnnotation(annotation) != null) {
                annotatedFields.add(field);
            }
        }
        return annotatedFields;
    }

    /**
     * 找出所有标注了该annotation的属性，循环遍历父类，包含private属性
     * <p>暂未支持Spring风格Annotation继承Annotation</p>
     *
     * @param clazz      指定类
     * @param annotation 注解
     * @param <T>        注解类型
     * @return 所有field set
     */
    public static <T extends Annotation> Set<Field> getAnnotatedFields(Class<? extends Object> clazz,
                                                                       Class<T> annotation) {
        if (Object.class.equals(clazz)) {
            return Collections.emptySet();
        }
        Set<Field> annotatedFields = new HashSet<Field>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(annotation) != null) {
                annotatedFields.add(field);
            }
        }
        annotatedFields.addAll(getAnnotatedFields(clazz.getSuperclass(), annotation));
        return annotatedFields;
    }
}
