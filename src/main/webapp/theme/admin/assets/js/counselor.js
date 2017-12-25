'use strict';

function toggleLoading() {
		if ($('body').find('#ajax_loading').length) {
	        $('#ajax_loading').css('display') == 'none'
	            ? $('#ajax_loading').css('display', '')
	            : $('#ajax_loading').css('display', 'none');
	    } else {
	        var html = '<div id="ajax_loading" class="loading-page">';
	        html    +=      '<div class="img-loading">';
	        html    +=          '<img src="/theme/admin/assets/images/loading.svg" alt="loading"/>';
	        html    +=      '</div>';
	        html    += '</div>';

	        $('body').append(html);
	    }
}

function toggleLoadingDisconnect() {
		if ($('body').find('#ajax_loading_disconnect').length) {
	        $('#ajax_loading_disconnect').css('display') == 'none'
	            ? $('#ajax_loading_disconnect').css('display', '')
	            : $('#ajax_loading_disconnect').css('display', 'none');
	    } else {
	        var html = '<div id="ajax_loading_disconnect" class="loading-page">';
	        html    +=      '<div class="img-loading">';
	        html    +=          '<img src="/theme/admin/assets/images/loading.svg" alt="loading"/>';
	        html    +=          '<span style="color: #fff;position: absolute;width: 100%;text-align: center;left: 0;bottom: -15px;font-size: 12px; opacity: 0.8;font-size: 12px;" >상담실로 연결중입니다</span>';
	        html    +=      '</div>';
	        html    += '</div>';

	        $('body').append(html);
	    }
}


function extractQueryParam(query) {
    var searchString    = location.search;
    var queryRegEx      = new RegExp(query + '=[^?&]*');
    var queryParam      = searchString.match(queryRegEx) ? searchString.match(queryRegEx)[0] : '';
    return decodeURIComponent(queryParam.replace(query + '=', ''));
}

function sortable(selector) {
    var PATTERN_SORTS       = /sorts=[^?&]*/i;
    var PATTERN_DIRECTION   = /direction=[^?&]*/i;

    $(selector).on('change', function(evt) {
        var sorts       = $(this).val();
        var direction   = $(this).find('option:selected').attr('data-direction');

        var currentHref = window.location.href;

        console.log(currentHref);

        if (currentHref.match(PATTERN_SORTS) && currentHref.match(PATTERN_DIRECTION)) {
            currentHref = currentHref.replace(PATTERN_SORTS, 'sorts=' + encodeURIComponent(sorts))
            currentHref = currentHref.replace(PATTERN_DIRECTION, 'direction=' + encodeURIComponent(direction));
        } else {
            if (location.search.length) {
                currentHref += '&sorts=' + encodeURIComponent(sorts) + '&direction=' + encodeURIComponent(direction);
            } else {
                currentHref += '?sorts=' + encodeURIComponent(sorts) + '&direction=' + encodeURIComponent(direction);
            }
        }

        window.location = currentHref;
    });
}

function formatNumber(data) {
    return Number.parseInt(data).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,').replace('.00','');
}

function isValidImage(contentType) {
    return contentType.startsWith('image/png')
            || contentType.startsWith('image/gif')
            || contentType.startsWith('image/jp');
}

function isValidImageOfFile(contentType) {
    return contentType.startsWith('image/png')
            || contentType.startsWith('image/gif')
            || contentType.startsWith('image/jp')
            || contentType.startsWith('application/pdf')
            || contentType.startsWith('application/vnd.ms-powerpoint')
            || contentType.startsWith('application/vnd.openxmlformats-officedocument');
}

function getDayOfWeekName(dayIndex) {
    var days = ['일','월','화','수','목','금','토'];
    return days[dayIndex];
}

function init() {
    Metronic.init(); // init metronic core components
    Layout.init(); // init current layout
    QuickSidebar.init(); // init quick sidebar
    Demo.init(); // init demo features

    $('.selectpickermember').selectpicker({
        style: 'btn-info',
        size: 4
    });

    $('.date-picker').datepicker({
        rtl: Metronic.isRTL(),
        orientation: "left",
        autoclose: true,
        language: 'kr'
    });
}
$(document).ready(init);


function countDisconnect(roomId){
	var url = "/v1/public/countDisconnect";
		$.ajax({
			type: "GET",
			url: url,
			data: {roomId:roomId},
			success: function(response)
			{
				console.log(response);
				if(response.code == 0){
					var count = response.data;
					return count;
				}else{
					return 0;
				}
			}
		});
}

function getCountDisconnect(roomId){
	var url = "/v1/public/getCountDisconnect";
	var count =null ;
		$.ajax({
			type: "GET",
			url: url,
			data: {roomId:roomId},
			async: false,
			success: function(response)
			{
				console.log("response getCountDisconnect: "+response);
				if(response.code == 0){
					count = response.data;
					console.log("count getCountDisconnect:"+count);
				}else{
					count = 0;
				}
			}
		});
		return count;
}

function removeCountDisconnect(roomId){
	var url = "/v1/public/removeCountDisconnect";
		$.ajax({
			type: "GET",
			url: url,
			data: {roomId:roomId},
			success: function(response)
			{
				console.log(response);
				if(response.code == 0){
					console.log("remove count disconnect successfully");
				}else{
					console.log("remove count disconnect unsuccessfully");
				}
			}
		});
}

function getStatusGM(roomId){
	var url = "/v1/public/getStatusGM";
	var status =null ;
		$.ajax({
			type: "GET",
			url: url,
			data: {roomId:roomId},
			async: false,
			success: function(response)
			{
				console.log("response getStatusGM: "+response);
				if(response.code == 0){
					status = response.data;
					console.log("status :"+status);
				}else{
					status = 0;
				}
			}
		});
		return status;
}