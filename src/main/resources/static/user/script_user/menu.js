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
            console.log("ERROR: ", e);
            alert("Lỗi hệ thống");
        }
    });
}

