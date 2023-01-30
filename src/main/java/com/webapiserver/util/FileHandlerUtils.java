package com.webapiserver.util;


import com.webapiserver.common.SpecialCharConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SuppressWarnings("ALL")
@Slf4j
public class FileHandlerUtils {
    public static File createDir() throws IOException {
        String userDir = System.getProperty("user.dir");
        File directory = new File(userDir+ SpecialCharConstant.DOUBLE_FORWARD_SLASH+"dump");
        if(directory.exists()){
            log.info("Directory already exists :{}",directory.getName(),directory.getCanonicalPath());
            return directory;
        }else {
            directory.mkdir();
            log.info("file is created path is :{}",directory.getCanonicalPath());
            return directory;
        }
    }

    public static File createFile(String canonicalPath,String filename, String format) throws IOException {
       log.info("SPECIAL CHARACTERS :{}", SpecialCharConstant.class.toString());
        ZonedDateTime date=ZonedDateTime.now();
        String fileLocation = null;
        try {
            if(CustomStringValidator.emptyORnull(filename)){
                fileLocation= canonicalPath+ SpecialCharConstant.DOUBLE_BACKWARD_SLASH
                        + UUID.randomUUID()
                        + SpecialCharConstant.HYPHEN+date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-hhmm-a"))
                        + SpecialCharConstant.PERIOD+format;
            }else {
                fileLocation= canonicalPath
                        + SpecialCharConstant.DOUBLE_BACKWARD_SLASH
                        +filename+ SpecialCharConstant.HYPHEN
                        +date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-hhmm-a"))
                        + SpecialCharConstant.PERIOD+format;

            }

        }catch (Exception e){
            log.info("File not created."+e.getMessage());
        }

        File file = new File(fileLocation);
        if (file.createNewFile())	{
            log.info("file is created :{}",file.getName(),file.getCanonicalPath());
        }
        else{
            log.info("File already exists : {}",filename);
        }
        return file;
    }

    public static List<String> listFilesForDirectory(File DIR) {
        List<String> filesForDirectory=new ArrayList<>();
        for (final File fileEntry : DIR.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForDirectory(fileEntry);
            } else {
                filesForDirectory.add(fileEntry.getName());
            }
        }
        return  filesForDirectory;
    }
}