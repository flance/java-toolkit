package com.github.flance.util.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Axis2 Webservice stub 处理工具
 *
 * @author zhangying
 * @date 2019/3/5 22:03
 */
public class Axis2StubUtil {
    /**
     * "java.lang.* " 对应的正则表达式
     */
    public static final String[] langPackage = {"java.lang.reflect.", "java.lang.ref.", "java.lang.management.", "java.lang.invoke.", "java.lang.instrument.", "java.lang.annotation.", "java.lang."};

    /**
     * 删除没有实际意义的代码行(所有空行、所有注释行)
     *
     * @param input  源文件
     * @param output 清理之后的目标文件
     */
    public static void removeInvalidCodeLine(File input, File output) {
        try {
            List<String> lines = FileUtils.readLines(input, "utf-8");
            //多行注释标志
            boolean multiLineFlag = false;
            List<String> out = new ArrayList<>();
            for (String line : lines) {
                line = line.trim();
                if (isInlineComment(line)) {
                    continue;
                }
                //多行注释的处理
                if (line.startsWith("/*")) {
                    multiLineFlag = true;
                    continue;
                }
                if (multiLineFlag && line.endsWith("*/")) {
                    multiLineFlag = false;
                    continue;
                }

                out.add(simplifyLangPackage(line));
            }
            FileUtils.writeLines(output, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否为行内注释(单行注释)
     *
     * @param line 一行字符串
     * @return 单行注释为true，否则为false
     */
    public static boolean isInlineComment(String line) {
        if (!StringUtils.isBlank(line)) {
            line = line.trim();
            //标准的单行注释
            if (line.startsWith("//")) {
                return true;
            }
            /** 特殊的单行注释 */
            if (line.startsWith("/*") && line.endsWith("*/")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 简化一行代码中的"java.lang.*"的引用
     *
     * @param line 代码行
     * @return 简化后的代码行
     */
    public static String simplifyLangPackage(String line) {
        for (String reg : langPackage) {
            line = line.replaceAll(reg, "");
        }
        return line;
    }
}
