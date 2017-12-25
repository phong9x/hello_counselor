<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div id="popup-psychlogical" class=" popup-facetalk popup-psychlogical" style="height: 100% !important;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>심리검사 사이트 정보</h4>
            </div>
            <div class="modal-body">
                <div class="modal-body-title"><span>심리검사 사이트 접속 시 아래의 계정으로 로그인하시면 됩니다.</span></div>
                <div class="modal-list">
                    <ul>
                        <li>
                            <span class="title" style="height: 100% !important;line-height: 65px;">가이던스</span>
                            <p style="margin-bottom: 0;padding-top: 5px;line-height: 30px;"><span>아이디</span><span class="color4">${user.username}</span></p>
                            <p style="margin-bottom: 0;padding-bottom: 5px;line-height: 30px;"><span>비밀번호</span><span class="color4">
                            <fmt:formatDate pattern = "yyyyMMdd" value = "${user.dayOfBirth}" />
                            </span></p>
                            <p style="margin-bottom: 0;padding-bottom: 5px;line-height: 30px;"><span>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</span><span class="color4">
                          		로그인 후 수정 가능합니다
                            </span></p>
                        </li>
                        <li><span class="title">어세스타</span><span>직접 가입하신 계정으로 로그인</span></li>
                    </ul>
                </div>
                <div class="modal-footer">
                    <a target="_blank" href="http://guidance.hellowithyou.com" class="link1 link-update1">가이던스 바로가기</a>
                    <a target="_blank" href="http://www.career4u.net/Main/Main.asp" class="link2 link-update1">어세스타 바로가기</a>
                </div>
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
                      beforeSend: toggleLoading
                  })
                  .then(function(response) {
                      toggleLoading();
                      window.close();
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
</script>