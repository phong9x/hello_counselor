'use strict';

(function() {

    // init page
    $(document).ready(function() {
        // menu file toggle
        $(".icon-next a, #btn_doc").on("click", function(evt){
            $('body.face-talk').toggleClass('face-talk-menu-right');
        });

        // resize talk screen
        resizeTalkScreen();

        $("#owl_memo").owlCarousel({
            singleItem: true,
            pagination: true,
            autoPlay: false,
            stopOnHover:true,
            navigation: true,
            addClassActive: true
        });
    });


    var video       = {};
    video.width     = 640;
    video.height    = 480;

    var deviceSetting   = {};
    deviceSetting.video = video;

    var settings            = {};
    settings.deviceSetting  = deviceSetting;

    var option              = {};
    option.serviceServerUrl = 'https://facetalk.hellowithyou.com:8443';
    option.settings         = settings;

    var GM  = new GooroomeeMeeting(option);

    var tick;

    // handle upload file
    var tpl_file_upload_source   = $("#tpl_file_upload").html();
    var tpl_file_upload_template = Handlebars.compile(tpl_file_upload_source);

    var tpl_file_thumb_source    = $("#tpl_file_upload_thumb").html();
    var tpl_file_thumb_template  = Handlebars.compile(tpl_file_thumb_source);

    var disconnectCount = parseInt(DISCONNECT_COUNT);
    var hasConnectionProblem = false;
    
    var SESSION_LENGTH  = Number.parseInt(SESSION_TIME) * 60;
        
    var pcTime; 
    var serverTime;
    
    var userId;
    
    joinRoom(0);   
    getCurrentTime();
    
    function getCurrentTime(){
    	$
    	.ajax({
    		url     : '/v1/public/rtc/current',
    		method  : 'get'
    	})
    	.then(function(response) {
    		pcTime = new Date().getTime(); 
    		serverTime = response.data.current;
    		initCounseling();
    	}, function(error) {
    		console.log(error);
    		getCurrentTime();
    	});
    }
    
    function initCounseling(){
    	var passTime = new Date().getTime() - pcTime;
    	var serverNow = serverTime + passTime;
    	var delta_time = serverNow - START_TIME;
    		
    	if (delta_time < 0) {
            var timeout = (0 - delta_time);
            setTimeout(initRoom, timeout);
        } else {
        	initRoom();
        }
    }
    
    function joinRoom(type) {
        $
            .ajax({
                url     : '/auth/talk/join',
                method  : 'get',
                data    : {
                    roomId  : ROOM_ID,
                    type    : type
                }
            })
            .then(function(response) {

            }, function(error) {

            });
    }
    
    function joinRoomAgain() {
        joinAgain({
                'counselingSessionId'  : parseInt(SESSION_ID),
                'disconnectCount'      : parseInt(disconnectCount)
            }, function(res) {
                location.href = "/auth/talk?id=" + res.session.id;        
            }, function(error) {
            	console.error(error);
            });
    }
    
	function joinAgain(params, success, failure) {
		$
			.ajax({
                url         : '/auth/talk/room',
                method      : 'post',
                data        : params,
                beforeSend  : toggleLoadingDisconnect
			})
			.then(function(res) {
				toggleLoadingDisconnect();
                if (success) success(res);
			}, function(error) {
				toggleLoadingDisconnect();
				if (failure) failure(error);
			});
	}
	
	var isTimeout = true;
	function finishCauseTimeout(){
		if(isTimeout){
			// disconnect mobile
			GM.leave();
			// show disconnected popup
			$('#popup_finish_error').modal('show');
			// call api finish
			finishMeeting(resetMeeting);
			$('#ajax_loading').css('display', 'none');
		}
    }
	
	function countInitTime(){
		var timeout = 120000;
		isTimeout = true;
		setTimeout(finishCauseTimeout, timeout);
	}
	
    function initRoom() {
        getOtp({
            'roomId'    : ROOM_ID,
            'username'  : COUNSELOR_NAME.trim()
        }, function(res) {
        	toggleLoading();
    		countInitTime();
        	//init start
            GM.init().then(function() {
                // join room
                GM.join({
                	otp         : res.roomUserOtp.otp,
                    reqInfoList : ['roomUser', 'roomDoc']
                }, function(room, me) {
                	toggleLoading();
                	isTimeout = false;
                	joinRoom(0);
                    // countdown timer
                    if (tick == undefined) {
                        if (COUNSEL_TYPE == '2') {
                        	renderRemainingClock();
                        } 
                    }

                    // bind upload function
                    $('#btn_upload').on('click', function(evt) {
                        $('#inpUpload').trigger('click');
                    });

                    $('#inpUpload').on('change', function(evt) {
                        GM.Document.upload(evt.target.files, {
                            start: function(file) {

                            },
                            progress: function(file, loaded, total) {
                                console.log(loaded/total * 100);
                            },
                            error: function(error) {
                                console.error(error);
                            },
                            complete: function() {
                                console.log('completed');
                            }
                        });
                    });

                    if (room.roomDocInfo) {
                        if (room.roomDocInfo.roomDocList) {
                            var roomDocList = room.roomDocInfo.roomDocList;

                            for (var i = 0, n = roomDocList.length; i < n; i++) {
                                var roomDoc = roomDocList[i];
                                if (roomDoc.docId == 'whiteboard') continue;
                                (function(roomDoc){
                                    GM.Document.getRoomDocThumbnailImage(roomDoc.docId).then(function(img) {
                                        var html = tpl_file_upload_template({
                                            fileName : roomDoc.filename,
                                            docId    : roomDoc.docId,
                                            fileSrc  : $(img).attr('src')
                                        });

                                        $('#doc_file').append(html);
                                    });
                                })(roomDoc);
                            }

                        }
                    }
                    
                    if(room.roomUserList){
                    	 var roomUserList = room.roomUserList;
                         for (var i = 0, n = roomUserList.length; i < n; i++) {
                        	 var roomUser = roomUserList[i];
	                 		 if(!GM.isMe(roomUser)){
	                 			 userId = roomUser.userId;
	                 		 }
                         }
                    }

                }, function(code, data) {
                	toggleLoading();
                    console.error(code);
                    console.error(data);
                })

                // init doc share
                GM.Document.init('#load-file', true);
            })
            .catch(function(error) {
            	toggleLoading();
            	console.error(error);
            });
            //init end
        }, function(error) {
        	console.error(error);
        });
    }

	function getOtp(params, success, failure) {
		$
			.ajax({
                url         : '/auth/otp',
                method      : 'post',
                data        : params,
                beforeSend  : toggleLoading
			})
			.then(function(res) {
			    toggleLoading();
                if (success) success(res);
			}, function(error) {
			    toggleLoading();
				if (failure) failure(error);
			});
	}
	
    function toggleFullScreen() {
        if ((document.fullScreenElement && document.fullScreenElement !== null) ||
            (!document.mozFullScreen && !document.webkitIsFullScreen)) {
                if (document.documentElement.requestFullScreen) {
                    document.documentElement.requestFullScreen();
                } else if (document.documentElement.mozRequestFullScreen) {
                    document.documentElement.mozRequestFullScreen();
                } else if (document.documentElement.webkitRequestFullScreen) {
                    document.documentElement.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
                }
        } else {
            if (document.cancelFullScreen) {
                document.cancelFullScreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if (document.webkitCancelFullScreen) {
                document.webkitCancelFullScreen();
            }
        }
    }

    function resizeTalkScreen() {
        var heightBody = $(window).height() - 320;
        $('.face-video').css({"height": heightBody});
    }

    var finished = false;
    
    function renderRemainingClock() {
    	
        var remainingTime = 0;
        var passTime = new Date().getTime() - pcTime;
    	var serverNow = serverTime + passTime;
    	console.log('serverNow:' + serverNow);
        if(COUNSEL_TYPE == '1'){
        	if(END_TIME == null || END_TIME == ''){ //initial
        		remainingTime = SESSION_LENGTH;
        	}else{ //reconnect, calculate after converting minute to sync with app 
        		console.log('END_TIME:' + END_TIME);
        		remainingTime = parseInt((Number(END_TIME) - serverNow) / 1000);
        	}
        }else{
        	var deltaTime = parseInt((serverNow - Number(START_TIME)) / 1000);
        	console.log('deltaTime:' + deltaTime);
        	remainingTime = SESSION_LENGTH - deltaTime;
        }
        console.log('remainingTime:' + remainingTime);
        setTick(remainingTime);
    }
    
    function setTick(remainingTime){
    	var $minute = $('#minute');
        var $second = $('#second');
        var minute,second;
        
        tick = setInterval(function() {
        	remainingTime -= 1;
            if (remainingTime >= 0) {
                minute = parseInt(remainingTime / 60);
                second = parseInt(remainingTime - (minute * 60) );

                $minute.text(minute < 10 ? "0" + minute : minute);
                $second.text(second < 10 ? "0" + second : second);
            } else {
                clearInterval(tick);
                if (GM) {
                    // disconnect mobile
                    GM.leave();
                    // show disconnected popup
                }
                finished = true;
                // call api finish
                finishMeeting(resetMeeting);
                $('#popup_finish').modal('show');
            }
        }, 1000);
    }
    
    function finishMeeting(callback) {
        $
            .ajax({
                url: '/auth/talk/finish',
                method: 'post',
                data: {
                    sessionId: parseInt(SESSION_ID)
                },
                beforeSend: toggleLoading
            })
            .then(function(res) {
//            	removeCountDisconnect(roomId);
                toggleLoading();
                callback(null, res);
            }, function(err) {
                toggleLoading();
                callback(err, null);
            });
    }

    function resetMeeting() {
        if (tick) clearInterval(tick);
        $('#me_video').removeAttr('src');
        $('#partner_video').removeAttr('src');
        $('#hour').text('00');
        $('#minute').text('00');
        $('#second').text('00');
    }
    
    GM.on('joinUser', function(roomUser) {
        console.log("===================joinUser==================");
//        var check = COUNSELOR_NAME.trim() != roomUser.nickname;
//        if (COUNSELOR_NAME.trim() != roomUser.nickname) {
        if(!GM.isMe(roomUser)){
        	userId = roomUser.userId;
            joinRoom(1);
            hasConnectionProblem = false;
        }
        console.log("===================joinUser end==================");
//        console.log("COUNSELOR_NAME.trim(): " + COUNSELOR_NAME.trim());
//        console.log("roomUser.nickname: " + roomUser.nickname);
//        console.log("check: " + check);
    });

    GM.on('leaveUser', function(roomUser, reason) {
    	console.log("===================leaveUser start==================");
    	console.log("roomUser:" + roomUser.nickname);
    	console.log("reason:" + reason);
        if (!GM.isMe(roomUser)) {
            // disconnect mobile
        	var status = getStatusGM(ROOM_ID);
        	if(status == 1 && !hasConnectionProblem && reason == "normal"){
        		 GM.leave();	
                 // show disconnected popup
                 $('#popup_finish').modal('show');
                 // call api finish
                 finishMeeting(resetMeeting);
        	}
        }
    	console.log("===================leaveUser end==================");
    });

	// handle local video streaming
    GM.on('addLocalStream', function(stream) {
        var video = document.querySelector('#me_video');
        video.src = URL.createObjectURL(stream);
        video.play();
    });

    // handle partner video streaming
    GM.on('addRemoteStream', function(roomUser, stream) {
    	if (tick == undefined) {
            renderRemainingClock();
        }
        var video = document.querySelector('#partner_video');
        video.src = URL.createObjectURL(stream);
        video.play();
    });

    // handle connection failed
    GM.on('userConnectionFailed', function(roomUser) {
        // re-connect
    	console.log("-----------userConnectionFailed---------");
    	console.log("roomId: "+ROOM_ID);
    	console.log("countDisconnect before: "+disconnectCount);
    	var checkUser = (userId == roomUser.userId);
    	
    	if(!hasConnectionProblem && !finished && checkUser){
    	    hasConnectionProblem = true;
    	    disconnectCount = parseInt(disconnectCount) + 1;
    		$('#popup_reconnect_app').modal('show');
     	}
    	console.log("countDisconnect after: "+disconnectCount);
    });

    GM.on('userConnectionDisconnected', function(roomUser) {
    	// re-connect
    	console.log("-----------userConnectionDisconnected---------");
    	console.log("roomId: "+ROOM_ID);
    	console.log("countDisconnect before: "+disconnectCount);
    	var checkUser = (userId == roomUser.userId);
    	
    	if(!hasConnectionProblem && !finished && checkUser){
    	    hasConnectionProblem = true;
    	    disconnectCount = parseInt(disconnectCount) + 1;
    		$('#popup_reconnect_app').modal('show');
    	}
    	console.log("countDisconnect after: "+disconnectCount);
    });
    
//    GM.on('serverDisconnected', function(roomUser) {
//    	console.log("Network is disconnected");
//    	console.log("__________*****_______________");
//    	var roomId = extractQueryParam('roomId');
//    	countDisconnect(roomId);
//    	var count = getCountDisconnect(roomId);
//    	console.log("-----------serverDisconnected---------");
//    	console.log("roomId: "+roomId);
//    	console.log("countDisconnect: "+count);
//    	if(count < 5){
//    		$('#popup_reconnect_web').modal('show');
//    	}else{
//    		// disconnect mobile
//    		GM.leave();
//    		 // show disconnected popup
//    		$('#popup_finish_error').modal('show');
//    		// call api finish
//    		 finishMeeting(resetMeeting);
//    	}
//    });

    GM.on('addRoomDoc', function(user, roomDoc) {
        if (roomDoc.fileExt == 'png' || roomDoc.fileExt == 'jpg') {
            GM.Document.getRoomDocThumbnailImage(roomDoc.docId).then(function(img) {
                var html = tpl_file_upload_template({
                    fileName : roomDoc.filename,
                    docId    : roomDoc.docId,
                    fileSrc  : $(img).attr('src')
                });

                $('#doc_file').append(html);
            });
        }
    });

    GM.on('addRoomDocConvertComplete', function(user, roomDoc) {
        GM.Document.getRoomDocThumbnailImage(roomDoc.docId).then(function(img) {
            var html = tpl_file_upload_template({
                fileName : roomDoc.filename,
                docId    : roomDoc.docId,
                fileSrc  : $(img).attr('src')
            });

            $('#doc_file').append(html);
        });
    });
    
    GM.on('addRoomDocConvertComplete', function(user, roomDoc) {
        GM.Document.getRoomDocThumbnailImage(roomDoc.docId).then(function(img) {
            var html = tpl_file_upload_template({
                fileName : roomDoc.filename,
                docId    : roomDoc.docId,
                fileSrc  : $(img).attr('src')
            });

            $('#doc_file').append(html);
        });
    });

    $("#partner_video").bind("loadedmetadata", function () {

        var heightbody = $( window ).height() - 160;
        var heighvideo = $('.position-center').height();

        $('.bg-video.position-center').css({"height": heightbody});
        $('.face-video').css({"height": heightbody});
    });
    
    // bind functional buttons
    //// fullscreen
    $('#btn_fullscreen').on('click', toggleFullScreen);
    $(document).on('mozfullscreenchange webkitfullscreenchange fullscreenchange', function() {
        var $spanLabel  = $('#btn_fullscreen').find('span');
        $spanLabel.text( (document.fullScreen || document.mozFullScreen || document.webkitIsFullScreen) ? '기본화면' : '전체화면');
        resizeTalkScreen();
    });

    //// mute
    $('#btn_mute').on('click', function(evt) {
        if (GM) {
            if ($(this).hasClass('talk')) {
                GM.changeDeviceStatus('mic', 'off');
                $(this).removeClass('talk');
                $(this).find('span').text('마이크 켜기');
            } else {
                GM.changeDeviceStatus('mic', 'on');
                $(this).addClass('talk');
                $(this).find('span').text('마이크 끄기');
            }
        }
    });

    //// exit
    $('#btn_exit').on('click', function(evt) {
        $('#popupLogout-facetalk').modal('show');
    });
    $('#popupLogout-facetalk').on('click', '.popup_accept', function(evt) {
        if (GM) {
        	GM.leave();
        	//finishMeeting(resetMeeting);
        }
        finishMeeting(function(err, res) {
            window.opener.closeWindow();
        });

    });

    $('#btnInfo').on('click', function() {
        window.open("/auth/talk/user_info?roomId="+ROOM_ID, '_blank', 'width=300,height=300,top=400,left=400');
    });

    $('#btnAccountPsy').on('click', function() {
        window.open("/auth/talk/account_test?roomId="+ROOM_ID, '_blank', 'width=500,height=320,top=400,left=400');
    });

    $('#btnPsyTest').on('click', function() {
    	var memoWindow = window.open("/auth/talk/psychological?roomId="+ROOM_ID, '_blank', 'width=900,height=500,top=400,left=400,scrollbars=yes');
    	
    });
    
    $('#btnMemo').on('click', function() {
    	var fullname = $('input[name=memberName]').val();
        memoWindow = window.open("/auth/talk/memoPopup?roomId="+ROOM_ID+"&fullname="+fullname, '_blank', 'width=900,height=730,top=200,left=400,scrollbars=yes');
    });

    $('[name=psyName]').on('change', function(evt) {
        var fee             = Number.parseInt(evt.target.attributes['data-fee'].value);
        var totalPsy        = Number.parseInt($('#total-psy').attr('data-value'));
        var totalMoney      = Number.parseInt($('#total-money').attr('data-value'));
        var totalVoucher    = Number.parseInt($('#total-voucher').attr('data-value'));

        if (evt.target.checked) {
            totalPsy++;
            totalMoney += fee;
            totalVoucher++;
        } else {
            totalPsy--;
            totalMoney -= fee;
            totalVoucher--;
        }

        $('#total-psy').attr('data-value', totalPsy).text(totalPsy);
        $('#total-money').attr('data-value', totalMoney).text(formatNumber(totalMoney));
        $('#total-voucher').attr('data-value', totalVoucher).text(totalVoucher);
    });


    $('#btnMindcheck').on('click', function() {
    	window.open("/auth/talk/mindcheck_result?roomId="+ROOM_ID, '_blank', 'width=600,height=770,top=0,right=0');
    });

    $('#btnSelfDiagnosis').on('click', function(evt) {
    	var myWindow = window.open("/auth/talk/self_diagnosis?roomId="+ROOM_ID, '_blank', 'width=600,height=600,top=0, left=600');
    	myWindow.moveBy(600, 0);
        myWindow.focus();
    });

    $('#doc_file').on('click', '.img', function(evt) {
        if (GM) {
            var docId = $(this).parent().attr('data-docId');

            // re-render submenu
            $('#doc_thumb').html('');
            // show submenu
            var roomDoc = GM.Document.getRoomDoc(docId);

            // handle image
            if (roomDoc.fileExt == 'png' || roomDoc.fileExt == 'jpg') {
                // change room mode to docshare
                GM.changeRoomMode('docshare');

                // set roomDoc
                GM.Document.setRoomDocPage(roomDoc.docId, roomDoc.docSeq);

                // minimize video
                if (!$('.video_large').attr('id')) {
                    $('.video_large').attr('id', 'thum-video2');
                    $('body').addClass('loadfile');
                    $('#load-file').css('display', '');
                }
            }
            // handle other files
            else {
                for(var i = 0, n = roomDoc.docPageList.length; i < n; i++) {
                    var docPage = roomDoc.docPageList[i];

                    (function (_docPage) {
                        console.log('_docPage', _docPage);
                        GM.Document.getRoomDocThumbnailImage(_docPage.docId, _docPage.docPageSeq).then(function(img) {
                            var html = tpl_file_thumb_template({
                                docId       : _docPage.docId,
                                docPageSeq  : _docPage.docPageSeq,
                                fileName    : _docPage.fileInfo.filename,
                                fileSrc     : $(img).attr('src')
                            });

                            $('#doc_thumb').append(html);
                        });
                    })(docPage);
                }
                $('.menu-right').addClass('show-submenu');
            }

        }
    });

    $('#doc_thumb').on('click', 'li', function(evt) {
        if (GM) {
            var $this   = $(this);
            var docId   = $this.attr('data-docId');
            var docSeq  = $this.attr('data-pageseq');

            // change room mode to docshare
            GM.changeRoomMode('docshare');

            // set roomDoc
            GM.Document.setRoomDocPage(docId, docSeq);

            // minimize video
            if (!$('.video_large').attr('id')) {
                $('.video_large').attr('id', 'thum-video2');
                $('body').addClass('loadfile');
                $('#load-file').css('display', '');
            }

        }
    });

    $('#partner_video').on('click', function(evt) {
        if (GM) {
            var $parent = $(this).parent();
            if ($parent.attr('id')) {
                GM.changeRoomMode('call');
                $('body').removeClass('loadfile');
                $parent.removeAttr('id');
                $('#load-file').css('display', 'none');
            }
        }
    });

    $('.menu-right').on('click', '.close-right', function(evt) {
        $('.menu-right').removeClass('show-submenu');
    });

    $('#doc_file').on('click', '.remove-pdf', function(evt) {
        $(this).closest('li').remove();
    });
    
    $("#btn-reconnect-room-app").on("click", function(event){
    	$('#popup_reconnect_app').modal('hide');
    	if(hasConnectionProblem){
    		joinRoomAgain();
    	}
	  });
    
//    $("#btn-reconnect-room-web").on("click", function(event){
//    	$('#popup_reconnect_web').modal('hide');
//    	var roomId  = extractQueryParam('roomId');
//    	var count = getCountDisconnect(roomId);
//    	if(count == 0){
//    		countDisconnect(roomId);
//    	}
//    	countDisconnect(roomId);
//    	initRoom()
//	  });

})();




