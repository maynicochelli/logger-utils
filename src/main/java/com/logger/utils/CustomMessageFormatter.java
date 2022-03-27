package com.logger.utils;

import java.util.logging.*;

public class CustomMessageFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(record.getMessage()).append('\n');
        return stringBuilder.toString();
    }

}