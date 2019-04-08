package com.javaex.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GeustBookVo;


@Repository
public class GeustBookDao {

	// private final String url = "jdbc:oracle:thin:@10.37.129.3:1521:xe";

	@Autowired
	private SqlSession sqlsession;
	
	public List<GeustBookVo> getList(){
		List<GeustBookVo> list = sqlsession.selectList("geustbook.selectList");
		System.out.println(list.toString());
		return list;
	}
	
	/*
	 * public int insert(GeustBookVo vo) { int count =
	 * sqlsession.insert("geustbook.insert",vo); return count; }
	 */
	
	public int delete(GeustBookVo vo) {
		int count = sqlsession.delete("geustbook.delete",vo);
		return count;
	}
	
	public int insert(String id , String password, String noticeBoard) {
	    Map<String, Object> geustBookMap = new HashMap<String, Object>();
	    geustBookMap.put("id", id);
	    geustBookMap.put("password", password);
	    geustBookMap.put("noticeBoard", noticeBoard);

	    int count = sqlsession.insert("geustbook.insert", geustBookMap);
	    return count;
	}
	
	public GeustBookVo getVo(int no) {
		
		GeustBookVo vo = sqlsession.selectOne("geustbook.getbyno", no);
		return vo;
	}
	
}