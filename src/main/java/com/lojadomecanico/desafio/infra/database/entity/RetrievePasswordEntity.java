package com.lojadomecanico.desafio.infra.database.entity;

import com.lojadomecanico.desafio.domain.entities.RetrievePassword;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "code_retrieve_password")
@Entity
public class RetrievePasswordEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private Integer isValid;
    @Column(name = "created_at")
    private Date createdAt;
    private int expiration;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public static RetrievePassword fromDomain(RetrievePasswordEntity code){
        return new RetrievePassword(
                code.getId(),
                code.getCode(),
                code.getIsValid(),
                code.getCreatedAt(),
                UserEntity.fromDomain(code.getUser())
        );
    }
    public static RetrievePasswordEntity fromEntity(RetrievePassword code){
        return RetrievePasswordEntity.builder()
                .id(code.getId())
                .code(code.getCode())
                .isValid(code.getIsValid())
                .createdAt(code.getCreatedAt())
                .expiration(code.getExpiration())
                .user(UserEntity.fromEntity(code.getUser()))
                .build();

        }
}


