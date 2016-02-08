<#ftl encoding='UTF-8'>
<br>
<form action="writeSubmit.do" method="post">
<table id="writeTable">
	<tr>
		<td></td>
		<td>
			작성자: <input type="text" id="modifier" name="text">
			공지글: <input type="checkbox" id="isNotice" name="isNotice">
		</td>
	</tr>
	<tr>
		<td>카테고리: </td>
		<td>
			<select id="category" name="category">		
				<#list categoryList as category>
					<option value="${category.id}">${category.varcharValue1}</option>
				</#list>
			</select>
		</td>
	</tr>
	<tr>
		<td>제목: </td>
		<td><input type="text" id="subject" name="subject"></td>
	</tr>
	<tr>
		<td>링크: </td>
		<td><input type="text" id="link" name="link"></td>
	</tr>
	<tr>
		<td>파일: </td>
		<td>
			<input type="text" id="filePath" name="filePath">
			<input type="file" id="fileSelector" name="fileSelector">
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			비밀글: <input type="checkbox" id="isSecret" name="isSecret">
			비밀번호: <input type="text" id="password" name="password">
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<textarea name="content" id="content" rows="10" cols="80">
			</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" id="write" class="button" value="완료" onclick="submitContents()">
			<input type="button" id="cancel" class="button" value="취소">
		</td>
	</tr>
	
</table>

<input type="hidden" name="boardId" value="${boardId}">
</form>

<script type="text/javascript" src="../resources/js/se2/js/HuskyEZCreator.js" 
charset="utf-8">
</script>

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "content",
    sSkinURI: "../resources/js/se2/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});


function submitContents(elClickedObj) {
    oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
    // document.getElementById("content").value
    
    var isNoticeInput = $("input[id=isNotice]");
    if (isNoticeInput.is(":checked")) {
	    isNoticeInput.val('true');
	} else {
	    isNoticeInput.val('false');
	}
	
	var isSecretInput = $("input[id=isSecret]");
    if (isSecretInput.is(":checked")) {
	    isSecretInput.val('true');
	} else {
	    isSecretInput.val('false');
	}
	
    try {
        elClickedObj.form.submit();
    } catch(e) {}
}

</script>

<style type="text/css">

#writeTable {
	width: 750px;
	margin: 0 auto;
}

.button {
	margin: 10 auto;
}
</style>

