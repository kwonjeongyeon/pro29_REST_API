# pro29_REST_API

REST : representational state transfer 
       하나의 URI가 고유한 리소스를 처리하는 공통 방식


@RestController : JSP같은 뷰를 반환하는 것이 아니라 JSON, XML 같은 데이터를 브라우저로 전송하는 컨트롤러
@PathVariable : 브라우저에서 요청 URL로 전달된 매개 변수를 가져올 수 있음
@RequestBody : 브라우저에서 전달되는 JSON 데이터를 객체로 자동 변환해줌
@ResponseBody : 컨트롤러의 특정메서드에 @ResponseBody를 적용하면 JSP가 아닌 텍스트나 JSON으로 결과를 전송할 수 있음

@ResponseEntity : @RestController는 별도의 View를 제공하지 않은 채 데이터를 전달하므로 전달과정에서 예외 발생 가능, 
                  예외에 대해 좀 더 세밀한 제어가 필요한 경우 REsponseEntity 클래스 사용
*HttpHeaders 클래스를 이용해 ResponseEntity로 전송할 데이터의 종류와 한글 인코딩 설정 가능
*ResponseEntity를 이용하면 JSON뿐만 아니라, HTML이나 자바스크립트를 브라우저로 전송할 수 있어
결과 메시지나 오류 메시지를 전송할 때 편리함.

<REST 방식으로 요청하는 URI 형식>
/작업명/기본키+메서드+데이터
작업명 : 요청하는 작업 종류
기본키 : 요청하는 작업에 해당하는 대상의 기본키
메서드 : 요청하는 기능
데이터 : 기능 수행에 필요한 JSON 데이터

POST : 추가 (create)
GET : 조회 (select)
PUT : 수정(update)
DELETE : 삭제(delete)


