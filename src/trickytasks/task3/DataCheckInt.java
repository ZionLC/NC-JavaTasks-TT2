package trickytasks.task3;

public interface DataCheckInt {

    /** isLeapYear() - It defines a leap year or not.
     *
     * @param year
     * @return true, if year is leap.
     */
    boolean isLeapYear(int year);

    //Осуществляет проверку даты на корректность.

    /** Checks the date for correctness.
     *
     * @param year
     * @param month
     * @param day
     * @return true, if date is correct.
     */
    boolean isValidDate(int year, int month, int day);

    /** Calculates the day of the week
     *
     * @param year
     * @param month
     * @param day
     * @return the number of the day of the week, where 0 - MON, 6 - SUN
     */
    int getDayOfWeek(int year, int month, int day);

    /** Formats the date nicely. for instance Tuesday 14 Feb 2012
     *
     * @param year
     * @param month
     * @param day
     * @return null
     */
    String toString(int year, int month, int day);

    /** Calculates how many days have passed from a given date to today
     *
     * @param year
     * @param month
     * @param day
     * @return quantity of days
     */
    int countDays(int year, int month, int day);

}
