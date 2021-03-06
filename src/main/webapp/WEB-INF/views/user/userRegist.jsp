<%@page import="org.apache.catalina.User"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
<%-- <%UserVo userVo = (UserVo) request.getAttribute("userVo");%> --%>
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
		
	<%-- 	$("#submitBtn").on("click", function() {
	<%if (userVo != null) {%>
			 var userid = "<%=userVo.getUserid()%>"
			 var usernm = "<%=userVo.getUsernm()%>"
			 var pass = "<%=userVo.getPass()%>"
			 var reg_dt = "<%=userVo.getReg_dt_fmt()%>"
			 var alias = "<%=userVo.getAlias()%>"
			 var addr1 = "<%=userVo.getAddr1()%>"
			 var addr2 = "<%=userVo.getAddr2()%>"
			 var zipcode = "<%=userVo.getZipcode()%>
	"
			/*  $("#userid").val(userid);
			 $("#usernm").val(usernm);
			 $("#pass").val(pass);
			 $("#reg_dt").val(reg_dt);
			 $("#alias").val(alias);
			 $("#addr1").val(addr1);
			 $("#addr2").val(addr2);
			 $("#zipcode").val(zipcode); */
<%}%>
	alert("잘못 입력하셨습니다")
		}) --%>
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
				<form class="form-horizontal" role="form"
					action="${cp}/user/registUser" method="post" enctype="multipart/form-data">
					
					<%-- <input type="hidden" name="userid" value="<%=user.getUserid()%>"> --%>
					<div class="form-group">
						<label for="userId" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userid" name="userid"
								placeholder="사용자 아이디" value="${param.userid }">
							<!-- 에러 메시지구현 -->	
							<span style="color:red"><form:errors path="userVo.userid"/></span>
							
							<input type="file" class="form-control" name="profile"/>
						</div>
					</div>


					spring message : <spring:message code="GREETTING" arguments="brown"/> <br>
					spring message : <spring:message code="GREETTING" arguments="${S_USER.usernm }"/>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm" name="usernm"
								placeholder="사용자 이름" value="${param.usernm }">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass"
								placeholder="********" value="${param.pass }">
						</div>
					</div>

<!-- 					<div class="form-group"> -->
<!-- 						<label for="userNm" class="col-sm-2 control-label">등록일시</label> -->
<!-- 						<div class="col-sm-10"> -->
<!-- 							<input type="text" class="form-control" id="reg_dt" name="reg_dt" -->
<%-- 								placeholder="등록일시" value="${param.reg_dt }"> --%>
<!-- 						</div> -->
<!-- 					</div> -->

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias"
								placeholder="별명" value="${param.alias }">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addr1" name="addr1"
								placeholder="도로주소" value="" readonly="readonly">
						</div>
						<div class="col-sm-2">
							<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2"
								placeholder="상세주소" value="${param.addr2 }">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode"
								name="zipcode" placeholder="우편번호" value="${param.zipcode }" readonly="readonly">
						</div>
					</div>


					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" id="submitBtn" class="btn btn-default">사용자
								등록완료</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
