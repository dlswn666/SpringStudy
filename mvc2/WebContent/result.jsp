<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="book.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서결제페이지</title>
</head>
<%
	Hashtable<String, BookDTO> ht = (Hashtable) request.getAttribute("order");
%>
<body>
	<div align="center">
		<h2>도서결제페이지</h2>
		<table border="1" width="500">
			<tr>
				<th>도서명</th>
				<th>지은이</th>
				<th>수량</th>
				<th>단가</th>
				<th>가격</th>
<%
	int totalPrice = 0;
	Enumeration<String> enu = ht.keys();
	while(enu.hasMoreElements()){
		String key = enu.nextElement();
		BookDTO dto = ht.get(key); %>
				


			</tr>
			<td><%=dto.getTitle()%> </td>
			<td><%=dto.getAuthor()%></td>
			<td><%=dto.getQty()%> </td>
			<td><%=dto.getPrice() %></td>
			<td><%=dto.getPrice()*dto.getQty()%>   </td>
			<% totalPrice += dto.getPrice()*dto.getQty(); }%>
			<tr>
				<td colspan="4" align="center">결재금액</td>
				<td><%=totalPrice%></td>
			</tr>

		</table>
	</div>
</body>
</html>