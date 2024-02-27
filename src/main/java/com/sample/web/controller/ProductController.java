package com.sample.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.service.ProductService;
import com.sample.vo.Product;
import com.sample.web.form.ProductCreateForm;
/*
 * @Controller 
 * 		이 클래스가 MVC 패턴에서 컨트롤러의 역할을 수행하는 크래스임을 나타낸다.
 * 		이 클래스가 컴포넌트스캔 대상 클래스임을 나타낸다. 컴포넌트 스캔 대상 클래스는 애플리케이션 시작시에 스프링 컨테이너가 객체를 생성하고, 필요한 의존성을 주입한다.
 * 
 * @RequestMapping("/product")
 * 		URL 매핑정보를 정의한다.
 * 		클래스에 정의된 URL 매핑정보는 요청핸들러 메소드에 정의된 URL 매핑정보에 접두사처럼 추가된다.
 *   @RequestMapping("/product")+@GetMapping(path="/list")합쳐진다.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	
	/*
	 * 	@Autowired
	 * 		자동의존성 주입을 지원하는 어노테이션이다.
	 * 		ProductController가 의존하는 객체를 스프링 컨테이너에서 찾아서 자동으로 조립한다.
	 * 		만약 ProductService 타입의 객체가 스프링 컨테이너에 없으면, 애플리케이션 시작시 오류가 발생한다.
	 */
	
	@Autowired
	private ProductService productService;
	
	/*
	 * 요청URL
	 * 		/product/detail?no=10
	 * 요청 파라미터
	 * 		no=10
	 * jsp에게 전달해주기 위해서 모델을 정의해주는거 model 에 담고 request에게 전달해주기위해서
	 */
	@GetMapping("/detail")
	public String detail(int no, Model model) {
		Product product = productService.getProductDetail(no);
		model.addAttribute("product", product);
		
		return "product/detail";		// "/WEB-INF/views/products/detail.jsp로 내부이동
		
	}
	
	@GetMapping(path="/list")
	public String list(Model model) {
		List<Product> productsList = productService.getProducts();
		model.addAttribute("productsList", productsList);
		
		return "product/list"; 	//"WEB-INF/views/product/list.jsp"로 내부이동
	}
	
	@GetMapping("/create")
	public String form() {
		
		return "product/form";	// "WEB-INF/views/product/form.jsp로 내부이동시킨다.
	}
	
	@PostMapping("/create")
	public String create(ProductCreateForm productCreateForm) {
		productService.createProduct(productCreateForm);
		return "redirect:list";
		
		
		
	}
}
