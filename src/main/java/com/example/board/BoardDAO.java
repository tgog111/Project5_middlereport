package com.example.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insertBoard(BoardVO vo){
        String sql = "insert into Drama (title, writer, content) values("
                + "'" + vo.getTitle() + "',"
                + "'" + vo.getWriter() + "',"
                + "'" + vo.getContent() + "')";
        return jdbcTemplate.update(sql);
    }
    public int deleteBoard(int seq) {
        String sql = "delete from Drama  where seq = " + seq;
        return jdbcTemplate.update(sql);
    }
    public int updateBoard(BoardVO vo) {
        String sql = "update Drama set title ='" + vo.getTitle() + "',"
                + "writer='" + vo.getWriter() + "',"
                + "content='" + vo.getContent() + "' where seq =" + vo.getSeq();
        return jdbcTemplate.update(sql);
    }

    class BoardRowMapper implements RowMapper<BoardVO> {
        @Override
        public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BoardVO vo = new BoardVO();
            vo.setSeq(rs.getInt("seq"));
            vo.setTitle(rs.getString("title"));
            vo.setContent(rs.getString("content"));
            vo.setWriter(rs.getString("writer"));
            vo.setRegdate(rs.getDate("regdate"));
            return vo;
        }
    }
    public BoardVO getBoard(int seq){
        String sql = "select * from Drama where seq="+seq;
        return jdbcTemplate.queryForObject(sql, new BoardRowMapper());
    }
    public List<BoardVO> getBoardList(){
        String sql = "select * from Drama order by seq desc";
        return jdbcTemplate.query(sql, new BoardRowMapper());
    }


}
