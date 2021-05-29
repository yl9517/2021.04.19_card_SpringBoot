$(document).ready(function(){
    $("#submitBtn").on('click', dosubmit);

    function dosubmit() {
        if($("#bbsTitle").val()==''){
            alert("제목이 비어있습니다.");
            return false;
        } else if( $("#bbsContent").val()=='') {
            alert("내용이 비어있습니다.");
            return false;
        } else {
            $("#form").first().submit();
        }
    }
});

