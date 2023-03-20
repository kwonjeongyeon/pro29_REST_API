package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//RestController : 스프링 프레임워크 4.1.1.로 업그레이드 후 사용하여야 함.
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test/*")
public class TestController {

	// The LoggerFactory is a utility class producing Loggers for various logging
	// APIs, most notably for log4j, log back and JDK 1.4 logging.
	static Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/firsttest")
	public String test() {
		return "testtesttest";
	}

	@RequestMapping("/member")
	public MemberVO member() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("hong");
		memberVO.setPwd("1234");
		memberVO.setName("홍길동");
		memberVO.setEmail("hong@test.com");
		return memberVO;
	}

	@RequestMapping("/memberList")
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();

		for (int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong" + i);
			vo.setPwd("1234");
			vo.setName("홍길동" + i);
			vo.setEmail("hong@test.com");
			list.add(vo);
		}

		return list;
	}

	@RequestMapping("/membersMap")
	public Map<Integer, MemberVO> membersMap() {
		Map<Integer, MemberVO> map = new HashMap<Integer, MemberVO>();

		for (int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong" + i);
			vo.setPwd("1234");
			vo.setName("홍길동" + i);
			vo.setEmail("hong@test.com");
			map.put(i, vo);
		}

		return map;
	}

	// Annotation which indicates that a method parameter should be bound to a URI
	// template variable.
	@RequestMapping(value = "/notice/{num}", method = RequestMethod.GET)
	int notice(@PathVariable(value = "num") int num) throws Exception {

		return num;
	}

	@RequestMapping(value = "/notice2/{num}", method = RequestMethod.GET)
	public String notice2(@PathVariable(value = "num") String num) throws Exception {

		return num;
	}

	// Annotation indicating a method parameter should be bound to the body of the
	// web request.The body of the request is passed through an HttpMessageConverter
	// to resolve the method argument depending on the content type of the request.
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public void modify(@RequestBody MemberVO vo) {
		logger.info(vo.toString());
	}

	// @RestController는 별도의 View를 제공하지 않은 채 데이터를 전달하므로 전달 과정에서 예외가 발생할 수 있다.
	// 예외에 대해 좀 더 세밀한 제어가 필요한 경우 ResponseEntity 클래스를 사용
	// ResponseEntitydp HTTP 상태코드를 설정하여 전송할 수 있다.
	// 앱에서 HTTP 상태코드를 인식할 수 있는 기능을 이용해 주문 상태나 예외 발생을 알려줌
	// Extension of HttpEntity that adds an HttpStatusCode status code.
	@RequestMapping("/memberList2")
	public ResponseEntity<MemberVO> listMembers2() {

		MemberVO vo = new MemberVO();
		vo.setId("hong");
		vo.setPwd("1234");
		vo.setName("홍길동");
		vo.setEmail("hong@test.com");
		return new ResponseEntity<MemberVO>(vo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// ResponseEntity를 이용하면 JSON뿐만 아니라, HTML이나 자바스크립트를 브라우저로 전송할 수 있어
	// 결과 메시지나 오류 메시지를 전송할 때 편리함.
	@RequestMapping("/res3")
	public ResponseEntity res3() {

		// Constructs a new, empty instance of the HttpHeaders object
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		String message = "<script>";
		message += " alert('새 회원을 등록합니다.');";
		message += " location.href='/pro29/test/memberList2'; ";
		message += "</script>";
		return new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	}

}
