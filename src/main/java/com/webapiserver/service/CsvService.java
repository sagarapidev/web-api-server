package com.webapiserver.service;

import com.webapiserver.domain.User;

import java.util.List;

public interface CsvService {
    List<User> fetchAllUser();
}
