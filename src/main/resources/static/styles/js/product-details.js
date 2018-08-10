$(function () {
    $('.product-image').each(function (e, element) {
        element = $(element);
        var parent = element.parent();
        if(element.width() >= element.height()){
            element.width(parent.width());
            element.css('max-height', parent.height());
            element.css('max-width', '400px');
        }else {
            element.height(parent.height());
            element.css('max-width', parent.width());
            element.css('max-height', '400px');
        }
    });
});