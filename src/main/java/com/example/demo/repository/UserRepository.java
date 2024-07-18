package com.example.demo.repository;
import com.example.demo.entity.UserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserEntity> findAll() {
        String sqlQuery = QueryType.SELECT_ALL_USERS.getQuery();
        //TODO: в каждый метод вместо new UserRowMapper() передавать bean
        return jdbcTemplate.query(sqlQuery, new UserRowMapper());
    }

    public UserEntity findById(int id) {
        String sqlQuery = QueryType.SELECT_USER.getQuery();
        return jdbcTemplate.queryForObject(sqlQuery, new UserRowMapper(), id);
    }

    public void save(UserEntity user) {
        String sqlQuery = QueryType.SAVE_USER.getQuery();
        jdbcTemplate.queryForObject(sqlQuery, new UserRowMapper(), user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getPositionId(), user.getSalary(), user.getHireDate(), user.getFireDate());
    }

    public void update(UserEntity user) {
        String sqlQuery = QueryType.UPDATE_USER.getQuery();
        jdbcTemplate.queryForObject(sqlQuery, new UserRowMapper(), user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getPositionId(), user.getSalary(), user.getHireDate(), user.getFireDate());
    }

    public void delete(int id) {
        String sqlQuery = QueryType.DELETE_USER.getQuery();
        jdbcTemplate.queryForObject(sqlQuery, new UserRowMapper(), id);
    }

    //TODO: вынести маппер отдельно, на уровне пакета com.example.demo.repository.mapper
    private static class UserRowMapper implements RowMapper<UserEntity> {
        @Override
        //TODO: названия колонок вынести в enum, но не запросы
        public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserEntity user = new UserEntity();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setBirthDate(rs.getDate("birth_date"));
            user.setPositionId(rs.getInt("position_id"));
            user.setPosition(rs.getString("name"));
            user.setSalary(rs.getDouble("salary"));
            user.setHireDate(rs.getDate("hire_date"));
            user.setFireDate(rs.getDate("fire_date"));
            return user;
        }
    }
    public enum QueryType {
        SELECT_ALL_USERS("SELECT * FROM Users u JOIN position p ON p.id = u.position_id"),
        SELECT_USER("SELECT * FROM Users u JOIN position p on p.id = u.position_id WHERE u.id = ?"),
        SAVE_USER("INSERT INTO Users (first_name, last_name, birth_date, position_id, salary, hire_date, fire_date) VALUES (?, ?, ?, ?, ?, ?, ?)"),
        UPDATE_USER("UPDATE Users SET first_name = ?, last_name = ?, birth_date = ?, position_id = ?, salary = ?, hire_date = ?, fire_date = ? WHERE id = ?"),
        DELETE_USER("DELETE FROM Users WHERE id = ?");

        private final String query;

        QueryType(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }
}
