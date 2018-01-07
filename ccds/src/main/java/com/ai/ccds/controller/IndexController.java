package com.ai.ccds.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class IndexController {
	
	@RequestMapping(value = "/{module}")
	public String index(HttpServletRequest request, @PathVariable("module") String module) {
		request.setAttribute("module", module);
		return "index";
	}
	
	@RequestMapping(value = "/subMenu/{module}")
	public String nav(HttpServletRequest request, @PathVariable("module") String module) {
		request.setAttribute("module", module);
		return "subMenu";
	}
}
