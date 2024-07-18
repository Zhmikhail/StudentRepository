package com.example.demo.mapper;
import org.springframework.stereotype.Component;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;

@Component
public class UserMapper {

    public UserDto toDto(UserEntity user) {
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


    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPositionId(dto.getPositionId());
        entity.setSalary(dto.getSalary());
        entity.setHireDate(dto.getHireDate());
        entity.setFireDate(dto.getFireDate());
        return entity;
    }
}