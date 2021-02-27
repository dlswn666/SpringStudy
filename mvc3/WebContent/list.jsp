<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,student.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 조회</title>
</head>
<body>
	<div align="center">
	<hr color="green" width="300">
	<h2>학 생 목 록 보 기</h2>
	<hr color="green" width="300">
	<table border="1" width="500">
		<tr bgcolor="yellow">
			<th>아이디</th>
			<th>학생명</th>
			<th>학급명</th>
		</tr>
<%
		List<StudentDTO> list = (List)request.getAttribute("list");
		if (list==null || list.size()==0){%>
		<tr>
			<td colspan="3">등록된 학생이 없습니다.</td>
		</tr>	
<%		}else {
			for(StudentDTO dto : list){%>
		<tr>
			<td><%=dto.getId()%></td>
			<td><%=dto.getName()%></td>
			<td><%=dto.getCname()%></td>
		</tr>	
<%			}
		}%>					
	</table>
	</div>
</body>
</html>