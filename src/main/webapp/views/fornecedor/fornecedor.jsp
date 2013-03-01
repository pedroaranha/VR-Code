<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Fornecedores</title>
<LINK href="../styles/estilo.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<f:view>
		<div id="conteudo">
		<div id="header">
			<img src="../imagens/logo.png" id="logo"/>
		</div>
		<div id="corpo">
			<h:form id="fornecedorForm">

			<h:messages />
			<h:commandLink value="Novo Fornecedor"
				action="#{fornecedorController.adicionarFornecedor}"
				rendered="#{!fornecedorController.exibirForm}" />

			<!-- Inicio: Formulário de cadastro de fornecedores -->
			<h:panelGrid border="1" columns="2"
				rendered="#{fornecedorController.exibirForm}">

				<f:facet name="header">
					<h:outputText value="#{msg.supplier_registration}" />
				</f:facet>

				<h:outputLabel for="nome" value="#{msg.name2}" />
				<h:inputText id="nome" value="#{fornecedorController.fornecedor.nome}"
					required="true" />

				<h:outputLabel for="login" value="Login: " />
				<h:inputText id="login"
					value="#{fornecedorController.fornecedor.usuario.login}" required="true" />

				<h:outputLabel for="senha" value="#{msg.password2} }" />
				<h:inputSecret id="senha"
					value="#{fornecedorController.fornecedor.usuario.senha}" required="true"
					redisplay="true" />

				<f:facet name="footer">
					<h:panelGroup>
						<h:commandButton value="#{msg.save}"
							action="#{fornecedorController.salvarFornecedor}" />
						<h:commandButton value="#{msg.cancel}"
							action="#{fornecedorController.cancelarCadastro}" immediate="true" />
					</h:panelGroup>
				</f:facet>

			</h:panelGrid>
			<!-- Fim: Formulário de cadastro de fornecedor -->
			<br />


			<!-- Inicio: Tabela de fornecedores Cadastrados -->

			<h:dataTable var="item" value="#{fornecedorController.fornecedores}"
				border="1" cellpadding="1" cellspacing="1"
				rendered="#{fornecedorController.fornecedores.rowCount > 0}">

				<f:facet name="header">
					<h:outputText value="#{msg.registered_suppliers}" />
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

				<h:column rendered="#{!fornecedorController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.edit}" />
					</f:facet>
					<h:commandLink action="#{fornecedorController.alterarFornecedor}">
						<h:outputText value="#{msg.edit}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{fornecedorController.fornecedor}" />
					</h:commandLink>
				</h:column>

				<h:column rendered="#{!fornecedorController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.delete}" />
					</f:facet>
					<h:commandLink action="#{fornecedorController.excluirFornecedor}">
						<h:outputText value="#{msg.delete}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{fornecedorController.fornecedor}" />
					</h:commandLink>
				</h:column>

				<f:facet name="footer">
					<h:outputText
						value="#{msg.count2}#{fornecedorController.fornecedores.rowCount}" />
				</f:facet>
			</h:dataTable>
			<!-- Fim: Tabela de fornecedores Cadastrados -->


		</h:form>
		<p class="link"><a href="javascript:history.go(-1)">Voltar</a></p>
		</div>
	</div>
		
		
</f:view>
</body>
</html>