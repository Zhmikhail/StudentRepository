package com.example.demo.mapper;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBirthDate(user.getBirthDate());
        dto.setPosition(user.getPosition());
        dto.setSalary(user.getSalary());
        dto.setHireDate(user.getHireDate());
        dto.setFireDate(user.getFireDate());
        return dto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthDate(dto.getBirthDate());
        user.setPositionId(dto.getPositionId());
        user.setSalary(dto.getSalary());
        user.setHireDate(dto.getHireDate());
        user.setFireDate(dto.getFireDate());
        return user;
    }
}