package com.lojadomecanico.desafio.infra.database.entity;

import com.lojadomecanico.desafio.app.dtos.UserDto;
import com.lojadomecanico.desafio.domain.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@Table(name = "user")
@Entity
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;
    @Column(name = "is_cnpj")
    private String isCnpj;
    @Column(name = "is_active")
    private Integer isActive;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public static User fromDomain(UserEntity user){
        return new User(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCpfCnpj(),
                user.getIsCnpj(),
                user.getIsActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static UserEntity fromEntity(User user){
        return  UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .cpfCnpj(user.getCpfCnpj())
                .isCnpj(user.getIsCnpj())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}


