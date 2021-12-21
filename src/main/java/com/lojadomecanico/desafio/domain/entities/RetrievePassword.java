package com.lojadomecanico.desafio.domain.entities;

import java.util.Date;

public class RetrievePassword {
    private final Integer id;
    private final String code;
    private final Integer isValid;
    private final Date createdAt;
    private final int expiration = 1800000;
    private final User user;


    public RetrievePassword(Integer id, String code, Integer isValid, Date createdAt, User user) {
        this.id = id;
        this.code = code;
        this.isValid = isValid;
        this.createdAt = createdAt;
        this.user = user;
    }

    public static RetrievePassword cloneRetrievePasswordDelete(RetrievePassword code){
        return new RetrievePassword(
                code.getId(),
                code.getCode(),
                0,
                code.getCreatedAt(),
                code.getUser()
        );
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getExpiration() {
        return expiration;
    }

    public User getUser() {
        return user;
    }
}
