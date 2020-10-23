package trickytasks.task3;


public class DataCheck implements DataCheckInt {
    //Task completed without using Data API

    @Override
    public boolean isLeapYear(int year) {
        if (year <= 0) { //The countdown starts from 1 year. Zero values of year are also ignored.
            throw new IllegalArgumentException("Year value must be greater than 0");
        }
        if (!(year % 4 == 0)) { //Leap year - every fourth year
            return false;
        } else {
            if (!(year % 100 == 0)) { //Except for years divisible by 100
                return true;
            } else {
                if (year % 400 == 0) {  //But years divisible by 400 are leap years
                    return true;
                } else {
                    return false;
                }
            }
        }

    }

    @Override
    public boolean isValidDate(int year, int month, int day) {
        if (year <= 0 || month<=0 || day<=0) { //The countdown starts from 1 year. Zero values of year, month and day are also ignored.
            throw new IllegalArgumentException("Year, month and day value must be greater than 0");
        }
        if (month > 0 && month < 13) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (day > 0 && day <= 31) { //1,3,5,7,8,10,12 months have 31 days.
                        return true;
                    } else {
                        return false;
                    }
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day > 0 && day < 31) { //4,6,9,11 months have 30 days.
                        return true;
                    } else {
                        return false;
                    }
                case 2:
                    if (isLeapYear(year)) { //if year is leap 2nd month have 29 days.
                        if (day > 0 && day <= 29) {
                            return true;
                        } else {
                            return false;
                        }
                    } else { //else year is not leap 2nd month have 28 days.
                        if (day > 0 && day < 29) {
                            return true;
                        } else {
                            return false;
                        }
                    }
            }
        }
         System.out.println("There is no such month");
         return false;
    }

    @Override
    public int getDayOfWeek(int year, int month, int day) {
        if (!isValidDate(year, month, day)) {
            throw new IllegalArgumentException("The entered date is incorrect");
        }

        if (month < 3) {
            //if our month is 1st or 2nd month we must do next actions (because In this algorithm January and February are counted as months 13 and 14 of the previous year.
            // 3 = March, 4 = April, 5 = May, ..., 14 = February)
            month += 12;
            year--;
        }
        //weekday is Zeller's congruence for the Gregorian calendar (https://en.wikipedia.org/wiki/Zeller's_congruence)
        int weekday = (day + (int) ((13 * (month + 1)) / 5) + (int) (year % 100) + (int) ((year % 100) / 4) + (int) ((year / 100) / 4) - (int) (2 * (year / 100))) % 7;
        //cos algorithm return values (0 = Saturday, 1 = Sunday, 2 = Monday, ..., 6 = Friday) we must add 5 for Saturday and Sunday, and subtract 2 for others.
        if (weekday < 2) {
            weekday += 5;
        } else {
            weekday -= 2;
        }
        return weekday;
    }

    @Override
    public String toString(int year, int month, int day) {
        if (isValidDate(year, month, day)) {
            throw new IllegalArgumentException("The date entered is incorrect and cannot be converted");
        }
        String namemonth = null;
        switch (month) {
            case 1:
                namemonth = "January";
                break;
            case 2:
                namemonth = "February";
                break;
            case 3:
                namemonth = "March";
                break;
            case 4:
                namemonth = "April";
                break;
            case 5:
                namemonth = "May";
                break;
            case 6:
                namemonth = "June";
                break;
            case 7:
                namemonth = "July";
                break;
            case 8:
                namemonth = "August";
                break;
            case 9:
                namemonth = "September";
                break;
            case 10:
                namemonth = "October";
                break;
            case 11:
                namemonth = "November";
                break;
            case 12:
                namemonth = "December";
                break;
        }
        String dayofweek = null;
        switch (getDayOfWeek(year, month, day)) {
            case 0:
                dayofweek = "Monday";
                break;
            case 1:
                dayofweek = "Tuesday";
                break;
            case 2:
                dayofweek = "Wednesday";
                break;
            case 3:
                dayofweek = "Thursday";
                break;
            case 4:
                dayofweek = "Friday";
                break;
            case 5:
                dayofweek = "Saturday";
                break;
            case 6:
                dayofweek = "Sunday";
                break;
        }
        System.out.println("Entered date: " + dayofweek + " " + day + " " + namemonth + " " + year);
        return null;
    }

    @Override
    public int countDays(int year, int month, int day) {
        int yearstart = 1970; //the first date is a constant. Milliseconds are counted from January 1, 1970, so we will base our calculations on this date.
        int yearend = year;

        long timems = System.currentTimeMillis();
        long daysnow = timems / (1000 * 60 * 60 * 24);//Number of days from January 1, 1970 to the current date

        int alldaysinyear = 365;
        int count = 0; //Leap years
        long daysrazn = 0;  //The days in regular, leap years, also days in date year, between January 1, 1970 and the specified date
        long itogo = 0; //The total number of days between the current date and the specified date
        int[] daysmonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //The array stores the values of days in a month of a non-leap year
        int daysmonthcount = 0; //The number of days passed in a year
        if (yearstart > yearend) {//If the entered year is less than a constant
            yearstart--;// We do not include the year, since the countdown in it starts from 00:00 on January 1, and these values have already been calculated. So we have been working only since 1969
            yearend++;// You need to calculate the number of leap years excluding the year from the entered date
            for (int i = yearend; i <= yearstart; i++) { //We count the number of leap years between dates
                if (isLeapYear(i)) {
                    count++;
                }
            }

            if (isLeapYear(yearend)) { //If the year is a leap year, then the number of days in February = 29, and in the year 366
                alldaysinyear++;
                daysmonth[1] = 29;
            }
            //We count the number of past days in a year from the specified date
            for (int i = 1; i < month; i++) {
                daysmonthcount += daysmonth[i - 1];
            }
            daysmonthcount += day; //We only counted the number of days between January and the entered month, not including it. Now we have to add the number of days this month.

            yearstart++; //We return the years to their original state.
            yearend--; //We return the years to their original state.
            //the number of days elapsed from the entered date until January 1, 1970
            // is equal to the number of days in leap years, the number of days in non-leap years, and the number of days remaining in the year of the entered date.
            daysrazn = count * 366 + ((yearstart - yearend) - count) * 365 + (alldaysinyear - daysmonthcount);
            itogo = daysnow + daysrazn;

        } else { //The year entered is greater than or equal to a constant (1970)
            if (yearstart == yearend) {//The entered year is constant (1970)
                //We count the number of past days in a year from the specified date
                for (int i = 1; i < month; i++) {
                    daysmonthcount += daysmonth[i - 1];
                }
                daysmonthcount += day;
                itogo = daysnow - daysmonthcount; //Subtract from the number of days that have passed from January 1, 1970 to the current date,
                // the number of days that have passed in 1970 until the date entered.

            } else {//The entered year is greater than a constant (1970)
                yearend--; // You need to calculate the number of leap years excluding the year from the entered date

                for (int i = yearstart; i <= yearend; i++) { //We count the number of leap years between dates
                    if (isLeapYear(i)) {
                        count++;
                    }
                }
                if (isLeapYear(yearend)) { //If the year is a leap year, then the number of days in February = 29.
                    // in this case, we do not increase the number of days in a year, since we no longer work with this number.
                    daysmonth[1] = 29;
                }
                //We count the number of past days in a year from the specified date
                for (int i = 1; i < month; i++) {
                    daysmonthcount += daysmonth[i - 1];
                }
                daysmonthcount += day;
                yearend++;//We return the years to their original state.
                //the number of days elapsed from the entered date until January 1, 1970
                // is equal to the number of days in leap years, the number of days in non-leap years, and the number of days passed in the year of the entered date.
                daysrazn = count * 366 + ((yearend - yearstart) - count) * 365 + daysmonthcount;
                itogo = daysnow - daysrazn;
            }
        }
        return (int)itogo;
    }
}
