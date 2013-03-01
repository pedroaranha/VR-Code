<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Extrato</title>
</head>
<body>
	<f:view>
	
		<h:form id="extratoForm">

			<!-- Inicio: Tabela de Compras do Funcionário Selecionado -->

			<h:dataTable var="item"
				value="#{funcionarioComprasController.extratoCompras}"
				border="1" cellpadding="1" cellspacing="1"
				rendered="#{funcionarioComprasController.extratoCompras.rowCount > 0}">
				
				<f:facet name="header">
					<h:outputText value="#{msg.purchase_extract}" />
				</f:facet>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.date_of_purchase}" />
					</f:facet>
					<h:outputText value="#{item.dataCompra}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.product_name }" />
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
						<h:outputText value="Total" />
					</f:facet>
					<h:outputText value="#{item.valorTotalCompra}" />
				</h:column>

				<f:facet name="footer">
						<h:outputText value="#{msg.purchase_total}: #{funcionarioComprasController.valorTotalExtrato}" />
				</f:facet>
				
			</h:dataTable>
			
		</h:form>
		
		<h4><h:outputText value="Saldo Atual: #{carrinhoController.funcionario.saldo}" /></h4><br>
		<br>
		
		<p class="link"><a href="javascript:history.go(-1)">Voltar</a></p>
		
	</f:view>
</body>
</html>