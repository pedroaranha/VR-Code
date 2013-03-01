<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulário Usuario - JSF</title>
</head>
<body>
	<f:view>
		<h:form id="usuarioForm">

			<h:messages />
			<h:commandLink value="#{msg.new_user}"
				action="#{usuarioController.adicionarUsuario}"
				rendered="#{!usuarioController.exibirForm}" />

			<!-- Inicio: Formulário de cadastro de usuário -->
			<h:panelGrid border="1" columns="2"
				rendered="#{usuarioController.exibirForm}">

				<f:facet name="header">
					<h:outputText value="#{msg.user_registration}" />
				</f:facet>

				<h:outputLabel for="nome" value="#{msg.name2}" />
				<h:inputText id="nome" value="#{usuarioController.usuario.nome}"
					required="true" />

				<h:outputLabel for="login" value="Login: " />
				<h:inputText id="login" value="#{usuarioController.usuario.login}"
					required="true" />

				<h:outputLabel for="senha" value="#{msg.password2}" />
				<h:inputSecret id="senha" value="#{usuarioController.usuario.senha}"
					required="true" redisplay="true" />

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
			<!-- Fim: Formulário de cadastro de usuário -->
			<br />

			<!-- Inicio: Tabela de Usuarios Cadastrados -->

			<h:dataTable var="item" value="#{usuarioController.usuarios}"
				border="1" cellpadding="1" cellspacing="1"
				rendered="#{usuarioController.usuarios.rowCount > 0}">

				<f:facet name="header">
					<h:outputText value="#{msg.registered_users}" />
				</f:facet>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.name}" />
					</f:facet>
					<h:outputText value="#{item.nome}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Login" />
					</f:facet>
					<h:outputText value="#{item.login}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.password}" />
					</f:facet>
					<h:outputText value="#{item.senha}" />
				</h:column>

				<h:column rendered="#{!usuarioController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.edit}" />
					</f:facet>
					<h:commandLink action="#{usuarioController.alterarUsuario}">
						<h:outputText value="#{msg.edit}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{usuarioController.usuario}" />
					</h:commandLink>
				</h:column>

				<h:column rendered="#{!usuarioController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.delete}" />
					</f:facet>
					<h:commandLink action="#{usuarioController.excluirUsuario}">
						<h:outputText value="#{msg.delete}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{usuarioController.usuario}" />
					</h:commandLink>
				</h:column>

				<f:facet name="footer">
					<h:outputText
						value="#{msg.count2}#{usuarioController.usuarios.rowCount}" />
				</f:facet>
			</h:dataTable>
			<!-- Fim: Tabela de Usuarios Cadastrados -->


		</h:form>
		<p class="link"><a href="javascript:history.go(-1)">Voltar</a></p>
	</f:view>
</body>
</html>