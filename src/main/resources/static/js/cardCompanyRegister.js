$(document).ready(function(){ //해당 문서
    $("#registerbutton").on('click', dosubmit);

    function dosubmit(){




        if($("#companyLogo").val()==''){//카드이미지가 비어있으면..? => 아님. 명령어 바꿀것
            alert("카드사 이미지가 비어있습니다.")
            return false;
        }
        else if($("#companyName").val()==''){
            alert("카드사명을 입력해주세요.")
            return false;


        }else{
            $("#form").first().submit();
        }



    }

});