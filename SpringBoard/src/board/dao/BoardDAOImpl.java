package board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import board.dto.BoardDTO;

public class BoardDAOImpl implements BoardDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	class MyRowMapper implements RowMapper<BoardDTO>{
		@Override
		public BoardDTO mapRow(ResultSet rs, int arg1) throws SQLException {
			BoardDTO dto = new BoardDTO();
			dto.setNum(rs.getInt("num"));
			dto.setWriter(rs.getString("writer"));
			dto.setEmail(rs.getString("email"));
			dto.setSubject(rs.getString("subject"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setReg_date(rs.getString("reg_date"));
			dto.setReadcount(rs.getInt("readcount"));
			dto.setContent(rs.getString("content"));
			dto.setIp(rs.getString("ip"));
			dto.setFilename(rs.getString("filename"));
			dto.setFilesize(rs.getInt("filesize"));
			return dto;
		}
	}
	
	@Override
	public List<BoardDTO> listBoard() {
		String sql = "select * from board2 order by num desc";
		MyRowMapper mapper = new MyRowMapper();
		List<BoardDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

	@Override
	public BoardDTO getBoard(int num, String mode) {
		if (mode.equals("content")) {
			String sql = "update board2 set readcount = readcount+1 where num = ?";
			jdbcTemplate.update(sql, num);
		}
		String sql = "select * from board2 where num = ?";
		MyRowMapper mapper = new MyRowMapper();
		BoardDTO dto = jdbcTemplate.queryForObject(sql, new Object[] {num}, mapper);
		return dto;
	}

	@Override
	public int insertBoard(BoardDTO dto) {
		String sql = "insert into board2 values(spring_board_seq.nextval, ?,?,?,?,sysdate,0,?,?,?,?)";
		Object[] values = new Object[] {dto.getWriter(), dto.getEmail(), 
				dto.getSubject(), dto.getPasswd(), dto.getContent(), dto.getIp(), dto.getFilename(), dto.getFilesize()};
		int res = jdbcTemplate.update(sql, values);
		return res;
	}

	@Override
	public int deleteBoard(int num, String passwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
