package com.li.missyou.dto;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wx_user")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UmsUserDTO {
    @Id@NonNull
    private String openId;
    @NonNull private Date createTime;
    private Date lastVisitTime;
    @NonNull private String sessionKey;
    private String unionId;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String nickName;
    private Integer gender;
}
