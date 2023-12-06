package com.operations.booking.service.impl;

import com.operations.booking.dto.request.UserRequest;
import com.operations.booking.dto.response.UserResponse;
import com.operations.booking.entity.User;
import com.operations.booking.exception.NotDataFound;
import com.operations.booking.mapper.UserMapper;
import com.operations.booking.repository.UserRepository;
import com.operations.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUser() {
        log.info("Getting all users");

        List<User> userEntities = userRepository.findAll();
        return userMapper.toDTOs(userEntities);
    }

    @Override
    public UserResponse createNewUser(UserRequest userRequest) {
        log.info("Creating a new user: {}", userRequest);

        User userEntity = userMapper.fromDTO(userRequest);
        userEntity = userRepository.save(userEntity);
        return userMapper.toDTO(userEntity);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting user with ID: {}", id);

        User userEntity = userRepository.findById(id).orElseThrow(() -> {
            return new NotDataFound("User not found with id: " + id);
        });
        userRepository.delete(userEntity);
    }

    @Override
    public void updateUser(Integer id, UserRequest userRequest) {
        log.info("Updating user with ID: {}, new data: {}", id, userRequest);

        var userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotDataFound("User not found"));

        userMapper.mapUpdateRequestToEntity(userEntity, userRequest);
        userRepository.save(userEntity);
    }
}
