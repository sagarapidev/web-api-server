package com.webapiserver.service;

import com.webapiserver.domain.User;

import java.util.List;
import java.util.Map;

public interface CsvService {
    List<User> fetchAllUser();
    List<Map<String, Object>> getResultSet(String query);
}
