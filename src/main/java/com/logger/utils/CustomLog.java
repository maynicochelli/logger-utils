package com.logger.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Component
@PropertySource("classpath:application.properties")
public class CustomLog {

    private static final Logger logger = Logger.getLogger("CustomLog");

    private static String FILE_NAME;

    private static String PATH_FILE;

    @Value("${custom-log.path-file}")
    public void setPathFile(String name){
        CustomLog.PATH_FILE = name;
    }

    @Value("${custom-log.file-name}")
    public void setFileName(String name){
        CustomLog.FILE_NAME = name;
    }

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static <T> void log(T t) throws IOException {
        String dataFormatada = dateFormatted();

        String filePath = PATH_FILE + dataFormatada;

        FileHandler fileHandler;

        new File(filePath).mkdir();

        fileHandler = new FileHandler(filePath + "\\" + FILE_NAME, true);

        logger.addHandler(fileHandler);

        fileHandler.setFormatter(new CustomMessageFormatter());

        PojoB pojoB = new PojoB();

        toPojoB(pojoB, t);

        customAppender(pojoB);

        fileHandler.close();
    }

    private static String dateFormatted() {
        LocalDate localDate = LocalDate.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

        String dataFormatada = localDate.format(dateTimeFormatter);

        return dataFormatada;
    }

    private static <T> void toPojoB(PojoB pojoB, T t) {
        BeanUtils.copyProperties(t, pojoB);
    }

    private static void customAppender(PojoB pojoB) {
        logger.info("Nome: " + pojoB.getNome() + " | " + "Idade: " + pojoB.getIdade());
    }

}