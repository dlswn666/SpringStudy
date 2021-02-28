<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, boardMVC.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>
<% BoardDBBean dto = (BoardDBBean)request.getAttribute("getBoard"); %>
<style type="text/css">
	.readInfo{
		padding-top : 50px;
		height: 100px;
		width : 200px;
	}
	
	table {
		border: 1px solid black;
		width: 100%;
		 border-collapse: collapse;
	}
	th{
		border: 1px solid black;
		background-color: yellow;
		width: 25%;
		 padding: 10px;
	}
	td{
		border: 1px solid black;
		width: 25%;
		 padding: 10px;
	}
</style>

</head>
<body>
	<div align="center">
		<div class="readInfo">
			<h2> 글내용 보기</h2>
		</div>
		<table>
			<tr>
				<th>글 번호</th>
				<td><%=dto.getNum() %></td>
				<th>조회수</th>
				<td><%=dto.getReadcount() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=dto.getWriter() %></td>
				<th>작성일</th>
				<td><%=dto.getReg_date() %></td>
			</tr>
			<tr>
				<th>글 제목</th>
				<td colspan="3"><%=dto.getSubject() %></td>
			</tr>
			<tr>
				<th width="700px">글 내용</th>
				<td colspan="3"><%=dto.getContent() %></td>
			</tr>
			<tr align="right" bgcolor="yellow">
				<td colspan="4"> 
								 <input type="button" value="글수정" 
				onclick="window.location='updateForm.board?num=<%=dto.getNum()%>'" >	
		<a href="deleteForm.board?num=<%=dto.getNum()%>"> <input type="button" value="글삭제"></a>
								 <input type="button" value="글목록">
				</td>
				
			</tr>
		</table>
			
	</div>
</body>
</html>






























