# springProject

1. 주제 : 카드사[ 신용카드 추천 사이트 ] 

2. 사이트명 : Card24
      - 사용된 dependencies [ 제현 ]  
      - 스프링부트 사용하는 이유 [ 강현 ]
3. 레이아웃 [ 여름 ]




3-1 개발순서 

      1. entity  [ 테이블 생성 ]

      2. repository [ jpa db 접근 ] 

      3. HTML [ 프론트엔드 ] : 타임리프

      4. Controller [ html 과 연동 ] 

      5. service [ 행동 = 로직 ] 

4. 프론트엔드 페이지 구성
      1. 페이지 사이즈 : 1300px
      2. 전체 폰트 : 이사만루
      3. 페이지 갯수 
                 
5. DB 구성 
      1. OAUTH2 DB [ Oauth2Table ] : SMTP 보내기  : [제현] 
      2. 카드 DB   [ 카드사Table , 카드Table ]   : [여름]
      3. 게시판 DB [ 1:1상담 게시판 ]          : [강현]
      
6. 페이지 구조 [ HTML · CSS ]
      1. 메인페이지 : 메뉴(글꼴) , 메인세션 , 푸터 [여름]
            1. 메뉴.html [ 완 ]
            2. 메인세션.html [ 완 ]
            3. 푸터.html [ 완 ]
            5. 조건 검색.html  [ 완 ]
                  1.카드 리스트 html [ 완 ]
            7. 카드 비교.html  [ 완 ]
            8. 카드 순위 리스트.html [ 완 ]
            9. 로고.PNG [ 완 ]
            10. favicon.ico [ 완 ]
            11. 카드 상세페이지 .Html [ 완 ]
      2. 일반 : OAUTH2 로그인 페이지 , 로고 [ 제현  ]
            1. 로그인.Html [ 완 ]
            2. 에러페이지.Html
            3. 카드사.Html 
                  1. 카드사 상세페이지 .Html [ 1.카드 리스트 html [ 완 ] 참고 ]     
                
      4. 관리자 ,  1:1상담 게시판 [ 강현 ]
            1. 문의게시판 목록.html [ 완 ]
            2. 문의게시판 작성.html [ 완 ]
            3. 문의게시판 조회.html [ = 답변 ]  [ 완 ]
            4. 문의게시판 수정.html [ 완 ]
            5. 관리자 페이지 / 카드목록.HTML [ 완 ] 
            6. 관리자 : 카드사 목록.Html [ 완 ]
            7. 관리자 : 카드사 등록.Html  [ 완 ]
            8. 관리자 : 카드사 수정.Html [ 완 ]
            9. 관리자 : 카드 목록.Html  [ 완 ]
            10. 관리자 : 카드등록 .html [ 완 ]
            11. 관리자 : 카드수정 .html [ 완 ]

            
7. 백엔드[ spring 구현 ] 
      1. 카드 API
            1. entity
            2. repository
            3. service [ 메소드 선언만 ]
            4. dto [ dto , update ] 
            5. controller [ GetMapping ]  
            6. 
      2. user( OAUTH2 ) API
            1. entity
            2. 
            3. 
            4. 
            5. controller [ GetMapping ]  
            6. 
      3. 카드사( OAUTH2 ) API
            1. entity
            2. repository
            3. service [ 메소드 선언만 ]
            4. dto [ dto , update ] 
            5. controller [ GetMapping ]  
            6. 
      4. 게시판 API
            1. entity [ board , reply ] 
            2. repository [ board , reply ] 
            3. service [ 메소드 선언만 ]
            4. dto [ dto , update , reply ] 
            5. controller [ GetMapping ]  
           
           
8. 참고 사이트 
      1. 로고PNG  [ https://hatchful.shopify.com/ ]
      2. ico 변환 [ https://www.hipdf.com/kr/png-to-ico ]
      3. 스프링부트 파일 업로드 [ https://velog.io/@2yeseul/Spring-Boot-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C ]


9. 프론트엔드(HTML) <---[Dto]---> controller(제어) <---[Dto]----> service(로직)  <---[Dto]---> Repository(JPA) <---[entity]---> DB[h2]


10. oauth2 순서  oauth2  [ https://bamdule.tistory.com/62 ]
	1. SNS API 신청 
	2. application-oauth.properties : SNS 클라이언트 정보 입력 
	3. OAuth2UserService
		loadUser 재정의 
		1. 클라이언 정보 id 가져오기
		2. 요청한 클라이언트의 Attribute 가져오기
		3. 요청한 클라이언트의 인증키 가져오기 
 		4. OauthAttributes :  dto 넣기 
		6. db에 넣기 		
		7. 세션에 넣기 


------------------------------------------------------------------------------------------

1. 업로드 했을경우 사진 리로드 안되는 현상  [ https://lhb0517.tistory.com/entry/SpringIntelliJ-SpringBoot-HotSwap-with-IntelliJ ] [ 완 ] 
2. 로고 변경 [ 완 ] 
3. 오류 페이지 변경 [ 완 ] 
4. 관리자페이지 메뉴 링크 적용과 상단으로  [ 강 ] 
5. 메뉴 회원 버튼 [ 완 ] 
6. 작성일 날짜 출력 수정 [ 강 ] 
7. 푸터 와 본문 거리[ 완 ] 
8. 메뉴마다 body 크기 바뀌는 문제 


--------------------------월---------------------------
	
	1. 카드사 , 카드 수정 	[ 완 ]
	2. 카드 TOP 10 페이지 [ 완 ]

	1. 답변 출력  [ 완 ]
	2. 답변 삭제 [ 완 ]
	3. 답변 수정 [ 완 ]

---------------------------화---------------------------

	1.카드 상세 페이지  [ 완 ]
	2.카드 비교  [ 완 ] 

	1.게시판 페이징 [ 완 ] 
	
	
---------------------------수----------------------------

	1. 카드 검색 
	2. 카드 신청시 조회수 증가

	1.내가문의한 글보기 버튼  [ 완 ] 

---------------------------목----------------------------

	1. 카드사 프론트엔드

	1. 답글 없을경우 삭제 
	2. 관리자 메뉴 상단으로 
	3. 

---------------------------금----------------------------

	1. PPT 제작
	2. 배포 

---------------------------월----------------------------

	1. 수정 / 보안
	2. 발표 
