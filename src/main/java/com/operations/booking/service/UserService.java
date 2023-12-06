package com.operations.booking.service;



import com.operations.booking.dto.request.UserRequest;
import com.operations.booking.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();

    UserResponse createNewUser(UserRequest userRequest);

    void updateUser(Integer id, UserRequest userRequest);

    void deleteById(Integer id);
}
