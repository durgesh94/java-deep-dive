package com.learning.user;

import com.learning.user.dto.UserRequestDto;
import com.learning.user.dto.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto) {

        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    public UserResponseDto toDto(User user) {

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public List<UserResponseDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDto(UserRequestDto dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
    }
}