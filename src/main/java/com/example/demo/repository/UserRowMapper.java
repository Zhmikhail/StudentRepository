package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserEntity user = new UserEntity();
        user.setId(rs.getInt(TableFields.ID.getTableField()));
        user.setFirstName(rs.getString(TableFields.FIRST_NAME.getTableField()));
        user.setLastName(rs.getString(TableFields.LAST_NAME.getTableField()));
        user.setBirthDate(rs.getDate(TableFields.BIRTH_DATE.getTableField()));
        user.setPositionId(rs.getInt(TableFields.POSITION_ID.getTableField()));
        user.setPosition(rs.getString(TableFields.NAME.getTableField()));
        user.setSalary(rs.getDouble(TableFields.SALARY.getTableField()));
        user.setHireDate(rs.getDate(TableFields.HIRE_DATE.getTableField()));
        user.setFireDate(rs.getDate(TableFields.FIRE_DATE.getTableField()));
        return user;
    }

    public interface RowMapperBean<T> {
        T mapRow(ResultSet rs, int rowNum) throws SQLException;
    }

    public interface EntityRowMapper<T> extends RowMapper<T> {
        T mapRow(ResultSet rs, int rowNum);
    }

    public enum TableFields {
        ID("id"),
        FIRST_NAME("first_name"),
        LAST_NAME("last_name"),
        BIRTH_DATE("birth_date"),
        POSITION_ID("position_id"),
        NAME("name"),
        SALARY("salary"),
        HIRE_DATE("hire_date"),
        FIRE_DATE("fire_date");

        private final String field;

        TableFields(String field) {
            this.field = field;
        }

        public String getTableField() {
            return field;
        }
    }
}