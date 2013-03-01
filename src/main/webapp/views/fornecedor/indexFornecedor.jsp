<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fornecedor</title>
<LINK href="styles/estilo.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<f:view>
<div id="conteudo">
		<div id="header">
			<img src="imagens/logo.png" id="logo"/>
		</div>
		<div id="corpo">
			<div id="listaNav">
			<p class="titulo"><h:outputText value="#{msg.products}" /></p>
			<p class="link"><a href="produto/produto.jsf"><h:outputText value="#{msg.product_registration}" /></a></p>
			</div>
		</div>
	</div>
</f:view>
</body>
</html>