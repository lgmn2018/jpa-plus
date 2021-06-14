package com.yc.jpaplus.example.entity;

import com.yc.jpaplus.core.base.common.JpaPlusEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_address")
public class Address extends JpaPlusEntity {
    private String address;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    private User user;
}
