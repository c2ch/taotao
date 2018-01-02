package com.c2c.pojo;

/**
 * Copyright: Copyright (c) 2018 linewell
 *
 * @ClassName: person
 * @Description: 描述人的一个类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/2 21:29
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class person {
    private String name;
    private Integer age;
    private String sex;//性别
    private String desc;//描述信息


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
