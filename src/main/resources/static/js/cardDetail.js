//카드 삭제

${document}.ready(function(){

    var id = ${"#code"}.val();

    $("#deleteBtn").click(function(){
        $.ajax( {
            type : 'DELETE' ,
            url 'admin/delete'+id,
            dataType : 'application/json; charset=utf-8',
            data : id,
        }).done(function(data){
            alert("해당 카드가 삭제되었습니다.");
            window.location.href="/admin";
        }).fail(function(error){
            alert(JSON.stringify(error));
        }

    });

});