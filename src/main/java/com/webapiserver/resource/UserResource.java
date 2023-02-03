package com.webapiserver.resource;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.webapiserver.common.SpecialCharConstant;
import com.webapiserver.config.ConfigProperty;
import com.webapiserver.config.Constant;
import com.webapiserver.domain.User;
import com.webapiserver.repository.UserRepository;
import com.webapiserver.service.CsvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    UserRepository  userRepository;

    @Autowired
    ConfigProperty configProperty;

    @Autowired
    CsvService csvService;
    @Value("classpath:data\"")
    Resource resourcePath;
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> user =userRepository.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User usr) {
        try {
            Faker usFaker = new Faker(new Locale("en-US"));
            FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());
              usr.setName(usFaker.name().firstName());
              usr.setEmail(usFaker.name().firstName().concat("@gmail.com"));
            User user = userRepository.save(usr);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User usr) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(userRepository.save(usr), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users= userRepository.findAll();
        try {

            if(users.size()!=0){
                return new ResponseEntity<>(users,HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(users,HttpStatus.NO_CONTENT);
    }
    /*
    Download data current system download folder
     */
    @GetMapping("/user/csv/download")
    public void exportCSV(HttpServletResponse response,@RequestParam(required = true) String tableName)
            throws Exception {

        // set file name and content type
        String filename = tableName+configProperty.getExtention();
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename+ "\"");

        // create a csv writer
        StatefulBeanToCsv<User> writer =
                new StatefulBeanToCsvBuilder<User>
                        (response.getWriter())
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                        .withOrderedResults(false).build();

        // write all employees to csv file
        writer.write(csvService.fetchAllUser());

    }
/*
@Param tableName
Download file in external user defined file location
 */
    @GetMapping("/external/backup/file/csv")
    public ResponseEntity<String> downloadCSV(@RequestParam(required = true) String tableName)
            throws Exception {
        String FILE_LOCATION=configProperty.getDownloadLocation()
                +tableName+SpecialCharConstant.UNDERSCORE+ Instant.now().getEpochSecond()
                + Constant.CSV_EXTENSION;
        log.info("file-location:{}",FILE_LOCATION);
        Writer writer = new FileWriter(FILE_LOCATION);



        StatefulBeanToCsv<User> beanToCsv = null;
        try {
            beanToCsv = new StatefulBeanToCsvBuilder<User>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withOrderedResults(false).build();
        }catch (Exception e){
            log.info("Exception Occurred beanToCsv object ref:{}",e.getMessage());
        }


        beanToCsv.write(csvService.fetchAllUser());
        writer.flush();
        writer.close();
        return new ResponseEntity<>("Successfully csv file download !",HttpStatus.OK);
    }

}