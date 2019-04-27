package com.cmeu.service;

import com.cmeu.pojo.Care;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CareService {

	public Care get(int id) ;
	
	public List<Care> getList();

	//测试
	public List<Care> testList();


	public List<Care> queryList(Care care);
	public Boolean delete(int id) ;

	public Boolean save(Care care) ;
	
	
	public Boolean update(Care care) ;

	public int getTotal() ;

	public List<Care> getPageList(int start,int count);



}
