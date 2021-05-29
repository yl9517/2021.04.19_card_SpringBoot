$(document).ready(function () {

        $("#submitBtn").click(function(){

            var data={
                bbsId : $("#id").val(),
                bbsTitle:$("#bbsTitle").val(),
                bbsContent:$("#bbsContent").val()
            };

            $.ajax({
                type: 'PUT',
                url: '/post/update/'+data.bbsId,

                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)

            }).done(function() {
                alert('글이 수정되었습니다.');
                window.location.href="/boardlist_page";
            }).fail(function(error) {
                alert(JSON.stringify(error));
            });
        });
});
