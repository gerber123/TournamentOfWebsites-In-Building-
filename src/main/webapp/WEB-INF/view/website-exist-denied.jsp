<html>

<head>
	<title>Vote Denied</title>
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/form-page.css"/>
	<link href="https://fonts.googleapis.com/css?family=Cinzel" rel="stylesheet">
</head>

<body>
<div class="box">
	<img src="${pageContext.request.contextPath}/resources/images/error.jpg" class="avatar">
	<h1>Fail!</h1>
	<h3>You register already one website! <br>One Person = One Website <br>Be Fair!</h3>
	<a href="${pageContext.request.contextPath}/homepage"><input type="submit" value="Back to home-page" ></a>
	<a href="${pageContext.request.contextPath}/website/deleteWebsiteByOwner" onclick=" if (!confirm('Are You Sure You want Delete your website from Tournament, You will lose your all points!?')) return false;"><input type="submit" value="Delete Your Website" ></a>

</div>


</body>

</html>