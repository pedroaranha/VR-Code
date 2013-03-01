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
<title>Login</title>
<LINK href="styles/estilo.css" rel="stylesheet" type="text/css"/>
<LINK href="../styles/estilo.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<f:loadBundle basename="i18n.messages" var="msg" />
<f:view locale="#{languageDetails.locale}">
	<div id="conteudo">
			<div id="header">
				<img src="imagens/logo.png" id="logo"/>
				<img src="../imagens/logo.png" id="logo"/>
			</div>
			<div id="login">
			
	        <h:form>
	            <h:outputLabel for="locale" value="#{msg.language}" />
	            <h:selectOneMenu id="locale" value="#{languageDetails.language}" onchange="submit()">
	                <f:selectItem itemValue="pt" itemLabel="#{msg.portuguese}" />
	                <f:selectItem itemValue="en" itemLabel="#{msg.english}" />
	            </h:selectOneMenu>
	        </h:form>
	        
			<h:form id="loginForm">
				<h:panelGrid columns="2">
				
					<h:outputLabel value="Login: " for="login"/>
					<h:inputText id="login" value="#{usuarioController.login}" required="true"/>
					
					<h:outputLabel value="#{msg.password2}" for="senha"/>
					<h:inputSecret id="senha" value="#{usuarioController.senha}" required="true"/>
					
					<h:commandButton type="submit" action="#{usuarioController.realizarLogin}" value="#{msg.enter}" />
					
				</h:panelGrid>
			</h:form>
			</div>
		</div>
</f:view>
</body>
</html>
</jsp:root>