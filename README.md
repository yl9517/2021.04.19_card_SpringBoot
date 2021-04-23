# springProject

1. 주제 : 카드사[ 신용카드 추천 사이트 ] 

2. 사이트명 : ????????
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
            1. 메뉴.html 
            2. 메인세션.html 
            3. 푸터.html [ 완 ]
      2. 일반 : OAUTH2 로그인 페이지 , 로고 [ 제현  ]
            1. 로그인.HTML
            2. 로고.PNG
            3. 회원정보.HTML
            4. 홈페이지소개.HTML
      4. 관리자 : 1:1상담 게시판 [ 강현 ]
            1. 목록.html
            2. 작성.html 
            3. 조회.html [ = 답변 ] 
            4. 수정.html







