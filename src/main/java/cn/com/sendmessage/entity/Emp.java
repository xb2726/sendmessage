package cn.com.sendmessage.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Auther X .
 */
@Data
public class Emp {
    private String id;
    private String name;
    private Date birthday;
    private String email;
    private String birthdayDateType;

}
