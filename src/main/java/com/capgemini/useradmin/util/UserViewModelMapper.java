package com.capgemini.useradmin.util;

import com.capgemini.useradmin.model.domain.User;
import com.capgemini.useradmin.model.view.user.UserCreateViewModel;
import com.capgemini.useradmin.model.view.user.UserViewModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class UserViewModelMapper {

    public static TypeMap<User, UserViewModel> userToViewMap() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<User, UserViewModel> typeMap = modelMapper.typeMap(User.class, UserViewModel.class);
        Converter<LocalDate, Long> dateLongConverter = date -> date.getSource().atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        typeMap.addMappings(mapper -> mapper.using(dateLongConverter).map(User::getStartDate, UserViewModel::setStartDate));

        return typeMap;
    }

    public static TypeMap<UserViewModel, User> viewToUserMap() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<UserViewModel, User> typeMap = modelMapper.typeMap(UserViewModel.class, User.class);
        Converter<Long, LocalDate> dateLongConverter = millis -> {
            if (millis.getSource() == null) {
                return null;
            }
            LocalDate date = Instant.ofEpochMilli(millis.getSource()).atZone(ZoneId.systemDefault()).toLocalDate();
            return date;
        };
        typeMap.addMappings(mapper -> mapper.using(dateLongConverter).map(UserViewModel::getStartDate, User::setStartDate));

        return typeMap;
    }

    public static TypeMap<UserCreateViewModel, User> createViewToUserMap() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<UserCreateViewModel, User> typeMap = modelMapper.typeMap(UserCreateViewModel.class, User.class);
        Converter<Long, LocalDate> dateLongConverter = millis -> Instant.ofEpochMilli(millis.getSource()).atZone(ZoneId.systemDefault()).toLocalDate();
        typeMap.addMappings(mapper -> mapper.using(dateLongConverter).map(UserCreateViewModel::getStartDate, User::setStartDate));

        return typeMap;
    }


}
