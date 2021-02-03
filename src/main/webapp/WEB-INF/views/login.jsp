<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
	<%-- commo_lib.jsp의 내용을 지금 기술되는 부분에 코드를 복사해서 붙여넣기 --%>
	
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>

    <!-- Custom styles for this template -->
    <link href="${cp}/css/signin.css" rel="stylesheet">
    
    <script type="text/javascript">
    	function getCookieValue(cookieStr, cookieName) {
    		cookies = cookieStr.split("; ");
 
    		for(i = 0; i<cookies.length; i++) {
    			cookies1 = cookies[i].split("=");
    			if(cookies1[0]==cookieName) {
    				return cookies1[1];
    			}
    		}
    		return "";
    	}
    	//cookie 값 설정
    	
    	//cookieName : 추가하고자 하는 쿠키이름
    	//cookieValue : 쿠키의 값
    	//expires : 유효기간 일수 
    	function addCookie(cookieName,cookieValue, expires){
    		var dt = new Date() ; // 지금현재 날짜 == > espires만큼 미래날짜로 변경
    		dt.setDate(dt.getDate() + parseInt(expires));
    		console.log(dt);
    		
    		document.cookie = cookieName + "=" + cookieValue + "; " +
    							"path=/; expires=" + dt.toGMTString();
    	}
    	
    	//cookie 값 삭제 
    	
    	function deleteCookie(cookieName){
    		addCookie(cookieName,"",-1);
    	}
    	
    	//html 문서로딩이 완료되고 나서 실행되는 코드
    	$(function(){
			<c:if test="${msg != null }">
    			alert("${msg}" +"redirect");
    		</c:if>

    		//userid, rememberme 쿠키를 확인하여 존재할 경우, 값 설정, 체크
    		if(Cookies.get('rememberme')=="Y"){
    			$("#userid").val(Cookies.get("userid"));
    			$("#rememberme").attr("checked",true);
    		}
    	//signin 아이디를 select
    	$("#signin").on("click",function(){
    		// rememberme 체크박스가 체크 되어있는지 확인
	    	// 체크되어있을 경우 
    		if($("#rememberme").is(":checked")==true){
	    		// userid inout에 있는 값을 userid쿠키로 자장
    			Cookies.set("userid",$("#userid").val());

	    		// rememberme 쿸키로 Y값을 저장
    			Cookies.set("rememberme","Y");
    			
    		// 체크가 해제 되어있는 경우 : 더이상 사용하지 않는다는 의미이므로  userid,rememberme 쿠키 삭제
    		}else{
    			Cookies.remove("userid");
    			Cookies.remove("rememberme");
    		}
    		
    		// form 태그를 이용하여 signin요청 
    			$("#frm").submit();
    	});
    });
    </script>
  </head>

  <body>

    <div class="container">
		<%-- UNT_CD : ${param.UNT_CD } / <%=request.getParameter("UNT_CD") %> --%>
	<%-- 	cp: ${cp } / <%=application.getAttribute("cp") %> --%>
      <form class="form-signin" id="frm" action="${cp}/login/process" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="userid" class="sr-only">userid</label>
        <input type="text" id="userid" name="userid" class="form-control" placeholder="사용자 아이디"  value="brown" required autofocus>
        <label for="pass" class="sr-only">Password</label>
        <input type="password" id="pass" name="pass" class="form-control" placeholder="사용자 비밀번호" value="brownPass" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" id="rememberme" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="signin">Sign in</button>
      </form>

    </div> <!-- /container -->


  </body>
</html>
