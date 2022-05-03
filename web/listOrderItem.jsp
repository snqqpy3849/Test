<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1 align="center">Shopping Cart</h1>
<br><br>
<table align="center" border="1" cellspacing="0">
	<tr>
		<td>Product Name</td>
		<td>Price</td>
		<td>Num</td>
		<td>Count</td>
	</tr>
	<c:forEach items="${ois }" var="oi" varStatus="st">
		<tr>
			<td>${oi.product.name }</td>
			<td>${oi.product.price }</td>
			<td>${oi.num }</td>
			<td>${oi.product.price*oi.num}</td>
		</tr>
	</c:forEach>
	
	<c:if test="${!empty ois }">
		<tr>
			<td colspan="4" align="right"><a href="createOrder">create order</a></td>
		</tr>
	</c:if>
</table>
