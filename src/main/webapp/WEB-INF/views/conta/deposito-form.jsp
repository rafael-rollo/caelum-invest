<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Deposito</title>
</head>
<body>
	<h2>Depósito</h2>
	
	<h3>Conta: ${conta.numero }</h3>

	<form:form action="/conta/deposito" method="post" commandName="depositaForm">
		<form:hidden path="contaId" />
		<div>
			<label for="valor">Valor: </label>
			<form:input path="valor" />
			<form:errors path="valor" />
		</div>
		<div>
			<input type="submit" value="Depositar" />
		</div>
		
	</form:form>
		
</body>
</html>