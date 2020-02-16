package com.task.worldpay.worldpayTask.util;

import com.task.worldpay.worldpayTask.constant.ApiConstant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OfferUtils {
    public static String formatDate(LocalDateTime dateTime) {

        if (dateTime != null) {
            try {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ApiConstant.DATE_FORMAT_PATTERN);
                return dateTime.format(dateTimeFormatter);
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }
}
