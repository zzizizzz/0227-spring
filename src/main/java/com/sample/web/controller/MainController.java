package com.sample.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	
	@GetMapping(path="/")
	public String home(Model model) { // 반환타입기본적으로String
		model.addAttribute("msg", "홈페이지 방문을 환영합니다");
		// 뷰페이지에 값을담는것을 model이라는 매개변수에 담는다.
		return "home"; //문자열 반환
	}
}
