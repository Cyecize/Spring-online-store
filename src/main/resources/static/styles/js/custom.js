$(function () {

    /**
     * TABLE OF CONTENT
     *
     * 1 -> Init navbar
     *
     * 2 -> BACK TO TOp
     *
     * 3 -> init slider
     */

    initNavbar();
    backToTop();
    initSlider();

    //1 -> INIT navbar
    function initNavbar() {
        var navbar = $('#navbar-main');
        var navbarUnderLayer = $('#navbar-under-layer');

        var navbarButtons = navbar.find('button');
        var navbarInputs = navbar.find('input');

        var widthBreakpoint = 991;

        configNavbarPos();
        $(window).on('resize', function () {
            configNavbarPos();
        });

        $(window).scroll(function () {
            configNavbarSize();
        });

        function configNavbarPos() {
            if ($(document).width() <= widthBreakpoint) {
                navbar.removeClass('fixed-top');
                navbarUnderLayer.hide();
            } else {
                navbar.addClass('fixed-top');
                navbarUnderLayer.show();
            }
        }

        function configNavbarSize(){
            if ($(document).scrollTop() > 50 && $(document).width() > widthBreakpoint) {
                navbar.addClass('shrink');
                navbarInputs.addClass('form-control-sm');
                navbarButtons.addClass('btn-sm');
            } else {
                navbar.removeClass('shrink');
                navbarInputs.removeClass('form-control-sm');
                navbarButtons.removeClass('btn-sm');
            }
        }


    }

    // END INIT Navbar fixed or normal position

    /*
        2 BACK TO TOP
     */
    function backToTop() {

        var btn = $('<a id="back-to-top" href="#" class="btn btn-primary back-to-top waves-effect waves-light d-none ">');
        btn.append($('<i class="fa fa-chevron-up">'));
        $('body').append(btn);

        configureBtnVisibility();


        $(window).scroll(function () {
            configureBtnVisibility();
        });
        // scroll body to 0px on click
        btn.click(function () {
            $('#back-to-top').tooltip('hide');
            $('body,html').animate({
                scrollTop: 0
            }, 800);
            return false;
        });

        //btn.tooltip('show');

        function configureBtnVisibility() {
            if ($(document).scrollTop() > 50) {
                btn.fadeIn();
                btn.removeClass('d-none');
            } else {
                btn.fadeOut();
            }
        }
    }

    /*
    end BACK TO TOP
     */

    /* 3 init slider */
    function initSlider() {
        adjust();
        window.onresize = adjust;

        function adjust() {
            var docHeight = $(window).height();
            $('.carousel-home-item').each(function (e, t) {
                $(t).height(docHeight - 100);
            })
        }
    }
    /* !init slider */

});