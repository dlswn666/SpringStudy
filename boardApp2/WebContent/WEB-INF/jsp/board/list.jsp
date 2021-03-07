<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, board.dto.*"%>
<!-- list.jsp -->
<html>
<head>
	<title>게시글</title>
</head>
<body>
	<div align="center">
		<b>글 목 록</b>
		<table border="0" width="800">
			<tr bgcolor="yellow">
				<td align="right"><a href="board_write.do">글쓰기</a></td>
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
				<th>파일</th>
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
				<td><a href="board_content.do?num=<%=dto.getNum()%>"><%=dto.getSubject()%></a></td>
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




