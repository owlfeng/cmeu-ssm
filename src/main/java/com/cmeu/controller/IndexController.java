package com.cmeu.controller;

import com.cmeu.pojo.Customer;
import com.cmeu.pojo.Permission;
import com.cmeu.result.TreeMenuResult;
import com.cmeu.service.CustomerService;
import com.cmeu.service.PermissionService;
import com.cmeu.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController extends BaseController {
	@Autowired
	CustomerService customerService;
	@Autowired
	PermissionService permissionService;
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		modelAndView.addObject("shiroUser", shiroUser);
		
		//取出来的总权限
		List<Permission> menusList = permissionService.listPermissions(shiroUser.getUser().getUserId());
		
		List<TreeMenuResult> menus = new ArrayList<>();

		//主菜单
		for(Permission menu: menusList) {
			if("1".equals(menu.getSeq())) {
				TreeMenuResult temp = new TreeMenuResult();
				temp.setMenu(menu);
				menus.add(temp);
			}
	}
		//子菜单
		for(TreeMenuResult option: menus) {
			//menusList 全权限
			for(Permission menu: menusList) {
				// 个别有个节点的权限
				if(option.getMenu().getId()==menu.getParentid()) {
					TreeMenuResult temp = new TreeMenuResult();
					temp.setMenu(menu);
					option.getNodes().add(temp);
				}
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("menus", menus);
			
		return modelAndView;
	}


	
	@RequestMapping("/content")
	public ModelAndView content() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("content");
		return modelAndView;
	}
	@RequestMapping("/phone")
	public ModelAndView phone() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("phone");
		return modelAndView;
	}
	@RequestMapping("/demo")
	public ModelAndView demo() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Customer> customerList = customerService.getList();
		modelAndView.addObject("customerList", customerList);
//		JSONObject json = new JSONObject();
		//System.out.println(json);
		modelAndView.setViewName("demo");
		return modelAndView;
	}

}
