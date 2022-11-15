function join() {
    const empNo = $("#empNo").val();
    const id =$("#id").val();
    const password = $("#password").val();

    $.ajax({
        type : "POST",
        url : "/account/join",
        contentType: "application/json",
        data : JSON.stringify({
            empNo : empNo,
            id : id,
            password : password
        })
    }).done(function(response){
        if(response){
            alert("회원가입 성공");
            location.href="login";
        }else{
            alert("회원가입 실패")
        }
    }).fail(function (err){
        console.log(err);
    });
}