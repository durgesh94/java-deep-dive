package com.learning.user;

import com.learning.user.dto.UserRequestDto;
import com.learning.user.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        return userMapper.toDto(user);
    }

    public UserResponseDto saveUser(UserRequestDto userRequest) {
        User user = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequest) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        userMapper.updateEntityFromDto(userRequest, existingUser);
        return userMapper.toDto(userRepository.save(existingUser));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
