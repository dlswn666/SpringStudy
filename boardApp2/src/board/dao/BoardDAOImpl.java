package board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import board.dto.BoardDBBean;

public class BoardDAOImpl implements BoardDAO {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	class MyRowMapper implements RowMapper<BoardDBBean>{
		@Override
		public BoardDBBean mapRow(ResultSet rs, int arg1) throws SQLException {
			BoardDBBean dto = new BoardDBBean();
			dto.setNum(rs.getInt("num"));
			dto.setWriter(rs.getString("writer"));
			dto.setEmail(rs.getString("email"));
			dto.setSubject(rs.getString("subject"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setReg_date(rs.getString("reg_date"));
			dto.setReadcount(rs.getInt("readcount"));
			dto.setContent(rs.getString("content"));
			dto.setIp(rs.getString("ip"));
			return dto;
		}
	}
	
	private MyRowMapper mapper = new MyRowMapper();
	
	@Override
	public List<BoardDBBean> listBoard() {
		String sql = "select * from board order by num desc";
		List<BoardDBBean> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

	@Override
	public BoardDBBean getBoard(int num, String mode) {
		if (mode.equals("content")) {
			String sql = "update board set readcount = readcount+1 where num = ?";
			jdbcTemplate.update(sql, num);
		}
		String sql = "select * from board where num = ?";
		BoardDBBean dto = jdbcTemplate.queryForObject(sql, new Object[] {num}, mapper);
		return dto;
	}

	@Override
	public int insertBoard(BoardDBBean dto) {
		String sql = "insert into board values(spring_board_seq.nextval, ?,?,?,?,sysdate,0,?,?)";
		Object[] values = new Object[] {dto.getWriter(), dto.getEmail(), 
				dto.getSubject(), dto.getPasswd(), dto.getContent(), dto.getIp()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public int deleteBoard(int num, String passwd) {
		BoardDBBean dto = getBoard(num, "delete");
		if (dto.getPasswd().equals(passwd)) {
			String sql = "delete from board where num = ?";
			return jdbcTemplate.update(sql, num);
		}else {
			return -1;
		}
	}

	@Override
	public int updateBoard(BoardDBBean dto) {
		BoardDBBean dbDTO = getBoard(dto.getNum(), "update");
		if (dbDTO.getPasswd().equals(dto.getPasswd())) {
			String sql = "update board set writer=?, email=?, subject=?, content=? where num = ?";
			Object[] values = new Object[] {dto.getWriter(), dto.getEmail(), 
												dto.getSubject(), dto.getContent(), dto.getNum()};
			return jdbcTemplate.update(sql, values);
		}else {
			return -1;
		}
	}

}









