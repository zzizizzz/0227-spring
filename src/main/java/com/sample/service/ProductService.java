package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.mapper.ProductMapper;
import com.sample.vo.Product;
import com.sample.web.dto.Criteria;
import com.sample.web.dto.ListDto;
import com.sample.web.dto.Pagination;
import com.sample.web.form.ProductCreateForm;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	/**
	 * ProductCreateForm 객체를 전달받아서 신규 상품으로 등록한다.
	 * @param form 신규 상품정보가 포함된 ProductCreateForm 객체
	 */
	public void createProduct(ProductCreateForm form) {
		// ProductCreateForm 객체에 저장된 값을 Product객체를 생성하고, 초기화한다.
		Product product = Product.builder()
				.name(form.getName())
				.description(form.getDescription())
				.price(form.getPrice())
				.stock(form.getPrice())
				.build();
		
		productMapper.insertProduct(product);
	}
	/**
	 * 목록 조회에 필요한 정보를 담고있는 Criteria객체를 전달받아서 목록조회한다<p>
	 * @param criteria 목록조회에 필요한 정보가 들어있는 객체다
	 * @return 상품목록정보와 페이징처리 정보가 저장된 객체를 반환한다.
	 */
	public ListDto<Product> getProducts(Criteria criteria) {
		int totalRows = productMapper.getTotalRows(criteria);
		
		// 현재페이지 번호, 총데이터갯수, 총 페이지갯수 , 총블록갯수, 현재 불록번호, 범위시작번호, 범위끝번호, 페이지시작번호 , 페이지끝번호
		Pagination pagination = new Pagination(criteria.getPage(), totalRows, criteria.getRows());
		// ㅎ현재 페이지 번호에 해당하는 조회범위를 Criteria객체에 저장한다.
		criteria.setBegin(pagination.getBegin());
		criteria.setEnd(pagination.getEnd());
		
		//상품목록을 조회한다.
		// Criteria객체에 rows, sort, opt, keyword, begin, end값이 설정되어있다.
		List<Product> productList = productMapper.getProducts(criteria);
		
		ListDto<Product> dto = new ListDto<Product>(productList, pagination);
		
		return dto;
	}

	public Product getProductDetail(int no) {
		return productMapper.getProductByNo(no);
	}
}
