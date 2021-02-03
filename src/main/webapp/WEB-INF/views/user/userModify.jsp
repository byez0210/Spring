<%@page import="org.apache.catalina.User"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>

<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>

<link href="${cp} /css/dashboard.css"
	rel="stylesheet">
<link href="${cp}/css/blog.css" rel="stylesheet">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

	$(function() {
		$("#addrBtn").on("click", function() {
			new daum.Postcode({
				oncomplete : function(data) {

					$("#addr1").val(data.address); //도로주소
					$("#zipcode").val(data.zonecode); //우편변호
					// 사용자 편의성을 위헤 상세주소 입력 input 태그 focus 설정
					$("#addr2").focus();
				}
			
			}).open();

		})
	})
</script>
</head>

<body>

	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/WEB-INF/views/common/left.jsp"%>
			</div>
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<form class="form-horizontal" role="form" action="${cp}/user/userModify" method="post" enctype="multipart/form-data">
					<input type="hidden" name="userid" value="${user.userid }">

					<div class="form-group">
						<label for="userId" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
							<img src="${cp }/profile/${user.userid}.png"/>
							<input type="file" class="form-control" id="profile" name="profile" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="userId" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<label class="control-label">${user.userid }</label>
						</div>
					</div>

					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm"
								name="usernm" placeholder="${user.userid }" value="${user.usernm }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="userpass"
								name="pass" placeholder="********" value="${user.pass }">
						</div>
					</div>
					
<!-- 					<div class="form-group"> -->
<!-- 						<label for="userNm" class="col-sm-2 control-label">등록일시</label> -->
<!-- 						<div class="col-sm-10"> -->
<!-- 						<input type="text" class="form-control" id="reg_dt" -->
<%-- 								name="reg_dt" placeholder=<fmt:formatDate value="${user.reg_dt }" pattern="yyyy-MM-dd"/> value="<fmt:formatDate value="${user.reg_dt }" pattern="yyyy.MM.dd"/>"> --%>
<!-- 						</div> -->
<!-- 					</div> -->
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userAlias"
								name="alias" placeholder="${user.alias }" value="${user.alias }"  >
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addr1"
									name="addr1" placeholder="도로주소" value="${user.addr1 }" readonly="readonly">
						</div>
						<div class="col-sm-2">
							<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
						<input type="text" class="form-control" id="addr2"
								name="addr2"placeholder="상세주소" value="${user.addr2 }">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode"
								name="zipcode" placeholder="우편번호"  value="${user.zipcode }" readonly="readonly">
						</div>
					</div>
					

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">사용자 수정완료</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
