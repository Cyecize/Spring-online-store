$(function () {
    var orderButtons = $('.buy-product-btn');

    ordersController.updateCartIcon("#shoppingCartSizeCount");

    orderButtons.on('click', function () {
       var prodId = $(this).attr('prodId');
        ordersController.addToCartRequest(prodId, 1);
    });
});


var ordersController = (function () {
    var iconId = "#shoppingCartSizeCount";

    function updateCartIcon(icon) {
        icon = $(icon);
        icon.hide();
        $.ajax({
            method:"GET",
            url:"/cart/size",
            success:function (data) {
                var size = Number(data['body']['size']);
                if(size > 0){
                    icon.show();
                    icon.text(size);
                }
            }
        });
    }

    function addToCartRequest(productId, quantity) {
        var orderObj = {
            productId:productId,
            quantity:quantity
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
                updateCartIcon(iconId);
            },
            error:console.error
        });
    }

    function removeFromCartRequest(productId) {
        $.ajax({
            method:"GET",
            url:"/cart/remove/"+productId,
            success:function (data) {
                console.log(data);
                location.reload();
            }
        });
    }

    return{
        updateCartIcon: updateCartIcon,
        addToCartRequest: addToCartRequest,
        removeFromCartRequest: removeFromCartRequest,
    };
})();