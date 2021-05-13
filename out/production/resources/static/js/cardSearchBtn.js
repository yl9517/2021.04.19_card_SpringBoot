 //혜택박스 배열 선언
         var beAry = [ $('input[name=inbene0]'), $('input[name=inbene1]') , $('input[name=inbene2]') ];

         // 혜택박스 값 초기화
         for(var i=0 ; i<beAry.length; i++){
                beAry[i].attr('value',"");
         }


         var gender = document.getElementsByClassName("gender");
         var age = document.getElementsByClassName("age");
         var type = document.getElementsByClassName("type");
         var bene = document.getElementsByClassName("bene");

         init();

         function init() {
            for (var i = 0; i < gender.length; i++) {
              gender[i].addEventListener("click", genderClick);
                 //addEventListener : 이벤트 등록. 특정이벤트가 발생했을 시 특정함수를 실행할수 있도록함
                                        //즉, 클릭시 handleClick 함수 실행
            }
            for (var i = 0; i < age.length; i++) {
              age[i].addEventListener("click", ageClick);
            }
            for (var i = 0; i < type.length; i++) {
              type[i].addEventListener("click", typeClick);
            }
            for (var i = 0; i < bene.length; i++) {
              bene[i].addEventListener("click", beneClick);
            }

         }

         function genderClick(event) {
              for (var i = 0; i < gender.length; i++) {
                gender[i].classList.remove("clicked"); //전체 clicked 삭제
              }
               event.target.classList.add("clicked"); //타켓에 clicked 추가

              var thisGender = event.target.value;
              $('input[name=ingender]').attr('value',thisGender); //attr : 선택한 요소의 속성값 가져오기

         }

         function ageClick(event) {
              for (var i = 0; i < age.length; i++) {
                age[i].classList.remove("clicked");
              }
              event.target.classList.add("clicked");

               var thisAge = event.target.value;
              $('input[name=inage]').attr('value',thisAge);

         }

         function typeClick(event) {
              for (var i = 0; i < type.length; i++) {
                type[i].classList.remove("clicked");
              }
              event.target.classList.add("clicked");

               var thisType = event.target.value;
              $('input[name=intype]').attr('value',thisType);
         }

         function beneClick(event) {
             if($(this).hasClass('clicked')){ //clicked 클래스를 가지고 있다면 삭제하기
                 event.target.classList.remove("clicked");

                 //삭제한 혜택의 박스값도 null
                 for(var i=0 ; i<i<beAry.length; i++){
                     if( beAry[i].attr('value') == event.target.value ){
                        beAry[i].attr('value',"");
                     }
                 }
             }
             else{
               event.target.classList.add("clicked"); //타켓에 clicked 추가

                //혜택 박스에 담기
               for(var i=0 ; i<beAry.length; i++){
                  if(beAry[i].attr('value') == ""){
                        beAry[i].attr('value',event.target.value);
                      break;
                  }
                 }
             }

             //혜택div의 자식들이 3개 이상 clicked를 가지고 있다면 더이상 추가 x
             if( $('#benefits').children('.clicked').length > 3  ){
                 alert('혜택은 최대 3개까지 선택할 수 있습니다.');
                 event.target.classList.remove("clicked");
                 return false;
             }


         }