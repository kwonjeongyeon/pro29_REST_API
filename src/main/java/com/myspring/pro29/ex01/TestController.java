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
//RestController : ������ �����ӿ�ũ 4.1.1.�� ���׷��̵� �� ����Ͽ��� ��.
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
		memberVO.setName("ȫ�浿");
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
			vo.setName("ȫ�浿" + i);
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
			vo.setName("ȫ�浿" + i);
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

	// @RestController�� ������ View�� �������� ���� ä �����͸� �����ϹǷ� ���� �������� ���ܰ� �߻��� �� �ִ�.
	// ���ܿ� ���� �� �� ������ ��� �ʿ��� ��� ResponseEntity Ŭ������ ���
	// ResponseEntitydp HTTP �����ڵ带 �����Ͽ� ������ �� �ִ�.
	// �ۿ��� HTTP �����ڵ带 �ν��� �� �ִ� ����� �̿��� �ֹ� ���³� ���� �߻��� �˷���
	// Extension of HttpEntity that adds an HttpStatusCode status code.
	@RequestMapping("/memberList2")
	public ResponseEntity<MemberVO> listMembers2() {

		MemberVO vo = new MemberVO();
		vo.setId("hong");
		vo.setPwd("1234");
		vo.setName("ȫ�浿");
		vo.setEmail("hong@test.com");
		return new ResponseEntity<MemberVO>(vo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// ResponseEntity�� �̿��ϸ� JSON�Ӹ� �ƴ϶�, HTML�̳� �ڹٽ�ũ��Ʈ�� �������� ������ �� �־�
	// ��� �޽����� ���� �޽����� ������ �� ����.
	@RequestMapping("/res3")
	public ResponseEntity res3() {

		// Constructs a new, empty instance of the HttpHeaders object
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		String message = "<script>";
		message += " alert('�� ȸ���� ����մϴ�.');";
		message += " location.href='/pro29/test/memberList2'; ";
		message += "</script>";
		return new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	}

}
