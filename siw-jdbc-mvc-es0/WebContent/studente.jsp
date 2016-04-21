<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="it.uniroma3.model.Studente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dati dello studente</title>
</head>
<body>
	<div>Matricola: ${studente.matricola}</div>
	<div>Nome: ${studente.nome}</div>
	<div>Cognome: ${studente.cognome}</div>
	<div>Data di nascita: ${studente.dataNascita}</div>
</body>
</html>