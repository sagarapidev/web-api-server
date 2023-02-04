package com.webapiserver.repository;

import com.webapiserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Modifying
    @Transactional
    @Query(value="LOAD DATA INFILE 'C:/Users/bsagar8/sagarapidev/my/csv/TTuser_data.csv' INTO TABLE USER_DATA FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' IGNORE 1 LINES",nativeQuery = true)
    public void bulkLoadData();

}