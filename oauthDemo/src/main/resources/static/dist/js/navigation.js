$().ready(function () {
    doPollServer();
});

function handlePollSuccess(result){
    if(result){
        $('#notificationDropDown').empty();
        $.each(result, function () {
            var Group = this.name
            var item = '<a href="#"><div><i class="fa fa-comment fa-fw"></i>Sie wurden eingeldaden der '+Group+' beizutreten!<span class="pull-right text-muted small"></div> </a>'
            $('#notificationDropDown').append('<li>'+item+'</li>');
        });
    }
}

function doPollServer(){
    $.ajax({
        url: "/data/shareGroups/open",
        type: "GET",
        success: function (result) {
            handlePollSuccess(result);
            pollServer();
        },
        error: function () {
            pollServer();
        }
    });
}

function pollServer(){
    window.setTimeout(function () {
        doPollServer();
    }, 100000);
}