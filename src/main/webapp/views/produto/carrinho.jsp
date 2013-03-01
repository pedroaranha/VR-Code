<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK href="../styles/estilo.css" rel="stylesheet" type="text/css"/>
<title>Produtos</title>
</head>
<body>
	<f:view>
		<div id="conteudo">
		<div id="header">
			<img src="../imagens/logo.png" id="logo"/>
		</div>
		<div id="corpo">
			<c:if test="${empty carrinho or carrinho.totalDeItens eq 0 }">
		      <span><h:outputText value="#{msg.empty_cart}" /> <br><br>
		       <h:outputText value="#{msg.instructions1}" /><br> 
		        <h:outputText value="#{msg.instructions2}" /></span>
		</c:if>      
		<br><br>
		<c:if test="${carrinhoController.funcionario.saldo < carrinhoController.valorCompra }">
			<span><h:outputText value="Seu saldo é insuficiente para realizar esta compra."  /></span><br>
			<span><h:outputText value="*Por favor, " /><a href="../funcionario/extrato.jsf">cheque</a><h:outputText value=" seu saldo atual." /></span><br><br>
		</c:if>
		<br><br><br><br>
		 
		<c:if test="${carrinho.totalDeItens > 0 }">
		
		<h:form id="carrinhoForm">
			<!-- Inicio: Tabela de produtos no Carrinho -->
	
			<h:dataTable var="item" value="#{carrinho.itensCarrinho}" border="1"
				cellpadding="1" cellspacing="1"
				rendered="#{carrinho.itensCarrinho.rowCount > 0}">
	
				<f:facet name="header">
					<h:outputText value="#{msg.your_cart_items}" />
				</f:facet>
	
				<h:column>
					<f:facet name="header">
						<h:outputText value="#" />
					</f:facet>
					<div class="produto_id">
						<h:outputText value="#{item.produto.id}" />
					</div>
				</h:column>
	
				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.name}" />
					</f:facet>
					<h:outputText value="#{item.produto.nome}" />
				</h:column>
	
				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.description}" />
					</f:facet>
					<h:outputText value="#{item.produto.descricao}" />
				</h:column>
	
				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.price}" />
					</f:facet>
					<h:outputText value="#{item.produto.preco}" />
				</h:column>
	
				<h:column>
					<f:facet name="header">
						<h:outputText value="QR Code" />
					</f:facet>
					<div id="qrcodeCanvas"></div>
				</h:column>
				
				<h:column rendered="true">
					<f:facet name="header">
						<h:outputText value="#{msg.remove_from_cart}" />
					</f:facet>
					<h:commandLink action="#{carrinhoController.excluir}">
						<h:outputText value="#{msg.delete}" />
						<f:setPropertyActionListener value="#{item.produto.id}"
							target="#{carrinhoController.itemId}" />
					</h:commandLink>
				</h:column>
	
				<f:facet name="footer">
					<h:outputText value="#{msg.count2}#{carrinho.itensCarrinho.rowCount} - Valor total:  #{carrinhoController.valorCompra}" />
				</f:facet>
			</h:dataTable>
			<!-- Fim: Tabela de produtos no Carrinho -->
		
		
			<h:commandButton value="Finalizar Compra" type="submit" action="#{carrinhoController.finalizarCompra}">
			</h:commandButton>
			
		</h:form>
		
		</c:if>  
		
		<p class="link"><a href="../produto/listProdutos.jsf"><h:outputText value="#{msg.product_list}" /></a></p>
		</div>
	</div>
		
		
	</f:view>
</body>
</html>