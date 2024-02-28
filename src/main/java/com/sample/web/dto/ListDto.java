package com.sample.web.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 목록 페이지를 표시할 때 필요한 정보를 표현하는 클래스다.<p>
 * {@code List<T> items}는 화면에 표시할 데이터를 담는 멤버변수다<p> 
 * {@code Pagination paging}는 화면에 표시할 페이징정보를 담는 멤버변수다<p> 
 *
 * @param <T>
 */
@AllArgsConstructor
@Getter
@ToString
public class ListDto<T> {

	private List<T> items;
	private Pagination paging;
}
