<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Conta ${conta.numero}</title>
</head>
<body>
	<sec:authentication property="principal" var="usuario"/>
	<span>Usuário: ${usuario.email}</span>
	<a href="/logout">Sair</a>
	<br />

	<h1>Conta ${conta.numero }</h1>
	<br />
	
	<span>Saldo: ${conta.saldo }</span>
	<br />
	
	<a href="/conta/${conta.id }/deposito/form">Depositar</a>
	<a href="/aplicacao/form/conta/${conta.id }">Nova Aplicação</a>
</body>
</html>