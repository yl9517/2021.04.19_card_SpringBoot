
$(document).ready(function(){
     $('#man').click(function(){
            var 변수명 = 'man';
            var box1 = document.getElementById("woman");
            var box2 = document.getElementById("man");

                //값넣기
            $('input[name=ingender]').attr('value',변수명); //attr : 선택한 요소의 속성값 가져오기
                                         //id,attr("속성명") : 해당 id의 속성가져오기
                                         //id,attr("속성명",값) : 해당 id의 속성의 값 가져오기

            //색변경
            box2.style.backgroundColor = "red";
            box1.style.backgroundColor = "rgb(239,239,239)";
        });


    $('#woman').click(function(){
            var 변수명 = 'woman';
            var box1 = document.getElementById("woman");
            var box2 = document.getElementById("man");

            $('input[name=ingender]').attr('value',변수명); //attr : 선택한 요소의 속성값 가져오기

            box1.style.backgroundColor = "red";
            box2.style.backgroundColor = "rgb(239,239,239)";
    });
});
