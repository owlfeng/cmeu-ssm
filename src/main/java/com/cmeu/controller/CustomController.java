package com.cmeu.controller;


import com.cmeu.pojo.FRMModel;
import com.cmeu.pojo.vo.AnalyVo;
import com.cmeu.pojo.vo.EchartVo;
import com.cmeu.pojo.vo.echartsRaderVo;
import com.cmeu.pojo.vo.ktestvo;
import com.cmeu.result.DataTable;
import com.cmeu.service.AnalyService;
import com.cmeu.service.CustomerService;
import com.cmeu.service.OrderTestService;
import com.cmeu.service.FRMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/custom")
public class CustomController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AnalyService analyService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FRMService frmService;

    @Autowired
    OrderTestService orderTestService;

    @RequestMapping("/analy")
    public ModelAndView analy(HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("custom/analy");
        List<FRMModel> list = frmService.list();

//        exportLog(response,list);
//        exportLog(response);
        return modelAndView;
    }
    public void exportLog(HttpServletResponse response) throws Exception{
        //获取数据
       List<FRMModel> frmModels = frmService.list();
        //拼接字符串
        StringBuffer text = new StringBuffer();
        int size = frmModels.size();

        for(FRMModel temp:frmModels){
            text.append(temp.getCid());
            text.append("|");
            text.append(temp.getInitiation());
            text.append("|");
            text.append(temp.getFrequency());
            text.append("|");
            text.append(temp.getMonetary());
            text.append("|");
            text.append(temp.getRecency());
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
        response.setCharacterEncoding("utf-8");
        //设置响应的内容类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition", "attachment;filename="
                + genAttachmentFileName("用户价值分析数据"+new Date(), "JSON_FOR_UCC_")//设置名称格式，没有这个中文名称无法显示
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
    @RequestMapping(value = "/queryEchart", method = RequestMethod.POST)
    @ResponseBody
    public List<EchartVo> queryEchart() throws Exception {
        List<AnalyVo> anlayVo = analyService.getAnlayVo();
        List<EchartVo> echartList = new ArrayList<>();
        for (AnalyVo anlay : anlayVo) {
            EchartVo echart = new EchartVo();
            echart.setName(anlay.getValue());
            echart.setValue(String.valueOf(anlay.getNumber()));
            echartList.add(echart);
        }
        return echartList;
    }


    @RequestMapping("/queryEchart2")
    @ResponseBody
    public List<echartsRaderVo> echarts(Model model) throws ParseException {
        List<String> stringList = stringList();
        //雷达图	信息
        String[] a = {"L", "F", "M", "R"};

        int L, R;

        String dateStr = "2020-1-1 1:21:28";
        ArrayList<Integer> T1 = new ArrayList<>();
        ArrayList<Integer> T2 = new ArrayList<>();
        ArrayList<Integer> T3 = new ArrayList<>();
        ArrayList<Integer> T4 = new ArrayList<>();
        ArrayList<Integer> cuid = new ArrayList<>();
        List<echartsRaderVo> list = new ArrayList<echartsRaderVo>();
        ArrayList<float[]> dataSet = new ArrayList<float[]>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<float[]> localArray = new ArrayList<float[]>();
        ArrayList<FRMModel> frmModels = new ArrayList<>();
        List<ktestvo> testlist = orderTestService.testlist();
        //对四个模型向量赋值
        for (int j = 0; j < testlist.size(); j++) {
            //客户总购买次数
            int F = testlist.get(j).getCount();
            //客户总购买金额
//			int M = Integer.parseInt(testlist.get(j).getPrice());
            String M = testlist.get(j).getPrice();
            //最后购买日期
            Date lasttime = testlist.get(j).getLasttime();
            //入会时间
            Date intime = testlist.get(j).getIntime();
//			Date date2 = simpleDateFormat.format(new Date());
//			Date date2 =simpleDateFormat.parse(new Date().toString());
            //获取当前时间
            Date d = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            //入会时长
            L = riqitest.differentDaysByMillisecond(intime, d);
            //最后一次购买 与当前时间差
            R = riqitest.differentDaysByMillisecond(lasttime, d);

            cuid.add(testlist.get(j).getId());
            T1.add(L);
            T2.add(F);
            T3.add(Integer.valueOf(M));
            T4.add(R);
        }

        //创建二维数组并赋值  便于z-score
        double[][] arr3 = new double[testlist.size()][5];
        for (int i = 0; i < arr3.length; i++) {
            for (int P = 0; P < 4; P++) {
                if (P == 0) {
//					arr3[i][P]=cuid.get(i);
                    arr3[i][P] = T1.get(i);//
                } else if (P == 1) {
//					arr3[i][P]=T1.get(i);//
                    arr3[i][P] = T2.get(i);

                } else if (P == 2) {
//					arr3[i][P]=T2.get(i);
                    arr3[i][P] = T3.get(i);

                } else if (P == 3) {
//					arr3[i][P]=T3.get(i);
                    arr3[i][P] = T4.get(i);

                } else if (P == 4) {
//					arr3[i][P]=T4.get(i);
                    arr3[i][P] = cuid.get(i);
                }
            }
        }
        //	进行z-score 处理
        double[][] doubles = zcUtil.normalize4ZScore(arr3);
        for (int u = 0; u < doubles.length; u++) {
            doubles[u][4] = cuid.get(u);
        }

        //将标准化处理后数据放进集合  下一步进行k-means
        for (int k = 0; k < doubles.length; k++) {
            float aa;
            float[] bb = new float[5];
            for (int i = 0; i < 5; i++) {
                aa = (float) doubles[k][i];
                bb[i] = aa;
            }
            dataSet.add(bb);
//			dataSet.add(new float[]{T1.get(k),T2.get(k),T3.get(k),T4.get(k)});
        }

        //进行k-means运算
        KMeansRun kRun = new KMeansRun(4, dataSet);
        Set<Cluster> clusterSet = kRun.run();
        //save user
        frmService.save(clusterSet);
        //得到簇 并进行操作
        int p = 0;
        for (Cluster cluster : clusterSet) {
            List<Point> members = cluster.getMembers();

            float[] floats = cluster.getCenter().getlocalArray();
            FRMModel frmModels1 = new FRMModel(p++, floats[0], floats[1], floats[2], floats[3]);
            frmModels.add(frmModels1);
        }
        //比较出各个中心点的权重
        List<Integer> list6 = new ArrayList();
        for (int i = 0; i < frmModels.size(); i++) {
            for (int o = 0; o < frmModels.size(); o++) {
                FRMModel frmModel = frmModels.get(i);
                if (o != i) {
                    frmModel.compare(frmModels.get(o));
                }
            }
        }
        //把获得的权重进行比较 放到 雷达图对象中
        Collections.sort(frmModels);
        for (FRMModel m : frmModels) {
            m.setFloats(m.getInitiation(), m.getFrequency(), m.getMonetary(), m.getRecency());
        }
        for (int o = 0; o < 4; o++) {
            echartsRaderVo echartsRader = new echartsRaderVo(stringList.get(o), frmModels.get(o).getFloats(), a[o], 15, -5);
            list.add(echartsRader);
        }
        return list;
    }

    public List<String> stringList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("一般客户");
        stringList.add("发展客户");
        stringList.add("重要挽留客户");
        stringList.add("重要保持客户");
        return stringList;
    }

    @RequestMapping(value = "/queryAnaly", method = RequestMethod.POST)
    public @ResponseBody
    DataTable<AnalyVo> queryAnaly(String draw, String start, String length) throws Exception {
        System.out.println("/queryAnaly");
        List<AnalyVo> anlayVo = analyService.getAnlayVo();
        //声明一个datatable对象封装数据

        DataTable<AnalyVo> data = new DataTable<AnalyVo>();
        data.setDraw(Integer.parseInt(draw == null ? "0" : draw) + 1);
        data.setRecordsTotal(5);
        data.setRecordsFiltered(5);
        data.setData(anlayVo);

        return data;
    }

    @RequestMapping("/control")
    public ModelAndView control() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("custom/control");
        return modelAndView;
    }


}
