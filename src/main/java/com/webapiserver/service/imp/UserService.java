package com.webapiserver.service.imp;

import com.webapiserver.domain.User;
import com.webapiserver.repository.UserRepository;
import com.webapiserver.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService implements CsvService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<User> fetchAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> getResultSet(String query) {
        return jdbcTemplate.queryForList(query);
    }


}
