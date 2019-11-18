package pl.tom.weatherappspring.rest;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateParser {

    public LocalDate getDate(String strDateTime) {

        String[] strDateTimeArray = strDateTime.split(" ");
        String strDate = strDateTimeArray[0];
        String[] strDateArray = strDate.split("-");
        int year = Integer.parseInt(strDateArray[0]);
        int month = Integer.parseInt(strDateArray[1]);
        int day = Integer.parseInt(strDateArray[2]);
        LocalDate localDate = LocalDate.of(year, month, day);

        return localDate;
    }

    public String getaTime(String strDateTime) {
        String[] strDateTimeArray = strDateTime.split(" ");
        String strTime = strDateTimeArray[1];

        return strTime;
    }
}
