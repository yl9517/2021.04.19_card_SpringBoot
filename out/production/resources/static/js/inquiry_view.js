$(document).ready(function () {

    var id = $("#id").val();

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

});
