package com.operations.booking.service;

import com.operations.booking.dto.request.UserLogRequest;
import com.operations.booking.dto.response.UserLogResponse;

import java.util.List;

public interface UserLogService {
    List<UserLogResponse> getAllUserLogs();

    UserLogResponse createNewUserLog(UserLogRequest userLogRequest);

    void updateUserLog(Integer id, UserLogRequest userLogRequest);

    void deleteById(Integer Id);
}
