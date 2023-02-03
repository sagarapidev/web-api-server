package com.webapiserver.service.imp;

import com.webapiserver.domain.User;
import com.webapiserver.repository.UserRepository;
import com.webapiserver.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements CsvService {
    private static final String INSERT_SQL ="INSERT INTO USER_DATA (EMAIL,ID,NAME, STATUS) VALUES (?, ?, ?,?)" ;
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

    @Override
    public void loadCsvData(String csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                jdbcTemplate.update(INSERT_SQL, values[0], values[1], values[2],values[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
