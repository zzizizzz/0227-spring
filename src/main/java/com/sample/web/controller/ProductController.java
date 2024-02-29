package com.sample.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.service.CompanyService;
import com.sample.service.ProductService;
import com.sample.vo.Company;
import com.sample.vo.Product;
import com.sample.web.dto.Criteria;
import com.sample.web.dto.ListDto;
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
	@Autowired
	private CompanyService companyService;
	
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
	/*
	 * 요청 URL
	 * 		/product/list
	 * 		/product/ list?page=1&rows=10&sort=date&opt=%keyword
	 * 		/product/list? page=2&rows=10&sort=lowprice&opt=name&keyword=노트북
	 * 
	 */
	
	@GetMapping(path="/list")
	public String list(@RequestParam(name = "page", required = false, defaultValue = "1")int page,
			@RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
			@RequestParam(name = "sort", required = false, defaultValue = "date") String sort,
			@RequestParam(name = "opt", required = false) String opt,
			@RequestParam(name = "keyword", required = false) String keyword,
			Model model ) {

		Criteria criteria = new Criteria();
		criteria.setPage(page);
		criteria.setRows(rows);
		criteria.setSort(sort);
		
		// 검색옵션(opt)와 거색어(keyword) 모두  null이나 빈 문자열이 아닐떄만 map에 저장한다
		if(StringUtils.hasText(opt) && StringUtils.hasText(keyword)) {
			criteria.setOpt(opt);
			criteria.setKeyword(keyword);
		}
		ListDto<Product> dto = productService.getProducts(criteria);
		model.addAttribute("productList", dto.getItems());
		model.addAttribute("paging", dto.getPaging());

		return "product/list"; 	//"WEB-INF/views/product/list.jsp"로 내부이동
	}
	
	@GetMapping("/create")
	public String form(Model model) {
		//전체 회사정보 조회하고, Model에 저장한다.
		List<Company> companyList = companyService.getAllCompanies();
		model.addAttribute("companyList", companyList);
		
		return "product/form";			// "/WEB-INF/views/product/form.jsp로 내부이동시킨다.
	}
	
	@PostMapping("/create")
	public String create(ProductCreateForm productCreateForm) {
		productService.createProduct(productCreateForm);
		return "redirect:list";
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("no") List<Integer> noList){
		productService.deleteProducts(noList);
		
		return "redirect:list";
	}
}
