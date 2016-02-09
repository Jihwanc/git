<#ftl encoding='UTF-8'>

<br>
<table id="writeTable">
	<tr>
		<td>작성자: </td>
		<td>
			${content.userId}
		</td>
	</tr>
	<#if categoryMap?exists>
	<tr>
		<td>카테고리: </td>
		<td></td>
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
			${file},
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
		<#list commentList as comment>
			<table>
				<td width="400">${comment.comment}</td>
				<td>${comment.userId}</td>
				<td>${comment.commonField.createDate}</td>
			</table>
		</#list>
		</td>
	</tr>

	<tr>
		<td colspan="2">
			<textarea id="commentArea" name="commentArea" rows="4" cols="80"></textarea>
			<input type="button" id="commentWriteButton" name="commentWriteButton" 
				onclick="writeComment()" value="코멘트 저장">
		</td>
		
	</tr>
	
	<tr>
		<td colspan="2">게시물 리스트</td>
	</tr>
</table>



<script type="text/javascript">
function writeComment() {


}

</script>

<style type="text/css">

.button {
	margin: 10 auto;
}
</style>

