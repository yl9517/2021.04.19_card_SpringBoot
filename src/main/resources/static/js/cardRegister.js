$(document).ready(function(){ //해당 문서
    $("#registerbutton").on('click', dosubmit);

    function dosubmit(){




        if($("#cardPhoto").val()==''){//카드이미지가 비어있으면..? => 아님. 명령어 바꿀것
            alert("카드 이미지가 비어있습니다.")
            return false;
        }
        else if($("#cardName").val()==''){
            alert("카드명을 입력해주세요.")
            return false;


        }else if($("#annualFee").val().length==0|| isNaN( $("#annualFee").val() )  ){

            alert("연회비 입력을 제대로 해주세요.")
            return false;

        }else if($("#beforePay").val().length==0|| isNaN( $("#beforePay").val() ) ){

            alert("전월실적 입력을 제대로 해주세요.")
            return false;

        }else if($("#cardLink").val()==''){

            alert("카드 링크를 입력해주세요.")
            return false;

        }else if($("#a option:selected").val()==''){

            alert('혜택 1을 골라주세요')
            return false;

        }else if($("#benefit1_detail").val()=='') {

            alert('혜택 1 상세내용을 입력해주세요')
            return false;

       }else if($("#b option:selected").val()==''){

            alert('혜택 2를 골라주세요')
            return false;

        }else if($("#benefit2_detail").val()==''){

                alert('혜택 2 상세내용을 입력해주세요')
                return false;

        }else if($("#c option:selected").val()==''){

            alert('혜택 3을 골라주세요')
            return false;
        }else if($("#benefit3_detail").val()==''){

                   alert('혜택 3 상세내용을 입력해주세요')
                   return false;

        }else{
            $("#form").first().submit();
        }



    }

});