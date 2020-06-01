<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<style type="text/css">
body {
	font-family: Arial;
	font-size: 10pt;
}

table {
	border: 1px solid #ccc;
	border-collapse: collapse;
}

table td {
	padding: 5px;
}
</style>
</head>
<body>
	<script type="text/javascript">
		function Upload() {
			var fileUpload = document.getElementById("fileUpload");
			var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
			if (regex.test(fileUpload.value.toLowerCase())) {
				if (typeof (FileReader) != "undefined") {
					var reader = new FileReader();
					reader.onload = function(e) {
						var a = 0;
						var table = document.createElement("table");
						var rows = e.target.result.split("\n");
						for (var i = 0; i < rows.length; i++) {
							var cells = rows[i].split(",");
							if (cells.length > 1) {
								var row = table.insertRow(-1);
								for (var j = 0; j < cells.length; j++) {
									var cell = row.insertCell(-1);
									cell.innerHTML = cells[j];
									a = cells[j];
								}
							}
						}
						window.location.replace("Default.jsp?COLEVEL=" + a);
						var pollution = document.getElementById("pollution");
						pollution.value = a;
						var dvCSV = document.getElementById("dvCSV");
						dvCSV.innerHTML = "";
						dvCSV.appendChild(table);
					}
					reader.readAsText(fileUpload.files[0]);
				} else {
					alert("This browser does not support HTML5.");
				}
			} else {
				alert("Please upload a valid CSV file.");
			}
		}
	</script>
	<input type="file" id="fileUpload" />
	<input type="hidden" name="pollution" id="pollution">
	<input type="button" id="upload" value="Upload" onclick="Upload()" />
	<hr />
	<!--     <div id="dvCSV">
    </div>
 -->
	<%
		String str = request.getParameter("COLEVEL");
	if (str != null) {
			System.out.println(str + " jnkn");
			request.setAttribute("poll", str);
			String username=(String)session.getAttribute("username");
			System.out.print(username);
			request.setAttribute("username", username);
			RequestDispatcher rd = request.getRequestDispatcher("/FinalDetailsSubmittedServlet");
			rd.forward(request, response);
		}
	%>
</body>
</html>
