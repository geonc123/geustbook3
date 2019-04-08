package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GeustBookDao;
import com.javaex.vo.GeustBookVo;

@Controller
public class GeustController {

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestAttribute GeustBookVo vo) {
		System.out.println("add vo");
		GeustBookDao dao = new GeustBookDao();
		dao.insert(vo);
		return "redirect:/addList";
	}

	@RequestMapping(value = "/deleteForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable("no") int no) {
		System.out.println("delete form start");
		GeustBookDao dao = new GeustBookDao();
		GeustBookVo  vo = (GeustBookVo)dao.getVo(no);
		System.out.println(vo.toString());
		
		return "/WEB-INF/views/deleteForm.jsp";
		
	}

	@RequestMapping(value = "/delete", method ={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("password") String password , @RequestParam("no") int no) {
		System.out.println("delete vo ");
		GeustBookDao dao = new GeustBookDao();
		dao.delete(no, password);
		return "redirect:/addList";
	}

	@RequestMapping(value = "/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("get list");
		GeustBookDao dao = new GeustBookDao();
		List<GeustBookVo> list = dao.getList();

		model.addAttribute("list", list);
		return "/WEB-INF/views/addList.jsp";

	}
}
