

$(document).ready(function () {

    var id = $("#id").val();

    $("#reply_update_div").hide();
    $("#do_reply_updateBtn").hide();
     $("#reply").hide(); //js 시작 시 reply form 숨기기
        $(".spc").hide(); //form 아래 버튼 숨기기

        $("#replyBtn").click(function(){
            $("#deleteBtn").hide();
            $("#replyBtn").hide();
            $("#reply").show(); //reply form 표시
            $(".spc").show();   //form 아래 버튼표시

        });

    $("#deleteBtn").click(function(){
        $.ajax({
            type: 'DELETE',
            url: '/post/delete/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: id,
        }).done(function(data) {
            alert('글이 삭제되었습니다.');
            window.location.href="/boardlist_page";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    });

 $("#reply_submit").click(function(){

        let bbsID = $("#id").val();
        const data = {
            reply_content: $("#send_reply").val()
        };

        $.ajax({
            type: 'POST',
            url: '/post/reply/'+bbsID,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data){
            alert('답변 완료');
            window.location.href = window.location.href;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    });

});
