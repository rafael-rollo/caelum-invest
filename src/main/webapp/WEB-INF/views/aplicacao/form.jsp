<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Investimentos</title>
</head>
<body>
	<sec:authentication property="principal" var="usuario"/>
	<span>Usuário: ${usuario.email}</span>
	<br />

	<h1>Investimentos</h1>
	<hr>
	
	<form:form servletRelativeAction="/aplicacao" method="post" commandName="aplicacaoForm">
		<form:hidden path="contaId" value="${conta.id }"/>
		<table>
			<thead>
				<tr>
					<td>Modalidade</td>
					<td>Fidelidade</td>
					<td>Rentabilidade</td>
					<td>Selecione</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="investimento" items="${investimentos}">
					<tr>
						<td>${investimento.tipoDeInvestimento.titulo }</td>
						<td>${investimento.fidelidade } meses</td>
						<td>${investimento.rentabilidade * 100 }% ao ano</td>
						<td>
							<form:radiobutton path="investimentoId" value="${investimento.id }"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form:errors path="investimentoId" />
		
		<br>
		<label for="valor">Valor: </label>
		<form:input path="valor" />
		<form:errors path="valor" />
		
		<br>
		<input type="submit" value="Aplicar"/>
	</form:form>
</body>
</html>