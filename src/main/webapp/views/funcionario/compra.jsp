<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compras</title>
</head>
<body>
	<f:view>
	
		<div id="conteudo">
		<div id="header">
			<img src="../imagens/logo.png" id="logo"/>
			<LINK href="../styles/estilo.css" rel="stylesheet" type="text/css"/>
		</div>
		<div id="corpo">
			<h:form id="compraForm">

			<h:messages />
			<h:commandLink value="#{msg.new_purchase}"
				action="#{compraController.adicionarCompra}"
				rendered="#{!compraController.exibirForm}" />

			<!-- Inicio: Formulário de cadastro de Compras -->
			<h:panelGrid border="1" columns="2"
				rendered="#{compraController.exibirForm}">

				<f:facet name="header">
					<h:outputText value="#{msg.purchase_registration}" />
				</f:facet>

				<h:outputLabel for="produto" value="#{msg.product2}" />
				<h:selectOneMenu id="produto"
					value="#{compraController.compra.produto}" required="true"
					requiredMessage="#{msg.obligatory_product}">
					<f:selectItem itemValue="" itemLabel="#{msg.select_product }" />
					<f:selectItems value="#{compraController.produtos}" 
					var="produto"
					itemValue="#{produto}" 
					itemLabel="#{produto.label}" />
					<f:converter converterId="produtoConverter" />
				</h:selectOneMenu>

				<h:outputLabel for="quantidade" value="#{msg.quantity}: " />
				<h:inputText id="quantidade"
					value="#{compraController.compra.quantidade}" required="true" />

				<h:outputLabel for="funcionario" value="#{msg.employee_code}: " />
				<h:inputText id="funcionario"
					value="#{compraController.compra.funcionario.codigo}" required="true" />

				<f:facet name="footer">
					<h:panelGroup>
						<h:commandButton value="#{msg.save}"
							action="#{compraController.salvarCompra}" />
						<h:commandButton value="#{msg.cancel}"
							action="#{compraController.cancelarCadastro}" immediate="true" />
					</h:panelGroup>
				</f:facet>

			</h:panelGrid>
			<!-- Fim: Formulário de cadastro de Compra -->
			<br />


			<!-- Inicio: Tabela de Compras Cadastradas -->

			<h:dataTable var="item" value="#{compraController.compras}"
				border="1" cellpadding="1" cellspacing="1"
				rendered="#{compraController.compras.rowCount > 0}">

				<f:facet name="header">
					<h:outputText value="#{msg.registered_purchases}" />
				</f:facet>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.purchase_code }" />
					</f:facet>
					<h:outputText value="#{item.id}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.product2}" />
					</f:facet>
					<h:outputText value="#{item.produto.nome}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.quantity}" />
					</f:facet>
					<h:outputText value="#{item.quantidade}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.purchase_total}" />
					</f:facet>
					<h:outputText value="#{item.valorTotalCompra}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.employee}" />
					</f:facet>
					<h:outputText value="#{item.funcionario.nome}" />
				</h:column>

				<h:column rendered="#{!compraController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.edit}" />
					</f:facet>
					<h:commandLink action="#{compraController.alterarCompra}">
						<h:outputText value="#{msg.edit}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{compraController.compra}" />
					</h:commandLink>
				</h:column>

				<h:column rendered="#{!compraController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.delete}" />
					</f:facet>
					<h:commandLink action="#{compraController.excluirCompra}">
						<h:outputText value="#{msg.delete}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{compraController.compra}" />
					</h:commandLink>
				</h:column>

				<f:facet name="footer">
					<h:outputText
						value="#{msg.count2}#{compraController.compras.rowCount}" />
				</f:facet>
			</h:dataTable>
			<!-- Fim: Tabela de Compras Cadastradas -->


		</h:form>
		<p class="link"><a href="javascript:history.go(-1)">Voltar</a></p>
		</div>
	</div>
		
	</f:view>
</body>
</html>