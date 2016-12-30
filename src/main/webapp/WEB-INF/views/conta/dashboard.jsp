<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard - Contas</title>
</head>
<body>
	<sec:authentication property="principal" var="usuario"/>

	<h1>DASHBOARD</h1>
	<span>Usuário: ${usuario.email}</span>
	<a href="/logout">Sair</a>

	<hr>
	<h2>Contas</h2>
	<table>
		<tr>
			<td>Número</td>
			<td>Saldo</td>
			<td>Ações</td>
		</tr>
		<c:forEach var="conta" items="${contas}">
			<tr>
				<td>${conta.numero}</td>
				<td>${conta.saldo}</td>
				<td><a href="/conta/${conta.id }">Detalhes</a></td>			
			</tr>
		</c:forEach>
	</table>
</body>
</html>