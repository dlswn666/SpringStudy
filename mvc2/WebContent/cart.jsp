<%@page import="book.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<BookDTO> list = (List)session.getAttribute("cart");
	if(list != null && list.size() != 0){
%>
<h2>현재 주문 도서 내역</h2>
<table border="1" width="500">
	<tr>
		<th>번호</th>
		<th>도서제목</th>
		<th>작가</th>
		<th>가격 </th>
		<th>수량</th>
		<th>삭제</th>
	</tr>
	<%for(int i=0; i<list.size(); ++i) { 
		BookDTO dto = list.get(i);
	%>

	
	<tr>
		<td><%=i+1 %> </td>
		<td><%=dto.getTitle() %></td>
		<td><%=dto.getAuthor() %></td>
		<td><%=dto.getPrice() %></td>
		<td><%=dto.getQty() %></td>
		<td>
			<form name="f" action="book.do" method="post">
				<input type="hidden" name="command" value="DEL">
				<!-- hidden의 위치를 반환함. -->
				<input type="hidden" name="idx" value=<%=i %>>
				<input type="submit" value="삭제">
			</form>
		</td>
	</tr>
	<%} %>
	<tr>
		<td colspan="6" align="center">
		<form name="f" action="book.do" method="post">
			<input type="hidden" name="command" value="CHK">
			<input type="submit" value="도서주문페이지로 이동">
		</form>
		</td>
	</tr>
</table>
<% } %>