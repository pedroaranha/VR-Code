<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Funcionários</title>
<LINK href="estilo.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<f:view>
		
		
		
		
		<h:form id="funcionarioForm">
		<div id="conteudo">
		<div id="header">
			<img src="../imagens/logo.png" id="logo"/>
		</div>
		<div id="corpo">
			<h:messages />
			<h:commandLink value="#{msg.new_employee}"
				action="#{funcionarioController.adicionarFuncionario}"
				rendered="#{!funcionarioController.exibirForm}" />

			<!-- Inicio: Formulário de cadastro de funcionarios -->
			<h:panelGrid border="1" columns="2"
				rendered="#{funcionarioController.exibirForm}">

				<f:facet name="header">
					<h:outputText value="#{msg.employee_registration}" />
				</f:facet>

				<h:outputLabel for="nome" value="#{msg.name2}" />
				<h:inputText id="nome" value="#{funcionarioController.funcionario.nome}"
					required="true" />

				<h:outputLabel for="login" value="Login: " />
				<h:inputText id="login"
					value="#{funcionarioController.funcionario.usuario.login}" required="true" />

				<h:outputLabel for="senha" value="#{msg.password2}" />
				<h:inputSecret id="senha"
					value="#{funcionarioController.funcionario.usuario.senha}" required="true"
					redisplay="true" />
					
				<h:outputLabel for="saldo" value="#{msg.balance}: " />
				<h:inputText id="saldo"
					value="#{funcionarioController.funcionario.saldo}" required="true" />	

				<f:facet name="footer">
					<h:panelGroup>
						<h:commandButton value="#{msg.save}"
							action="#{funcionarioController.salvarFuncionario}" />
						<h:commandButton value="#{msg.cancel}"
							action="#{funcionarioController.cancelarCadastro}" immediate="true" />
					</h:panelGroup>
				</f:facet>

			</h:panelGrid>
			<!-- Fim: Formulário de cadastro de funcionario -->
			<br />


			<!-- Inicio: Tabela de funcionarios Cadastradas -->

			<h:dataTable var="item" value="#{funcionarioController.funcionarios}"
				border="1" cellpadding="1" cellspacing="1"
				rendered="#{funcionarioController.funcionarios.rowCount > 0}">

				<f:facet name="header">
					<h:outputText value="#{msg.registered_employees}" />
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

				<h:column rendered="#{!funcionarioController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.edit}" />
					</f:facet>
					<h:commandLink action="#{funcionarioController.alterarFuncionario}">
						<h:outputText value="#{msg.edit}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{funcionarioController.funcionario}" />
					</h:commandLink>
				</h:column>

				<h:column rendered="#{!funcionarioController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.delete}" />
					</f:facet>
					<h:commandLink action="#{funcionarioController.excluirFuncionario}">
						<h:outputText value="#{msg.delete}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{funcionarioController.funcionario}" />
					</h:commandLink>
				</h:column>

				<f:facet name="footer">
					<h:outputText
						value="#{msg.count2}#{funcionarioController.funcionarios.rowCount}" />
				</f:facet>
			</h:dataTable>
			<!-- Fim: Tabela de funcionarios Cadastrados -->


		
		<p class="link"><a href="javascript:history.go(-1)">Voltar</a></p>
			
		</div>
	</div>
		
		</h:form>
		
				</f:view>
</body>
</html>