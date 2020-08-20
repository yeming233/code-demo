package org.itourshare.api.entity;

/**
 * @ClassName : User
 * @Description :
 * @Author : its
 * @Date: 2020-08-19 16:16
 */
public class User {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
