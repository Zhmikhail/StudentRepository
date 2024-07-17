package com.example.demo.repository;
import com.example.demo.entity.User;
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

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM Users u JOIN position p on p.id = u.position_id", new UserRowMapper());
    }

    public User findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Users u JOIN position p on p.id = u.position_id WHERE u.id = ?", new UserRowMapper(), id);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO Users (first_name, last_name, birth_date, position_id, salary, hire_date, fire_date) VALUES (?, ?, ?, ?, ?, ?, ?)",
                user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getPositionId(), user.getSalary(), user.getHireDate(), user.getFireDate());
    }

    public void update(User user) {
        jdbcTemplate.update("UPDATE Users SET first_name = ?, last_name = ?, birth_date = ?, position_id = ?, salary = ?, hire_date = ?, fire_date = ? WHERE id = ?",
                user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getPositionId(), user.getSalary(), user.getHireDate(), user.getFireDate(), user.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Users WHERE id = ?", id);
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
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
}
