package com.li.missyou.dto;

import com.li.missyou.validators.PasswordEqual;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@PasswordEqual(message = "密码长度错误")
@Data
@Entity
@NoArgsConstructor
public class PersionDTO {
    //@NonNull
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private String password1;
    private String password2;
    public PersionDTO(String name,Integer age){
        this.name = name;
        this.age = age;
    }
}
