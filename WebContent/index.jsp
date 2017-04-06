<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <% response.setHeader("Pragma","No-cache"); response.setHeader("Cache-Control","No-cache"); response.setDateHeader("Expires", -1); response.setHeader("Cache-Control", "No-store"); %> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Verify Code</title>
</head>
<body>
	<div>
		<tr>
			<td nowrap width="437"></td>
			<td>
				<img id="img" src="${pageContext.request.contextPath}/authImage" /><br/>
				<input type="text" name="verifyCode" id="verifyCode" />
				<button id="submit">submit</button>
			</td>
		</tr>
	</div>
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	
	<script type="text/javascript">
		window.onload = function() {
			$('#img').on('click', function() {
				this.src = "${pageContext.request.contextPath}/authImage?date=" + new Date();
			});
			$('#submit').on('click', function() {
				var code = $('#verifyCode')[0].value;
				if(code.length != 4) {
					alert('验证码长度不正确!!!');
					return;
				}
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/verify",
					data : {
						verifyCode : code 
					},
					success : function success(result) {
						alert(result.result);
						location.reload(true);
					}
				});
			});
		};
	</script>
</body>
</html>