package com.github.flance.util.file;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * word文档工具类(兼容2003和2007格式)
 *
 * @author zhangying
 * @Date 2018/12/11 13:58
 */
public class DocUtils {
    /**
     * 判断文件是否为docx文件
     *
     * @param filename 文件名
     * @return
     */
    public static boolean isDocx(String filename) {
        return filename.toLowerCase().endsWith(".docx");
    }

    /**
     * 根据文档模版自动生成指定word
     *
     * @param filename  模版文件全路径名
     * @param param     模版中需要替换的参数
     * @param targetDoc 参数替换完成后的最终word
     * @throws IOException
     */
    public static void createWordFile(String filename, Map<String, String> param, String targetDoc) throws IOException {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(filename));
            outputStream = new FileOutputStream(new File(targetDoc));
            if (isDocx(filename)) {
                create2007Docx(inputStream, param, outputStream);
            } else {
                create2003Doc(inputStream, param, outputStream);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

    /**
     * 创建2003格式word(doc后缀)
     *
     * @param in    模版文件inputStream
     * @param param 模版中需要替换的参数
     * @param out   生成的文件outputStream
     * @throws IOException
     */
    public static void create2003Doc(InputStream in, Map<String, String> param, OutputStream out) throws IOException {
        HWPFDocument doc = new HWPFDocument(in);
        Range range = doc.getRange();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            range.replaceText("${" + entry.getKey() + "}", entry.getValue());
        }
        doc.write(out);
    }

    /**
     * 创建2007格式word(docx后缀)
     *
     * @param in    模版文件inputStream
     * @param param 模版中需要替换的参数
     * @param out   生成的文件outputStream
     * @throws IOException
     */
    public static void create2007Docx(InputStream in, Map<String, String> param, OutputStream out) throws IOException {
        XWPFDocument docx = new XWPFDocument(in);
        //替换段落相关文字
        List<XWPFParagraph> paragraphList = docx.getParagraphs();
        for (XWPFParagraph paragraph : paragraphList) {
            List<XWPFRun> runList = paragraph.getRuns();
            for (XWPFRun run : runList) {
                String text = run.getText(run.getTextPosition());
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    if (StringUtils.isNotBlank(text) && text.equals("${" + entry.getKey() + "}")) {
                        run.setText(entry.getValue());
                    }
                }
            }
        }

        //替换内嵌表格中的文字
        List<XWPFTable> xwpfTableList = docx.getTables();
        for (XWPFTable table : xwpfTableList) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> tableCells = row.getTableCells();
                for (XWPFTableCell cell : tableCells) {
                    for (Map.Entry<String, String> entry : param.entrySet()) {
                        if (StringUtils.isNotBlank(cell.getText()) && cell.getText().equals("${" + entry.getKey() + "}")) {
                            cell.removeParagraph(0);
                            cell.setText(entry.getValue());
                        }
                    }
                }
            }
        }
        docx.write(out);
    }
}
