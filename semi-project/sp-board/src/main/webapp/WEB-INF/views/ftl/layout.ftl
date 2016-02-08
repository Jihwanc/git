<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>semi-project</title>
<script type="text/javascript" 
	src="../resources/jquery/js/jquery-1.12.0.min.js">
</script>
<style type="text/css">
body {
	font-family: Arial, Verdana, sans-serif;
}

.header,.footer,.leftPane,.rightPane {
	border: 1px solid gray;
}

.header,.main,.footer {
	width: 960px;
	color: #665544;
	margin: 10px auto;
	clear: both;
}

.leftPane,.rightPane {
	float: left;
	margin: 10px;
}

.leftPane {
	width: 100px;
}

.rightPane {
	width: 815px;
}

.leftMenu {
	list-style: none;
	padding: 0;
	margin: 10px;
}
</style>
</head>
<body>
<@tiles.insertAttribute name="header" />
	<div class="main">
		<@tiles.insertAttribute name="menu" />
		<div class="rightPane">
			<@tiles.insertAttribute name="body" />
		</div>
	</div>
<@tiles.insertAttribute name="footer" />
	
</body>
</html>