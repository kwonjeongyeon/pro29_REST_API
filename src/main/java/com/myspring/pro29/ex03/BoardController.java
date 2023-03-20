package com.myspring.pro29.ex03;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {
	static Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	// get 방식 요청 : 모든 글의 정보 조회
	public ResponseEntity<List<ArticleVO>> listArticles() {
		logger.info("listArticles 메서드 호출");
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		for (int i = 0; i < 10; i++) {
			ArticleVO vo = new ArticleVO();
			vo.setArticleNO(i);
			vo.setWriter("이순신" + i);
			vo.setTitle("안녕하세요" + i);
			vo.setContent("새 상품을 소개합니다" + i);
			list.add(vo);
		}
		return new ResponseEntity(list, HttpStatus.OK);
	}

	// get 방식으로 요청하면서 글 번호를 전달하므로 글 번호에 대한 글 정보 조회
	@RequestMapping(value = "/{articleNO}", method = RequestMethod.GET)
	public ResponseEntity<ArticleVO> findArticle(@PathVariable("articleNO") Integer articleNO) {
		logger.info("findArticle 메서드 호출");
		ArticleVO vo = new ArticleVO();
		vo.setArticleNO(articleNO);
		vo.setWriter("홍길동");
		vo.setTitle("안녕하세요");
		vo.setContent("홍길동 글입니다");
		return new ResponseEntity(vo, HttpStatus.OK);
	}

	// post 방식으로 요청하므로 요청시 JSON으로 전달되는 객체를 새 글로 추가합니다.
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> addArticle(@RequestBody ArticleVO articlevo) {
		ResponseEntity<String> resEntity = null;

		try {
			logger.info("addArticle 메서드 호출");
			logger.info(articlevo.toString());
			// 개체의 문자열 표현을 반환합니다. 일반적으로 toString 메서드는 이 개체를 "텍스트적으로 나타내는" 문자열을 반환합니다.
			resEntity = new ResponseEntity("ADD_SUCCEDED", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;
	}

	// put 방식으로 요청하므로 articleNO에 대한 글을 전달되는 JSON정보로 수정합니다.
	@RequestMapping(value = "/{articleNO}", method = RequestMethod.PUT)
	public ResponseEntity<String> modArticle(@PathVariable("articleNO") Integer articleNO,
			@RequestBody ArticleVO articlevo) {
		ResponseEntity<String> resEntity = null;

		try {
			logger.info("modArticle 메서드 호출");
			logger.info(articlevo.toString());
			resEntity = new ResponseEntity("MOD_SUCCEDED", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;
	}

	// delete 방식으로 요청하므로 저달되는 articleNO에 대한 글을 삭제
	@RequestMapping(value = "/{articleNO}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeArticle(@PathVariable("articleNO") Integer articleNO) {
		ResponseEntity<String> resEntity = null;

		try {
			logger.info("modArticle 메서드 호출");
			logger.info(articleNO.toString());
			resEntity = new ResponseEntity("REMOVE_SUCCEDED", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;
	}
}
