package com.operations.booking.service.impl;

import com.operations.booking.dto.request.UserLogRequest;
import com.operations.booking.dto.response.UserLogResponse;
import com.operations.booking.entity.UserLog;
import com.operations.booking.exception.NotDataFound;
import com.operations.booking.mapper.UserLogMapper;
import com.operations.booking.repository.UserLogRepository;
import com.operations.booking.service.UserLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLogServiceImpl implements UserLogService {

    private final UserLogRepository userLogRepository;
    private final UserLogMapper userLogMapper;

    @Override
    public List<UserLogResponse> getAllUserLogs() {
        log.info("Getting all user logs");

        List<UserLog> userLogEntities = userLogRepository.findAll();
        return userLogMapper.toDTOs(userLogEntities);
    }

    @Override
    public UserLogResponse createNewUserLog(UserLogRequest userLogRequest) {
        log.info("Creating a new user log: {}", userLogRequest);

        UserLog userLogEntity = userLogMapper.fromDTO(userLogRequest);
        userLogEntity = userLogRepository.save(userLogEntity);
        return userLogMapper.toDTO(userLogEntity);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting user log with ID: {}", id);

        UserLog userLogEntity = userLogRepository.findById(id).orElseThrow(() -> {
            return new NotDataFound("User log not found with id: " + id);
        });
        userLogRepository.delete(userLogEntity);
    }

    @Override
    public void updateUserLog(Integer id, UserLogRequest userLogRequest) {
        log.info("Updating user log with ID: {}, new data: {}", id, userLogRequest);

        var userLogEntity = userLogRepository.findById(id)
                .orElseThrow(() -> new NotDataFound("User log not found"));

        userLogMapper.mapUpdateRequestToEntity(userLogEntity, userLogRequest);
        userLogRepository.save(userLogEntity);
    }
}
