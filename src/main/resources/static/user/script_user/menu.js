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
            var amount=data.length;
            console.log(amount);
           $.each(data,function (i,item){

               $('#list-trademark-test').append(
                   '<div class="right-mega col-xs-3">'+
                       '<div class="mega-menu-list">'+
                           '<ul>'+
                               '<li><a href="">'+ item.name +'</a></li>'+
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