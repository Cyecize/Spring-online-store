$(function () {
    var orderButtons = $('.buy-product-btn');
    var cartSizeIcon = $('#shoppingCartSizeCount');

    updateCartIcon(cartSizeIcon);

    orderButtons.on('click', function () {
       var prodId = $(this).attr('prodId');
       var orderObj = {
           productId:prodId,
           quantity:1
       };

       $.ajax({
           method:"POST",
           url:"/cart/add",
           beforeSend: function (xhr) {
               xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
           },
           data:orderObj,
           success:function (data) {
               console.log(data);//TODO make some kind of modal to show info
               updateCartIcon(cartSizeIcon);
           },
           error:console.error
       });
    });
});

function updateCartIcon(icon) {
    icon = $(icon);
    icon.hide();
    $.ajax({
        method:"GET",
        url:"/cart/size",
        success:function (data) {
            var size = Number(data['body']['size']);
            console.log(data);
            if(size > 0){
                icon.show();
                icon.text(size);
            }
        }
    })
}