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
            4. 카드 차트.html
            5. 조건 검색.html
            6. 카드 비교.html
            7. 카드사.html
            8. 로고.PNG [ 완 ]
            9. favicon.ico
      2. 일반 : OAUTH2 로그인 페이지 , 로고 [ 제현  ]
            1. 로그인.html [ 완 ]
            2. 
      4. 관리자 : 1:1상담 게시판 [ 강현 ]
            1. 목록.html [ 완 ]
            2. 작성.html [ 완 ]
            3. 조회.html [ = 답변 ]  [ 완 ]
            4. 수정.html [ 완 ]
            5. 관리자 페이지.HTML
            6. 관리자 : 카드사 목록.Html
            7. 관리자 : 카드사 등록.Html
            8. 관리자 : 카드사 수정.Html
            9. 관리자 : 카드 목록.Html
            10. 관리자 : 카드등록 .html
            11. 관리자 : 카드수정 .html
           
            
      

7. 백엔드[ spring 구현 ] 
      1. 제품 API
      2. OAUTH2 API
      3. 게시판 API 


8. 참고 사이트 
      1. 로고PNG  [ https://hatchful.shopify.com/ ]
      2. ico 변환 [ https://www.hipdf.com/kr/png-to-ico ]





