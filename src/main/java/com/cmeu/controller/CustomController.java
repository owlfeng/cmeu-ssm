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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
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
        return modelAndView;
    }
    @GetMapping("/export")
    @ResponseBody
    public  List<FRMModel> exportTxt(HttpServletResponse response) throws Exception {
        ExportTxtFile exportTxtFile = new ExportTxtFile();
        List<FRMModel> list = frmService.list();
        exportTxtFile.exportLog(response,list);
        return list;
    }
    @PostMapping("/upload")
    @ResponseBody
    public  List<FRMModel> uploadTxt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = request.getServletContext().getRealPath("/upload");
        //上传时生成的临时文件保存目录
        String tempPath = request.getServletContext().getRealPath("/temp");
//       如果你使用idea，因为默认编译输出目录是target目录下
//        String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF");

        File file = new File(tempPath);
        if(!file.exists()&&!file.isDirectory()){
            System.out.println("目录或文件不存在！");
            file.mkdir();
        }
        //消息提示
        String message = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            diskFileItemFactory.setSizeThreshold(1024*100);
            //设置上传时生成的临时文件的保存目录
            diskFileItemFactory.setRepository(file);
            //2、创建一个文件上传解析器
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            //解决上传文件名的中文乱码
            fileUpload.setHeaderEncoding("UTF-8");
            //监听文件上传进度
            fileUpload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                }
            });
            //3、判断提交上来的数据是否是上传表单的数据
            if(!fileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return null;
            }
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            fileUpload.setFileSizeMax(1024*1024);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            fileUpload.setSizeMax(1024*1024*10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    String value1 = new String(name.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name+"  "+value);
                    System.out.println(name+"  "+value1);
                }else{
                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
                    String fileName = item.getName();
                    System.out.println(fileName);
                    if(fileName==null||fileName.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
                    //得到上传文件的扩展名
                    String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
                    if("zip".equals(fileExtName)||"rar".equals(fileExtName)||"tar".equals(fileExtName)||"jar".equals(fileExtName)){
                        request.setAttribute("message", "上传文件的类型不符合！！！");
                        request.getRequestDispatcher("/message.jsp").forward(request, response);
                        return null;
                    }
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传文件的扩展名为:"+fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream is = item.getInputStream();
                    //得到文件保存的名称
                    fileName = mkFileName(fileName);
                    //得到文件保存的路径
                    String savePathStr = mkFilePath(savePath, fileName);
                    System.out.println("保存路径为:"+savePathStr);
                    //创建一个文件输出流
                    FileOutputStream fos = new FileOutputStream(savePathStr+File.separator+fileName);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int length = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((length = is.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        fos.write(buffer, 0, length);
                    }
                    //关闭输入流
                    is.close();
                    //关闭输出流
                    fos.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功";
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "单个文件超出最大值！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return null;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return null;
        }catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            message = "文件上传失败";
        }
        request.setAttribute("message",message);

        return null;
    }
    //生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
    public String mkFileName(String fileName){
        return UUID.randomUUID().toString()+"_"+fileName;
    }
    public String mkFilePath(String savePath,String fileName){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = fileName.hashCode();
        int dir1 = hashcode&0xf;
        int dir2 = (hashcode&0xf0)>>4;
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2;
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
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

        // 从数据提取基础数据
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


        // 检查数据异常,初始化
        KMeansRun kRun = new KMeansRun(4, dataSet);
        //进行k-means运算
        Set<Cluster> clusterSet = kRun.run();
        //save user
        frmService.save(clusterSet);
        //得到簇 数组转集合
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
