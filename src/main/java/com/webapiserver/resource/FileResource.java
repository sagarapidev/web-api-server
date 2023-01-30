package com.webapiserver.resource;

import com.webapiserver.util.FileHandlerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
@Slf4j
public class FileResource {
    @GetMapping("/create/filedir/{filename}/{format}")
    public ResponseEntity<Object> createFile(@PathVariable String filename,
                                             @PathVariable String format) throws IOException {
        ConcurrentHashMap<String, Object> response = new ConcurrentHashMap<>();
        File DIR = FileHandlerUtils.createDir();
        File FILE = FileHandlerUtils.createFile(DIR.getCanonicalPath(), filename, format.equals("") || format.equals(null) ? ".txt" : format);
        //Write on file
       log.info("test");
        FileWriter FWRITE = new FileWriter(FILE);
        FWRITE.write(FILE.toString());
        FWRITE.close();

        List<String> FILES_IN_DIR = FileHandlerUtils.listFilesForDirectory(DIR);
        response.put("DUMP", FILES_IN_DIR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}