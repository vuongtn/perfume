
//thong bao
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
        }, 3000);
    }
}






//render list thương hiệu
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
                               '<li><a href="/products?type=2&menu=2&id='+item.id+'">'+ item.name +'</a></li>'+
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

//Thêm sản phẩm vào giỏ
function addProInCart(type,idPro){
    var amount=$('#amount-add-cart'+idPro).val();
    if(amount<1){
        messageError("Thất bại!","Số lượng không hợp lệ.")
    }
    if(type==1){
        amount=1;
    }
    if(amount>0){
        $.ajax({
            type : "post",
            enctype: 'multipart/form-data',
            url : "/per/update_cart",
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
                if(data.message==10){
                    messageError("Số lượng vượt quá trong kho.")
                }
                if(data.message!=10){
                    messageSuccess("Thành công!","Thêm vào giỏ hàng thành công.")
                }
            },
            error : function(e) {
                $(location).attr('href', "/login.html");
                //console.log("ERROR: ", e);
                //alert("Lỗi hệ thống");
            }
        });
    }

}

function showBrand() {
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "/all_brand",
        data : {
            // test:'do'
        },
        dataType : 'json',
        // timeout : 100000,
        success : function(data) {
            var html='';
            $.each(data,function (i,item){
                html+=
                    '<li>'+
                    '<label class="cheker">'+
                    '<input class="input-brand" value='+item.id+' type="checkbox" name="brands" id="cbb-brand'+item.id+'"/>'+
                    '<span></span>'+
                    '</label>'+
                    '<p><label class="cbb-filter" for="cbb-brand'+item.id+'">'+item.name+'</label></p>'+
                    '</li>';
            })
            $('#all-brand-filter').html(html);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}