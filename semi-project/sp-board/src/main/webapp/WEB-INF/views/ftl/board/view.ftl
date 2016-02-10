<#ftl encoding='UTF-8'>

<br>
<table id="writeTable">
	<tr>
		<td>작성자: </td>
		<td>
			${Session.userInfo.nickName}
		</td>
	</tr>
	<#if category?exists>
	<tr>
		<td>카테고리: </td>
		<td>${category.varcharValue1}</td>
	</tr>
	</#if>
	<tr>
		<td>제목: </td>
		<td>${content.subject}</td>
	</tr>
	<#if content.link?exists>
	<tr>
		<td>링크: </td>
		<td>${content.link}</td>
	</tr>
	</#if>
	
	<#if fileList?exists>
	<tr>
		<td>파일: </td>
		<td>
		<#list fileList as file>
			<a href="./fileDownload.do?id=${file.id}&file=${file.saveUrl}">
			${file.fileName}
			</a>
		</#list>
		</td>
	</tr>
	</#if>
	<tr>
		<td>내용</td>
		<td>
			<div id="contentDiv">
			${content.content}
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<hr>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<table>
		<#list commentList as comment>
			<tr>
				<td width="400">${comment.comment}</td>
				<td>${comment.userId}</td>
				<td>${comment.commonField.createDate}</td>
				<td><input type="button" id=${comment.id} 
					class="commentDeleteButton" value="삭제"></td>
			</tr>
		</#list>
		</table>
		</td>
	</tr>

	<tr>
		<td colspan="2">
			<form id="commentForm">
			<input type="hidden" name="contentId" value=${content.id} />
			<input type="hidden" name="userId" value=${Session.userInfo.id} />
			
			<textarea id="comment" name="comment" rows="4" cols="80"></textarea>
			<input type="button" id="commentWriteButton" value="코멘트 저장">
			
			</form>
		</td>
		
	</tr>
	
	<tr>
		<td colspan="2">
		<a href="list.do?boardId=${boardId}">리스트</a>
		</td>
	</tr>
</table>

<style type="text/css">
#contentDiv {
	border: 1px solid gray;
	height: 200px;
}
</style>

<script type="text/javascript">
$('#commentWriteButton').click(function() {
	$.ajax({
		url: './writeComment.do',
		type: 'POST',
		data:$('#commentForm').serialize(),
		success:function(data){
			alert("코멘트 등록 성공");
			location.reload(true);
		},
		fail:function(data){
			alert("코멘트 등록 실패");
		}
	})
})

$('.commentDeleteButton').click(function() {
	var commentId = $(this).attr('id');

	$.ajax({
		url: './deleteComment.do',
		type: 'POST',
		data: {commentId : commentId},
		success:function(data){
			alert("코멘트 삭제 성공");
			location.reload(true);
		},
		fail:function(data){
			alert("코멘트 삭제 실패");
		}
	})
})
</script>
