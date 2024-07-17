package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(int id) {
        User user = userRepository.findById(id);
        return UserMapper.toDto(user);
    }

    public void addUser(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        userRepository.save(user);
    }


    public void updateUser(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        userRepository.update(user);
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}
