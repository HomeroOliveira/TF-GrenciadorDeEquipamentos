package utils;

import java.time.LocalDate;
import java.util.Date;

public class LocalDateUtils {

	
	public static LocalDate toLocalDate(Date date) {
		return LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
	}
}
