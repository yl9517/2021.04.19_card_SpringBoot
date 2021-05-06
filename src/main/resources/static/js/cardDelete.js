$(document).ready(function () {

    var id = $("#id").val();

    $("#deleteBtn").click(function(){
        $.ajax({
            type: 'DELETE',
            url: '/admin/delete/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: id,
        }).done(function(data) {
            alert('카드 삭제되었습니다.');
            window.location.href="/admin";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    });

});
