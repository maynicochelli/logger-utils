package com.logger.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/teste")
public class DemoController {

    private static final Logger logger = LoggerFactory
            .getLogger(DemoController.class);

    @GetMapping
    public void getMessage() throws IOException, NoSuchFieldException {
        PojoA pojo = new PojoA("Nika", 333);

        CustomLog.log(pojo);
    }

}
