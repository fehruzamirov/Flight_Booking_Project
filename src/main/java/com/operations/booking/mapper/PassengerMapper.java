package com.operations.booking.mapper;


import com.operations.booking.dto.PassengerDto;
import com.operations.booking.entity.Passenger;

import org.mapstruct.Mapper;

@Mapper
public interface PassengerMapper {

    PassengerDto toDto(Passenger entity);

    Passenger toEntity(PassengerDto dto);
}
