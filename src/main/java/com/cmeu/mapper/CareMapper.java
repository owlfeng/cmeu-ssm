package com.cmeu.mapper;

import com.cmeu.pojo.Care;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CareMapper {
	Integer getTotal();
	
	Care get(int id);
	
	List<Care> getList();

	//test
	List<Care> testList();
	
	List<Care> getPageList(@Param("start")int start, @Param("count")int count);
	
	List<Care> queryList(Care care);
	
	void delete(int id) throws Exception;
	
	void save(Care care) throws Exception ;
	
	void update(Care care) throws Exception ;


}