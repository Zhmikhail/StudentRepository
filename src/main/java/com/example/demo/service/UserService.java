package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.ComponentScan;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(int id) {
        UserEntity user = userRepository.findById(id);
        return userMapper.toDto(user);
    }

    public void addUser(UserDto userDto) {
        UserEntity user = userMapper.toEntity(userDto);
        userRepository.save(user);
    }

    public void updateUser(UserDto userDto) {
        UserEntity user = userMapper.toEntity(userDto);
        userRepository.update(user);
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}
