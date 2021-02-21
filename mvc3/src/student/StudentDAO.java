package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StudentDAO {
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	static DataSource ds = null;
	static {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");
		} catch(NamingException e) {
			System.err.println("lookup실패 : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public int insertStudent(StudentDTO dto) throws SQLException{
		try {
			String sql = "insert into student values(?,?,?)";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getCname());
			return ps.executeUpdate();
		} finally {
			if(ps != null ) ps.close();
			if(con != null) ps.close();
		}
	}
	
	public List<StudentDTO> listStudent() throws SQLException{
		try {
			String sql = "select * from student";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<StudentDTO> list = new ArrayList<>();
			while(rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setCname(rs.getString("cname"));
				list.add(dto);
			}
			return list;
		} finally {
			if(ps != null ) ps.close();
			if(rs != null) ps.close();
			if(con != null) ps.close();
		}
	}

}






















