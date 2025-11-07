package com.base.servlet.unbind.search;

/**
 * 화면 기능 중 검색 기능이 있을 경우 해당 interface를 구현하도록 한다.
 * <p>본 프로젝트에서는 기본적으로 검색 조건을 HttpSession에 넣어 페이지 이동 시
 * 또는 화면 전환 시에 검색 조건을 유지 시킨다.</p>
 * 
 * <p>HttpSession에 검색조건을 넣어 검색조건을 유지시켜 주지만
 * 반대로 검색 조건을 초기화해야 할 경우 검색조건 초기화 시점이 애매하다.
 * (e.g. 검색조건을 초기화해야할 시점 : 메뉴 이동)</p>
 * 
 * <p>본 프로젝트에서는 검색조건 초기화 시점을 메뉴 이동으로 보고 메뉴 이동시 해당 interface를
 * 구현한 클래스를 찾아 HttpSession에서 해당 클래스를 제거하는 방법으로 검색조건 초기화를 수행한다.</p>
 * 
 * @author 조용상
 * @version 1.0
 * 
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.01.27 조용상 최초 작성
 * </pre>
 * 
 * @see SearchSessionUnbindingFilter
 */
public interface Search {
}