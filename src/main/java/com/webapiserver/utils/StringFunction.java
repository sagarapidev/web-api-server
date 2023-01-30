package com.webapiserver.resource;

import com.webapiserver.domain.Tutorial;
import com.webapiserver.domain.User;
import com.webapiserver.repository.TutorialRepository;
import com.webapiserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserResource {

    @GetMapping("commas/separator/{CommaSeparatedString}")
    public ResponseEntity<String> getUserById(@PathVariable("CommaSeparatedString") String CommaSeparatedString) {
      CommaSeparatedString.sp
    }



}