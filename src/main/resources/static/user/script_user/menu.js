
//Tạo thông báo
function createAlert(title, summary, details, severity, dismissible, autoDismiss, appendToId) {
    var iconMap = {
        info: "fa fa-info-circle",
        success: "fa fa-thumbs-up",
        warning: "fa fa-exclamation-triangle",
        danger: "fa ffa fa-exclamation-circle"
    };

    var iconAdded = false;

    var alertClasses = ["alert", "animated", "flipInX"];
    alertClasses.push("alert-" + severity.toLowerCase());

    if (dismissible) {
        alertClasses.push("alert-dismissible");
    }

    var msgIcon = $("<i />", {
        "class": iconMap[severity] // you need to quote "class" since it's a reserved keyword
    });

    var msg = $("<div />", {
        "class": alertClasses.join(" ") // you need to quote "class" since it's a reserved keyword
    });

    if (title) {
        var msgTitle = $("<h4 />", {
            html: title
        }).appendTo(msg);

        if(!iconAdded){
            msgTitle.prepend(msgIcon);
            iconAdded = true;
        }
    }

    if (summary) {
        var msgSummary = $("<strong />", {
            html: summary
        }).appendTo(msg);

        if(!iconAdded){
            msgSummary.prepend(msgIcon);
            iconAdded = true;
        }
    }

    if (details) {
        var msgDetails = $("<p />", {
            html: details
        }).appendTo(msg);

        if(!iconAdded){
            msgDetails.prepend(msgIcon);
            iconAdded = true;
        }
    }


    if (dismissible) {
        var msgClose = $("<span />", {
            "class": "close", // you need to quote "class" since it's a reserved keyword
            "data-dismiss": "alert",
            html: "<i class='fa fa-times-circle'></i>"
        }).appendTo(msg);
    }

    $('#' + appendToId).prepend(msg);

    if(autoDismiss){
        setTimeout(function(){
            msg.addClass("flipOutX");
            setTimeout(function(){
                msg.remove();
            },1000);
        }, 5000);
    }
}






$(document).ready(
    function (){
        showMenu();
        // setTimeout(console.log('testdfdfdf'),100000);
        // console.log('testdfdfdf');
        // $('#test').append('<h1>Tdfasdfadfafd</h1>');

    }
)

function showMenu() {

    // var name = $("#name").val();
    // var age = $("#age").val();

    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "/getMenu",
        data : {
            // test:'do'
        },
        dataType : 'json',
        // timeout : 100000,
        success : function(data) {
           $.each(data,function (i,item){
               // $('#list-trademark-test').empty();
               $('#list-trademark-test').append(
                   '<div class="right-mega col-xs-3">'+
                       '<div class="mega-menu-list">'+
                           '<ul>'+
                               '<li><a href="/products?sx=1&typeP=1&typeF=3&curPage=1&id='+item.id+'">'+ item.name +'</a></li>'+
                           '</ul>'+
                       '</div>'
               )
            }
        )
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function addProInCart(type,idPro){
    var amount=$('#amount-add-cart'+idPro).val();
    if(type==1){
        amount=1;
    }
    $.ajax({
        type : "post",
        enctype: 'multipart/form-data',
        url : "/update_cart",
        data : {
            type:3,
            idPro:idPro,
            amount:amount
        },
        dataType : 'json',
        // timeout : 100000,
        success : function(data) {
            if(data.message==0){
                var totalOld = parseInt($('#total-pro-cart').attr('data-count'));
                $('#total-pro-cart').attr('data-count', totalOld+1);
            }
        },
        error : function(e) {
            $(location).attr('href', "/login.html");
            //console.log("ERROR: ", e);
            //alert("Lỗi hệ thống");
        }
    });
}

