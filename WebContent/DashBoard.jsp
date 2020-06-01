<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://www.gstatic.com/firebasejs/5.5.9/firebase.js"></script>
<script>
	// Initialize Firebase
	var config = {
		apiKey : "AIzaSyCaoYae_vKOE4pRU-8QRsvy72OWXYHgNJI",
		authDomain : "aptrek-47b57.firebaseapp.com",
		databaseURL : "https://aptrek-47b57.firebaseio.com",
		projectId : "aptrek-47b57",
		storageBucket : "aptrek-47b57.appspot.com",
		messagingSenderId : "443127269033"
	};
	firebase.initializeApp(config);
	writeUserData();
</script>
<style>
* {
	border: 0;
	padding: 0;
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

#main {
	background: linear-gradient(to bottom, #870000, #190a05);
	min-height: 100vh;
	width: 100%;
	color: #eee;
	padding: 5%;
}

.user {
	width: 100%;
	margin-bottom: 3%;
}

h1 {
	text-align: center;
}

.user-img {
	display: inline-block;
	width: 10%;
	height: 20%;
	padding: 0 1% 0 1%;
	vertical-align: middle;
	border-radius: 50%;
	margin-right: 7%;
}

.user-age {
	display: inline-block;
	padding: 5% 2% 2px 2%;
	width: 15%;
	vertical-align: middle;
}

.user-name {
	display: inline-block;
	padding: 5% 2% 2px 2%;
	width: 15%;
	vertical-align: middle;
}

.user-poll {
	display: inline-block;
	padding: 5% 2% 2px 2%;
	width: 15%;
	vertical-align: middle;
}

.ankit1 {
	background-color: #5792d1; /* Green */
	border: none;
	color: white;
	padding: 10px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 20px;
	margin: 7px 2px;
	border-radius: 12px;
	cursor: pointer;
}

table {
	border-collapse: collapse;
	width: 50%;
	height: 400px;
	border: none;
	color: white;
	font-family: serif;
	font-size: 30px;
	text-align: center;
}

.ankit {
	width: 270px;
	padding: 15px 25px;
	font-family: "HelveticaNeue-Light", "Helvetica Neue Light",
		"Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
	font-weight: 400;
	font-size: 14px;
	color: #9D9E9E;
	text-shadow: 1px 1px 0 #FFF;
	background: #FFF;
	border: 1px solid #FFF;
	border-radius: 5px;
	box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.50);
	-moz-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.50);
	-webkit-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.50);
}

.ankit:focus {
	background: #DFE9EC;
	color: #414848;
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.25);
	-moz-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.25);
	-webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.25);
	outline: 0;
}

.ankit:hover {
	background: #DFE9EC;
	color: #414848;
}

.ankit::placeholder {
	text-align: center;
	font-family: Serif;
	font-size: 20px;
}
</style>
</head>
<body background="images/ap-detect.jpg">
	<form name="myform">
		<div id='main'>
			<table>
				<tr>
					<td>UserName:</td>
					<%
						String username = (String) session.getAttribute("username");
					%>
					<td><%=username%></td>
				</tr>
				<tr>
					<td>City:</td>
					<%
						String city = (String) session.getAttribute("city");
					%>
					<td><%=city%></td>
				</tr>
				<tr>
					<td>State:</td>
					<%
						String state = (String) session.getAttribute("state");
					%>
					<td><%=state%></td>
				</tr>
				<tr>
					<td>Email:</td>
					<%
						String email = (String) session.getAttribute("email");
					%>
					<td><%=email%></td>
				</tr>

				<tr>
					<td>Rank:</td>
					<%
						String rank = (String) request.getAttribute("rank");
					%>
					<td><%=rank%></td>
				</tr>


			</table>
			<br> <br> <br> <br> <br> <br> <a
				href="LeaderBoardServlet" style="color: white; font-size: 20px">
				LeaderBoard </a>

			<p id='users'>
			<p class='user'></p>
		</div>
	</form>
</body>
</html>