package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    public List<UserEntity> findAll() {
        String sqlQuery = QueryType.SELECT_ALL_USERS.getQuery();
        return jdbcTemplate.query(sqlQuery, userRowMapper);
    }

    public UserEntity findById(int id) {
        String sqlQuery = QueryType.SELECT_USER.getQuery();
        return jdbcTemplate.queryForObject(sqlQuery, userRowMapper, id);
    }

    public void save(UserEntity user) {
        String sqlQuery = QueryType.SAVE_USER.getQuery();
        jdbcTemplate.update(sqlQuery, user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getPositionId(), user.getSalary(), user.getHireDate(), user.getFireDate());
    }

    public void update(UserEntity user) {
        String sqlQuery = QueryType.UPDATE_USER.getQuery();
        jdbcTemplate.update(sqlQuery, user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getPositionId(), user.getSalary(), user.getHireDate(), user.getFireDate(), user.getId());
    }

    public void delete(int id) {
        String sqlQuery = QueryType.DELETE_USER.getQuery();
        jdbcTemplate.update(sqlQuery, id);
    }

    public enum QueryType {
        SELECT_ALL_USERS(String.format("SELECT * FROM Users u JOIN position p ON p.%s = u.position_id", UserRowMapper.TableFields.ID.getTableField())),
        SELECT_USER(String.format("SELECT * FROM Users u JOIN position p on p.%s = u.position_id WHERE u.%s = ?", UserRowMapper.TableFields.ID.getTableField(), UserRowMapper.TableFields.ID.getTableField())),
        SAVE_USER(String.format("INSERT INTO Users (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)", UserRowMapper.TableFields.FIRST_NAME.getTableField(), UserRowMapper.TableFields.LAST_NAME.getTableField(), UserRowMapper.TableFields.BIRTH_DATE.getTableField(), UserRowMapper.TableFields.POSITION_ID.getTableField(), UserRowMapper.TableFields.SALARY.getTableField(), UserRowMapper.TableFields.HIRE_DATE.getTableField(), UserRowMapper.TableFields.FIRE_DATE.getTableField())),
        UPDATE_USER(String.format("UPDATE Users SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?", UserRowMapper.TableFields.FIRST_NAME.getTableField(), UserRowMapper.TableFields.LAST_NAME.getTableField(), UserRowMapper.TableFields.BIRTH_DATE.getTableField(), UserRowMapper.TableFields.POSITION_ID.getTableField(), UserRowMapper.TableFields.SALARY.getTableField(), UserRowMapper.TableFields.HIRE_DATE.getTableField(), UserRowMapper.TableFields.FIRE_DATE.getTableField(), UserRowMapper.TableFields.ID.getTableField())),
        DELETE_USER(String.format("DELETE FROM Users WHERE %s = ?", UserRowMapper.TableFields.ID.getTableField()));

        private final String query;

        QueryType(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }
}
