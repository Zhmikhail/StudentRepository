package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final LocalDate MAX_DATE_BORN = LocalDate.now().minusYears(18);
    public static final LocalDate MIN_DATE_BORN = LocalDate.now().minusYears(100);
    public static final LocalDate MAX_DATE_HIRE = LocalDate.now();
    public static final LocalDate MIN_DATE_HIRE = LocalDate.of(1991, 1, 1);
    public static final LocalDate MAX_DATE_FIRE = LocalDate.now().plusWeeks(5);

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
}
