<#ftl encoding='UTF-8'>
<br>
<table name="boardContentList">
	<tr>
		<th>번호</th><th width="400px">제목</th><th>작성자</th><th>작성일</th>
	</tr>
	<#list contentList as content>
		<tr>
			<td>${content_index+1}</td>
			<td>
			<a href="view.do?boardId=${boardId}&contentId=${content.id}">
			${content.subject}
			</a>
			</td>
			<td>${content.userId}</td>
			<td>${content.commonField.createDate}</td>
		</tr>
	</#list>
	
</table>

<div>
<a href="write.do?boardId=${boardId}">글작성</a>
</div>