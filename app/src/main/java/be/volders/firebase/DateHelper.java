package be.volders.firebase;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    // Requires API level 26 or above.
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static int calculateAgeInMonths(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        Date date;
        Calendar calendar = Calendar.getInstance();
        try {
            date = sdf.parse(inputDate);
            if (date != null)
                calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int cyear = calendar.get(Calendar.YEAR);
        int cmonth = calendar.get(Calendar.MONTH) + 1;
        int cdate = calendar.get(Calendar.DATE);

        LocalDate patientDob = LocalDate.of(cyear, cmonth, cdate);
        LocalDate now = LocalDate.now();
        Period difference;
        difference = Period.between(patientDob, now);

        int ageInMonths = (difference.getYears() * 12) + difference.getMonths();
        return ageInMonths;
    }

}
