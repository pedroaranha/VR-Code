<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" version="2.0">
    <jsp:directive.page language="java"
        contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
    <jsp:text>
        <![CDATA[ <?xml version="1.0" encoding="ISO-8859-1" ?> ]]>
    </jsp:text>
    <jsp:text>
        <![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
    </jsp:text>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Funcionário - VRcode</title>
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
			<p class="link"><a href="produto/listProdutos.jsf"><h:outputText value="#{msg.list_products}" /></a></p>
			<p class="link"><a href="produto/carrinho.jsf"><h:outputText value="#{msg.cart}" /></a></p>
			<p class="titulo"><h:outputText value="#{msg.purchases}" /></p>
			<p class="link"><a href="funcionario/extrato.jsf"><h:outputText value="#{msg.purchase_extract}" /></a></p>
			</div>
		</div>
	</div>
</f:view>
</body>
</html>
</jsp:root>