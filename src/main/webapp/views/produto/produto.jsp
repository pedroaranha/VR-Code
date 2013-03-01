<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Produtos</title>
	<LINK href="../styles/estilo.css" rel="stylesheet" type="text/css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>

	<!--<script type="text/javascript" src="../jquery.qrcode.min.js"></script>-->
	<script type="text/javascript" src="../js/jquery.qrcode.js"></script>
	<script type="text/javascript" src="../js/qrcode.js"></script>
</head>
<body>
	<f:view>
	
	<div id="conteudo">
		<div id="header">
			<img src="../imagens/logo.png" id="logo"/>
		</div>
		<div id="corpo">
			<h:form id="produtoForm">

			<h:messages />
			<h:commandLink value="#{msg.new_product}"
				action="#{produtoController.adicionarProduto}"
				rendered="#{!produtoController.exibirForm}" />

			<!-- Inicio: Formulário de cadastro de produto -->
			<h:panelGrid border="0" columns="2"
				rendered="#{produtoController.exibirForm}">

				<f:facet name="header">
					<h:outputText value="#{msg.product_registration}" />
				</f:facet>

				<h:outputLabel for="nome" value="#{msg.name2}" />
				<h:inputText id="nome" value="#{produtoController.produto.nome}"
					required="true" />
					
				<h:outputLabel for="descricao" value="#{msg.description}: " />
				<h:inputText id="descricao" value="#{produtoController.produto.descricao}" />

				<h:outputLabel for="preco" value="#{msg.price}: " />
				<h:inputText id="preco"
					value="#{produtoController.produto.preco}" required="true" />

				<h:outputLabel for="fornecedor" value="#{msg.supplier}: " />
				<h:selectOneMenu id="fornecedor" value="#{produtoController.produto.fornecedor}" required="true"  
                requiredMessage="#{msg.obligatory_supplier}">
					<f:selectItem itemValue="" itemLabel="#{msg.select_supplier}" />
					<f:selectItems 
					value="#{produtoController.fornecedores}"
					var="fornecedor"
					itemValue="#{fornecedor}"
					itemLabel="#{fornecedor.label}"
					/> 
					<f:converter converterId="fornecedorConverter"/>
				</h:selectOneMenu>

				<f:facet name="footer">
					<h:panelGroup>
						<h:commandButton value="#{msg.save}"
							action="#{produtoController.salvarProduto}" />
						<h:commandButton value="#{msg.cancel}"
							action="#{produtoController.cancelarCadastro}" immediate="true" />
					</h:panelGroup>
				</f:facet>

			</h:panelGrid>
			<!-- Fim: Formulário de cadastro de produto -->
			<br />


			<!-- Inicio: Tabela de produtos Cadastrados -->

			<h:dataTable var="item" value="#{produtoController.produtos}"
				border="1" cellpadding="1" cellspacing="1"
				rendered="#{produtoController.produtos.rowCount > 0}">

				<f:facet name="header">
					<h:outputText value="#{msg.registered_products}" />
				</f:facet>
				
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="#" />
					</f:facet>
					<div class="produto_id"><h:outputText value="#{item.id}" /></div>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.name}" />
					</f:facet>
					<h:outputText value="#{item.nome}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.description}" />
					</f:facet>
					<h:outputText value="#{item.descricao}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.price}" />
					</f:facet>
					<h:outputText value="#{item.preco}" />
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="#{msg.supplier}" />
					</f:facet>
					<h:outputText value="#{item.fornecedor.nome}" />
				</h:column>
				
				<h:column>
					<f:facet name="header">
						<h:outputText value="QR Code" />
					</f:facet>
					<div id="qrcodeCanvas"></div>
				</h:column>

				<h:column rendered="#{!produtoController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.edit}" />
					</f:facet>
					<h:commandLink action="#{produtoController.alterarProduto}">
						<h:outputText value="#{msg.edit}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{produtoController.produto}" />
					</h:commandLink>
				</h:column>

				<h:column rendered="#{!produtoController.exibirForm}">
					<f:facet name="header">
						<h:outputText value="#{msg.delete}" />
					</f:facet>
					<h:commandLink action="#{produtoController.excluirProduto}">
						<h:outputText value="#{msg.delete}" />
						<f:setPropertyActionListener value="#{item}"
							target="#{produtoController.produto}" />
					</h:commandLink>
				</h:column>

				<f:facet name="footer">
					<h:outputText
						value="Contagem: #{produtoController.produtos.rowCount}" />
				</f:facet>
			</h:dataTable>
			<!-- Fim: Tabela de produtos Cadastrados -->

		</h:form>
		<h:inputHidden id="ROOT_URL" value="#{request.requestURL.substring(0, request.requestURL.length() - request.requestURI.length())}#{request.contextPath}/" />
		</div>
	</div>
		
	</f:view>
</body>

	<script type="text/javascript">
		$(document).ready(function(){
			ROOT_URL = $("#ROOT_URL").val();
			$('.produto_id').each(function() {
				produto_id = $(this).text();
				$(this).parent().parent().find("#qrcodeCanvas").qrcode(ROOT_URL+"views/produto/listProdutos.jsf?produto_id="+produto_id);
				//$(this).parent().parent().find("#qrcodeCanvas").qrcode("http://10.49.0.3:8080/VrCode/views/produto/listProdutos.jsf?produto_id="+produto_id);
			});	
		});
	</script>
</html>