<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper counseling-history register massage">

    <c:set var="totalBiography" value="${fn:length(biographies)}" />
    <c:set var="totalFile"      value="${fn:length(userFiles)}" />

    <div style="margin-bottom: 15px;" class="page-title">
        <div class="title"><span>내 정보</span></div>
        <div class="title-ct"><span id="sub_menu">기본 정보 및 계좌 정보 수정</span></div>
    </div>
    <div class="page-content">
        <div class="container">
            <div class="main-top border-full bg-fff">
                <div class="tabbable-custom">
                    <ul class="nav nav-tabs" id="menu_nav">
                        <li class="active">
                            <a href="#tab-11" data-toggle="tab" data-menu="기본 정보 및 계좌 정보 수정"><span>기본 정보 </span><i aria-hidden="true" class="fa fa-caret-down"></i></a>
                        </li>
                        <li>
                            <a href="#tab-12" data-toggle="tab" data-menu="서비스 노출 정보 수정"> <span>서비스 노출 정보</span><i aria-hidden="true" class="fa fa-caret-down"></i></a>
                        </li>
                        <li>
                            <a href="#tab-13" data-toggle="tab" data-menu="탈퇴 신청"> <span>탈퇴 신청</span><i aria-hidden="true" class="fa fa-caret-down"></i></a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="tab-11" class="tab-10 tab-pane active">
                            <form id="form_tab1">
                                <div class="module-content">
                                    <div class="page-content edit-my-info">
                                        <div class="item form-register">
                                            <div class="item-main-content">
                                                <div class="item-main">
                                                    <div class="item-content">
                                                        <div class="content-top">
                                                            <div class="md-title">
                                                                <span>기본 정보</span>
                                                            </div>
                                                            <div class="md-content">
                                                                <div class="md-item">
                                                                    <span class="title">아이디(이메일)</span>
                                                                    <div class="item-ct">
                                                                        <span>${user.email}</span>
                                                                    </div>
                                                                </div>
                                                                <div class="md-item">
                                                                    <span class="title">현재 비밀번호</span>
                                                                    <div class="item-ct input-form">
                                                                        <input type="password" id="pwdCurrent" name="pwdCurrent" class="form-control" data-validate="false" data-required="false"/>
                                                                        <button class="register" id="btnCheckPwd">비밀번호 변경</button>
                                                                        <span class="text-red notice" style="margin-left: 1em"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="md-item new-pwd" style="display:none">
                                                                    <span class="title">비밀번호 변경</span>
                                                                    <div class="item-ct input-form">
                                                                        <input type="password" name="pwd" disabled="disabled" placeholder="8-20자 이내, 영문+숫자+특수문자 혼용" class="form-control" data-validate="false" data-required="false"/>
                                                                        <span class="text-red notice" style="margin-left: 1em"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="md-item new-pwd" style="display:none">
                                                                    <span class="title">비밀번호 확인</span>
                                                                    <div class="item-ct input-form">
                                                                        <input type="password" name="pwdConfirm" disabled="disabled" placeholder="8-20자 이내, 영문+숫자+특수문자 혼용" class="form-control" data-validate="false" data-required="false"/>
                                                                        <span class="text-red error" style="margin-left: 1em"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="md-item">
                                                                    <span class="title">이름</span>
                                                                    <div class="item-ct input-form">
                                                                        <input type="text" name="fullname" class="form-control" value="${user.fullname}"/>
                                                                        <span class="text-red notice" style="margin-left: 1em"></span>
                                                                    </div>
                                                                </div>
                                                                <div style="height: 64px;" class="md-item">
                                                                    <span class="title">휴대폰번호</span>
                                                                    <div class="item-ct input-form">
                                                                        <input type="text" id="mobilephone" name="mobilephone" placeholder="-없이 입력" value="${user.phone}" class="form-control" data-validate="false" data-required="false"/>
                                                                        <button class="register" id="btnSendToken">인증번호 발송</button>
                                                                        <span class="text-red notice" style="margin-left: 1em"></span>
                                                                    </div>
                                                                    <div class="item-ct input-form">
                                                                        <input type="text" id="token" name="token" placeholder="인증번호 확인" class="form-control" data-validate="false" data-required="false"/>
                                                                        <button class="register" id="btnCheckToken">인증번호 확인</button>
                                                                        <span class="date" id="countdown_timer" style="display: none"><span id="mm">02</span>:<span id="ss">59</span></span>
                                                                        <span class="text-red notice" style="margin-left: 1em"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="md-item">
                                                                    <span class="title">생년월일</span>
                                                                    <fmt:formatDate value="${user.dayOfBirth}" pattern="yyyy" var="_year" />
                                                                    <fmt:formatDate value="${user.dayOfBirth}" pattern="MM" var="_month" />
                                                                    <fmt:formatDate value="${user.dayOfBirth}" pattern="dd" var="_day" />

                                                                    <fmt:formatNumber value="${_month}" type="number" var="_month" />
                                                                    <fmt:formatNumber value="${_day}" type="number" var="_day" />

                                                                    <jsp:useBean id="now" class="java.util.Date" />
                                                                    <fmt:formatDate value="${now}" pattern="yyyy" var="year" />

                                                                    <div class="item-ct input-form">
                                                                        <div class="item-select">
                                                                            <select name="dobYear" title="성명" class="form-control selectpickermember">
                                                                                <c:forEach begin="1900" end="${year}" step="1" var="i">
                                                                                    <option value="${i}" ${i eq _year ? 'selected' : ''}>${i}</option>
                                                                                </c:forEach>
                                                                            </select>
                                                                            <span class="note-select">년</span>
                                                                        </div>
                                                                        <div class="item-select">
                                                                            <select name="dobMonth" title="성명" class="form-control selectpickermember">
                                                                                <c:forEach begin="1" end="12" step="1" var="i">
                                                                                    <option value="${i}" ${i eq _month ? 'selected' : ''}>${i}</option>
                                                                                </c:forEach>
                                                                            </select>
                                                                            <span class="note-select">월</span>
                                                                        </div>
                                                                        <div class="item-select">
                                                                            <select name="dobDay" title="성명" class="form-control selectpickermember">
                                                                                <c:forEach begin="1" end="31" step="1" var="i">
                                                                                    <option value="${i}" ${i eq _day ? 'selected' : ''}>${i}</option>
                                                                                </c:forEach>
                                                                            </select>
                                                                            <span class="note-select">일</span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="md-item">
                                                                    <span class="title">주소</span>
                                                                    <div class="item-ct input-form">
                                                                        <input type="text" name="address" style="width: 600px;" class="form-control" value="${user.address}"/>
                                                                    </div>
                                                                </div>
                                                                <div class="md-item">
                                                                    <span class="title">사업자 구분</span>
                                                                    <div class="item-ct input-form">
                                                                        <span>
                                                                            <c:choose>
                                                                                <c:when test="${user.userRole.id eq 2}">
                                                                                    프리랜서
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    허그맘 아동청소년심리상담센터 소속
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div style="margin-top: 20px;" class="content-top">
                                                            <div style="margin-bottom: 20px;" class="md-title">
                                                                <span>계좌 정보</span>
                                                                <p>. 계좌정보는 매월 말일 전에 변경하셔야 변경된 계좌로 정산됩니다.</p>
                                                                <p>. 계좌정보를 입력하지 않으면 수익 발생 시 정산이 되지 않습니다.</p>
                                                            </div>
                                                            <div class="md-content">
                                                                <div class="md-item">
                                                                    <span class="title">은행명</span>
                                                                    <div class="item-ct">
                                                                        <div style="margin-top: 2px;width: 260px;" class="item-select page-edit">
                                                                            <select name="bankId" title="성명" class="form-control selectpickermember">
                                                                                <c:forEach items="${banks}" var="item">
                                                                                    <option value="${item.id}" ${item.id eq counselor.bank.id ? 'selected' : ''}>${item.bankName}</option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="md-item">
                                                                    <span class="title">예금주</span>
                                                                    <div class="item-ct">
                                                                        <div class="item-ct input-form">
                                                                            <input type="text" name="accountBankHolder" data-required="1" class="form-control" value="${counselor.accountBankHolder}"/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="md-item">
                                                                    <span class="title">계좌 번호</span>
                                                                    <div class="item-ct">
                                                                        <div class="item-ct input-form">
                                                                            <input type="text" name="accountBankNumber" data-required="1" class="form-control" value="${counselor.accountBankNumber}"/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div style="margin-top: 15px;" class="main-submit">
                                                                <button type="submit" class="submit">수정 완료</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div id="tab-12" class="tab-10 tab-pane">
                            <form id="form_tab2" action="/auth/info/submit/tab2" method="post">
                                <div class="module-content">
                                    <div class="page-content edit-my-info">
                                        <div class="item form-register">
                                            <div class="item-main-content">
                                                <div class="item-main">
                                                    <div class="item-content">
                                                        <div class="content-top">
                                                            <div style="margin-bottom: 50px;" class="md-content">
                                                                <div style="height: 74px;" class="md-item">
                                                                    <span class="title">프로필 이미지</span>
                                                                    <div class="item-ct input-form">
                                                                        <div class="inputfile avatar">
                                                                            <div class="tr-file-upload image-upload">
                                                                                <div class="preview-image img-avatar">
                                                                                    <img src="${counselor.thumbnailUrl}" alt="avatar" id="previewAvatar" />
                                                                                </div>
                                                                                <label class="btn green btn-select-file">찾아보기
                                                                                    <input type="file" name="avatar" id="fileAvatar" class="hidden input-file"/>
                                                                                </label>
                                                                                <div class="input-icon input-large input-inline right"></div>
                                                                                <div class="note-img">
                                                                                    <span class="show-file-info">${counselor.thumbnailName}</span>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div style="height: 130px;" class="md-item">
                                                                    <span class="title">상담 분야</span>
                                                                    <div class="item-ct input-form">
                                                                        <div class="row-check all-check" id="catList">
                                                                            <c:forEach items="${categories}" var="item">
                                                                                <c:if test="${item.parentId eq 0}">
                                                                                    <div class="item radio-checkboxstyle2">

                                                                                        <c:set var="checkedField" value="false" />
                                                                                        <c:forEach items="${fields}" var="field">
                                                                                            <c:if test="${item.id eq field}">
                                                                                                <c:set var="checkedField" value="true" />
                                                                                            </c:if>
                                                                                        </c:forEach>

                                                                                        <input type="checkbox" value="${item.id}" name="counselorFields[${item.id}].category" data-name="category" ${checkedField ? 'checked' : ''} />
                                                                                        <span>${item.categoryName} ${item.decription}</span>
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

                                                                                        <c:set var="checkedField" value="false" />
                                                                                        <c:forEach items="${fields}" var="field">
                                                                                            <c:if test="${item.id eq field}">
                                                                                                <c:set var="checkedField" value="true" />
                                                                                            </c:if>
                                                                                        </c:forEach>

                                                                                        <input type="checkbox" value="${item.id}" name="counselorFields[${item.id}].category" data-name="category" ${checkedField ? 'checked' : ''} />
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
                                                                        <textarea name="introduce" rows="5" maxlength="500" placeholder="500자 이내로 입력" data-error-container="#editor2_error" class="rw-ckeditor form-control">${counselor.introduce}</textarea>
                                                                        <span class="max-leng"><span class="remaining">${not empty counselor.introduce ? fn:length(counselor.introduce) : 0}</span>/500</span>
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
                                                                                    <label><input type="radio" value="1" name="eduLevel" class="radio1 edu_ck" ${education.type eq 1 ? 'checked' : ''} /></label>
                                                                                </div>
                                                                            </div>
                                                                            <input  type="text" name="university" class="edu_university form-control" ${education.type eq 1 ? '' : 'disabled'} value="${education.type eq 1 ? education.university : ''}" />
                                                                            <span   class="note-input">대학교</span>
                                                                            <input  type="text" name="faculty" class="edu_major form-control" ${education.type eq 1 ? '' : 'disabled'} value="${education.type eq 1 ? education.faculty : ''}" />
                                                                            <span   class="note-input">전공</span>
                                                                            <button class="register btnEduFile" ${education.type eq 1 ? '' : 'disabled'} >찾아보기</button>
                                                                            <input type="file" name="eduFile" class="eduFileInp" style="display: none" ${education.type eq 1 ? '' : 'disabled'} accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                            <span class="edu_file">${education.type eq 1 ? education.fileName : ''}</span>
                                                                            <span class="edu_file_remove ${education.type eq 1 ? 'remove' : ''}"></span>
                                                                        </div>
                                                                        <div class="item-list">
                                                                            <div class="item-radio">
                                                                                <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                                    <label><input type="radio" value="2" name="eduLevel" class="radio1 edu_ck" ${education.type eq 2 ? 'checked' : ''} /></label>
                                                                                </div>
                                                                            </div>
                                                                            <input  type="text" name="university" class="edu_university form-control" ${education.type eq 2 ? '' : 'disabled'} value="${education.type eq 2 ? education.university : ''}" />
                                                                            <span   class="note-input">대학원</span>
                                                                            <input  type="text" name="faculty" class="edu_major form-control" ${education.type eq 2 ? '' : 'disabled'} value="${education.type eq 2 ? education.faculty : ''}" />
                                                                            <span   class="note-input">전공</span>
                                                                            <button class="register btnEduFile" ${education.type eq 2 ? '' : 'disabled'}>찾아보기</button>
                                                                            <input type="file" name="eduFile" class="eduFileInp" style="display: none" ${education.type eq 2 ? '' : 'disabled'} accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                            <span class="edu_file">${education.type eq 2 ? education.fileName : ''}</span>
                                                                            <span class="edu_file_remove ${education.type eq 2 ? 'remove' : ''}"></span>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="md-item stylev2" id="listProfile">
                                                                    <span class="title">주요 약력</span>
                                                                    <div class="item-ct input-form">
                                                                        <div id="profileList">
                                                                            <c:forEach items="${biographies}" var="biography" varStatus="index">
                                                                                <div class="item-list">
                                                                                    <input type="text" name="biographies[${index.index}].biography" data-name="biography" placeholder="대학교 입력" value="${biography.content}" style="width: 302px;" class="form-control"/>
                                                                                    <c:if test="${index.index ne 0}">
                                                                                        <button style="padding: 0 5px;" class="register profile_remove">
                                                                                            <span class="empty"></span>
                                                                                            <span>삭제</span>
                                                                                        </button>
                                                                                    </c:if>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                        <div class="item-list">
                                                                            <button style="margin-left: 0;" class="register" id="btnAddProfile" ${fn:length(biographies) eq 5 ? 'disabled': ''}>+ 추가</button>
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
                                                                                        <c:set var="certificateIndex" value="999" />
                                                                                        <c:forEach items="${certificates}" var="certificate" varStatus="index">
                                                                                            <c:if test="${certificate.name eq '전문상담사 1급 (수련감독자)' and certificate.type eq 1}">
                                                                                                <c:set var="certificateIndex" value="${index.index}" />
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                        <div class="item-check">
                                                                                            <div class="item radio-checkboxstyle2">
                                                                                                <input type="checkbox" name="certificates[0].diploma"   value="전문상담사 1급 (수련감독자)" data-name="diploma" class="diploma_ck" ${certificateIndex eq 999 ? '' : 'checked'} />
                                                                                                <input type="hidden"   name="certificates[0].type"      value="1" />
                                                                                                 <input type="hidden" name="certificates[0].author" value="(사)한국상담학회" class="form-control"/>
                                                                                                <c:if test="${certificateIndex ne 999}">
                                                                                                    <input type="hidden"   name="certificates[0].id"        value="${certificates[certificateIndex].id}" />
                                                                                                </c:if>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="item-des" style="width: 35%;">
                                                                                            <p>전문상담사 1급 (수련감독자)</p>
                                                                                        </div>
                                                                                        <div class="item-main-ct" style="width: 58%;">
                                                                                        	<input ${certificateIndex eq 999 ? 'disabled' : ''} id="IPIssuedDate" name="certificates[0].issuedDate" value="<fmt:formatDate value="${certificates[certificateIndex].issuedDate}" pattern="yyyy.MM"/>" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                            <button style="float: left !important;margin-left: 10px !important;font-size: 11px;line-height: 12px;" class="register btnDipFile" class="register btnDipFile" ${certificateIndex eq 999 ? 'disabled' : ''}>자격증 사본 등록</button>
                                                                                            <input type="file" name="certificates[0].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                            <p style="text-align: right;">
                                                                                                <span class="diploma_file_name">${certificateIndex eq 999 ? '' : fn:substring(certificates[certificateIndex].fileName, 0, 20)}</span>
                                                                                                <span class="diploma_file_remove ${certificateIndex eq 999 ? '' : 'remove'}"></span>
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="item-main">
                                                                                        <c:set var="certificateIndex" value="999" />
                                                                                        <c:forEach items="${certificates}" var="certificate" varStatus="index">
                                                                                            <c:if test="${certificate.name eq '전문상담사 1급' and certificate.type eq 1}">
                                                                                                <c:set var="certificateIndex" value="${index.index}" />
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                        <div class="item-check">
                                                                                            <div class="item radio-checkboxstyle2">
                                                                                                <input type="checkbox" name="certificates[1].diploma"   value="전문상담사 1급" data-name="diploma" class="diploma_ck" ${certificateIndex eq 999 ? '' : 'checked'} />
                                                                                                <input type="hidden"   name="certificates[1].type"      value="1" />
                                                                                                <input type="hidden" name="certificates[1].author" value="(사)한국상담학회" class="form-control"/>
                                                                                                <c:if test="${certificateIndex ne 999}">
                                                                                                    <input type="hidden"   name="certificates[1].id"        value="${certificates[certificateIndex].id}" />
                                                                                                </c:if>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="item-des" style="width: 35%;">
                                                                                            <p>전문상담사 1급</p>
                                                                                        </div>
                                                                                        <div class="item-main-ct" style="width: 58%;">
                                                                                        	<input ${certificateIndex eq 999 ? 'disabled' : ''} id="IPIssuedDate" value="<fmt:formatDate value="${certificates[certificateIndex].issuedDate}" pattern="yyyy.MM"/>" name="certificates[1].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                            <button style="float: left !important;margin-left: 10px !important;font-size: 11px;line-height: 12px;" class="register btnDipFile" class="register btnDipFile" ${certificateIndex eq 999 ? 'disabled' : ''}>자격증 사본 등록</button>
                                                                                            <input type="file" name="certificates[1].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                            <p style="text-align: right;">
                                                                                                <span class="diploma_file_name">${certificateIndex eq 999 ? '' : fn:substring(certificates[certificateIndex].fileName, 0, 20)}</span>
                                                                                                <span class="diploma_file_remove ${certificateIndex eq 999 ? '' : 'remove'}"></span>
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="item-main">
                                                                                        <c:set var="certificateIndex" value="999" />
                                                                                        <c:forEach items="${certificates}" var="certificate" varStatus="index">
                                                                                            <c:if test="${certificate.name eq '전문상담사 2급' and certificate.type eq 1}">
                                                                                                <c:set var="certificateIndex" value="${index.index}" />
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                        <div class="item-check">
                                                                                            <div class="item radio-checkboxstyle2">
                                                                                                <input type="checkbox" name="certificates[2].diploma" value="전문상담사 2급" data-name="diploma" class="diploma_ck" ${certificateIndex eq 999 ? '' : 'checked'} />
                                                                                                <input type="hidden"   name="certificates[2].type"      value="1" />
                                                                                                <input type="hidden" name="certificates[2].author" value="(사)한국상담학회" class="form-control"/>
                                                                                                <c:if test="${certificateIndex ne 999}">
                                                                                                    <input type="hidden"   name="certificates[2].id"        value="${certificates[certificateIndex].id}" />
                                                                                                </c:if>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="item-des" style="width: 35%;">
                                                                                            <p>전문상담사 2급</p>
                                                                                        </div>
                                                                                        <div class="item-main-ct" style="width: 58%;">
                                                                                        	<input ${certificateIndex eq 999 ? 'disabled' : ''} value="<fmt:formatDate value="${certificates[certificateIndex].issuedDate}" pattern="yyyy.MM"/>" id="IPIssuedDate" name="certificates[2].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                            <button style="float: left !important;margin-left: 10px !important;font-size: 11px;line-height: 12px;" class="register btnDipFile" class="register btnDipFile" ${certificateIndex eq 999 ? 'disabled' : ''}>자격증 사본 등록</button>
                                                                                            <input type="file" name="certificates[2].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                            <p style="text-align: right;">
                                                                                                <span class="diploma_file_name">${certificateIndex eq 999 ? '' : fn:substring(certificates[certificateIndex].fileName, 0, 20)}</span>
                                                                                                <span class="diploma_file_remove ${certificateIndex eq 999 ? '' : 'remove'}"></span>
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
                                                                                    <c:set var="certificateIndex" value="999" />
                                                                                    <c:forEach items="${certificates}" var="certificate" varStatus="index">
                                                                                        <c:if test="${certificate.name eq '상담심리사 1급 (주 수퍼바이저)' and certificate.type eq 1}">
                                                                                            <c:set var="certificateIndex" value="${index.index}" />
                                                                                        </c:if>
                                                                                    </c:forEach>
                                                                                    <div class="item-main">
                                                                                        <div class="item-check">
                                                                                            <div class="item radio-checkboxstyle2">
                                                                                                <input type="checkbox"  name="certificates[3].diploma"  value="상담심리사 1급 (주 수퍼바이저)" data-name="diploma" class="diploma_ck" ${certificateIndex eq 999 ? '' : 'checked'} />
                                                                                                <input type="hidden"    name="certificates[3].type"     value="1" />
                                                                                                <input type="hidden" name="certificates[3].author" value="(사)한국상담심리학회" class="form-control"/>
                                                                                                <c:if test="${certificateIndex ne 999}">
                                                                                                    <input type="hidden"   name="certificates[3].id"        value="${certificates[certificateIndex].id}" />
                                                                                                </c:if>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="item-des" style="width: 35%;">
                                                                                            <p>상담심리사 1급 (주 수퍼바이저)</p>
                                                                                        </div>
                                                                                        <div class="item-main-ct" style="width: 58%;">
                                                                                        	<input ${certificateIndex eq 999 ? 'disabled' : ''} value="<fmt:formatDate value="${certificates[certificateIndex].issuedDate}" pattern="yyyy.MM"/>" id="IPIssuedDate" name="certificates[3].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                            <button style="float: left !important;margin-left: 10px !important;font-size: 11px;line-height: 12px;" class="register btnDipFile" class="register btnDipFile" ${certificateIndex eq 999 ? 'disabled' : ''}>자격증 사본 등록</button>
                                                                                            <input type="file" name="certificates[3].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                            <p style="text-align: right;">
                                                                                                <span class="diploma_file_name">${certificateIndex eq 999 ? '' : fn:substring(certificates[certificateIndex].fileName, 0, 20)}</span>
                                                                                                <span class="diploma_file_remove ${certificateIndex eq 999 ? '' : 'remove'}"></span>
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="item-main">
                                                                                        <c:set var="certificateIndex" value="999" />
                                                                                        <c:forEach items="${certificates}" var="certificate" varStatus="index">
                                                                                            <c:if test="${certificate.name eq '상담심리사 1급' and certificate.type eq 1}">
                                                                                                <c:set var="certificateIndex" value="${index.index}" />
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                        <div class="item-check">
                                                                                            <div class="item radio-checkboxstyle2">
                                                                                                <input type="checkbox"  name="certificates[4].diploma"  value="상담심리사 1급" data-name="diploma" class="diploma_ck" ${certificateIndex eq 999 ? '' : 'checked'} />
                                                                                                <input type="hidden"    name="certificates[4].type"     value="1" />
                                                                                                <input type="hidden" name="certificates[4].author" value="(사)한국상담심리학회" class="form-control"/>
                                                                                                <c:if test="${certificateIndex ne 999}">
                                                                                                    <input type="hidden"   name="certificates[4].id"        value="${certificates[certificateIndex].id}" />
                                                                                                </c:if>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="item-des" style="width: 35%;">
                                                                                            <p>상담심리사 1급</p>
                                                                                        </div>
                                                                                        <div class="item-main-ct" style="width: 58%;">
                                                                                        	<input ${certificateIndex eq 999 ? 'disabled' : ''} value="<fmt:formatDate value="${certificates[certificateIndex].issuedDate}" pattern="yyyy.MM"/>" id="IPIssuedDate" name="certificates[4].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                            <button style="float: left !important;margin-left: 10px !important;font-size: 11px;line-height: 12px;" class="register btnDipFile" class="register btnDipFile" ${certificateIndex eq 999 ? 'disabled' : ''}>자격증 사본 등록</button>
                                                                                            <input type="file" name="certificates[4].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                            <p style="text-align: right;">
                                                                                                <span class="diploma_file_name">${certificateIndex eq 999 ? '' : fn:substring(certificates[certificateIndex].fileName, 0, 20)}</span>
                                                                                                <span class="diploma_file_remove ${certificateIndex eq 999 ? '' : 'remove'}"></span>
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="item-main">
                                                                                        <c:set var="certificateIndex" value="999" />
                                                                                        <c:forEach items="${certificates}" var="certificate" varStatus="index">
                                                                                            <c:if test="${certificate.name eq '상담심리사 2급' and certificate.type eq 1}">
                                                                                                <c:set var="certificateIndex" value="${index.index}" />
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                        <div class="item-check">
                                                                                            <div class="item radio-checkboxstyle2">
                                                                                                <input type="checkbox" name="certificates[5].diploma"   value="상담심리사 2급" data-name="diploma" class="diploma_ck" ${certificateIndex eq 999 ? '' : 'checked'} />
                                                                                                <input type="hidden"   name="certificates[5].type"      value="1" />
                                                                                                <input type="hidden" name="certificates[5].author" value="(사)한국상담심리학회" class="form-control"/>
                                                                                                <c:if test="${certificateIndex ne 999}">
                                                                                                    <input type="hidden"   name="certificates[5].id"        value="${certificates[certificateIndex].id}" />
                                                                                                </c:if>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="item-des" style="width: 35%;">
                                                                                            <p>상담심리사 2급</p>
                                                                                        </div>
                                                                                        <div class="item-main-ct" style="width: 58%;">
                                                                                        	<input value="<fmt:formatDate value="${certificates[certificateIndex].issuedDate}" pattern="yyyy.MM"/>" id="IPIssuedDate" name="certificates[5].issuedDate" ${certificateIndex eq 999 ? 'disabled' : ''} style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                            <button style="float: left !important;margin-left: 10px !important;font-size: 11px;line-height: 12px;" class="register btnDipFile" class="register btnDipFile" ${certificateIndex eq 999 ? 'disabled' : ''}>자격증 사본 등록</button>
                                                                                            <input type="file" name="certificates[5].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                            <p style="text-align: right;">
                                                                                                <span class="diploma_file_name">${certificateIndex eq 999 ? '' : fn:substring(certificates[certificateIndex].fileName, 0, 20)}</span>
                                                                                                <span class="diploma_file_remove ${certificateIndex eq 999 ? '' : 'remove'}"></span>
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
                                                                                        <c:set var="certificateIndex" value="999" />
                                                                                        <c:forEach items="${certificates}" var="certificate" varStatus="index">
                                                                                            <c:if test="${certificate.name eq '임상심리전문가' and certificate.type eq 1}">
                                                                                                <c:set var="certificateIndex" value="${index.index}" />
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                        <div class="item-check">
                                                                                            <div class="item radio-checkboxstyle2">
                                                                                                <input type="checkbox" name="certificates[6].diploma"  value="임상심리전문가" data-name="diploma" class="diploma_ck" ${certificateIndex eq 999 ? '' : 'checked'} />
                                                                                                <input type="hidden"   name="certificates[6].type"     value="1" />
                                                                                                <input type="hidden" name="certificates[6].author" value="(사)한국임상심리학회" class="form-control"/>
                                                                                                <c:if test="${certificateIndex ne 999}">
                                                                                                    <input type="hidden"   name="certificates[6].id"        value="${certificates[certificateIndex].id}" />
                                                                                                </c:if>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="item-des" style="width: 35%;">
                                                                                            <p>임상심리전문가</p>
                                                                                        </div>
                                                                                        <div class="item-main-ct" style="width: 58%;">
                                                                                        	<input id="IPIssuedDate" ${certificateIndex eq 999 ? 'disabled' : ''} value="<fmt:formatDate value="${certificates[certificateIndex].issuedDate}" pattern="yyyy.MM"/>" name="certificates[6].issuedDate" style="float: left;width: 160px;margin-left: 10px;" type="text" data-date-format="yyyy.mm" placeholder="취득연월(YYYY.MM)" class="form-control date-picker"/>
                                                                                            <button style="float: left !important;margin-left: 10px !important;font-size: 11px;line-height: 12px;" class="register btnDipFile" class="register btnDipFile" ${certificateIndex eq 999 ? 'disabled' : ''}>자격증 사본 등록</button>
                                                                                            <input type="file" name="certificates[6].file" style="display: none" class="dipFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                            <p style="text-align: right;">
                                                                                                <span class="diploma_file_name">${certificateIndex eq 999 ? '' : fn:substring(certificates[certificateIndex].fileName, 0, 20)}</span>
                                                                                                <span class="diploma_file_remove ${certificateIndex eq 999 ? '' : 'remove'}"></span>
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
                                                                        <c:forEach items="${certificates}" var="certificate" varStatus="index">
                                                                            <c:if test="${certificate.type eq 2}">
                                                                                <c:set var="certificateIndex" value="${index.index}" />
                                                                                <div class="item-ct input-form">
                                                                                    <div class="item-list">
                                                                                        <input type="text" name="certificates[${7 + index.index}].diploma"       placeholder="자격증 명"             value="${certificate.name}"         class="form-control"/>
                                                                                        <input type="text" name="certificates[${7 + index.index}].author"        placeholder="발급 기관"             value="${certificate.author}"       class="form-control"/>
                                                                                        <input type="text" name="certificates[${7 + index.index}].issuedDate"    placeholder="취득연월(YYYY.MM)"     value="<fmt:formatDate value='${certificate.issuedDate}' pattern='yyyy.MM'/>" class="form-control date-picker"/>
                                                                                        <input type="hidden"   name="certificates[${7 + index.index}].type"     value="2" />
                                                                                        <input type="hidden"   name="certificates[${7 + index.index}].id"       value="${certificate.id}" />
                                                                                        <button style="padding: 0 5px;" class="register dipAddBtnRemove">
                                                                                            <span class="empty"></span>
                                                                                            <span>삭제</span>
                                                                                        </button>
                                                                                    </div>
                                                                                    <div class="item-list">
                                                                                        <button style="margin-left: 0;" class="register btnAddDipFile">찾아보기</button>
                                                                                        <input type="file" name="addDiplomaFile" style="display: none" class="dipAddFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" />
                                                                                        <span>${certificate.fileName}</span>
                                                                                        <span class="dipAddFileBtnRemove ${not empty certificate.fileName ? 'remove' : ''}"></span>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </div>
                                                                    <div class="item-ct input-form">
                                                                        <div style="margin-top: 10px;" class="item-list">
                                                                            <button style="margin-left: 0;" class="register" id="btnAddDiploma">+ 추가</button>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div style="min-height: 60px;" class="md-item stylev2 style-list">
                                                                    <span class="title">증명 서류 등록</span>
                                                                    <div class="item-ct input-form">
                                                                        <span class="content">※ 이력서 및 기타 증명서를 등록해주세요. (중복 등록가능)</span>
                                                                        <div style="margin-top: -4px;" class="item-list">
                                                                            <button style="margin-left: 0;" class="register" id="btnCerFile">찾아보기</button>
                                                                            <input type="file" name="certificateFiles" style="display: none" class="cerFileInp" accept="image/*,.pdf,.ppt,.pptx,.doc,.docx" multiple />
                                                                            <div class="list-remove" id="cerList">
                                                                                <c:forEach items="${userFiles}" var="certificate" varStatus="index">
                                                                                    <div class="item-list-remove" data-file="${certificate.fileName}">
                                                                                        <input type="hidden"    name="otherCertificates[${index.index}].id"     value="${certificate.id}" />
                                                                                        <span>${certificate.fileName}</span>
                                                                                        <span class="remove"></span>
                                                                                    </div>
                                                                                </c:forEach>
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
                                                                                <option value="100회 미만" ${counselor.counselNumber eq '100회 미만' ? 'selected' : ''}>100회 미만</option>
                                                                                <option value="100회 이상" ${counselor.counselNumber eq '100회 이상' ? 'selected' : ''}>100회 이상</option>
                                                                                <option value="200회 이상" ${counselor.counselNumber eq '200회 이상' ? 'selected' : ''}>200회 이상</option>
                                                                                <option value="300회 이상" ${counselor.counselNumber eq '300회 이상' ? 'selected' : ''}>300회 이상</option>
                                                                                <option value="500회 이상" ${counselor.counselNumber eq '500회 이상' ? 'selected' : ''}>500회 이상</option>
                                                                                <option value="1000회 이상" ${counselor.counselNumber eq '1000회 이상' ? 'selected' : ''}>1,000회 이상</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="item-main style2 full-register">
                                                    <div class="item-top">
                                                        <span class="title">아래의 추가정보를 입력해주세요 </span>
                                                    </div>
                                                    <div style="padding-bottom: 30px;" class="item-content">
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
                                                                                    <input type="radio" value="1" name="mbti" class="radio1" ${counselor.isFromAssesta eq 1 ? 'checked' : ''} />
                                                                                </label>
                                                                            </div>
                                                                            <span class="note-radio">예</span>
                                                                        </div>
                                                                        <div class="item-radio">
                                                                            <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                                <label>
                                                                                    <input type="radio" value="0" name="mbti" class="radio1" ${counselor.isFromAssesta eq 0 ? 'checked' : ''}/>
                                                                                </label>
                                                                            </div>
                                                                            <span class="note-radio">아니오</span>
                                                                        </div>
                                                                        <input type="text" name="mbtiSchool" placeholder="수료번호 입력" value="${counselor.isFromAssesta eq 1 ? counselor.mbtiCertificate : ''}" class="form-control" ${counselor.isFromAssesta eq 1 ? '' : 'disabled'} />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="main-submit">
                                                        <button type="button" id="btnPreview" class="submit style-edit-acc preview-mobile-acc">미리 보기 </button>
                                                        <button type="submit" class="submit">신청 완료 </button>
                                                    </div>

                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div id="tab-13" class="tab-10 tab-pane">

                            <form id="form_tab3">
                                <div class="module-content content-tab3">
                                    <div class="page-content edit-my-info">
                                        <div class="item form-register">
                                            <div class="item-main-content">
                                                <div class="item-main style2 full-register">
                                                    <div class="item-content">
                                                        <div class="content-top">
                                                            <div class="title-top3">
                                                                <h3 style="font-weight: bold;">회원탈퇴 안내</h3>
                                                                <p>ㆍ 당분간 상담을 진행할 수 없는 상황이라면 스케줄 메뉴에서 [서비스 노출 : OFF]를 하시면 서비스 상에 노출되지 않습니다.</p>
                                                                <p>ㆍ 서비스 탈퇴 시 상담사님의 개인정보는 개인정보 취급방침에 의거하여 삭제될 예정입니다.</p>
                                                                <p>ㆍ 탈퇴 후에는 삭제된 개인 정보를 복원할 수 없습니다.</p>
                                                            </div>
                                                            <div class="md-content-tab3 border-full">
                                                                <p class="title">탈퇴 사유</p>
                                                                <div class="main-tab3">
                                                                    <div class="item-radio">
                                                                        <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                            <label>
                                                                                <input type="radio" value="다른 서비스와 스케줄 중복" name="withdraw_reason" checked="checked" class="radio1"/>
                                                                            </label>
                                                                            </div>
                                                                            <span class="note-radio">다른 서비스와 스케줄 중복</span>
                                                                        </div>
                                                                        <div class="item-radio">
                                                                            <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                                <label>
                                                                                    <input type="radio" value="회원들의 예의 없는 말과 행동" name="withdraw_reason" class="radio1"/>
                                                                                </label>
                                                                            </div>
                                                                            <span class="note-radio">회원들의 예의 없는 말과 행동</span>
                                                                        </div>
                                                                        <div class="item-radio">
                                                                            <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                                <label>
                                                                                    <input type="radio" value="수익 개선의 여지가 보이지 않음" name="withdraw_reason" class="radio1"/>
                                                                                </label>
                                                                            </div>
                                                                            <span class="note-radio">수익 개선의 여지가 보이지 않음</span>
                                                                        </div>
                                                                        <div class="item-radio">
                                                                            <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                                <label>
                                                                                    <input type="radio" value="사이트나 시스템 등 사용하기 불편함" name="withdraw_reason" class="radio1"/>
                                                                                </label>
                                                                            </div>
                                                                            <span class="note-radio">사이트나 시스템 등 사용하기 불편함</span>
                                                                        </div>
                                                                        <div class="item-radio">
                                                                            <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                                <label>
                                                                                    <input type="radio" value="불합리한 서비스 정책" name="withdraw_reason" class="radio1"/>
                                                                                </label>
                                                                            </div>
                                                                            <span class="note-radio">불합리한 서비스 정책</span>
                                                                        </div>
                                                                        <div class="item-radio">
                                                                            <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                                                                <label>
                                                                                    <input type="radio" value="기타 사유" id="withdraw_other_reason" name="withdraw_reason" class="radio1"/>
                                                                                </label>
                                                                            </div>
                                                                            <span class="note-radio">기타 사유</span>
                                                                            <input type="text" id="withdraw_reason" class="form-control" disabled/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="note-content">
                                                            <p>회원 탈퇴를 하시면 <span>개인정보 및 회원님의 이용 내역과 자료들이 모두 삭제 </span>됩니다.</p>
                                                            <p>그래도 탈퇴신청을 진행하시겠습니까</p>
                                                        </div>
                                                        <div class="main-submit">
                                                            <button type="reset" class="submit">취소 </button>
                                                            <button type="submit" class="submit style-edit-acc">회원탈퇴 신청</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
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

<div id="popup_mobilephone_token_empty" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>인증번호를 입력해 주세요.</strong>
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
                <strong>인증번호를 확인해주세요.</strong>
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

<div id="popup_not_yet_session" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>예약된 상담 건이 있습니다. 상담 완료 후 탈퇴가 가능합니다.</strong>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_not_yet_paid" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>아직 정산이 완료되지 않았습니다. 정산 완료 후 탈퇴가 가능합니다.</strong>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_confirm_withdraw" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>심리상담사 탈퇴 신청</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>정말 탈퇴 하시겠습니까?</strong>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal">취소</button>
                <button type="button" class="btn close-face-popup popup_accept">탈퇴하기</button>
            </div>
        </div>
    </div>
</div>

<div id="popup-preview-mobile-acc" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="true" class="modal fade popup-facetalk popup-preview-mobile-acc">
    <div class="modal-dialog">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
        <div class="modal-content">
            <div class="container-counselor-intro main">
                <div class="main-counselor-intro">
                    <div class="counselor-intro">
                        <div class="thumb-avatar">
                            <div class="avatar">
                                <img id="preview_avatar" src="${counselor.thumbnailUrl}" alt="avatar"/>
                            </div>
                            <div class="rank rank_white"></div>
                        </div>
                        <div class="counselor-name">
                            <span class="first-name">${user.fullname}</span>
                            <span class="last-name">심리상담사</span>
                        </div>
                        <div class="result-rated">
                            <input type="number" data-readonly="" value="3" class="rating"/>
                        </div>
                        <div class="counselor-other"><span class="total-vote">
                            <i aria-hidden="true" class="fa fa-thumbs-o-up">
                            </i>10</span>
                            <input type="hidden" name="status_support" value="Off"/><a href="javascript:;" class="btn-support">쪽지보내기</a>
                        </div>
                    </div>
                    <div class="counselor-content">
                        <div class="title-counselor">바로 상담가능</div>
                        <div class="counselor-item about">
                            <div class="sub-item">소개</div>
                            <div class="detail">
                                <p id="preview_introduce">${counselor.introduce}</p>
                            </div>
                        </div>
                        <div style="border-bottom: 0;" class="counselor-item info-user">
                            <div class="sub-item">주요 경력</div>
                                <div class="detail">
                                    <ul id="preview_other">

                                    </ul>
                                </div>
                            </div>
                            <div class="voucher-info text-center">
                                <span class="item-text text-gray">30분</span>
                                <span class="item-text text-blue">33,000원</span>
                                <span class="item-text text-gray">또는</span>
                                <span class="item-text text-blue">상담권 1매</span>
                            </div>
                            <div class="box-review">
                                <div class="total-review">
                                    <div class="title-review">상담 만족도 평가 및 후기<a href="#" class="btn-review">
                                        <i aria-hidden="true" class="fa fa-pencil">
                                        </i>작성하기</a>
                                    </div>
                                    <div class="result-review">
                                        <span class="text-review">만족도 평가</span>
                                        <input type="number" data-readonly="" value="4.5" class="rating"/>
                                    </div>
                                </div>
                                <div class="list-review">
                                    <div class="item-review">
                                        <div class="user-info">
                                            <div class="user-name">이 * 희 님</div>
                                                <input type="hidden" data-readonly="" value="4.5" class="rating"/>
                                            </div>
                                        <div class="content-review">너무 친절하고 귀에 쏙쏙 들어옵니다.<br/>그런 방법을 왜 몰랐을까요? ㅠ.ㅠ</div>
                                    </div>
                                    <div class="item-review">
                                        <div class="user-info">
                                            <div class="user-name">홍 * 동</div>
                                                <input type="hidden" data-readonly="" value="3" class="rating"/>
                                            </div>
                                            <div class="content-review">감사합니다~ 제 아이에 대하여 다시 처음부터 생각해봐야겠네요. 말씀해주신 부분을 ….</div>
                                        </div>
                                        <div style="border-bottom: 0;" class="item-review">
                                            <div class="user-info">
                                                <div class="user-name">홍 * 동</div>
                                                <input type="hidden" data-readonly="" value="2" class="rating"/>
                                            </div>
                                            <div class="content-review">그다지 나아진 것은 없었지만 일리는 있었습니다.</div>
                                        </div>
                                        <div class="box-control"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<script>

    /**********************************************
    *
    * Menu bar
    *
    ***********************************************/
    $('#menu_nav').on('click', 'a', function(evt) {
        $('#sub_menu').text($(this).attr('data-menu'));
    });

    /************************************************************************
    *
    * Tab 1
    *
    ************************************************************************/
    var currentPhone = ${user.phone} || '';

    /**********************************************
    *
    * Check current password
    *
    ***********************************************/
    $('#btnCheckPwd').on('click', function(evt) {
        evt.preventDefault();

        $('#pwdCurrent').attr('data-required', true);
        $('[name=pwd]').removeAttr('disabled');
        $('[name=pwdConfirm]').removeAttr('disabled');
        $('.new-pwd')
            .attr('data-required', true)
            .css('display','');

        var $this       = $(this);
        var pwdCurrent  = $('#pwdCurrent').val();
        if (pwdCurrent) {
            $
                .ajax({
                    url     : '/auth/info/checkpwd',
                    method  : 'get',
                    data    : {
                        pwd     : pwdCurrent
                    },
                    beforeSend: toggleLoading
                })
                .then(function(res) {
                    toggleLoading();
                    $this.prev().attr('data-validate', true);
                    $this.next()
                        .removeClass('text-red')
                        .addClass('text-blue')
                        .text('비밀번호가 확인되었습니다.');
                }, function(err) {
                    toggleLoading();
                    $this.next()
                        .removeClass('text-blue')
                        .addClass('text-red')
                        .text('비밀번호가 일치하지 않습니다.');
                });
        } else {
            $this.next()
                .removeClass('text-blue')
                .addClass('text-red')
                .text('현재 비밀번호를 입력해 주세요.');
        }
    });

    /**********************************************
    *
    * Check new password
    *
    ***********************************************/
    var regexPwd = new RegExp("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$&+,:;=?@#|'<>.^*()%!-])");
    function checkPwd(password) {
        return regexPwd.test(password) && password.length >= 8 && password.length <= 20;
    }

    function checkPwdConfirm(password, passwordConfirm) {
        return password === passwordConfirm;
    }

    /**********************************************
    *
    * Mobile change
    *
    ***********************************************/
    $('[name=mobilephone]').on('keyup', function(evt) {
        if ($(this).val() != currentPhone) {
            $(this).attr('data-required', true);
        } else {
            $(this).attr('data-required', false);
        }
    });

    /**********************************************
    *
    * Send token
    *
    ***********************************************/
    var timer;

    $('#btnSendToken').on('click', function(evt) {
        evt.preventDefault();

        $(this).attr('data-required', true);
        $('[name=token]').attr('data-required', true);
        var phone = $('#mobilephone').val();

        if (timer) {
            clearInterval(timer);
        }

        $
            .ajax({
                url 	    : '/public/sendToken',
                method 	    : 'post',
                data 	    : {
                    phone 	: phone
                },
                beforeSend  : toggleLoading
            })
            .then(function(res) {
                toggleLoading();

                $('#popup_mobilephone_token_sent').modal('show');
                $('#countdown_timer').css('display', '');

                var token 		= res.token;
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

                    var _token = $('#token').val();

                    if (!_token) $('#popup_mobilephone_token_empty').modal('show');
                    else if (_token != token) $('#popup_mobilephone_token_wrong').modal('show');
                    else $('#popup_mobilephone_verified').modal('show');
                    $('#mobilephone').attr('data-validate', true);
                });

            }, function(error) {
                toggleLoading();
                console.error(error);
            });
    });

    /**********************************************
    *
    * Submit tab 1
    *
    ***********************************************/
    function validateForm1() {
        var errors = [];

        if ($('#pwdCurrent').attr('data-required') === 'true') {
            if (!checkPwd($('[name=pwd]').val())) {
                errors.push('비밀번호');
                $('[name=pwd]').next()
                    .removeClass('text-blue')
                    .addClass('text-red')
                    .text('비밀번호는 영문+숫자+특수문자 혼용하여 8~20자 이내로 설정해야 합니다.');
            } else {
                $('[name=pwd]').next()
                    .removeClass('text-blue')
                    .removeClass('text-red')
                    .text('');
            }

            if (!checkPwdConfirm($('[name=pwd]').val(), $('[name=pwdConfirm]').val())) {
                errors.push('비밀번호 확인');
                $('[name=pwdConfirm]').next()
                    .removeClass('text-blue')
                    .addClass('text-red')
                    .text('비밀번호가 일치하지 않습니다.');
            } else {
                $('[name=pwdConfirm]').next()
                    .removeClass('text-blue')
                    .removeClass('text-red')
                    .text('');
            }
        }

        if ($('[name=mobilephone]').attr('data-required') === 'true') {
            if ($('[name=mobilephone]').attr('data-validate') === 'false') {
                errors.push('휴대폰번호');
            }
        }

        if ($('[name=fullname]').attr('data-validate') === 'false') {
            errors.push('이름');
        }
        if (!$('[name=address]').val().length) {
            errors.push('주소');
        }

        return errors;
    }

    $('#form_tab1').on('submit', function(evt) {

        evt.preventDefault();
        var $this = $(this);

        var errors = validateForm1();
        if (errors.length) {
            var html = '';
            errors.forEach(function(item) {
                html += '<li>' + item + '</li>';
            });
            $('#popup_form_errors').find('#error_fields').html(html);
            $('#popup_form_errors').modal('show');
        } else {
            $
                .ajax({
                    url         : '/auth/info/submit/tab1',
                    method      : 'post',
                    data        : $(this).serialize(),
                    beforeSend  : toggleLoading
                })
                .then(function(res) {
                    toggleLoading();
                    window.location.reload();
                }, function(err) {
                    toggleLoading();
                    console.error(err);
                    if (err.responseText == '1') {
                        $this.find('[name=pwd]').next()
                            .removeClass('text-blue')
                            .addClass('text-red')
                            .text('입력하신 비밀번호는 사용할 수 없습니다. 다른 비밀번호를 입력해주세요.');
                    }
                });

        }
    });

    /************************************************************************
    *
    * Tab 2
    *
    ************************************************************************/
    var FILES = [];

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

    /**********************************************
    *
    * Avatar upload
    *
    **********************************************/
    $('#fileAvatar').on('change', function(evt) {

        var type = evt.target.files[0].type;
        var size = evt.target.files[0].size;

        if (isValidImage(type)) {
            if (size <= 3145728) {
                $('#previewAvatar').attr('src', URL.createObjectURL(evt.target.files[0]));
                $('#preview_avatar').attr('src', URL.createObjectURL(evt.target.files[0]));
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
        $('#preview_introduce').text($(this).val());
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
                $(this).next('p').find('.diploma_file_name').text(name);
                $(this).next('p').find('.diploma_file_remove').addClass('remove');

                if (!$(this).attr('hadFile')) {
                    $(this).attr('hadFile', 1);
                }
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
        $(this).closest('p').prev('.dipFileInp').removeAttr('hadFile').val('');
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

                if (!$(this).attr('hadFile')) {
                    $(this).attr('hadFile', 1);
                }
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
        $(this).siblings('.dipAddFileInp').removeAttr('hadFile').val('');
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
        }
        // remove from html
        $parent.remove();
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

    /**********************************************
    *
    * Preview
    *
    ***********************************************/
    $('#btnPreview').on('click', function(evt) {
        var html = '';
        $('[data-name=biography]').each(function(index, item) {
            if (item.value) {
                html += '<li>' + item.value + '</li>';
            }
        });

        $('[data-name=diploma]').each(function(index, item) {
            if (item.type == 'checkbox') {
                if (item.checked && item.value) {
                    html += '<li>' + item.value + '</li>';
                }
            }
            if (item.type == 'text') {
                if (item.value) {
                    html += '<li>' + item.value + '</li>';
                }
            }
        });

        html += '<li>' + $('[name=counseledTimes]').val() + '</li>';
        $('#popup-preview-mobile-acc')
            .find('#preview_other')
            .html(html);
        $('#popup-preview-mobile-acc').modal('show');
    });

    /**********************************************
    *
    * Submit tab 2
    *
    ***********************************************/
    function validateForm2() {
        var errors = [];

        if (!$('[data-name=category]').filter(function(index, elem) { return $(elem).is(':checked'); }).length) {
            errors.push('상담 분야');
        }
        if (!$('[name=introduce]').val().length) {
            errors.push('소개 문구');
        }
        if (!$('[name=eduLevel]:checked').length) {
            errors.push('학력');
        }
        if (!$('[data-name=biography]').filter(function(index, elem) { return $(elem).val(); } ).length) {
            errors.push('주요 약력');
        }
        if (!$('[data-name=diploma]').filter(function(index, elem) { return $(elem).is(':checked'); }).length) {
            errors.push('자격증');
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

    $('#form_tab2').on('submit', function(evt) {
    	console.log(234);
        evt.preventDefault();

        // validate form
        var errors = validateForm2();
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
                var totalFile = Number.parseInt(${totalFile});
                for(var i = 0, n = FILES.length; i < n; i++) {
                    formData.append('otherCertificates[' + totalFile + i + '].file', FILES[i]);
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
                    window.location.reload();
                }, function(err) {
                    console.error(err);
                    $('#error_fields').html('입력하지 않은 정보가 있습니다');
                    $('#popup_form_errors').modal('show');
                });
        }
    });


    /************************************************************************
    *
    * Tab 3
    *
    ************************************************************************/
    var notYetSession   = ${notYetSession};
    var notYetPaid      = ${notYetPaid};

    /**********************************************
    *
    * Other reason
    *
    ***********************************************/
    $('[name=withdraw_reason]').on('change', function(evt) {
        $(this).attr('id')
            ? $('#withdraw_reason').removeAttr('disabled')
            : $('#withdraw_reason').attr('disabled', 'disabled');
    });

    $('#withdraw_reason').on('change', function(evt) {
        $(this).closest('.item-radio').find('[name=withdraw_reason]').val($(this).val());
    });

    /**********************************************
    *
    * Submit tab 3
    *
    ***********************************************/
    $('#popup_confirm_withdraw').on('click', '.popup_accept', function(evt) {
        var $reason = $('[name=withdraw_reason]:checked');
        var _reason = $reason.val();
        var _type   = $reason.attr('id') != undefined ? 2 : 1;
        $
            .ajax({
                url         : '/auth/info/submit/tab3',
                method      : 'put',
                data        : {
                    reason  : _reason,
                    type    : _type
                },
                beforeSend  : toggleLoading
            })
            .then(function(res) {
                window.location.reload();
            }, function(err) {
                toggleLoading();
                console.error(err);
            });
    });

    $('#form_tab3').on('submit', function(evt) {
        evt.preventDefault();
        if (notYetSession) {
            $('#popup_not_yet_session').modal('show');
            return;
        }
        if (notYetPaid) {
            $('#popup_not_yet_paid').modal('show');
            return;
        }
        $('#popup_confirm_withdraw').modal('show');
    });

</script>