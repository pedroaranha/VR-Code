<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html" version="2.0">
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
<title>Insert title here</title>
<LINK href="../styles/estilo.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<f:view>
		<div id="conteudo">
			<div id="header">
				<img src="../imagens/logo.png" id="logo" />
			</div>
			<div id="corpo">
				<div id="menu">
					<p class="link">
						<a href="listUsuario.jsf"><h:outputText value="#{msg.back}" /></a>
					</p>
				</div>
				
				<div id="middle">
					<h:form id="usuarioForm">
						<h:messages />
						<h:commandLink value="Novo Usuario" action="#{usuarioController.addUsuario}"/>
						<!-- Inicio: Formulário de cadastro de usuário -->
						<h:panelGrid columns="2" styleClass="table">

							<f:facet name="header">
								<h:outputText value="#{msg.user_registration}" />
							</f:facet>

							<h:outputLabel for="nome" value="#{msg.name2 }" />
							<h:inputText id="nome" value="#{usuarioController.usuario.nome}"
								required="true" />

							<h:outputLabel for="login" value="Login: " />
							<h:inputText id="login"
								value="#{usuarioController.usuario.login}" required="true" />

							<h:outputLabel for="senha" value="#{msg.password2}" />
							<h:inputSecret id="senha"
								value="#{usuarioController.usuario.senha}" required="true"
								redisplay="true" />

							<f:facet name="footer">
								<h:panelGroup>
									<h:commandButton value="#{msg.save}"
										action="#{usuarioController.salvarUsuario}" />
									<h:commandButton value="#{msg.cancel}"
										action="#{usuarioController.cancelarCadastroUsuario}"
										immediate="true" />
								</h:panelGroup>
							</f:facet>

						</h:panelGrid>
					</h:form>
				</div>
			</div>
		</div>
	</f:view>
</body>
	</html>
</jsp:root>