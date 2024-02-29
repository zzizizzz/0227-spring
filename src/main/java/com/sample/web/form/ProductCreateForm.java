package com.sample.web.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
// 입력폼에 똑같은이름으로
@Getter
@Setter
@ToString
public class ProductCreateForm {

	private int companyNo; // 폼option입력값 그대로 작성해야한다.
	private String name;
	private int price;
	private int stock;
	private MultipartFile photofile; //MultipartFile(타입) : 스프링에서 첨부파일 업로드를 지원하기위해서 만든것 
	private String description;

	
	
}
