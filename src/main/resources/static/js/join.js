function join() {
    const empNo = $("#empNo").val();
    const id =$("#id").val();
    const password = $("#password").val();

    if(!id || !empNo || !password){
        alert("누락된 정보가 있습니다")
        return;
    }

    if(id.length<4){
        alert("아이디는 4글자 이상으로 생성해 주세요");
        return;
    }

    const reg = /^[A-Za-z0-9]{6,12}$/;
    if(!reg.test(password) ) {
        alert("패스워드는 6~12자리 이내, 하나 이상의 문자와 하나의 숫자를 포함해서 생성해 주세요");
        return;
    }


    // const idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
    // if( !idReg.test(id)){
    //     alert("아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.");
    //     return;
    // }
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
        alert("회원가입 성공");
        location.href="/login";
    }).fail(function (err){
        err.responseJSON && alert(err.responseJSON.message);
    });
}

function sendKeyByEmail(){

    const empNo = $("#empNo").val();
    if(!empNo){
        alert("값을 입력해 주십시오");
        return;
    }

    $.ajax({
        type : "POST",
        url : "/account/send-identification-email",
        data :{
            empNo : empNo
        }
    }).done(function(response){
        alert("발송이 완료되었습니다.");
        $("#checkKeyForm").show();
        $("#empNo").attr("readonly",true);
        $("#sendEmailButton").attr("disabled", true);
    }).fail(function (err){
        err.responseJSON && alert(err.responseJSON.message);
    });

}

function checkKey(){
    const empNo = $("#empNo").val();
    const key = $("#key").val();

    $.ajax({
        type : "GET",
        url : "/account/validate-identification-email",
        data :{
            empNo : empNo,
            key : key
        }
    }).done(function(response){
        if(response){
            alert("인증 완료")
            $("#keyCheckButton").attr("disabled", true);
            $("#checkKeyForm").hide();
        }else{
            alert("인증 실패")
        }
    }).fail(function (err){
        err.responseJSON && alert(err.responseJSON.message);
    });
}

function checkDuplicateId(){
    const id = $("#id").val();

    $.ajax({
        type : "GET",
        url : "/account/duplicate-id",
        data :{
            id : id
        }
    }).done(function(response){
        if(response){
            if(id.length > 3){
                $("#duplicateMsg").removeClass()
                    .addClass("text-success")
                    .text("사용할 수 있는 아이디입니다");
            }else{
                $("#duplicateMsg").removeClass()
                    .addClass("text-danger")
                    .text("아이디는 4글자 이상으로 생성해 주세요");
            }
        }else{
            $("#duplicateMsg").removeClass()
                .addClass("text-danger")
                .text("사용할 수 없는 아이디입니다");
        }
    }).fail(function (err){
        err.responseJSON && alert(err.responseJSON.message);
    });

}

$(document).ready(function() {
    $("#id").focusout(function() {
        checkDuplicateId();
    });
});