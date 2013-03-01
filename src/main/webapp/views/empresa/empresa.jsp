<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Empresa</title>
<LINK href="../styles/estilo.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<f:view>
		<div id="conteudo">
		<div id="header">
			<img src="../imagens/logo.png" id="logo"/>
		</div>
		<div id="corpo">
			<h:form id="empresaForm">

			<h:messages />
			<h:commandLink value="#{msg.new_company}"
				action="#{empresaController.adicionarEmpresa}"
				rendered="#{!empresaController.exibirForm}" />

			<!-- Inicio: Formulário de cadastro de empresa -->
			<h:panelGrid border="1" columns="2"
				rendered="#{empresaController.exibirForm}">

				<f:facet name="header">
					<h:outputText value="#{msg.company_registration}" />
				</f:facet>

				<h:outputLabel for="nome" value="#{msg.name2}" />
				<h:inputText id="nome" value="#{empresaController.empresa.nome}"
					required="true" />

				<h:outputLabel for="login" value="Login: " />
				<h:inputText id="login"
					value="#{empresaController.empresa.usuario.login}" required="true" />

				<h:outputLabel for="senha" value="#{msg.password}" />
				<h:inputSecret id="senha"
					value="#{empresaController.empresa.usuario.senha}" required="true"
					redisplay="true" />

				<f:facet name="footer">
					<h:panelGroup>
						<h:commandButton value="#{msg.save}"
							action="#{empresaController.salvarEmpresa}" />
						<h:commandButton value="#{msg.cancel}"
							action="#{empresaController.cancelarCadastro}" immediate="true" />
					</h:panelGroup>
				</f:facet>

			</h:panelGrid>
			<!-- Fim: Formulário de cadastro de empresa -->
			<br />


			<!-- Inicio: Tabela de empresas Cadastradas -->

			<h:dataTable var="item" value="#{empresaController.empresas}"
				border="1" cellpadding="1" cellspacing="1"
				rendered="#{empresaController.empresas.rowCount > 0}">

				<f:facet name="header">
					<h:outputText value="#{msg.registered_companies}" />
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
					<h:outputText value="#{item.usuario.login}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.password}" />
					</f:facet>
					<h:outputText value="#{item.usuario.senha}" />
				</h:column>

				<h:column rendered="#{!empresaController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.edit}" />
					</f:facet>
					<h:commandLink action="#{empresaController.alterarEmpresa}">
						<h:outputText value="#{msg.edit}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{empresaController.empresa}" />
					</h:commandLink>
				</h:column>

				<h:column rendered="#{!empresaController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.delete}" />
					</f:facet>
					<h:commandLink action="#{empresaController.excluirEmpresa}">
						<h:outputText value="#{msg.delete}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{empresaController.empresa}" />
					</h:commandLink>
				</h:column>

				<f:facet name="footer">
					<h:outputText
						value="#{msg.count2}#{empresaController.empresas.rowCount}" />
				</f:facet>
			</h:dataTable>
			<!-- Fim: Tabela de empresas Cadastradas -->


		</h:form>
		<p class="link"><a href="javascript:history.go(-1)">Voltar</a></p>
		</div>
	</div>
		
		
		
		
	</f:view>
</body>
</html>