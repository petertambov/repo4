package com;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = new RowMapper<User>() {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            return user;
        }
    };

    public void initTable() {
        jdbcTemplate.execute("create table if not exists users "+
                " (id bigserial primary key, username varchar(255) unique)");
        jdbcTemplate.execute("truncate table users");
    }

    public Long create(String username) {
        String sql = "insert into users (username) values (?) returning id";
        return jdbcTemplate.queryForObject(sql, Long.class, username);
    }
    public int delete(Long id) {
        String sql = "delete from users where id = ?";
        return jdbcTemplate.update(sql, id);
    }
    public User select(Long id) {
        String sql = "select id, username from users where id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }

    public List<User> selectAll() {
        String sql = "select id, username from users";
        return jdbcTemplate.query(sql, userRowMapper);
    }


}