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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<LINK href="../styles/estilo.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<f:view>
		<div id="conteudo">
			<div id="header">
				<img src="../imagens/logo.png" id="logo"/>
			</div>
			<div id="corpo">
				<div id="listaNav">
					<p class="titulo"><h:outputText value="#{msg.users}" /></p>
					<p class="link"><a href="usuario.jsf"><h:outputText value="#{msg.user_management}" /></a></p>
				</div>
			</div>
		</div>
</f:view>
</body>
</html>
</jsp:root>