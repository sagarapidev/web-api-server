package com.webapiserver.repository;

import com.webapiserver.domain.Tutorial;
import com.webapiserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
/*
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);*/
}