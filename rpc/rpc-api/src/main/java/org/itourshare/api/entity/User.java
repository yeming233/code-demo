package org.itourshare.api.entity;

import java.io.Serializable;

/**
 * @ClassName : User
 * @Description :
 * @Author : its
 * @Date: 2020-08-19 16:16
 */
public class User implements Serializable {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
