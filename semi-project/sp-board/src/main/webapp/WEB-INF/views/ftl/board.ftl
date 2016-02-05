<html>
<head>
<title>Insert title here</title>
</head>
<body>
${contentList}<br>

<#list model["userList"] as content>
번호 : ${item_index+1} | 제목 : ${content.subject} | 작성자 : ${content.userId } | 작성일 : ${item.commonField.createDate}<br>
</#list>
	
</body>
</html>