package com.lojadomecanico.desafio.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lojadomecanico.desafio.domain.entities.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class UserDto {
    private final Integer id;
    private final String name;
    private final String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final String password;
    private final String cpfCnpj;
    private final String isCnpj;
    private final Integer isActive;
    private final Date createdAt;
    private final Date updatedAt;

    public static List<UserDto> fromDtoList(List<User> users){
        return users.stream().map(user -> UserDto.fromDto(user))
                .collect(Collectors.toList());
    }

    public static UserDto fromDto(User user){
        return UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .cpfCnpj(user.getCpfCnpj())
                        .isCnpj(user.getIsCnpj())
                        .isActive(user.getIsActive())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(user.getUpdatedAt())
                        .build();
    }


    public static User fromDomain(UserDto dto){
        return new User(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getCpfCnpj(),
                dto.getIsCnpj(),
                dto.getIsActive(),
                dto.getCreatedAt(),
                dto.getUpdatedAt()
                );
    }
}
