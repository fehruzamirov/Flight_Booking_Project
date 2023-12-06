package com.operations.booking.mapper;


import com.operations.booking.dto.request.UserRequest;
import com.operations.booking.dto.response.UserResponse;
import com.operations.booking.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true)
)
public interface UserMapper {
    User fromDTO(UserRequest userRequest);

    UserResponse toDTO(User user);

    List<UserResponse> toDTOs(List<User> users);

    void mapUpdateRequestToEntity(@MappingTarget User user, UserRequest userRequest);


}

