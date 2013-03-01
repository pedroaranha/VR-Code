<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compras do Funcionário</title>
</head>
<body>
	<f:view>
		<h:form id="listComprasForm">

			<!-- Inicio: Busca por funcionário -->
			<h:panelGrid border="1" columns="2"
				rendered="#{!funcionarioComprasController.exibirForm}">

				<f:facet name="header">
					<h:outputText value="#{msg.list_purchases}" />
				</f:facet>

				<h:outputLabel for="funcionario" value="#{msg.employee}: " />
				<h:selectOneMenu id="funcionario"
					value="#{funcionarioComprasController.idfuncionario}" required="true"
					requiredMessage="#{msg.obligatory_employee}" >
					<f:selectItem itemValue="" itemLabel="#{msg.select_employee}" />
					<f:selectItems value="#{funcionarioComprasController.funcionarios}" 
					var="funcionario"
					itemValue="#{funcionario.value}" 
					itemLabel="#{funcionario.label}" />
				</h:selectOneMenu>

				<f:facet name="footer">
					<h:panelGroup>
						<h:commandButton value="#{msg.search}"
							action="#{funcionarioComprasController.listarFuncionarioCompras}" />
						<h:commandButton value="#{msg.cancel}"
							action="#{funcionarioComprasController.cancelarCadastro}" immediate="true" />
					</h:panelGroup>
				</f:facet>

			</h:panelGrid>
			<!-- Fim: Busca por funcionário -->
			<br />


			<!-- Inicio: Tabela de Compras do Funcionário Selecionado -->

			<h:dataTable var="item" value="#{funcionarioComprasController.funcionarioCompras}"
				border="1" cellpadding="1" cellspacing="1"
				rendered="#{funcionarioComprasController.funcionarioCompras.rowCount > 0}">

				<f:facet name="header">
					<h:outputText value="#{msg.registered_purchases}" />
				</f:facet>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.date_of_purchase}" />
					</f:facet>
					<h:outputText value="#{item.dataCompra}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.product_price}" />
					</f:facet>
					<h:outputText value="#{item.produto.nome}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.product_price} (Un.)" />
					</f:facet>
					<h:outputText value="#{item.produto.preco}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.quantity_bought}" />
					</f:facet>
					<h:outputText value="#{item.quantidade}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.purchase_total}" />
					</f:facet>
					<h:outputText value="#{item.valorTotalCompra}" />
				</h:column>

				<f:facet name="footer">
					<h:outputText
						value="Contagem: #{funcionarioComprasController.funcionarioCompras.rowCount}" />
				</f:facet>
			</h:dataTable>
			<!-- Fim: Tabela de Compras do Funcionário Selecionado -->
		</h:form>
		<p class="link"><a href="javascript:history.go(-1)">Voltar</a></p>
	</f:view>
</body>
</html>