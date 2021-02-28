<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 주문</title>


</head>
<body>
	<h2>도서 주문 페이지</h2>
	<form action="book.do" method="post" name="f">
		<input type="hidden" name="command" value="ADD">
		<b>도서 선택 : </b>
			<select name="book">
				<option > 자바의 정석/ 남궁성/ 30000</option>
				<option > 자바의 신/ 이상민/ 27000</option>
				<option > 열혈강의 자바/ 엄진영/ 35000</option>
				<option > 이것이 자바다/ 신용권/ 30000</option>
			</select>
			<input type="text" name="qty" size="2" value="1">
			<input type="submit" value="도서주문">
	
	</form>
	<hr color="red">
	<jsp:include page="cart.jsp"/>
</body>
</html>