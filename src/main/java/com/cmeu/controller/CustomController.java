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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import util.Cluster;
import util.KMeansRun;
import util.riqitest;
import util.zcUtil;

import javax.servlet.http.HttpServletRequest;
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
	OrderTestService orderTestService;

    @RequestMapping("/analy")
    public ModelAndView analy() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("custom/analy");
        return modelAndView;
    }

	@RequestMapping(value = "/queryEchart", method = RequestMethod.POST)
	@ResponseBody
	public  List<EchartVo> queryEchart() throws Exception
	{
		List<AnalyVo> anlayVo = analyService.getAnlayVo();
		List<EchartVo> echartList=new ArrayList<>();
		for (AnalyVo anlay:anlayVo) {
			EchartVo echart=new EchartVo();
			echart.setName(anlay.getValue());
			echart.setValue(String.valueOf(anlay.getNumber()));
			echartList.add(echart);
		}
		return  echartList;
	}


	@RequestMapping("/queryEchart2")
	@ResponseBody
	public  List<echartsRaderVo> echarts(Model model) throws ParseException {
//		List<echartsRaderVo> list =new ArrayList<echartsRaderVo>();
//		ArrayList<List<String>> temp = new ArrayList<>();
//		ArrayList<float[]> dataSet = new ArrayList<float[]>();
//		ArrayList<float[]> localArray = new ArrayList<float[]>();
//		ArrayList<String> strings = new ArrayList<>();
//		dataSet.add(new float[] { 1, 2, 3 ,1});
//		dataSet.add(new float[] { 3, 3, 3 ,12});
//		dataSet.add(new float[] { 3, 4, 4,6});
//		dataSet.add(new float[] { 5, 6, 5,1});
//		dataSet.add(new float[] { 8, 9, 6,1});
//		dataSet.add(new float[] { 4, 5, 4,1});
//		dataSet.add(new float[] { 6, 4, 2,3});
//		dataSet.add(new float[] { 3, 9, 7,2});
//		dataSet.add(new float[] { 5, 9, 8,10});
//		dataSet.add(new float[] { 4, 2, 10,2});
//		dataSet.add(new float[] { 1, 9, 12,9});
//		dataSet.add(new float[] { 7, 8, 11,4});
//		dataSet.add(new float[] { 7, 8, 4,2});
//		KMeansRun kRun =new KMeansRun(4, dataSet);
//		Set<Cluster> clusterSet = kRun.run();
//		//这里把聚类完成的中心点
//		for (Cluster cluster : clusterSet) {
//			localArray.add(	cluster.getCenter().getlocalArray());
//		}
//		for (int o=0;o<4;o++){
//			echartsRaderVo echartsRader = new echartsRaderVo("客户"+(o+1), localArray.get(o), "特点"+(o+1), 15,-5);
//			list.add(echartsRader);
//		}
//		return  list;

		int L,R;
		String dateStr = "2020-1-1 1:21:28";
		ArrayList<Integer> T1 = new ArrayList<>();
		ArrayList<Integer> T2 = new ArrayList<>();
		ArrayList<Integer> T3 = new ArrayList<>();
		ArrayList<Integer> T4 = new ArrayList<>();
		ArrayList<Integer> cuid = new ArrayList<>();

		List<echartsRaderVo> list =new ArrayList<echartsRaderVo>();

		ArrayList<float[]> dataSet = new ArrayList<float[]>();
		List<ktestvo> testlist = orderTestService.testlist();
		//对四个模型向量赋值
		for (int j = 0; j < testlist.size(); j++){
			//客户总购买次数
			int F = testlist.get(j).getCount();
			//客户总购买金额
//			int M = Integer.parseInt(testlist.get(j).getPrice());
			String M = testlist.get(j).getPrice();
			//最后购买日期
			Date lasttime = testlist.get(j).getLasttime();
			//入会时间
			Date intime = testlist.get(j).getIntime();

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date date2 = simpleDateFormat.format(new Date());
//			Date date2 =simpleDateFormat.parse(new Date().toString());
			//获取当前时间
			Date d = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
			//入会时长
			L= riqitest.differentDaysByMillisecond(intime,d);
			//最后一次购买 与当前时间差
			R=riqitest.differentDaysByMillisecond(lasttime,d);

			cuid.add(testlist.get(j).getId());
			T1.add(L);
			T2.add(F);
			T3.add(Integer.valueOf(M));
			T4.add(R);
		}

		//创建二维数组并赋值  便于z-score
		double[][] arr3=new double[testlist.size()][5];
		for(int i=0;i<arr3.length;i++)
		{
			for(int P=0; P<5; P++)
			{
				if (P == 0){
					arr3[i][P]=cuid.get(i);
//					arr3[i][P]=T1.get(i);//
				}else if (P==1){
					arr3[i][P]=T1.get(i);//
//					arr3[i][P]=T2.get(i);

				}else if (P==2){
					arr3[i][P]=T2.get(i);
//					arr3[i][P]=T3.get(i);

				}else if (P==3){
					arr3[i][P]=T3.get(i);
//				arr3[i][P]=T4.get(i);

				}else if (P==4){
					arr3[i][P]=T4.get(i);
//					arr3[i][P]=cuid.get(i);
				}


			}
		}
		//	进行z-score 处理
		double[][] doubles = zcUtil.normalize4ZScore(arr3);
//		for (int k = 0; k < doubles.length; k++) {
//			for(int P=0; P<5; P++)
//			{
//				if (P == 4){
//					arr3[k][P]=cuid.get(k);//
//				}
//			}
//		}

		//将标准化处理后数据放进集合  下一步进行k-means
		for (int k = 0; k<doubles[0].length; k++) {
			float aa;
			float[] bb=new float[doubles.length];
			for (int i = 0; i < 5; i++)
			{
			aa=(float) doubles[k][i];
			bb[i]=aa;
			}
			dataSet.add(bb);
//			dataSet.add(new float[]{T1.get(k),T2.get(k),T3.get(k),T4.get(k)});
		}



		ArrayList<float[]> localArray = new ArrayList<float[]>();

		//进行k-means运算
		KMeansRun kRun =new KMeansRun(4, dataSet);
		Set<Cluster> clusterSet = kRun.run();

		ArrayList<FRMModel> frmModels = new ArrayList<>();
		//得到簇
		for (Cluster cluster : clusterSet) {
			localArray.add(	cluster.getCenter().getlocalArray());
			float[] floats = cluster.getCenter().getlocalArray();
			FRMModel frmModels1 = new FRMModel(cluster.getCenter().getTitle(),floats[0],floats[1],floats[2],floats[3]);
			frmModels.add(frmModels1);
		}
			Collections.sort(frmModels);
		for (FRMModel fe:frmModels) {
			Float weight = fe.getWeight();
		}


		//雷达图	信息
		String[] a={"L","F","M","R"};
 		for (int o=0;o<4;o++){
			echartsRaderVo echartsRader = new echartsRaderVo("客户"+(o+1), localArray.get(o),a[o], 15,-5);
			list.add(echartsRader);
		}
		return  list;


	}

	@RequestMapping(value = "/queryAnaly", method = RequestMethod.POST)
	public @ResponseBody DataTable<AnalyVo> queryAnaly(String draw, String start, String length) throws Exception
	{
		System.out.println("/queryAnaly");
		List<AnalyVo> anlayVo = analyService.getAnlayVo();
		//声明一个datatable对象封装数据

		DataTable<AnalyVo> data = new DataTable<AnalyVo>();
		data.setDraw(Integer.parseInt(draw == null ? "0": draw) + 1);
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
