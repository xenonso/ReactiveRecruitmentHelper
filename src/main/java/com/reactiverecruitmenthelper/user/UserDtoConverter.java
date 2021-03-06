package com.reactiverecruitmenthelper.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserDtoConverter {
    Flux<UserDto> userFluxToDtoFluxWithRoles(Flux<User> userFlux) {
        return userFlux.flatMap(user -> userMonoToDtoMonoWithRoles(Mono.just(user)));
    }

    public Mono<UserDto> userMonoToDtoMonoWithRoles(Mono<User> userMono) {
        return userMono.flatMap(user ->
                Mono.just(UserDto.builder()
                        ._id(user.get_id())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRoles())
                        .active(user.isActive())
                        .build()
                ));
    }

    public Mono<User> userMonoFromDtoMonoWithRoles(Mono<UserDto> userDtoMono) {
        return userDtoMono.flatMap(userDto ->
                Mono.just(User.builder()
                        ._id(userDto.get_id())
                        .firstName(userDto.getFirstName())
                        .lastName(userDto.getLastName())
                        .email(userDto.getEmail())
                        .password(userDto.getPassword())
                        .roles(userDto.getRoles())
                        .active(userDto.isActive())
                        .build()
                ));
    }

    UserDto userToDtoWithRoles(User user) {
        return UserDto.builder()
                        ._id(user.get_id())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRoles())
                        .active(user.isActive())
                        .build();
    }
}
