package com.webapiserver.service.imp;

import com.webapiserver.domain.User;
import com.webapiserver.repository.UserRepository;
import com.webapiserver.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements CsvService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> fetchAllUser() {
        return (List<User>) userRepository.findAll();
    }
}
