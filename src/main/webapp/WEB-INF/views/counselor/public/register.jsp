<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="page-container">

    <div class="member page-content-wrapper counseling-history register">
        <div class="page-title">
            <div class="title">
                <span>심리상담사 등록 신청</span>
            </div>
        </div>
        <div class="page-content">
            <div class="container">
                <div id="slide-register">
                    <div class="owl-carousel owl-theme">
                        <div class="item slide1">
                            <div class="item-title">
                                <span>아래의 서비스 이용약관과 개인정보 취급방침 내용을 반드시 읽어 보시고 동의해 주세요.</span>
                            </div>
                            <div class="item-content row">
                                <div class="item-main-content">
                                    <div class="item-ct">
                                        <div class="item-top">
                                            <span class="title">서비스 이용약관</span>
                                            <div class="item radio-checkboxstyle2">
                                                <input type="checkbox" id="terms1" /><span>동의합니다</span>
                                            </div>
                                            <div class="top-note terms_error" style="display:none">약관에 동의해주세요!</div>
                                        </div>
                                        <div class="content">
                                            ${termsOfService.content}
                                        </div>
                                    </div>
                                </div>

                                <div class="item-main-content">
                                    <div class="item-ct">
                                        <div class="item-top">
                                            <span class="title">개인정보 취급방침</span>
                                            <div class="item radio-checkboxstyle2">
                                                <input type="checkbox" id="terms2"/><span>동의합니다</span>
                                            </div>
                                            <div class="top-note terms_error" style="display:none">약관에 동의해주세요!</div>
                                        </div>
                                        <div class="content">
                                            ${privacyPolicy.content}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="main-submit">
                                <button id="btnRegister2_backlist">
                                    <span>신청 취소</span>
                                </button>
                                <button class="v2" id="btnRegister1">
                                    <span>다음 단계</span>
                                </button>
                            </div>
                        </div>

                        <div class="item form-register">
                            <form id="form_register" method="post" action="/public/register" enctype="multipart/form-data">
                                <div class="item-main-content">
                                    <div class="item-main">
                                        <div class="item-top">
                                            <span class="number">01</span>
                                            <span class="title">가입을 위한 기본 정보를 입력해 주세요.</span>
                                            ${userEntity}
                                        </div>
                                        <div class="item-content">
                                            <div class="content-top">
                                                <div class="md-content">
                                                    <div class="md-item">
                                                        <span class="title">아이디(이메일)</span>
                                                        <div class="item-ct input-form">
                                                            <input type="text" name="email" data-required="1" class="form-control" data-validate="false" />
                                                            <button class="register" id="btnCheckId">중복확인</button>
                                                        </div>
                                                    </div>
                                                    <div class="md-item">
                                                        <span class="title">비밀번호</span>
                                                        <div class="item-ct input-form">
                                                            <input type="password" name="pwd" data-required="1" placeholder="8-20자 이내, 영문+숫자+특수문자 혼용" class="form-control" data-validate="false"/>
                                                            <span class="text-red notice" style="margin-left: 1em"></span>
                                                        </div>
                                                    </div>
                                                    <div class="md-item">
                                                        <span class="title">비밀번호 확인</span>
                                                        <div class="item-ct input-form">
                                                            <input type="password" name="pwdConfirm" data-required="1" placeholder="8-20자 이내, 영문+숫자+특수문자 혼용" class="form-control" data-validate="false"/>
                                                            <span class="text-red notice" style="margin-left: 1em"></span>
                                                        </div>
                                                    </div>
                                                    <div class="md-item">
                                                        <span class="title">이름</span>
                                                        <div class="item-ct input-form">
                                                            <input type="text" name="fullname" data-required="1" class="form-control" data-validate="false"/>
                                                        </div>
                                                    </div>
                                                    <div style="height: 64px;" class="md-item">
                                                        <span class="title">휴대폰번호</span>
                                                        <div class="item-ct input-form">
                                                            <input type="text" name="mobilephone" data-required="1" placeholder="-없이 입력" class="form-control" data-validate="false"/>
                                                            <button class="register" id="btnSendToken">인증번호 발송</button>
                                                        </div>
                                                        <div class="item-ct input-form">
                                                            <input type="text" name="token" data-required="1" placeholder="인증번호 입력" class="form-control"/>
                                                            <button class="register" id="btnCheckToken">인증번호 확인</button>
                                                            <span class="date" id="countdown_timer" style="display: none"><span id="mm">02</span>:<span id="ss">59</span></span>
                                                        </div>
                                                    </div>
                                                    <div class="md-item">
                                                        <span class="title">생년월일</span>
                                                        <jsp:useBean id="now" class="java.util.Date" />

                                                        <div class="item-ct input-form">
                                                            <div class="item-select">
                                                                <select name="dobYear" title="성명" class="form-control selectpickermember">
                                                                    <option value="0">선택</option>
                                                                    <fmt:formatDate value="${now}" pattern="yyyy" var="year" />
                                                                    <c:forEach begin="1900" end="${year}" step="1" var="i">
                                                                        <option value="${i}">${i}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                <span class="note-select">년</span>
                                                            </div>
                                                            <div class="item-select">
                                                                <select name="dobMonth" title="성명" class="form-control selectpickermember">
                                                                    <option value="0">선택</option>
                                                                    <c:forEach begin="1" end="12" step="1" var="i">
                                                                        <option value="${i}">${i}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                <span class="note-select">월</span>
                                                            </div>
                                                            <div class="item-select">
                                                                <select name="dobDay" title="성명" class="form-control selectpickermember">
                                                                    <option value="0">선택</option>
                                                                    <c:forEach begin="1" end="31" step="1" var="i">
                                                                        <option value="${i}">${i}</option>
                                                                    </c:forEach>
                                                                </select><span class="note-select">일</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div style="height: 64px;" class="md-item">
                                                        <span class="title">사업자 구분</span>
                                                        <div style="height: 28px;" class="item-ct">
                                                            <span class="content">* 저장 후에는 수정 불가</span>
                                                        </div>
                                                        <div class="item-ct input-form">
                                                            <div class="item-radio">
                                                                <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                    <label><input type="radio" value="2" name="counselType" class="radio1"/></label>
                                                                </div>
                                                                <span class="note-radio">개인</span>
                                                            </div>
                                                            <div class="item-radio">
                                                                <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                    <label><input type="radio" value="3" name="counselType" class="radio1"/></label>
                                                                </div>
                                                                <span class="note-radio">센터 소속</span>
                                                            </div>
                                                            <div class="item-select style2">
                                                                <select name="counselCenter" class="form-control selectpickermember" disabled>
                                                                    <option value="0">센터 선택</option>
                                                                    <c:forEach items="${businesses}" var="item">
                                                                        <option value="${item.id}">${item.centerName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item-main">
                                        <div class="item-top">
                                            <span class="number">02</span>
                                            <span class="title">사용자들에게 보여지는 심리상담사 정보를 입력해 주세요. </span>
                                        </div>
                                        <div class="item-content">
                                            <div class="content-top">
                                                <div class="md-content">
                                                    <div style="height: 74px;" class="md-item">
                                                        <span class="title">프로필 이미지</span>
                                                        <div class="item-ct input-form">
                                                            <div class="inputfile avatar">
                                                                <div class="tr-file-upload image-upload">
                                                                    <div class="preview-image img-avatar">
                                                                        <img src="/theme/admin/assets/images/avatar_men.png" alt="avatar" id="previewAvatar" />
                                                                    </div>
                                                                    <p class="note-avatar">프로필 사진은 내담자에게 가장 먼저 보여집니다.</p>
                                                                    <p class="note-avatar">신뢰감을 줄 수 있도록 단정한 사진을 등록해주세요.(정면 사진, 하얀색 배경 권장)</p>
                                                                    <label class="btn green btn-select-file">찾아보기
                                                                        <input type="file" name="avatar" id="fileAvatar" class="hidden input-file" />
                                                                    </label>
                                                                    <div class="input-icon input-large input-inline right"></div>
                                                                    <div class="note-img">
                                                                        <p>. jpg, gif, png (3MB 이하)</p>
                                                                        <p>. 최소 300x300px 정사각형 형태로 업로드</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div style="height: 122px;" class="md-item">
                                                        <span class="title">상담 분야</span>
                                                        <div class="item-ct input-form">
                                                            <div class="row-check all-check" id="catList">
                                                                <c:forEach items="${categories}" var="item">
                                                                    <c:if test="${item.parentId eq 0}">
                                                                        <div class="item radio-checkboxstyle2">
                                                                            <input type="checkbox" value="${item.id}" name="counselorFields[${item.id}].category" data-name="category" checked="checked" />
                                                                            <span>${item.categoryName}${item.decription}</span>
                                                                            <c:if test="${item.id eq 9}">
                                                                                <span class="last-note">최대 5개까지 등록 가능</span>
                                                                            </c:if>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                            <div class="row-check all-check border" id="subCatList">
                                                                <c:forEach items="${categories}" var="item">
                                                                    <c:if test="${item.parentId gt 0}">
                                                                        <div class="item radio-checkboxstyle2">
                                                                            <input type="checkbox" value="${item.id}" name="counselorFields[${item.id}].category" data-name="category" checked="checked" />
                                                                            <span>${item.categoryName} ${item.decription}</span>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div style="height: 123px;" class="md-item">
                                                        <span class="title">소개 문구</span>
                                                        <div class="item-ct input-form">
                                                            <textarea name="introduce" rows="5" maxlength="500" placeholder="500자 이내로 입력" data-error-container="#editor2_error" class="rw-ckeditor form-control"></textarea>
                                                            <span class="max-leng"><span class="remaining">0</span>/500</span>
                                                        </div>
                                                    </div>
                                                    <div style="height: 105px;" class="md-item">
                                                        <span class="title">학력</span>
                                                        <div style="height: 28px;" class="item-ct">
                                                            <span class="content">. 최종 졸업 학교를 입력 후 졸업증명서를 업로드 해주세요.</span>
                                                        </div>
                                                        <div class="item-ct input-form">
                                                            <div class="item-list">
                                                                <div class="item-radio">
                                                                    <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                        <label><input type="radio" value="1" name="eduLevel" class="radio1 edu_ck"/></label>
                                                                    </div>
                                                                </div>
                                                                <input  type="text" name="university" class="edu_university form-control" disabled/>
                                                                <span   class="note-input">대학교</span>
                                                                <input  type="text" name="faculty" class="edu_major form-control" disabled/>
                                                                <span   class="note-input">전공</span>
                                                                <button class="register btnEduFile" disabled>찾아보기</button>
                                                                <input type="file" name="eduFile" class="eduFileInp" style="display: none" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                <span class="edu_file"></span>
                                                                <span class="edu_file_remove"></span>
                                                            </div>
                                                            <div class="item-list">
                                                                <div class="item-radio">
                                                                    <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                        <label><input type="radio" value="2" name="eduLevel" class="radio1 edu_ck"/></label>
                                                                    </div>
                                                                </div>
                                                                <input  type="text" name="university" class="edu_university form-control" disabled/>
                                                                <span   class="note-input">대학원</span>
                                                                <input  type="text" name="faculty" class="edu_major form-control" disabled/>
                                                                <span   class="note-input">전공</span>
                                                                <button class="register btnEduFile" disabled>찾아보기</button>
                                                                <input type="file" name="eduFile" class="eduFileInp" style="display: none" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                <span class="edu_file"></span>
                                                                <span class="edu_file_remove"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="md-item stylev2" id="listProfile">
                                                        <span class="title">주요 약력</span>
                                                        <div class="item-ct input-form">
                                                            <div id="profileList">
                                                                <div class="item-list">
                                                                    <input type="text" name="biographies[0].biography" data-name="biography" placeholder="약력 입력" data-name="biography" style="width: 302px;" class="form-control"/>
                                                                </div>
                                                            </div>
                                                            <div class="item-list">
                                                                <button style="margin-left: 0;" class="register" id="btnAddProfile">+ 추가</button>
                                                                <span class="last-note">최대 5개까지 등록 가능</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="md-item stylev2" id="dipList">
                                                        <span class="title">자격증</span>
                                                        <div style="height: 28px;" class="item-ct">
                                                            <span class="content">. 필수 선택 포함 최대 5개까지 등록 가능 </span>
                                                            <span class="content color2">필수 선택 (중복 선택 가능) </span>
                                                        </div>
                                                        <div class="item-ct input-form">
                                                            <div class="item-list-style2">
                                                                <div class="item-list-left itemdes-style">
                                                                    <div class="top">
                                                                        <span class="content">(사)한국상담학회</span>
                                                                    </div>
                                                                    <div class="list-main">
                                                                        <div class="item-main">
                                                                            <div class="item-check">
                                                                                <div class="item radio-checkboxstyle2">
                                                                                    <input type="checkbox"  name="certificates[0].diploma"   value="전문상담사 1급(수련감독자)" data-name="diploma" class="diploma_ck" />
                                                                                    <input type="hidden"   name="certificates[0].type"      value="1" />
                                                                                    <input type="hidden" name="certificates[0].author" value="(사)한국상담학회" class="form-control"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="item-des" style="width: 35%;">
                                                                                <p>전문상담사 1급 <br/>(수련감독자)</p>
                                                                            </div>
                                                                            <div class="item-main-ct" style="width: 58%;">
                                                                            	<input id="IPIssuedDate" disabled name="certificates[0].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                <button style="float: left !important;margin-left: 10px !important;font-size: 11px;line-height: 12px;" class="register btnDipFile" disabled>자격증 사본 등록</button>
                                                                                <input type="file" name="certificates[0].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                <p style="text-align: right;">
                                                                                    <span class="diploma_file_name"></span>
                                                                                    <span class="diploma_file_remove"></span>
                                                                                </p>
                                                                            </div>
                                                                        </div>
                                                                        <div class="item-main">
                                                                            <div class="item-check">
                                                                                <div class="item radio-checkboxstyle2">
                                                                                    <input type="checkbox" name="certificates[1].diploma"   value="전문상담사 1급" data-name="diploma" class="diploma_ck" />
                                                                                    <input type="hidden"   name="certificates[1].type"      value="1" />
                                                                                    <input type="hidden" name="certificates[1].author" value="(사)한국상담학회" class="form-control"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="item-des" style="width: 35%;">
                                                                                <p>전문상담사 1급</p>
                                                                            </div>
                                                                            <div class="item-main-ct" style="width: 58%;">
                                                                            	<input id="IPIssuedDate" disabled name="certificates[1].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                <button class="register btnDipFile" disabled>자격증 사본 등록</button>
                                                                                <input type="file" name="certificates[1].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                <p style="text-align: right;">
                                                                                    <span class="diploma_file_name"></span>
                                                                                    <span class="diploma_file_remove"></span>
                                                                                </p>
                                                                            </div>
                                                                        </div>
                                                                        <div class="item-main">
                                                                            <div class="item-check">
                                                                                <div class="item radio-checkboxstyle2">
                                                                                    <input type="checkbox" name="certificates[2].diploma" value="전문상담사 2급" data-name="diploma" class="diploma_ck" />
                                                                                    <input type="hidden"   name="certificates[2].type"  value="1" />
                                                                                    <input type="hidden" name="certificates[2].author" value="(사)한국상담학회" class="form-control"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="item-des" style="width: 35%;">
                                                                                <p>전문상담사 2급</p>
                                                                            </div>
                                                                            <div class="item-main-ct" style="width: 58%;">
                                                                            	<input id="IPIssuedDate" disabled name="certificates[2].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                <button class="register btnDipFile" disabled>자격증 사본 등록</button>
                                                                                <input type="file" name="certificates[2].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                <p style="text-align: right;">
                                                                                    <span class="diploma_file_name"></span>
                                                                                    <span class="diploma_file_remove"></span>
                                                                                </p>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="item-list-left itemdes-style">
                                                                    <div class="top">
                                                                        <span class="content">(사)한국상담심리학회</span>
                                                                    </div>
                                                                    <div class="list-main">
                                                                        <div class="item-main">
                                                                            <div class="item-check">
                                                                                <div class="item radio-checkboxstyle2">
                                                                                    <input type="checkbox"  name="certificates[3].diploma"  value="상담심리사 1급 (주 수퍼바이저)" data-name="diploma" class="diploma_ck" />
                                                                                    <input type="hidden"    name="certificates[3].type"     value="1" />
                                                                                    <input type="hidden" name="certificates[3].author" value="(사)한국상담심리학회" class="form-control"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="item-des" style="width: 35%;">
                                                                                <p>상담심리사 1급<br/>(주 수퍼바이저)</p>
                                                                            </div>
                                                                            <div class="item-main-ct" style="width: 58%;">
                                                                            	<input id="IPIssuedDate" disabled name="certificates[3].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                <button class="register btnDipFile none-float" disabled>자격증 사본 등록</button>
                                                                                <input type="file" name="certificates[3].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                <p style="text-align: right;">
                                                                                    <span class="diploma_file_name"></span>
                                                                                    <span class="diploma_file_remove"></span>
                                                                                </p>
                                                                            </div>
                                                                        </div>
                                                                        <div class="item-main">
                                                                            <div class="item-check">
                                                                                <div class="item radio-checkboxstyle2">
                                                                                    <input type="checkbox"  name="certificates[4].diploma"  value="상담심리사 1급" data-name="diploma" class="diploma_ck" />
                                                                                    <input type="hidden"    name="certificates[4].type"     value="1" />
                                                                                    <input type="hidden" name="certificates[4].author" value="(사)한국상담심리학회" class="form-control"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="item-des" style="width: 35%;">
                                                                                <p>상담심리사 1급</p>
                                                                            </div>
                                                                            <div class="item-main-ct" style="width: 58%;">
                                                                            	<input id="IPIssuedDate" disabled name="certificates[4].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                <button class="register btnDipFile none-float" disabled>자격증 사본 등록</button>
                                                                                <input type="file" name="certificates[4].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                <p style="text-align: right;">
                                                                                    <span class="diploma_file_name"></span>
                                                                                    <span class="diploma_file_remove"></span>
                                                                                </p>
                                                                            </div>
                                                                        </div>
                                                                        <div class="item-main">
                                                                                <div class="item-check">
                                                                                <div class="item radio-checkboxstyle2">
                                                                                    <input type="checkbox" name="certificates[5].diploma"   value="상담심리사 2급" data-name="diploma" class="diploma_ck" />
                                                                                    <input type="hidden"   name="certificates[5].type"      value="1" />
                                                                                    <input type="hidden" name="certificates[5].author" value="(사)한국상담심리학회" class="form-control"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="item-des" style="width: 35%;">
                                                                                <p>상담심리사 2급</p>
                                                                            </div>
                                                                            <div class="item-main-ct" style="width: 58%;">
                                                                           		<input id="IPIssuedDate" disabled name="certificates[5].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                <button class="register btnDipFile none-float" disabled>자격증 사본 등록</button>
                                                                                <input type="file" name="certificates[5].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                <p style="text-align: right;">
                                                                                    <span class="diploma_file_name"></span>
                                                                                    <span class="diploma_file_remove"></span>
                                                                                </p>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="item-list-left itemdes-style" style="margin-top: 10px">
                                                                    <div class="top">
                                                                        <span class="content">(사)한국임상심리학회</span>
                                                                    </div>
                                                                    <div class="list-main">
                                                                        <div class="item-main">
                                                                            <div class="item-check">
                                                                                <div class="item radio-checkboxstyle2">
                                                                                    <input type="checkbox" name="certificates[6].diploma"  value="임상심리전문가" data-name="diploma" class="diploma_ck" />
                                                                                    <input type="hidden"   name="certificates[6].type"     value="1" />
                                                                                    <input type="hidden" name="certificates[6].author" value="(사)한국임상심리학회" class="form-control"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="item-des" style="width: 35%;">
                                                                                <p>임상심리전문가</p>
                                                                            </div>
                                                                            <div class="item-main-ct" style="width: 58%;">
                                                                            	<input id="IPIssuedDate" disabled name="certificates[6].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                <button class="register btnDipFile" disabled>자격증 사본 등록</button>
                                                                                <input type="file" name="certificates[6].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                <p style="text-align: right;">
                                                                                    <span class="diploma_file_name"></span>
                                                                                    <span class="diploma_file_remove"></span>
                                                                                </p>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="item-ct input-form">
                                                            <span class="content">추가 등록</span>
                                                        </div>
                                                        <div id="addDipList">

                                                        </div>
                                                        <div class="item-ct input-form">
                                                            <div style="margin-top: 10px;" class="item-list">
                                                                <button style="margin-left: 0;" class="register" id="btnAddDiploma">+ 추가</button>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="md-item stylev2">
                                                        <span class="title">증명 서류 등록</span>
                                                        <div class="item-ct input-form">
                                                            <span class="content">※ 이력서 및 기타 증명서를 등록해주세요. (중복 등록가능)</span>
                                                            <div style="margin-top: -4px;" class="item-list">
                                                                <button style="margin-left: 0;" class="register" id="btnCerFile">찾아보기</button>
                                                                <input type="file" name="certificateFiles" style="display: none" class="cerFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" multiple />
                                                                <div class="list-remove" id="cerList">

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div style="min-height: 52px;" class="md-item">
                                                        <span style="top: 20px;line-height: 16px;" class="title">자격증 취득 후</span>
                                                        <span style="top: 40px;line-height: 16px;" class="title">상담 횟수</span>
                                                        <div style="padding-top: 10px;" class="item-ct input-form">
                                                            <div class="item-select style2">
                                                                <select name="counseledTimes" title="성명" class="form-control selectpickermember">
                                                                    <option value="0">상담 횟수 선택</option>
                                                                    <option value="100회 미만">100회 미만</option>
                                                                    <option value="100회 이상">100회 이상</option>
                                                                    <option value="200회 이상">200회 이상</option>
                                                                    <option value="300회 이상">300회 이상</option>
                                                                    <option value="500회 이상">500회 이상</option>
                                                                    <option value="1000회 이상">1,000회 이상</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item-main style2">
                                        <div class="item-top">
                                            <span class="number">03</span>
                                            <span class="title">아래의 추가 정보를 입력해 주세요.</span>
                                        </div>
                                        <div class="item-content">
                                            <div class="content-top">
                                                <div class="md-content">
                                                    <div class="md-item">
                                                        <span style="margin-top: -20px;" class="title">(주)어세스타</span>
                                                        <span style="margin-top: 0px;" class="title">교육 수료 여부</span>
                                                        <div class="item-ct input-form">
                                                            <span style="float: left;margin-right: 10px;width: initial;" class="content">MBTI 자격교육 보수과정을 수료하셨습니까?</span>
                                                            <div class="item-radio">
                                                                <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                    <label>
                                                                        <input type="radio" value="1" name="mbti" class="radio1"/>
                                                                    </label>
                                                                </div>
                                                                <span class="note-radio">예</span>
                                                            </div>
                                                            <div class="item-radio">
                                                                <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                    <label>
                                                                        <input type="radio" value="0" name="mbti" class="radio1"/>
                                                                    </label>
                                                                </div>
                                                                <span class="note-radio">아니오</span>
                                                            </div>
                                                            <input type="text" name="mbtiSchool" placeholder="수료번호 입력" class="form-control" disabled/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="main-submit">
                                            <button class="submit" id="btnRegister2">신청 완료</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="item slide3">
                            <div class="item-content" style="width: 460px;">
                                <div class="item-title">
                                    <span>감사합니다. 등록 신청이 완료되었습니다.</span>
                                </div>
                                <div class="text-content">
                                    <p>내부 심사는 영업일 기준 5일 정도 소요됩니다. </p>
                                    <p>심사 결과는 입력하신 이메일 주소로 알려 드리겠습니다. </p>
                                </div>
                            </div>
                            <div class="main-submit">
                                <button id="btnRegister3" onclick="window.location='/'" tabindex="-1">
                                    <span>처음으로</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="popup_terms" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-register modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"><span class="search">search</span><span class="color2">심리상담사 등록 신청 전</span><span>아래의 내용을 확인해주세요!</span>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <div class="item">
                    <div class="item-left">
                        <div class="title">01</div>
                        <div class="content"><span>심리상담 진행 </span><span>관련 내용</span></div>
                    </div>
                    <div class="item-right">
                        <p>. 내담자는 모바일 App에서, 심리상담사는 Web에서 상담 진행 (태블릿 PC 가능)</p>
                        <p>. 상담 전 내담자의 문진 검토부터 실제 상담, 심리검사 요청 및 결과 상담까지 오프라인 상담센터와 유사한 형태의 서비스 기능 제공</p>
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        <div class="title">02</div>
                        <div class="content"><span>지원 </span><span>자격</span></div>
                    </div>
                    <div class="item-right">
                        <p>. (사)한국상담심리학회, (사)한국상담학회 2급 이상 또는 (사)한국임상심리학회 임상심리전문가 자격증을 소지하신 분</p>
                        <p>. 카메라 기능이 장착된 데스크탑/노트북을 활용하여 화상상담이 가능하신 분</p>
                        <p>. 2년 이상 상담 경력자 우대</p>
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        <div class="title">03</div>
                        <div class="content"><span>상담 </span><span>비용 </span></div>
                    </div>
                    <div class="item-right">
                        <p>. 심리상담사의 자격사항 및 경력에 따라 차등 설정</p>
						<p>. 실지급 비용은 회사 내부 정책 기준에 따름</p>
						<p>. Hello 심리상담사 등록 최종 승인 후, 확인가능</p>
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        <div class="title">04</div>
                        <div class="content"><span>심리상담사 </span><span>등록 신청 방법 </span></div>
                    </div>
                    <div class="item-right">
                        <p>. Web페이지에서 양식에 맞게 작성하고, 증명 서류 업로드</p>
                        <p>. 증명 서류 : 이력서 1부, 최종학력 졸업증명서 1부, 자격증 사본</p>
                    </div>
                </div>
                <div class="item">
                    <div class="item-left">
                        <div class="title">05</div>
                        <div class="content"><span>문의처 </span></div>
                    </div>
                    <div class="item-right">
                        <p class="email">hello_counselor@aimmed.com</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="popup_email_verified" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>사용가능한 이메일입니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_email_wrong" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>이메일 형식이 올바르지 않습니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_mobilephone_verified" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>인증되었습니다</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_mobilephone_token_sent" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>인증번호가 전송되었습니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_mobilephone_token_wrong" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>인증번호가 일치하지 않습니다. 다시 확인해주세요.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_input_username_empty" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>아이디를 입력해주세요.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_input_username_existed" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>사용중인 ID입니다. 다른 ID를 입력해주세요.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_input_username_paused" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>이미 입력된 정보가 있습니다.해당 정보를 불러옵니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_input_file_size_limit" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>첨부파일은 5MB 이하만 가능합니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_input_avatar_size_limit" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>이미지는 3MB 이하만 등록 가능합니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_input_file_content_wrong_format" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>파일 형식이 맞지 않습니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_certificate_limit_exceeded" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>자격증 정보는 5개까지만 선택 가능합니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_form_errors" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>입력하지 않은 항목이 있습니다.</strong>
                <ul id="error_fields">

                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
    	$('#btnRegister2_backlist').on('click', function(evt) {
    		window.location = "/";
    	});
    	

        var EMAIL_PATTERN = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        var FILES         = [];

        /*******************************************************
        *
        * Owlcarousel
        *
        *******************************************************/
        var owl = $("#slide-register .owl-carousel");
        owl.owlCarousel({
            singleItem      :   true,
            pagination      :   true,
            autoPlay        :   false,
            stopOnHover     :   false,
            touchDrag       :   false,
            mouseDrag       :   false,
            nav             :   false,
            addClassActive  :   true,
            autoHeight      :   true
        });

        function owlNext() {
            owl.trigger('owl.next');
            window.scrollTo(0,0);
        }

        function owlLast() {
            owl.trigger('owl.next');
            owl.trigger('owl.next');
            window.scrollTo(0,0);
        }

        /*******************************************************
        *
        * Check finish
        *
        *******************************************************/
        var hash = location.hash;
        if (hash == '#finish') {
            owlLast();
        } else {
            $('#popup_terms').modal('show');
        }

        /*******************************************************
        *
        * Submit register step 1
        *
        *******************************************************/
        $('#btnRegister1').on('click', function(evt) {
			
            evt.preventDefault();

            var $terms1 = $('#terms1');
            var $terms2 = $('#terms2');
            var isError = false;
			var notChecked = false;
            // reset error
            $('.terms_error').css('display', 'none');

            notChecked = !$terms1.is(':checked');
            if (notChecked) {
            	isError = true;
                $terms1.closest('.item').next('.terms_error').css('display', '');
            }

            notChecked = !$terms2.is(':checked');
            if (notChecked) {
            	isError = true;
                $terms2.closest('.item').next('.terms_error').css('display', '');
            }
			
            if (!isError) {
            	var isIE = /*@cc_on!@*/false || !!document.documentMode;
            	if(isIE){
            		$('.owl-item:eq(0)').css('display', 'none');
            	}
                owlNext();
            }
        });

        /*******************************************************
        *
        * Counsel type
        *
        *******************************************************/
        $('[name=counselType]').on('change', function(evt) {
            var type = evt.target.value;
            var $selectCenter = $('[name=counselCenter]');
            if (type == 3) {
                $selectCenter.removeAttr('disabled');
            } else {
                $selectCenter.attr('disabled', 'disabled');
            }
            $selectCenter.selectpicker('refresh');
        });

        /*******************************************************
    	*
    	* Check ID
    	*
    	*******************************************************/
    	$('#popup_input_username_paused').on('click', '.popup_accept', function(evt) {
    	    var username = $('[name=email]').val();
    	    window.location.href = '/public/postpone?username=' + encodeURIComponent(username);
    	});

    	$('#btnCheckId').on('click', function(evt) {

    		evt.preventDefault();

    		var $username   = $('[name=email]');
    		var username    = $username.val();
    		if (!username) {
    		    $('#popup_input_username_empty').modal('show');
    		} else if (!EMAIL_PATTERN.test(username)) {
    		    $('#popup_email_wrong').modal('show');
    		} else {
                $
                    .ajax({
                        url 	    : '/public/checkexistence',
                        method 	    : 'post',
                        data 	    : {
                            username : username
                        },
                        beforeSend  : toggleLoading
                    })
                    .then(function(res) {
                        toggleLoading();
                        if (res.status === -1) {
                        	$('#popup_email_verified').modal('show');
                            $username.attr('data-validate', true);
                        } else if (res.status === 3) {
                            $('#popup_input_username_paused').modal('show');
                        } else {
                            $('#popup_input_username_existed').modal('show');
                        }
                    }, function(err) {
                        toggleLoading();
                        $('#popup_email_verified').modal('show');
                        $username.attr('data-validate', true);
                    });
    		}
    	});

    	/*******************************************************
        *
        * Check password
        *
        *******************************************************/
        var regexPwd = new RegExp("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$&+,:;=?@#|'<>.^*()%!-])");
        function checkPwd(password) {
            return regexPwd.test(password) && password.length >= 8 && password.length <= 20;
        }

        function checkPwdConfirm(password, passwordConfirm) {
            return password === passwordConfirm;
        }

        $('[name=pwd]').on('blur', function(evt) {
            var pwd = $(this).val();
            if (!checkPwd(pwd)) {
                $(this).next().text('비밀번호는 영문+숫자+특수문자 혼용하여 8~20자 이내로 설정해야 합니다.');
                $(this).attr('data-validate', false);
            } else {
                $(this).next().text('');
                $(this).attr('data-validate', true);
            }
        });

        $('[name=pwdConfirm]').on('blur', function(evt) {
            var pwd         = $('[name=pwd]').val();
            var pwdConfirm  = $(this).val();
            if (!(checkPwd(pwd) && checkPwdConfirm(pwd, pwdConfirm))) {
                $(this).next().text('비밀번호가 일치하지 않습니다.');
                $(this).attr('data-validate', false);
            } else {
                $(this).next().text('');
                $(this).attr('data-validate', true);
            }
        });

        /*******************************************************
        *
        * Fullname
        *
        *******************************************************/
        $('[name=fullname]').on('keyup', function(evt) {
            if ($(this).val().trim()) {
                $(this).attr('data-validate', true);
            } else {
                $(this).attr('data-validate', false);
            }
        });

        /*******************************************************
    	*
    	* Send token
    	*
    	*******************************************************/
    	var timer;

    	$('#btnSendToken').on('click', function(evt) {

    		evt.preventDefault();

    		if (timer) {
    		    clearInterval(timer);
    		}
    		$
    			.ajax({
    				url 	    : '/public/sendToken',
    				method 	    : 'post',
    				data 	    : {
    					phone 	: $('[name=mobilephone]').val()
    				},
    				beforeSend  : toggleLoading
    			})
    			.then(function(res) {
    				toggleLoading();

    				$('#popup_mobilephone_token_sent').modal('show');
    				$('#countdown_timer').css('display', '');

    				var token 		= res.token;
    				console.log("token:" + token);
    				var countdown 	= 180;
    				timer 		    = setInterval(function() {
                        countdown--;
                        if (countdown <= 0) clearInterval(timer);

                        var mm = parseInt(countdown/60)
                        var ss = countdown - mm*60;

                        $('#mm').text(mm < 10 ? '0' + mm : mm);
                        $('#ss').text(ss < 10 ? '0' + ss : ss);

    				}, 1000);

    				$('#btnCheckToken').off('click').on('click', function(evt) {

    				    evt.preventDefault();

    					if (countdown <= 0) return;
    					clearInterval(timer);
    					if (token === $('[name=token]').val()) {
    					    $('#popup_mobilephone_verified').modal('show');
    					    $('[name=mobilephone]').attr('data-validate', true);
    					} else {
    					    $('#popup_mobilephone_token_wrong').modal('show');
    					}
    				});

    			}, function(error) {
    			    toggleLoading();
    				console.error(error);
    			});
    	});

    	/*******************************************************
        *
        * Date of birth
        *
        *******************************************************/
        $('[name=dobYear]').on('shown.bs.select', function(evt) {
            // first display 1980
            var selected = evt.target.value;
            if (selected == 0) {
                // 81 items, 24px/item
                $(this).prev().find('ul').scrollTop(1944);
            }
        });

    	/*******************************************************
    	*
    	* Avatar upload
    	*
    	*******************************************************/
    	$('#fileAvatar').on('change', function(evt) {

    		var type = evt.target.files[0].type;
            var size = evt.target.files[0].size;

            if (isValidImage(type)) {
                if (size <= 3145728) {
                    $('#previewAvatar').attr('src', URL.createObjectURL(evt.target.files[0]));
                } else {
                    $('#popup_input_avatar_size_limit').modal('show');
                    return;
                }
            } else {
                $('#popup_input_file_content_wrong_format').modal('show');
                return;
            }
    	});

    	/*******************************************************
        *
        * Category
        *
        *******************************************************/
        $('[data-name=category][value=9]').on('change', function(evt) {
            var isChecked = $(this).is(':checked');
            if (isChecked) {
                $('#subCatList')
                    .find('input[data-name=category]')
                    .removeAttr('disabled')
                    .attr('checked', 'checked');

                $.uniform.update();
            } else {
                $('#subCatList')
                    .find('input[data-name=category]')
                    .removeAttr('checked')
                    .attr('disabled', 'disabled');

                $.uniform.update();
                $('#subCatList')
                    .find('input[data-name=category]').closest('.checker')
                    .removeClass('disabled');
            }
        });

        $('#subCatList').find('input[data-name=category]').on('change', function(evt) {
            var totalChecked
                = $('#subCatList')
                    .find('input[data-name=category]')
                    .filter(function(index, element) {
                        return $(element).is(':checked');
                    }).length;

            if (totalChecked == 0) {
                $('[data-name=category][value=9]').removeAttr('checked');
                $.uniform.update();

                $('#subCatList')
                    .find('input[data-name=category]')
                    .removeAttr('checked')
                    .attr('disabled', 'disabled');

                $.uniform.update();
                $('#subCatList')
                    .find('input[data-name=category]').closest('.checker')
                    .removeClass('disabled');

            } else {
                $('[data-name=category][value=9]').attr('checked', 'checked');
                $.uniform.update();
            }

        });

    	/*******************************************************
        *
        * Introduce
        *
        *******************************************************/
        $('[name=introduce]').on('keyup', function(evt) {
            $(this).next('.max-leng').find('.remaining').text($(this).val().length);
        });

    	/*******************************************************
        *
        * Education
        *
        *******************************************************/
        $('.edu_ck').on('change', function(evt) {
            var checked     = $(this).val();
            var unchecked   = checked == 1 ? 2 : 1;

            var $parentChecked     = $('[name=eduLevel][value=' + checked + ']').closest('.item-radio');
            var $parentUnchecked   = $('[name=eduLevel][value=' + unchecked + ']').closest('.item-radio');

            // set checked
            $parentChecked.siblings('.edu_university').removeAttr('disabled');
            $parentChecked.siblings('.edu_major').removeAttr('disabled');
            $parentChecked.siblings('.btnEduFile').removeAttr('disabled');
            $parentChecked.siblings('.eduFileInp').removeAttr('disabled');
            // set unchecked
            $parentUnchecked.siblings('.edu_university').attr('disabled', 'disabled');
            $parentUnchecked.siblings('.edu_major').attr('disabled', 'disabled');
            $parentUnchecked.siblings('.btnEduFile').attr('disabled', 'disabled');
            $parentUnchecked.siblings('.eduFileInp').attr('disabled', 'disabled');
        });

        $('.btnEduFile').on('click', function(evt) {

            evt.preventDefault();

            if ($(this).attr('disabled')) return;
            $(this).siblings('.eduFileInp').trigger('click');
        });

        $('.eduFileInp').on('change', function(evt) {
            if ($(this).siblings('.btnEduFile').attr('disabled')) return;
            var type = evt.target.files[0].type;
            var size = evt.target.files[0].size;
            var name = evt.target.files[0].name;

            if (isValidImageOfFile(type)) {
                if (size <= 5242880) {
                    $(this).siblings('.edu_file').text(name);
                    $(this).siblings('.edu_file_remove').addClass('remove');
                } else {
                    $('#popup_input_file_size_limit').modal('show');
                    return;
                }
            } else {
                $('#popup_input_file_content_wrong_format').modal('show');
                return;
            }
        });

        $('.edu_file_remove').on('click', function(evt) {
            if (!$(this).hasClass('remove')) return;
            $(this).siblings('.edu_file').text('');
            $(this).siblings('.eduFileInp').val('');
            $(this).removeClass('remove');
        });

        /*******************************************************
        *
        * Diploma
        *
        *******************************************************/
        var requiredDiploma         = 0;
        var otherDiploma            = 0;

        $('.diploma_ck').on('change', function(evt) {
            var isChecked = $(this).is(':checked');
            if (isChecked) {
                requiredDiploma++;
                if (requiredDiploma + otherDiploma > 5) {
                    requiredDiploma--;
                    $(this).removeAttr('checked');
                    $('#popup_certificate_limit_exceeded').modal('show');
                } else if (requiredDiploma + otherDiploma == 5) {
                    $('#btnAddDiploma').attr('disabled', 'disabled');
                }

                // enable upload
                $(this).closest('.item-check').siblings('.item-main-ct').find('.btnDipFile').removeAttr('disabled');
                $(this).closest('.item-check').siblings('.item-main-ct').find('#IPIssuedDate').removeAttr('disabled');
            } else {
                requiredDiploma--;
                $('#btnAddDiploma').removeAttr('disabled');
                // disable upload
                $(this).closest('.item-check').siblings('.item-main-ct').find('.btnDipFile').attr('disabled', 'disabled');
                $(this).closest('.item-check').siblings('.item-main-ct').find('#IPIssuedDate').attr('disabled', 'disabled');
            }

            $.uniform.update();
        });

        $('.btnDipFile').on('click', function(evt) {

            evt.preventDefault();

            if ($(this).attr('disabled')) return;
            $(this).next().trigger('click');
        });

        $('.dipFileInp').on('change', function(evt) {
            if ($(this).prev().attr('disabled')) return;
            var type = evt.target.files[0].type;
            var size = evt.target.files[0].size;
            var name = evt.target.files[0].name;

            if (isValidImageOfFile(type)) {
                if (size <= 5242880) {
                    if (name.length > 20) {
                        var extensions  = name.match(/(\.\w+$)/g);
                        var _name       = name.slice(0,15) + '....' + (extensions ? extensions[0] : '');
                        $(this).next('p').find('.diploma_file_name').text(_name);
                    } else {
                        $(this).next('p').find('.diploma_file_name').text(name);
                    }
                    $(this).next('p').find('.diploma_file_remove').addClass('remove');
                } else {
                    $('#popup_input_file_size_limit').modal('show');
                    return;
                }
            } else {
                $('#popup_input_file_content_wrong_format').modal('show');
                return;
            }
        });

        $('.diploma_file_remove').on('click', function(evt) {
            if (!$(this).hasClass('remove')) return;
            $(this).prev('.diploma_file_name').text('');
            $(this).removeClass('remove');

            otherDiploma--;
            $('#btnAddDiploma').removeAttr('disabled');
        });

        $('#btnAddDiploma').on('click', function(evt) {

            evt.preventDefault();
            if ($(this).attr('disabled')) return;

            otherDiploma++;
            if (requiredDiploma + otherDiploma > 5) {
                otherDiploma--;
                $('#popup_certificate_limit_exceeded').modal('show');
                return;
            }
            if ((requiredDiploma + otherDiploma) == 5) {
                $('#btnAddDiploma').attr('disabled', 'disabled');
            }

            var id = 10 + Math.floor(Math.random() * 10);

            var html = '<div class="item-ct input-form">';
            html    +=      '<div class="item-list">';
            html    +=          '<input type="text" name="certificates[' + id + '].diploma"  data-name="diploma" placeholder="자격증 명" class="form-control"/>';
            html    +=          '<input type="text" name="certificates[' + id + '].author" placeholder="발급 기관" class="form-control"/>';
            html    +=          '<input type="text" name="certificates[' + id + '].issuedDate"  data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>';
            html    +=          '<input type="hidden"   name="certificates[' + id + '].type"     value="2" />';
            html    +=          '<button style="padding: 0 5px;" class="register dipAddBtnRemove">';
            html    +=              '<span class="empty"></span>';
            html    +=              '<span>삭제</span>';
            html    +=          '</button>';
            html    +=      '</div>';
            html    +=      '<div class="item-list">';
            html    +=          '<button style="margin-left: 0;" class="register btnAddDipFile">찾아보기</button>';
            html    +=          '<input type="file" name="certificates[' + id + '].file" style="display: none" class="dipAddFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />';
            html    +=          '<span></span>';
            html    +=          '<span class="dipAddFileBtnRemove"></span>'
            html    +=      '</div>';
            html    +=  '</div>';

            $(this).closest('.item-ct').prev('#addDipList').append(html);

            $('.date-picker').datepicker({
                rtl: Metronic.isRTL(),
                orientation: "left",
                autoclose: true,
                language: 'kr',
                format: "yyyy.mm",
                startView: "months",
                minViewMode: "months"
            });
            owl.data('owlCarousel').updateVars();
        });

        $('#dipList').on('click', '.dipAddBtnRemove', function(evt) {

            evt.preventDefault();

            $(this).closest('.item-ct').remove();
            otherDiploma--;
            $('#btnAddDiploma').removeAttr('disabled');
        });

        $('#dipList').on('click', '.btnAddDipFile', function(evt) {

            evt.preventDefault();

            $(this).next('.dipAddFileInp').trigger('click');
        });

        $('#dipList').on('change', '.dipAddFileInp', function(evt) {
            var type = evt.target.files[0].type;
            var size = evt.target.files[0].size;
            var name = evt.target.files[0].name;

            if (isValidImageOfFile(type)) {
                if (size <= 5242880) {
                    $(this).next('span').text(name);
                    $(this).siblings('.dipAddFileBtnRemove').addClass('remove');
                } else {
                    $('#popup_input_file_size_limit').modal('show');
                    return;
                }
            } else {
                $('#popup_input_file_content_wrong_format').modal('show');
                return;
            }
        });

        $('#dipList').on('click', '.dipAddFileBtnRemove', function(evt) {

            evt.preventDefault();

            $(this).prev('span').text('');
            $(this).removeClass('remove');
        });

        /*******************************************************
        *
        * Profile
        *
        *******************************************************/
        var totalProfile = 1;
        $('#btnAddProfile').on('click', function(evt) {

            evt.preventDefault();

            if (totalProfile >= 5) {
                $(this).attr('disabled', 'disabled');
                return;
            }

            $(this).removeAttr('disabled');
            var id = 10 + Math.floor(Math.random() * 10);

            var html = '<div class="item-list">';
            html    +=      '<input type="text" name="biographies[' + id + '].biography" data-name="biography" placeholder="약력 입력" style="width: 302px;" data-name="biography" class="form-control"/>';
            html    +=      '<button style="padding: 0 5px;" class="register profile_remove">';
            html    +=          '<span class="empty"></span>';
            html    +=          '<span>삭제</span>';
            html    +=      '</button>';
            html    += '</div>';

            $('#profileList').append(html);
            totalProfile++;
            owl.data('owlCarousel').updateVars();
        });

        $('#listProfile').on('click', '.profile_remove', function(evt) {
            $(this).closest('.item-list').remove();
            totalProfile--;
            $('#btnAddProfile').removeAttr('disabled');
        });


        /*******************************************************
        *
        * Certificate
        *
        *******************************************************/
        $('#btnCerFile').on('click', function(evt) {

            evt.preventDefault();

            $('.cerFileInp').trigger('click');
        });

        $('.cerFileInp').on('change', function(evt) {
            var html = '';
            for (var i=0, n=evt.target.files.length; i < n; i++) {
                var file = evt.target.files[i];
                var type = file.type;
                var size = file.size;
                var name = file.name;

                if (isValidImageOfFile(type)) {
                    if (size <= 5242880) {
                        FILES.push(file);
                        html += '<div class="item-list-remove" data-file="' + name + '">';
                        html +=     '<span>' + name + '</span>';
                        html +=     '<span class="remove"></span>';
                        html += '</div>';
                    } else {
                        $('#popup_input_file_size_limit').modal('show');
                        return;
                    }
                } else {
                    $('#popup_input_file_content_wrong_format').modal('show');
                    return;
                }

            }

            if (html) {
                $('#cerList').append(html);
            }
        });

        $('#cerList').on('click', '.remove', function(evt) {
            var $parent = $(this).closest('.item-list-remove');
            // remove from files array
            var fileName = $parent.attr('data-file');
            var index = FILES.map(function(elem) {
                return elem.name;
            }).indexOf(fileName);
            if (index > -1) {
                FILES.splice(index, 1);
                // remove from html
                $parent.remove();
            }
        });

        /*******************************************************
        *
        * MBTI
        *
        *******************************************************/
        $('[name=mbti]').on('change', function(evt) {
            if ($(this).val() == 1) {
                $('[name=mbtiSchool]').removeAttr('disabled');
            } else {
                $('[name=mbtiSchool]').attr('disabled', 'disabled');
            }
        });

        /*******************************************************
        *
        * Submit register step 2
        *
        *******************************************************/
        function validateForm() {
            var errors = [];
            if ($('[name=email]').attr('data-validate') === 'false') {
                errors.push('아이디(이메일)');
            }
            if ($('[name=pwd]').attr('data-validate') === 'false') {
                errors.push('비밀번호');
            }
            if ($('[name=pwdConfirm]').attr('data-validate') === 'false') {
                errors.push('비밀번호 확인');
            }
            if ($('[name=fullname]').attr('data-validate') === 'false') {
                errors.push('이름');
            }
            if ($('[name=mobilephone]').attr('data-validate') === 'false') {
                errors.push('휴대폰번호');
            }
            if ($('[name=dobYear]').val() == 0 || $('[name=dobMonth]').val() == 0 || $('[name=dobDay]').val() == 0) {
                errors.push('생년월일');
            }
            if (!$('[name=counselType]:checked').length) {
                errors.push('사업자 구분');
            } else {
                var typeSelected = $('[name=counselType]:checked')[0].value;
                if (typeSelected == 3) {
                    if ($('[name=counselCenter]').val() == 0) {
                        errors.push('센터 소속');
                    }
                }
            }
            if ($('[name=avatar]').val() === '') {
                errors.push('프로필 이미지');
            }
            if (!$('[data-name=category]').filter(function(index, elem) { return $(elem).is(':checked'); }).length) {
                errors.push('상담 분야');
            }
            if (!$('[name=introduce]').val().length) {
                errors.push('소개 문구');
            }
            if (!$('[name=eduLevel]:checked').length) {
                errors.push('학력');
            } else {
                if (!$('[name=eduLevel]:checked').closest('.item-list').find('[name=eduFile]').val()) {
                    errors.push('학력');
                }
            }

            if (!$('[data-name=biography]').filter(function(index, elem) { return $(elem).val(); } ).length) {
                errors.push('주요 약력');
            }
            if (!$('[data-name=diploma]:checked').length) {
                errors.push('자격증');
            } else {
                $('[data-name=diploma]:checked').each(function(index, item) {
                    if(!$(item).closest('.item-main').find('.dipFileInp').val()) {
                        if (errors.indexOf('자격증') == -1) errors.push('자격증');
                    }
                });
            }
            $('.dipAddFileInp').each(function(index, elem) {
                if (!$(elem).val()) {
                    if (errors.indexOf('자격증') == -1) errors.push('자격증');
                }
            });


            if (!FILES.length) {
                errors.push('증명 서류 등록');
            }
            if ($('[name=counseledTimes]').val() == 0) {
                errors.push('자격증 취득 후상담 횟수');
            }
            if (!$('[name=mbti]:checked').length) {
                errors.push('(주)어세스타 교육 수료 여부');
            } else {
                var mbtiSelected = $('[name=mbti]:checked')[0].value;
                if (mbtiSelected == 1) {
                    if ($('[name=mbtiSchool]').val() == 0) {
                        errors.push('(주)어세스타 교육 수료 여부');
                    }
                }
            }

            return errors;
        }

        $('#form_register').on('submit', function(evt) {

            evt.preventDefault();

            // validate form
            var errors = validateForm();

            if (errors.length) {
                var html = '';
                errors.forEach(function(item) {
                    html += '<li>' + item + '</li>';
                });
                $('#popup_form_errors').find('#error_fields').html(html);
                $('#popup_form_errors').modal('show');
            } else {
                var form        = $(this)[0];
                var formData    = new FormData(form);
                if (FILES.length) {
                    for(var i = 0, n = FILES.length; i < n; i++) {
                        formData.append('otherCertificates[' + i + '].file', FILES[i]);
                    }
                }

                $
                    .ajax({
                        url         : $(this).attr('action'),
                        method      : $(this).attr('method'),
                        processData : false,
                        contentType : false,
                        data        : formData,
                        beforeSend  : toggleLoading
                    })
                    .then(function(res) {
                        toggleLoading();
                        var isIE = /*@cc_on!@*/false || !!document.documentMode;
                        if(isIE){
                    		$('.owl-item:eq(1)').css('display', 'none');
                    	}
                        owlNext();
                    }, function(err) {
                        toggleLoading();
                        $('#error_fields').html('입력하지 않은 정보가 있습니다');
                        $('#popup_form_errors').modal('show');
                    });
            }

        });

    });


</script>