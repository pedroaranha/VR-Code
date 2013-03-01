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
<title>Área Administrativa - VRCode</title>
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
				<p class="titulo"><h:outputText value="#{msg.users}" /></p>
				<p class="link"><a href="usuario/usuario.jsf"><h:outputText value="#{msg.user_management}" /></a></p>
				<p class="titulo"><h:outputText value="#{msg.companies}" /></p>
				<p class="link"><a href="empresa/empresa.jsf"><h:outputText value="#{msg.company_management}" /></a></p>
				<p class="titulo"><h:outputText value="#{msg.employees}" /></p>
				<p class="link"><a href="funcionario/funcionario.jsf"><h:outputText value="#{msg.employee_management}" /></a></p>
				<p class="link"><a href="funcionario/compra.jsf"><h:outputText value="#{msg.shopping_management}" /></a></p>
				<p class="link"><a href="funcionario/listCompras.jsf"><h:outputText value="#{msg.shopping_history}" /></a></p>
				<p class="titulo"><h:outputText value="#{msg.suppliers}" /></p>
				<p class="link"><a href="fornecedor/fornecedor.jsf"><h:outputText value="#{msg.supplier_management}" /></a></p>
				<p class="titulo"><h:outputText value="#{msg.products}" /></p>
				<p class="link"><a href="produto/produto.jsf"><h:outputText value="#{msg.product_management}" /></a></p>
			</div>
		</div>
	</div>
</f:view>
</body>
</html>
</jsp:root>