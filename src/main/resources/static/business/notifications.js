var notificationIcon = document.getElementById('notificationsBtn');

var NotificationModalManager = (function () {
    var notificationBar = document.getElementById('hidden-noti-bar');

    //private methods
    function hasClickedOnParentChild(children, event) {
        var isCl = false;
        for (var i = 0; i < children.length; i++) {
            if (children[i] == event.target)
                isCl = true;
        }
        return isCl;
    }

    function fillNotificationBarWithData(htmlData) {
        var a = $(notificationBar).text().trim();
        var b = $(htmlData).text().trim();
        if (a.localeCompare(b) !== 0)
            $(notificationBar).html(htmlData);
        //attachDynamicEvents();
    }

    //public methods
    function removeNotification(id) {
        $.ajax({
            type: "POST",
            url: "/notifications/remove/"+id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
            },
            success: fillNotificationBarWithData,
            error: console.error
        });
    }

    function viewNotification(id) {
        $.ajax({
            type: "POST",
            url: "/notifications/view/"+id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
            },
            success: fillNotificationBarWithData,
            error: console.error
        });
    }

    function showOrHideForm() {
        if (window.innerWidth < 991) {
            location.href = "/user/notifications/mobile";
            return;
        }
        if ($(notificationBar).css('display') == "none")
            $(notificationBar).show(100);
        else
            $(notificationBar).hide(100);
    }

    function onDocumentClick(event) {
        if(notificationBar == null) return;
        if (event.target !== notificationBar && event.target !== notificationIcon && !hasClickedOnParentChild(notificationBar.getElementsByTagName('*'), event))
            $(notificationBar).hide(100);
    }

    function update() {
        $.ajax({
            type: "POST",
            url: "/notifications/request",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
            },
            success: fillNotificationBarWithData,
            error: console.error
        });
    }

    function removeAllNotifications() {
        $.ajax({
            type: "POST",
            url: "/notifications/remove-all",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
            },
            success: function (data) {
                fillNotificationBarWithData(data);
            },
            error: console.error
        });
    }

    return {
        showOrHideForm: showOrHideForm,
        onDocumentClick: onDocumentClick,
        update: update,
        removeAllNotifications: removeAllNotifications,
        removeNotification: removeNotification,
        viewNotification:viewNotification,
    };
})();

$(notificationIcon).on('click', NotificationModalManager.showOrHideForm);
document.addEventListener('click', NotificationModalManager.onDocumentClick);
NotificationModalManager.update();
var clock = setInterval(NotificationModalManager.update, 4000);
