$(function () {
    initImagePosition();
    initGalleryLayout();

    $(window).on('resize', initImagePosition);

   function initGalleryLayout(){
       var IMAGE_HIGHLIGHT = $('#productHighlightedPicture');
       var ITEMS = $('.gallery-item');
       initGallery();


       ITEMS.find('img').on('click',function () {
           clearGallery();
           var parent = IMAGE_HIGHLIGHT.parent();
           parent.html('');
           IMAGE_HIGHLIGHT = $('<img id="productHighlightedPicture" src="'+ $(this).attr('src') +'" class="product-image" style="height: auto;">')
           parent.html(IMAGE_HIGHLIGHT);
           initGallery();
           initImagePosition(); //RESETS new image pos
       });

       function initGallery() {
           ITEMS.each(function (i, el) {
               var img = $(el).find('img:first');
               if(img.attr('src') === IMAGE_HIGHLIGHT.attr('src')){
                   img.parent().addClass('active-image');
               }
           });
       }

       function clearGallery() {
           ITEMS.each(function (i, el) {
               $(el).removeClass('active-image');
           });
       }
   }


   function initImagePosition() {
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
   }
});