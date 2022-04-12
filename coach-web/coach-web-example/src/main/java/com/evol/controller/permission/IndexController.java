//package com.evol.controller.permission;
//
//import com.evol.controller.BaseController;
//import com.evol.util.JwtUtil;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//
//@Controller
//@RequestMapping("/system")
//public class IndexController extends BaseController {
//
//	@RequestMapping({ "", "/index" })
//	public String index() {
//		return "index";
//	}
//
//
//	@GetMapping("/currentUser")
//	public String getUsername(String token) {
//		if (StringUtils.isBlank(token)) {
//			return "";
//		}
//		return JwtUtil.getUsername(token);
//	}
//}