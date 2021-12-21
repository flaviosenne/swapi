package com.lojadomecanico.desafio.domain.entities;

import java.util.Date;

public class User {
    private final Integer id;
    private final String name;
    private final String email;
    private final String password;
    private final String cpfCnpj;
    private final String isCnpj;
    private final Integer isActive;
    private final Date createdAt;
    private final Date updatedAt;

    public User(Integer id, String name, String email, String password, String cpfCnpj, String isCnpj, Integer isActive, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpfCnpj = cpfCnpj;
        this.isCnpj = isCnpj;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getIsCnpj() {
        return isCnpj;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public static User cloneUserUpdate(User user, User existUser){
        return new User(
                user.getId(),
                user.getName(),
                user.getEmail(),
                existUser.getPassword(),
                user.getCpfCnpj(),
                user.getIsCnpj(),
                existUser.getIsActive(),
                existUser.getCreatedAt(),
                new Date());
    }


    public static User cloneUserDelete(User existUser){
        return new User(
                existUser.getId(),
                existUser.getName(),
                existUser.getEmail(),
                existUser.getPassword(),
                existUser.getCpfCnpj(),
                existUser.getIsCnpj(),
                0,
                existUser.getCreatedAt(),
                new Date());
    }
}
