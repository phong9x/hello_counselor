
$(document).ready(function() {
	var dataIdPopup3 = $(this).attr('data-id-popup3');
	if (dataIdPopup3 == 3) {
	    $(".statusPopup3").addClass('#popupInforChangedPass');
	  }
	
	
	$('.sent-mail_mess_setail').on('click', function(){
      $.uniform.update('.checkall label input');
    });
	var total_fee = 0;
	

	  $(".popup-psychlogical1").on("click", function(event){
	      $('#popup-psychlogical').modal('show');
	  });

	  $(".popup-psychlogical2").on("click", function(event){
	      $('#popup-psychlogical2').modal('show');
	  });

	  $(".popup-psychlogical3").on("click", function(event){
	      $('#popup-psychlogical3').modal('show');
	  });
	  
	  $(".sent-mail").on("click", function(event){
	      $('#popup-psychlogical3').modal('show');
	      
	  });
	  
	  $(".login-contact .popup-faq").on("click", function(event){
	      $('#popup-faq').modal('show');
	  });
	  
	  $(".sent-mail_mess_setail").on("click", function(event){
	      $('#popup-psychlogical4').modal('show');
	  });
	  
	  $('#massage-all-checkbox').bind('click', function(){
	      var status = $(this).is(':checked');
	      $('.item-checkbox').attr('checked', status);
	      $.uniform.update('.item-checkbox');
	    });
	  
	  $('.search_user_mess').on("click", function(event){
		  $('li').removeClass('hide');
		  var key = $("input[name=search_keyword]").val();
		  $('.list-search-items').each(function(i, element) {
			  var text = $(this).text().toLowerCase();
			  if(!text.match(key)) {
				  $(this).closest('li').addClass('hide');
			  }
		  });
		  
  
	  });
	  
	  $('.btn-send-mail').on('click', '', function() {
		  var dataUserId =  $("#user_id_ms:checked").val();
		  var content = $("#content2_ms").val();
		   if (content.length == 0) {
				 alert("쪽지 내용을 입력하세요.");
		   } else if (dataUserId == null) {
				alert("쪽지를 받을 회원을 입력하세요.")
		   } else {
				 $.ajax({
					 method:"POST",
					 url:"/api/v1/public/ajax_send_message_detail",
					 data:{
						"dataUserId":dataUserId,
						"content":content
					},
					 success:function(rs) {
						if(rs.status == 200){
							$('li').removeClass('hide');
							$('#popup_msg_complete').modal('show');
							$('.popup_accept_close_complete').on('click', function(evt) {
								var params = window.location;
								window.location=params.pathname + params.search;
	                        });
						} else {
							alert("can not request !!!")
						}
					 }
				 })
			}
	  });
	  
	  $(".massage .openpopup-confim").on("click", function(event){
	      $('#popup-massage').modal('show');
	      $('.fullname_').html('');
	      var messId = $(this).attr('data-id');
	      var dataFullname = $(this).attr('data-fullname');
	      var dataEmail = $(this).attr('data-email');
	      var dataDate = $(this).attr('data-date');
	      var dataContent = $(this).attr('data-content');
	      var dataUserId = $(this).attr('data-userId');
	     
	      
	      $('.fullname_').html(dataFullname);
	      $('.email_').html(dataEmail);
	      $('.date_').html(moment(dataDate).format('YYYY-MM-DD HH:mm:ss'));
	      $('.content_').html(dataContent);
	      
	      $('#popup-massage').find('.send_mess_detail').attr('dataFullname', dataFullname);
	      $('#popup-massage').find('.send_mess_detail').attr('dataEmail', dataEmail);
	      $('#popup-massage').find('.send_mess_detail').attr('dataUserId', dataUserId);
	      $('.openpopup-confim').modal('hide');
	      
	      $.ajax({
				 method:"POST",
				 url:"/api/v1/public/ajax_update_status_message",
				 data:{
					"mess_id":messId
				},
				 success:function(rs) {
					if(rs.status == 200){
					} else {
						alert("can not request !!!")
					}
				 }
			 })
	      
	  });
	  
	  $('.close-mss-setup').on("click", function(e) {
		  var params = window.location;
		  window.load=params.pathname + params.search;
	  });
	  
	  $('.send_mess_detail').on("click", function(e) {
		  var dataFullname = $(this).attr('dataFullname');
		  var dataEmail = $(this).attr('dataEmail');
		  $('.data-mail').append(dataEmail);
		  $('.data-fullname').append(dataFullname);
		  var dataUserId = $(this).attr('dataUserId');
		  $('.data-userid').append(dataUserId);
		  $('.sent-mail_mess_setail').modal('show');
		  $('#popup-psychlogical4').find('.send_email_mess_detail').attr('dataFullname', dataFullname);
		  $('#popup-psychlogical4').find('.send_email_mess_detail').attr('dataEmail', dataEmail);
		  $('#popup-psychlogical4').find('.send_email_mess_detail').attr('dataUserId', dataUserId);
		  $('#popup-massage').modal('hide');
	  });
	  
	  $('.send_email_mess_detail').on("click", function(e) {
		  var dataUserId = $(this).attr('dataUserId');
		  var content = $("#content_mss").val();
		  $('#sent-mail_mess_setail').modal('hide');
		  
		  if (content.length == 0) {
			  $('#popup_msg_empty').modal('show');
			 } else {
				 $.ajax({
					 method:"POST",
					 url:"/api/v1/public/ajax_send_message_detail",
					 data:{
						"dataUserId":dataUserId,
						"content":content
					},
					 success:function(rs) {
						if(rs.status == 200){
							$('#popup_msg_complete').modal('show');
							$('.popup_accept_close_complete').on('click', function(evt) {
								var params = window.location;
								window.location=window.location=params.pathname + params.search;
	                        });
						} else {
							alert("can not request !!!")
						}
					 }
				 })
			}
	  });
	  
	  $('.ps4').on("click", function(e) {
		  var params = window.location;
		  window.location=window.location=params.pathname + params.search;
	  });
	  
	  $('#order_psy_test').on('change', function(){
		    var selected = $(this).find("option:selected").val();
		    var fullname = document.getElementById("fullname").value;
		    var testnameList = [];
		    var cbs = document.forms['test'].elements['testname0'];
		    for(var i=0,cbLen=cbs.length;i<cbLen;i++){
		    	  if(cbs[i].checked){
		    		  testnameList.push(cbs[i].value);
		    	  } 
		    }
		    
		    var statussList = [];
		    var cbs2 = document.forms['test'].elements['statuss'];
		    for(var j=0,cbLen=cbs2.length;j<cbLen;j++){
		    	  if(cbs2[j].checked){
		    		  statussList.push(cbs2[j].value);
		    	  } 
		    }
		    window.location = "/auth/psychologicaltest/list?testname0="+testnameList+"&statuss=" +statussList+"&fullname=" +fullname+"&orderBy=" +selected;
	  });
	  
	  $('.test-name').on('change', function () {
		  var fee = parseInt($(this).attr('data-fee'));
		  var count_checked = $('#popup-psychlogical2 .radio-checkboxstyle2 input:checked').length;
		  if($(this).is(':checked')) {
				total_fee +=fee;
		  }else {
			  if(total_fee > fee) {
				  total_fee -=fee;
			  }else {
				  total_fee = 0;
			  }
		  }
		  $('#display-fee').text(total_fee);
		  $('.count-checked').text(count_checked);
	  });	
	  
	  $('.save-user-psychlogical').on('click', function() {
		  var test_name = [];
		  var count_test_name = 0;
		  $('.test-name').each(function(i, val) {
			  if($(this).is(':checked')) {
				  test_name.push($(this).val());
				  count_test_name +=1;
			  }
		  });
		 var user_id = $("input[name='user_id']:checked").val();
		 var json = JSON.stringify(test_name);
		var total_fee = $('#display-fee').text();
		var counselor_id = parseInt($(this).attr('data-id'));
		 if(user_id == null) {
			 alert("회원을 선택하세요.");
		 } else if (test_name.length == 0) {
			 alert("요청할 심리검사를 선택하세요.");
		 } else {
			 $.ajax({
				 method:"POST",
				 url:"/api/v1/public/ajax_save_psychological",
				 data:{
					"user_id":user_id,
					 "test_name":json,
					 "total_fee":total_fee,
					 "counselor_id":counselor_id,
					 "count_test_name":count_test_name
				},
				 success:function(rs) {
					if(rs.status == 200){
						window.location="/auth/psychologicaltest/list";
					} else {
						alert("can not request !!!")
					}
				 }
			 })
		}
		 
	  });
	  
	  $('.psychological_back_list').click(function(){
		  window.location="/auth/psychologicaltest/list";
		});
	  
	  
	  (function($) {
		    if (!$('.tr-file-upload').length) return;

		    var popupUploadAgain = $('#popupUploadAgain');

		    $('.tr-file-upload').each(function() {
		      var fileUploadWrapper = $(this);
		      var inputFile = fileUploadWrapper.find('.input-file');
		      var borderBox = fileUploadWrapper.find('.border-box');
		      var showFileInfo = fileUploadWrapper.find('.show-file-info');
		      var previewImage = fileUploadWrapper.find('.preview-image');
		      var deleteButton = fileUploadWrapper.find('.btn-delete');

		      inputFile.on('change', function() {
		        var thisInputFile = $(this);
		        var file = thisInputFile[0].files[0];
		        var fileName = file.name;
		        var fileSize = file.size;
		        showFileInfo.empty().text(fileName);

		        if ($('.tr-file-upload.image-upload').length) {
		          var capacityLimit = 3 * 1024 * 1024; // = 21MB
		          var fileExtension = fileName.split('.').pop().toLowerCase();
		          var listExtensionArray = ['jpg', 'jpeg', 'gif', 'png', 'pdf'];
		          var inArrayExtension = $.inArray(fileExtension, listExtensionArray);

		          if (inArrayExtension >= 0 && fileSize / capacityLimit <= 1) {
		            thisInputFile.removeClass('input-empty').addClass('input-not-empty');
		            borderBox.css('border', '');
		            previewImage.removeClass('no-image');
		          } else {
		            thisInputFile.replaceWith(thisInputFile = thisInputFile.clone(true));
		            thisInputFile.removeClass('input-not-empty').addClass('input-empty');
		            borderBox.css('border', '1px solid #ff0000');
		            previewImage.addClass('no-image');
		            showFileInfo.empty();
		            popupUploadAgain.modal('show');
		          }
		        }

		        if (fileUploadWrapper.find('.input-file').val() === '') {
		          thisInputFile.addClass('input-empty').removeClass('input-not-empty');
		        } else {
		          thisInputFile.addClass('input-not-empty').removeClass('input-empty');
		        }
		      });

		      deleteButton.on('click', function() {
		        inputFile.replaceWith(inputFile = inputFile.clone(true));
		        inputFile.removeClass('input-not-empty').addClass('input-empty');
		        borderBox.css('border', '');
		        previewImage.addClass('no-image').find('> img').attr('src', '');
		        previewImage.addClass('no-image').find('> .image-bg').attr('style', 'background-image: url();');
		        showFileInfo.empty();
		      });
		    });
		  })(jQuery);
	  
	  
	  $( ".cancel_psychologicaltest" ).click(function() {
		  var userPsyFileId = $(this).attr('data-id');
		  $('#popupConfirmCancelRequest').find('.btn-confirm-cancelrequest-psy').attr('userPsyFileId', userPsyFileId);
		  $('#popupConfirmCancelRequest').modal('show'); 
	  });
	  
	  $('.send-email-psychlogical').click(function(e) {
		  var email = $(this).attr('data-email');
		  var content_mess = $("#content_mess").val();
		  var userId = $(this).attr('data-userid');
		  var psyTestCounselorId = parseInt($(this).attr('data-psyTestCounselorId'));
		  if(content_mess.length == 0){
			  alert("쪽지 내용을 입력하세요.");
		  } else {
			  $.ajax({
					 method:"POST",
					 url:"/api/v1/public/ajax_send_email_psychological",
					 data:{
						"content_mess":content_mess,
						"email":email,
						"userId":userId
					},
					 success:function(rs) {
						if(rs.status == 200){
							$('#popup_msg_complete').modal('show');
							$('.popup_accept_close_complete').on('click', function(evt) {
								var params = window.location;
								window.location=window.location=params.pathname + params.search;
	                        });
						} else {
							alert("can not request !!!")
						}
					 }
				 })
		  }
	  });
	  
	  (function($) {
		    if (!$(".date-picker_message").length) return;

		    $('.date-picker_message').datepicker({
		      rtl: Metronic.isRTL(),
		      orientation: "left",
		      autoclose: true,
		      language: 'kr'
		    });
		  })(jQuery);
	  
	  /*message list*/
	  $('#end_mess_tab1').datepicker().on('changeDate', function (ev) {
		  var endDateSearch = $("#end_mess_tab1").datepicker().val();
		  var startDateSearch = $("#start_mess_tab1").datepicker().val();
		  
		  var date1 = new Date(endDateSearch);
		  var date2 = new Date(startDateSearch);
		  if(endDateSearch != null) {
			  var timeDiff = Math.abs(date1.getTime() - date2.getTime());
			  if(timeDiff < 0) {
				  timeDiff = 0;
			  }
			  var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
			  if(diffDays > 60) {
				  alert("날짜 조회는 60일까지만 가능합니다.");
			  }
		  }

		});
	  
	  $('#end_mess_tab2').datepicker().on('changeDate', function (ev) {
		  var endDateSearch = $("#end_mess_tab2").datepicker().val();
		  var startDateSearch = $("#start_mess_tab2").datepicker().val();
		  
		  var date1 = new Date(endDateSearch);
		  var date2 = new Date(startDateSearch);
		  if(endDateSearch != null) {
			  var timeDiff = Math.abs(date1.getTime() - date2.getTime());
			  if(timeDiff < 0) {
				  timeDiff = 0;
			  }
			  var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
			  if(diffDays > 60) {
				  alert("날짜 조회는 60일까지만 가능합니다.");
			  }
		  }

		});
	  
	  $('.tab_message_2').on("click" , function(){ 
		  window.location = '/auth/message/list?number_diff=2';
//		       var startTab2 = new Date();
//		       var endTab2 = new Date();
//		       $("#end_mess_tab2").datepicker().val(moment(endTab2).format('YYYY-MM-DD'));
//		       $("#start_mess_tab2").datepicker().val(moment(startTab2).format('YYYY-MM-DD'));
//		    	$('.tab_message_1').removeClass('active');
//		    	$('.t_mess1').removeClass('active');
//		    	$('.tab_message_2').addClass('active');
//		    	$('.t_mess2').addClass('active');
	  });
	  
	  $('.tab_message_1').on("click" , function(){ 
		  window.location = '/auth/message/list?number_diff=1';
//	       var startTab2 = new Date();
//	       var endTab2 = new Date();
//	       $("#end_mess_tab1").datepicker().val(moment(endTab2).format('YYYY-MM-DD'));
//	       $("#start_mess_tab1").datepicker().val(moment(startTab2).format('YYYY-MM-DD'));
//	    	$('.tab_message_2').removeClass('active');
//	    	$('.tab_message_1').addClass('active');
//	    	$('.t_mess2').removeClass('active');
//	    	$('.t_mess1').addClass('active');
	  });
	  
	  
	  $('#order_inbox_mess').on('change', function(){
		    var selected = $(this).find("option:selected").val();
		    
		    if($('.tab_message_1').hasClass('active')){
		    	var end_mess_tab1 = $("#end_mess_tab1").datepicker().val();
		    	var start_mess_tab1 = $("#start_mess_tab1").datepicker().val();
		    	var keyword_message1 = $("#keyword_message1").val();
		    	var end_mess_tab2 = null;
		    	var start_mess_tab2 = null;
		    	var keyword_message2 = null;
		    	window.location = "/auth/message/list?number_diff=1&start_mess_tab1="+start_mess_tab1+"&end_mess_tab1="+end_mess_tab1+"&keyword_message1="+keyword_message1+"&orderBy="+ selected;
		    } else {
		    	var end_mess_tab2 = $("#end_mess_tab2").datepicker().val();
		    	var start_mess_tab2 = $("#start_mess_tab2").datepicker().val();
		    	var keyword_message2 = $("#keyword_message2").val();
		    	var end_mess_tab1 = null;
		    	var start_mess_tab1 = null;
		    	var keyword_message1 = null;
		    	window.location = "/auth/message/list?number_diff=2&start_mess_tab2="+start_mess_tab2+"&end_mess_tab2="+end_mess_tab2+"&keyword_message2="+keyword_message2+"&orderBy="+ selected;
			}
	  });
	  
	  $('.delete_message_list').click(function(e) {
		  
			var searchIDs = ($('#rates input:checked').map(function() {
				return $(this).val();
			})).get();
	  		var json = JSON.stringify(searchIDs);
			if (searchIDs.length == 0) {
				alert("삭제할 쪽지를 선택하세요.");
			} else {
				var params = window.location;
				$.ajax({
					 method:"POST",
					 url:"/api/v1/public/ajax_delete_message_list",
					 data:{
						"ids":json
					 },
					 success:function(rs) {
						if(rs.status == 200){
							window.location=window.location=params.pathname + params.search;
						} else {
							alert("can not request !!!")
						}
					 }
				 })
			}
	  });
	  
	  // Gate
	  $('.btn-close-popup-faq').click(function(e) {
		  $('#popup-faq').modal('hide'); 
		  $('.popup-faq .panel-collapse.in').removeClass('in');
	      $('.popup-faq .item-header a.pull-right').addClass('collapsed');
	  });
	  
	  // login (change password guide)
	  $('.btn-changepass').click(function(e) {
	  });
	  
	  // notice
	  $('#btn-notice-sort').on('change', function(){
		    var selected = $(this).find("option:selected").val();
		    window.location = "/auth/notice/list?orderBy=" +selected;
	  });
	  
	  $('.btn-back-list').click(function(e) {
		  window.location = "/auth/notice/list";
	  });
	  
	  $('.btn-search-faq').click(function(e) {
		  var keyword = $('input[name="keyword"]').val();
		  window.location = "/auth/faq/list?keyword=" +keyword;
	  });
	  
	  /*inquiry wc*/
	  $('.btn-send-inquiry').click(function(e) {
		  var email_inquiry = $('input[name="email_inquiry"]').val();
		  var e = document.getElementById("category_inquiry");
		  var selected = e.options[e.selectedIndex].value;
		  var content_inquiry = $("#content_inquiry").val();
		  
		  if((email_inquiry == null) ||(email_inquiry.length == 0)) {
			  alert("이메일을 입력하세요.")
		  } else if (selected == 0) {
			  alert("카테고리를 선택하세요.")
		  } else if ((content_inquiry == null) || (content_inquiry.length === 0)) {
			  alert("내용을 입력하세요.")
		  } else {
				$.ajax({
					 method:"POST",
					 url:"/api/v1/public/inquiry/counsoler/write",
					 data:{
						"email":email_inquiry,
						"categoryId":selected ,
						"content":content_inquiry
					 },
					 success:function(rs) {
						if(rs.status == 200){
							$('#popup-inquiry').modal('show'); 
						} else {
							alert("can not request !!!")
						}
					 }
				 })
		  }
	  });
	  
	  
	  $('.btn-confirm-result-ok').click(function(e) {
		  window.location = "/auth/inquiry/inquiry";
	  });
	  
	  /*change password*/
	  var regexPwd = new RegExp("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$&+,:;=?@#|'<>.^*()%!-])");
	  function checkPwd(password) {
		  return regexPwd.test(password)&& password.length >=8 && password.length <= 20;
	  }
	  
	  function checkPwdConfirm(password, passwordConfirm) {
		  return password === passwordConfirm;
	  }
	  
	  $('.onchange_current_pass').on('change', function(){
		  
		  var current_password = $("input[name=current_password]").val();
		  var resutl_check_current_password = checkPwd(current_password);
		  if (resutl_check_current_password == false) {
			  $('.result_pass').css({"display": 'inline-block'});
			  $('.main-content').find('.btn-change-pass').attr('resutl_check_current_password', false);
		  } else {
			  $('.main-content').find('.btn-change-pass').attr('resutl_check_current_password', true);
		  }
	  });
	  
	  $('.onchange_new_password').on('change', function(){
		  var new_password = $("input[name=new_password]").val();
		  var resutl_check_new_password = checkPwd(new_password);
		  if (resutl_check_new_password == false) {
			  $('.result_new_password').css({"display": 'inline-block'});
			  $('.main-content').find('.onchange_confirm_password').attr('resutl_check_new_password', false);
			  $('.main-content').find('.btn-change-pass').attr('resutl_check_new_password', false);
		  } else {
			  $('.main-content').find('.onchange_confirm_password').attr('resutl_check_new_password', true);
			  $('.main-content').find('.btn-change-pass').attr('resutl_check_new_password', true);
		  }
		  $('.main-content').find('.onchange_confirm_password').attr('new_password', new_password);
	  });
	  
	  $('.onchange_confirm_password').on('change', function(){
		  var confirm_password = $("input[name=confirm_password]").val();
		  
		  var resutl_not_enough_characters = checkPwd(confirm_password);
		  if (resutl_not_enough_characters == false) {
			  $('.result_confirm_password').css({"display": 'inline-block'});
			  $('.main-content').find('.btn-change-pass').attr('resutl_check_confirm_password', false);
		  } else {
			  var new_password = $(this).attr('new_password');
			  var result_not_mass_pass = checkPwdConfirm(new_password, confirm_password);
			  if (result_not_mass_pass == false) {
				  $('.result_confirm_password2').css({"display": 'inline-block'});
				  $('.main-content').find('.btn-change-pass').attr('resutl_check_confirm_password', false);
			  } else {
				  $('.main-content').find('.btn-change-pass').attr('resutl_check_confirm_password', true);
			  }
		  }
	  });
	  
	  
	  
	  $('.btn-change-pass').click(function(e) {
		  var current_password = $("input[name=current_password]").val();
		  var new_password = $("input[name=new_password]").val();
		  var confirm_password = $("input[name=confirm_password]").val();
		  var resutl_check_current_password = $(this).attr('resutl_check_current_password');
		  var resutl_check_new_password = $(this).attr('resutl_check_new_password');
		  var resutl_check_confirm_password = $(this).attr('resutl_check_confirm_password');
		  if ((current_password.length == 0) || (new_password.length == 0) || (confirm_password.length == 0) ) {
			alert("입력하지 않은 항목이 있습니다. 다시 확인해주세요.");
		  } else {
			if((resutl_check_current_password == 'true') && (resutl_check_new_password == 'true') && (resutl_check_confirm_password == 'true')) {
				$.ajax({
					 method:"POST",
					 url:"/api/v1/public/ajax_changepassword",
					 data:{
						"current_password":current_password,
						"new_password": new_password
					},
					 success:function(rs) {
						if(rs.status == 200){
							if (rs.data.statusPopup == 1) {
								$('.result_current_test').css({"display": 'inline-block'});
							} else if (rs.data.statusPopup == 2) {
								alert("입력하신 비밀번호는 사용할 수 없습니다. 다른 비밀번호를 입력해주세요.");
							} else {
								$('#popupInforChangedPass').modal('show');
							}
						} else {
							alert("can not request !!!")
						}
					 }
				 })
			}
		  }
	  });
	  
	  $('.btn-back_gate-screen').click(function() {
		  $.ajax({
              url     : '/auth/logout',
              method  : 'get'
          })
          .then(function(res) {
              window.location.href = '/';
          }, function(err) {
              console.error(err);
          });
	  });
	  
	  /* cancel request in psychologicaltest*/
	  $('.btn-confirm-cancelrequest-psy').click(function() {
		  var userPsyFileId = $(this).attr('userPsyFileId');
			$.ajax({
				 method:"POST",
				 url:"/api/v1/public/ajax_cancel_psychological",
				 data:{
					"userPsyFileId":userPsyFileId
				},
				 success:function(rs) {
					if(rs.status == 200){
						window.location="/auth/psychologicaltest/list";
					} else {
						alert("can not request !!!")
					}
				 }
			 })
	  });
	  
	  $('.btn-not-cancelrequest-psy').click(function() {
		  $('#popupConfirmCancelRequest').modal('hide');
	  });
	  
});


$(document).on('change','#pdfFile' , function(){ 
	
	var userPsyTestId = parseInt($(this).attr('data-userPsyTestId'));
	var userPsyFileId = parseInt($(this).attr('data-userPsyFileId'));
	var psyTestCounselorId = parseInt($(this).attr('data-psyTestCounselorId'));
	var testName = $(this).attr('data-testName');
	
	var formdata = new FormData();
	formdata.append('fullPath', $('#pdfFile')[0].files[0]);
	formdata.append('userPsyTestId', userPsyTestId);
	formdata.append('userPsyFileId', userPsyFileId);
	formdata.append('testName', testName);
	
	$.ajax({
		 method:"POST",
		 url:"/api/v1/public/ajax_upload_pdf_psychological",
		 data: formdata,
		 contentType: false,
		 processData: false,
		 success:function(rs) {
			 if(rs.status == 200){
				 var params = window.location;
				window.location=window.location=params.pathname + params.search;
			 } else {
					alert("can not request !!!")
			 }
		 }
	 })
});








