package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GeustBookDao;
import com.javaex.vo.GeustBookVo;

@Controller
public class GeustController {
	
	@Autowired
	private GeustBookDao dao;
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam("id") String id , @RequestParam("password") String password, @RequestParam("noticeBoard") String noticeBoard) {
		System.out.println("add vo");
		dao.insert(id, password, noticeBoard);
		return "redirect:/addList";
	}

	@RequestMapping(value = "/deleteForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable("no") int no , Model model) {
		System.out.println("delete form start");
		GeustBookVo  vo = (GeustBookVo)dao.getVo(no);
		System.out.println(vo.toString());
		model.addAttribute("vo", vo);
		return "/WEB-INF/views/deleteForm.jsp";
		
	}

	@RequestMapping(value = "/delete", method ={RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GeustBookVo geustBookVo) {
		System.out.println("delete vo ");
		System.out.println(geustBookVo.toString());
		dao.delete(geustBookVo);
		return "redirect:/addList";
	}

	@RequestMapping(value = "/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("get list");
		List<GeustBookVo> list = dao.getList();

		model.addAttribute("list", list);
		return "/WEB-INF/views/addList.jsp";

	}
}
