<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="board.dto.*"%>
<!-- updateForm.jsp -->
<script type="text/javascript">
	function check(){
		if (f.subject.value==""){
			alert("������ �Է��� �ּ���!!")
			f.subject.focus()
			return false
		}
		if (f.content.value==""){
			alert("������ �Է��� �ּ���!!")
			f.content.focus()
			return false
		}
		if (f.passwd.value==""){
			alert("��й�ȣ�� �Է��� �ּ���!!")
			f.passwd.focus()
			return false
		}
		return true
	}
</script>
<%	BoardDBBean dto = (BoardDBBean)request.getAttribute("getBoard"); %>
<div align="center">
	<form name="f" action="board_updatePro.do" method="post" onsubmit="return check()">
		<input type="hidden" name="num" value="<%=dto.getNum()%>"/>
		<table border="1" width="500">
			<tr bgcolor="yellow">
				<td align="center" colspan="2">�� �� ��</td>
			</tr>
			<tr>  
				<td align="center" width="20%" bgcolor="yellow">�� ��</td>
				<td><input type="text" name="writer" value="<%=dto.getWriter()%>"></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">�� ��</td>
				<td><input type="text" name="subject" value="<%=dto.getSubject()%>" size="50"></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">Email</td>
				<td><input type="text" name="email" value="<%=dto.getEmail()%>" size="50"></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">�� ��</td>
				<td><textarea name="content" rows="10" cols="50" class="box"><%=dto.getContent()%></textarea></td>
			</tr>
			<tr>
				<td align="center" width="20%" bgcolor="yellow">��й�ȣ</td>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="yellow" align="center">
					<input type="submit" value="�ۼ���">
					<input type="reset" value="�ٽ��ۼ�">
					<input type="button" value="��Ϻ���" onclick="window.location='board_list.do'">
				</td>
			</tr>
		</table>
	</form>
</div>