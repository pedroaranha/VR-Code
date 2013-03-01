<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:p="http://primefaces.org/ui"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core" version="2.0">
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
<title>Lista de Usuários</title>
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
						<h:outputLink value="cadastrarUsuario.jsf" ><h:outputText value="#{msg.user_registration}" /></h:outputLink>
					</p>
				</div>
				<div id="middle">
					<h:form id="usuarioForm">
						<!-- Inicio: Tabela de Usuarios Cadastrados -->

						<h:dataTable var="item" value="#{usuarioController.usuarios}"
							rendered="#{usuarioController.usuarios.rowCount > 0}" styleClass="table"
							>

							<f:facet name="header">
								<h:outputText value="#{msg.registered_users}"/>
							</f:facet>

							<h:column headerClass="tableTitulo">
								<f:facet name="header">
									<h:outputText value="#{msg.name}"/>
								</f:facet>
								<h:outputText value="#{item.nome}" />
							</h:column>

							<h:column headerClass="tableTitulo">
								<f:facet name="header" >
									<h:outputText value="Login"/>
								</f:facet>
								<h:outputText value="#{item.login}" />
							</h:column>

							<h:column headerClass="tableTitulo">
								<f:facet name="header">
									<h:outputText value="#{msg.password}"/>
								</f:facet>
								<h:outputText value="#{item.senha}" />
							</h:column>

							<h:column headerClass="tableTitulo">
								<f:facet name="header">
									<h:outputText value="#{msg.options}"/>
								</f:facet>
								<h:commandLink action="#{usuarioController.alterarUsuario}">
									<h:graphicImage value="../imagens/icones/edit.png" styleClass="icone" title="Alterar" alt="Alterar"/>
									<f:setPropertyActionListener value="#{item}"
										target="#{usuarioController.usuario}" />
								</h:commandLink>
								<h:commandLink action="#{usuarioController.excluirUsuario}">
									<h:graphicImage value="../imagens/icones/delete.png" styleClass="icone" title="Excluir" alt="Excluir"/>
									<f:setPropertyActionListener value="#{item}"
										target="#{usuarioController.usuario}" />
								</h:commandLink>
							</h:column>
							
							<f:facet name="footer">
								<h:outputText value="#{msg.quantity}: #{usuarioController.usuarios.rowCount}" style="margin-top: 10px; background:#ccc"/>
							</f:facet>

						</h:dataTable>
						<!-- Fim: Tabela de Usuarios Cadastrados -->
					</h:form>
				</div>
			</div>
		</div>
	</f:view>
</body>
	</html>
</jsp:root>