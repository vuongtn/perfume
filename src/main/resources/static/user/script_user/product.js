
function showProductByTrademark(idTrade,typeFil,curPage) {

    // var name = $("#name").val();
    // var age = $("#age").val();

    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "/product-by-trademark",
        data : {
            idTrade:idTrade,
            typeFil:typeFil,
            curPage:curPage
        },
        dataType : 'json',
        // timeout : 100000,
        success : function(result) {
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