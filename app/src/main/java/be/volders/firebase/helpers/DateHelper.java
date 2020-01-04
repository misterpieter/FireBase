package be.volders.firebase.helpers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Class responsible for handling Date related actions and properties whithin the application
 *
 * @author Gaetan Dumortier
 * @since 01/01/2019
 */
public class DateHelper {

    /**
     * Calculates the age of the patient and returns it in months.
     * @param inputDate The date of birth of the patient as a string
     * @return int the age of the patient converted into months
     */
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
