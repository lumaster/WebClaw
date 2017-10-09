package com.webclaw.corewebclaw.config;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Constant {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

}
