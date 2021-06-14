package com.yc.jpaplus.example.repository;

import com.yc.jpaplus.core.repository.JpaPlusRepository;
import com.yc.jpaplus.example.entity.User;

public interface UserRepository extends JpaPlusRepository<User,Long> {
}
