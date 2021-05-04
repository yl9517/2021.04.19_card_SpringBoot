${document}.ready(function(){ //해당 문서
    $("#registerbutton").on('click', dosubmit);

    function dosubmit(){

        if($("#cardPhonto").val()==''){//카드이미지가 비어있으면
            alert("카드 이미지가 비어있습니다.")
            return false;
        }
        else if(){
        }
        else{
            $("#form").first().submit();
        }
    }

})