package com.yc.jpaplus.example.entity;

import com.yc.jpaplus.core.base.common.JpaPlusEntity;
import com.yc.jpaplus.example.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User extends JpaPlusEntity {
    private String name;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
