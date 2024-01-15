package com.iao.users.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Table(name = "users")
@Entity
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
}
