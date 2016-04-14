<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Studente <% out.print(request.getAttribute("NOME"));out.print(request.getAttribute("COGNOME")); %></title>
</head>
<body>
	<p> Dati Studente Inseriti: </p>
		<ul>
			<li>NOME:<%out.print(request.getAttribute("NOME")); %></li>
			<li>COGNOME:<%out.print(request.getAttribute("COGNOME")); %></li>
			<li>MATRICOLA:<%out.print(request.getAttribute("MATRICOLA")); %></li>
			<li>DATA:<%out.print(request.getAttribute("DATA")); %></li>
			<li>INDIRIZZO:<%out.print(request.getAttribute("INDIRIZZO")); %></li>
			<li>ADDRESS:<%out.print(request.getAttribute("ADDRESS")); %></li>
			<li>HOST:<%out.print(request.getAttribute("HOST")); %></li>
			<li>USERAGENT:<%out.print(request.getAttribute("USERAGENT")); %></li>
		</ul>
</body>
</html>
