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

	@Override
	public List<BoardDBBean> listBoard() {
		String sql = "select * from board order by num desc";
		RowMapper<BoardDBBean> mapper = new RowMapper<BoardDBBean>() {
			@Override
			public BoardDBBean mapRow(ResultSet rs, int arg1) throws SQLException {
				BoardDBBean dto = new BoardDBBean();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setContent(rs.getString("content"));
				dto.setIp(rs.getString("ip"));
				return dto;
			}
		};
		List<BoardDBBean> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

	@Override
	public BoardDBBean getBoard(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBoard(BoardDBBean dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(int num, String passwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(BoardDBBean dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
