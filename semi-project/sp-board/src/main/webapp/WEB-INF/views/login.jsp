<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Login Page</title>

<jsp:include page="include/isInclude.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function (){
	
	$("#loginbtn").click(function(){
		if($("#loginid").val() == ""){
			alert("로그인 아이디를 입력해주세요");
			$("#loginid").focus();
		}else if($("#loginpwd").val() == ""){
			// check validation 
			alert("로그인 비밀번호를 입력해주세요");
			$("#loginpwd").focus();
		}else{
			$("#loginfrm").attr("action", "<c:url value='/j_spring_security_check'/>");
			$("#loginfrm").submit();
		}
	});
});
</script>  
</head>

<body>
<div style="display:inline-block;">
    로그인 화면
    <form id="loginfrm" name="loginfrm" action="<c:url value='${ctx}/j_spring_security_check'/>" method="POST">
    <table>
    	<tr>
    		<td>ID</td>
    		<td>
    			<input type="text" id="loginid" name="loginid" value="${loginid}" />
    		</td>
    		<td rowspan="2">
    			<input type="button" id="loginbtn" value="확인s" />
    		</td>
    	</tr>
    	<tr>
    		<td>Password</td>
    		<td>
    			<input type="text" id="loginpwd" name="loginpwd" value="${loginpwd}" />
    		</td>
    	</tr>
    	<c:if test="${not empty securityexceptionmsg}">
    	<tr>
    		<td colspan="2">
				<font color="red">
				<p>Login Fail, try again.</p>
				<p>${securityexceptionmsg}</p>
				</font>
    		</td>
    	</tr>
    	</c:if>
    </table>
    <input type="hidden" name="loginRedirect" value="${loginRedirect}" />
    </form>
</div>
</body>
</html>
