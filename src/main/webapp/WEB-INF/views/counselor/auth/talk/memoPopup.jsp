<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div id="popupList-facechat" class="popup-facetalk face-chat" style="height: 100% !important;">
    <div class="modal-dialog" style="position: relative;height: 760px;overflow-x: auto;margin-top: 0 !important;">
        <div class="modal-content">
            <div class="modal-header">
                <h4>상담 메모</h4>
            </div>
            <div class="modal-body">
                <div class="modal-top">
                    <span class="title">내담자 이름</span>
                    <span class="content" id="fullname">${fullname}</span>
                </div>
                <div class="modal-chat">
                    <div class="title"><span>상담 중에 가급적 필요한 내용만 적어주세요. 다른 상담사들은 볼 수 없습니다</span></div>
                    <textarea name="editor2" rows="6" data-error-container="#editor2_error" class="rw-ckeditor form-control"></textarea>
                    <div class="footer"><button id="btnUploadMemo">상담메모 입력</button></div>
                </div>
                <div class="modal-main">
                    <div class="modal-main-header">
                        <span class="title">상세 메모</span>
                    </div>
                    <div id="owl_memo" class="owl-carousel">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="popup_delete_memo" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupLogout-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body"><strong>상담 메모를 삭제하시겠습니까? 삭제하시면 복구되지 않습니다.</strong>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal">취소</button>
                <button type="button" class="btn close-face-popup popup_accept">확인</button>
            </div>
        </div>
    </div>
</div>
<style>
	.modal-backdrop{display: none !important;}
	.modal-dialog{width: 100% !important;margin-top: 0 !important;border: none !important;box-shadow: none !important;}
	.modal-content{box-shadow: none !important;border: none !important;border-radius: 0 !important;}
	.modal-list li .title{float: left;width: 100px;}
</style>
<script>
	  $('#fullname').val($('input[name=memberName]').val());
      var owl     = $('#owl_memo');
      var roomId = extractQueryParam('roomId');
      if (roomId) {
          $
              .ajax({
                  url     : '/auth/talk/memo',
                  method  : 'get',
                  data    : {
                      roomId  : roomId
                  },
                  beforeSend: toggleLoading
              })
              .then(function(response) {
                  toggleLoading();
                  var memos       = response.content;
                  var memosArr    = {};

                  for (var i = 0, n = memos.length; i < n; i++) {
                      var dateOccur   = moment(memos[i].createDate);
                      var key         = dateOccur.year() + '년' + (dateOccur.month() + 1) + '월';

                      if (memosArr[key] == undefined) {
                          memosArr[key] = [memos[i]]
                      } else {
                          memosArr[key].push(memos[i]);
                      }
                  }

                  var html = '';
                  _.each(_.keys(memosArr), function(element) {
                      html += '<div class="item">';
                      html +=     '<div class="item-date">';
                      html +=         '<span>' + element + '</span>';
                      html +=     '</div>';
                      html +=     '<div class="item-content">';
                      _.each(memosArr[element], function(subElement) {
                          var dateMoment = moment(subElement.createDate)
                          html +=         '<div class="item-ct" data-memoId="' + subElement.id + '">';
                          html +=             '<div class="item-chat">';
                          html +=                 '<div class="title"><span>' + dateMoment.year() + '-' + (dateMoment.month() + 1) + '-' + dateMoment.date() + '(' + getDayOfWeekName(dateMoment.day()) + ')</span></div>'
                          html +=                 '<div class="date-time"><span>' + dateMoment.hour() + ':' + dateMoment.minute() + ':' + dateMoment.second() + '</span><span class="remove"></span></div>';
                          html +=                 '<div class="content"><p>' + subElement.memo + '</p></div>'
                          html +=             '</div>';
                          html +=         '</div>';
                      });
                      html +=     '</div>';
                      html += '</div>';
                  });

                  owl.data('owlCarousel').destroy();
                  owl.html(html);
                  owl.owlCarousel({
                      singleItem: true,
                      pagination: true,
                      autoPlay: false,
                      stopOnHover:true,
                      navigation: true,
                      addClassActive: true
                  });
                  $('#popupList-facechat').modal('show');

              }, function(error) {
                  toggleLoading();
                  console.error(error);
              });
      }
      
      $('#btnUploadMemo').on('click', function(evt) {
      	console.log(1111111111111111);
          var roomId = extractQueryParam('roomId');
          var memo = $('[name=editor2').val();

          if (roomId && memo) {
              $
                  .ajax({
                      url     : '/auth/talk/memo',
                      method  : 'post',
                      data    : {
                          roomId  : roomId,
                          memo    : memo
                      },
                      /* beforeSend: toggleLoading */
                  })
                  .then(function(response) {
                      /* toggleLoading(); */
                      /* window.close(); */
                      
					  window.location = window.location.href;
                      $('[name=editor2').val('');
                  }, function(error) {
                      toggleLoading();
                      window.close();
                      console.error(error);
                  });
          }

      });
      
      $('#popupList-facechat').on('click', '#owl_memo .remove', function(evt) {
          var memoId  = $(this).closest('.item-ct').attr('data-memoId');
          var $this   = $(this);

          $('#popup_delete_memo')
              .off('click', '.popup_accept')
              .on('click', '.popup_accept', function(evt) {
                  $
                      .ajax({
                          url     : '/auth/talk/memo?memoId=' + memoId,
                          method  : 'delete',
                          beforeSend: toggleLoading
                      })
                      .then(function(response) {
                          toggleLoading();
                          $this.closest('.item-ct').remove();
                          $('#popup_delete_memo').modal('hide');
                      }, function(error) {
                          toggleLoading();
                          $('#popup_delete_memo').modal('hide');
                      });
              });

          $('#popup_delete_memo').modal('show');

      });
      
      $(document).ready(function() {
    	  $("#owl_memo").owlCarousel({
              singleItem: true,
              pagination: true,
              autoPlay: false,
              stopOnHover:true,
              navigation: true,
              addClassActive: true
          });
      });
</script>