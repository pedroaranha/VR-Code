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
<title>Produtos</title>
</head>
<body>
	<f:view>
	
		<div id="header">
    
		  <div id="carrinho">
		    <p class="link"><a href="../produto/carrinho.jsf"><h:outputText value="#{msg.my_cart}" /></a></p>
		    <c:if test="${empty carrinho or carrinho.totalDeItens eq 0 }">
		      <span><h:outputText value="#{msg.empty_cart_notice}" /></span>
		    </c:if>
		    <c:if test="${carrinho.totalDeItens > 0 }">
		      <ul>
		        <li>
		          <strong><h:outputText value="#{msg.items}" />:</strong> ${carrinho.totalDeItens}
		        </li>
		        <li>
		          <strong>Total:</strong> 
		          <fmt:formatNumber type="currency" 
		            value="${carrinho.total}"/>
		        </li>
		      </ul>
		    </c:if>
		  </div>
		</div>
		<br><br><br><br><br>
		
		<h:form id="lisProdutoForm">

			<!-- Inicio: Tabela de produtos Cadastrados -->

			<h:dataTable var="item" value="#{carrinhoController.itensProduto}"
				border="1" cellpadding="1" cellspacing="1"
				rendered="#{carrinhoController.itensProduto.rowCount > 0}">

				<f:facet name="header">
					<h:outputText value="#{msg.products}" />
				</f:facet> 

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
						<h:outputText value="#{msg.purchase}" />
					</f:facet>
					
					<h:commandButton value="#{msg.purchase}" type="submit" action="#{carrinhoController.comprar}">
						<f:setPropertyActionListener target="#{carrinhoController.produtoSelecionado}" value="#{item}" />
					</h:commandButton>
				</h:column>

				<f:facet name="footer">
					<h:outputText
						value="#{msg.count2}#{carrinhoController.itensProduto.rowCount}" />
				</f:facet>
			</h:dataTable>
			
			<!-- Fim: Tabela de produtos Cadastrados -->

		</h:form>
	</f:view>
</body>
</html>