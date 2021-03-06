<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, boardMVC.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<style>
	th{
		background-color:green;
		width:200px;
	}
	
	table{
		border:1px solid white;
	}
</style>
<body>
	<div align="center">
		<b>글 목 록</b>
		<table border="0" width="800">
			<tr bgcolor="yellow">
				<td align="right"><a href="writeForm.board">글쓰기</a></td>
			</tr>
		</table>
		<table border="1" width="800">
			<tr bgcolor="green">
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
				<th>IP</th>
			</tr>
<% 		List<BoardDBBean> list = (List)request.getAttribute("list");
		if (list == null || list.size()==0){%>
			<tr>
				<td colspan="6">게시된 글이 없습니다.</td>
			</tr>
<%		}else { 
			for(BoardDBBean dto : list){%>
			<tr>
				<td><%=dto.getNum()%></td>
				<!-- Get 방식으로 num값을 넘겨줌. -->
				<td><a href="context.board?num=<%=dto.getNum()%>"><%=dto.getSubject()%></a></td>
				<td><%=dto.getWriter()%></td>
				<td><%=dto.getReg_date()%></td>
				<td><%=dto.getReadcount()%></td>
				<td><%=dto.getIp()%></td>
			</tr>
<%			}
		}	%>		
		</table>
	</div>
</body>
</html>