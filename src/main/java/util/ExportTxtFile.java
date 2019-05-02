package util;


import com.cmeu.pojo.FRMModel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author fudada
 * @date 2019/4/30 - 20:08
 */
public class ExportTxtFile {

    public void exportLog(HttpServletResponse response,List<FRMModel> frmModels) throws Exception{
        //获取数据
//       List<FRMModel> frmModels = frmService.list();
        //拼接字符串
        StringBuffer text = new StringBuffer();
        int size = frmModels.size();

        for(FRMModel temp:frmModels){
            text.append(temp.getCid());
            text.append("|");
            text.append(String.valueOf(temp.getInitiation()));
            text.append("|");
            text.append(String.valueOf(temp.getFrequency()));
            text.append("|");
            text.append(String.valueOf(temp.getMonetary()));
            text.append("|");
            text.append(String.valueOf(temp.getRecency()));
            text.append("|");
            text.append("\r\n");//换行字符
        }
        exportTxt(response,text.toString());

    }

    /* 导出txt文件
     * @author
     * @param	response
     * @param	text 导出的字符串
     * @return
     */
    public void exportTxt(HttpServletResponse response, String text) {
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String format = bf.format(date);
        response.setCharacterEncoding("utf-8");
        //设置响应的内容类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition", "attachment;filename="
                + genAttachmentFileName("用户价值分析数据"+format, "JSON_FOR_UCC_")//设置名称格式，没有这个中文名称无法显示
                + ".txt");
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(text.getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            //LOGGER.error("导出文件文件出错:{}",e);
        } finally {
            try {
                buff.close();
                outStr.close();
            } catch (Exception e) {
                //LOGGER.error("关闭流对象出错 e:{}",e);
            }
        }
    }

    public String genAttachmentFileName(String cnName, String defaultName) {
        try {
            cnName = new String(cnName.getBytes("gb2312"), "ISO8859-1");
        } catch (Exception e) {
            cnName = defaultName;
        }
        return cnName;
    }

}
