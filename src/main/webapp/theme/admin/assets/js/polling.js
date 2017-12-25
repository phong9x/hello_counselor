'use strict';

function polling() {
    $
        .ajax({
            url     : '/auth/polling',
            method  : 'get'
        })
        .then(function(res) {
            // redraw layout
            redraw(res);
            console.log(res);
            // real time popup
            showpopup(res);
            // udpate data
            updatedata(res);
        }, function(err) {
            console.error(err);
        });
}

function redraw(data) {
    if (data.schedule.status && data.schedule.data) {
    	$('#new-session').attr('read-flag', '1');
        $('#new-session').css('display', '');
    } else {
    	if($('#new-session').attr('read-flag') == '0')
    		$('#new-session').css('display', 'none');
    }

    if (data.psypayment.status && data.psypayment.data) {
    	$('#new-psypayment').attr('read-flag', '1');
        $('#new-psypayment').css('display', '');
    } else {
    	if($('#new-psypayment').attr('read-flag') == '0')
    		$('#new-psypayment').css('display', 'none');
    }

    if (data.realtime.status && data.realtime.data) {
    	$('#new-session').attr('read-flag', '1');
        $('#new-session').css('display', '');
    } else {
    	if($('#new-session').attr('read-flag') == '0')
    		$('#new-session').css('display', 'none');
    }

    if (data.message.status && data.message.data) {
    	$('#new-message').attr('read-flag', '1');
        $('#new-message').css('display', '');
    } else {
    	if($('#new-message').attr('read-flag') == '0')
    		$('#new-message').css('display', 'none');
    }
}

function remove_new_status(element) {
	notice_display = [];
	$(element).find('.new-menu').attr('read-flag','0').css('display', 'none');
	$('.new-menu').each(function(i,item){
		if($(item).attr('read-flag') == '1') {
			notice_display.push($(item).attr('id'));
		}			
	}).promise().done(function(){
		localStorage.setItem('notice_display', notice_display);
	});
	
}

function showpopup(data) {
    if (data.schedule.status) {
        $('#popup-schedule')
            .off('click', '.popup_accept')
            .on('click', '.popup_accept', function(evt) {
                window.location.href = '/auth/reservation/detail?sessionId=' + data.schedule.data.sessionId;
            });
        $('#popup-schedule').modal('show');
    }
    if (data.psypayment.status) {
        $('#popup-psypayment')
            .off('click', '.popup_accept')
            .on('click', '.popup_accept', function(evt) {
                window.location.href = '/auth/psychologicaltest/detail?id=' + data.psypayment.data.psyId;
            });
        $('#popup-psypayment')
            .find('.member-info').text(data.psypayment.data.member);
        $('#popup-psypayment').modal('show');
    }
    if (data.realtime.status) {
        $('#popup-realtime')
            .off('click', '.popup_accept')
            .on('click', '.popup_accept', function(evt) {
                window.location.href = '/auth/reservation/detail?sessionId=' + data.realtime.data.sessionId;
            });
        $('#popup-realtime').modal('show');
    }
    
    if (data.message.status) {
        $('#popup-message')
            .off('click', '.popup_accept')
            .on('click', '.popup_accept', function(evt) {
                window.location.href = '/auth/message/list';
            });
        $('#popup-message')
            .find('.member-info').text(data.message.data.member);
        $('#popup-message').modal('show');
    }
}

function updatedata(data) {
    if (!localStorage.getItem('setting')) {
        localStorage.setItem('setting', JSON.stringify(data));
    } else {
        var setting = JSON.parse(localStorage.getItem('setting'));
        if (data.schedule.status) {
            setting.schedule.status = true;
        }
        if (data.psypayment.status) {
            setting.psypayment.status = true;
        }
        if (data.realtime.status) {
            setting.realtime.status = true;
        }
        if (data.message.status) {
            setting.message.status = true;
        }
        localStorage.setItem('setting', JSON.stringify(setting));
    }
}
var notice_display = [];

function init() {

    var setting;
    if(localStorage.getItem('notice_display') !==null && localStorage.getItem('notice_display') !='') {
    	notice_display = localStorage.getItem('notice_display');
    }else {
    	localStorage.setItem('notice_display', notice_display);
    }
    if(notice_display.length) {
    	notice_display = notice_display.split(',');
    	 $(notice_display).each(function(i, item){
    	    	$('#'+item).attr('read-status','0').css('display', '');
    	    });
    }
    console.log(typeof(notice_display));
    if (!localStorage.getItem('setting')) {
        setting = {
            realtime: {
                status: false
            },
            psypayment: {
                status: false
            },
            schedule: {
                status: false
            },
            message: {
                status: false
            }
        }
    } else {
        setting = JSON.parse(localStorage.getItem('setting'));
        if (location.pathname.startsWith('/auth/reservation/detail?sessionId=')) {
            setting.schedule.status = false;
            setting.realtime.status = false;
        } else if (location.pathname.startsWith('/auth/psychologicaltest/detail?id=')) {
            setting.psypayment.status = false;
        } else if (location.pathname.startsWith('/auth/message/list')) {
            setting.message.status = false;
        }
    }

    localStorage.setItem('setting', JSON.stringify(setting));
    redraw(setting);

    setInterval(polling, 5000);
}

$(document).ready(init);



