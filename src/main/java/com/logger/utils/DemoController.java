package com.logger.utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/teste")
public class DemoController {

    @GetMapping
    public void getMessage() throws IOException {
        PojoA pojo = new PojoA("Nika", 333);

        CustomLog.log(pojo);
    }

}
