<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Deposito</title>
</head>
<body>
	<h2>Depósito</h2>
	
	<h3>Conta: ${conta.numero }</h3>
	<form action="/conta/deposito" method="post">
		<input type="hidden" value="${conta.id }" name="contaId">
		<div>
			<label for="valor">Valor: </label>
			<input type="text" id="valor" name="valor" />
		</div>
		<div>
			<input type="submit" value="Depositar" />
		</div>
	</form>
</body>
</html>