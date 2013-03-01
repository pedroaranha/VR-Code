<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<f:view>
		<head>
<title>Sistema de Login de Usuários</title>
		</head>
		<body>
			<h:form>
				<h3><h:outputText value="#{msg.failed_login}" /></h3>
			</h:form>
		</body>
		
		<p class="link"><a href="../index.jsf"><h:outputText value="#{msg.back}" /></a></p>
		
	</f:view>
</body>
</html>