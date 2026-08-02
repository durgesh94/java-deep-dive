import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Calendar;

/**
 * DateTimeDemo — a single runnable class covering the core java.time API:
 * LocalDate, LocalTime, LocalDateTime, ZonedDateTime, Instant,
 * Period, Duration, formatting/parsing, comparisons, and legacy interop.
 *
 * Run:
 *   javac DateTimeDemo.java
 *   java DateTimeDemo
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        section("1. Creating dates and times");
        creatingDatesAndTimes();

        section("2. Manipulating dates (immutability in action)");
        manipulatingDates();

        section("3. Comparing dates");
        comparingDates();

        section("4. Period vs Duration (differences)");
        periodAndDuration();

        section("5. Formatting and parsing");
        formattingAndParsing();

        section("6. Timezones with ZonedDateTime");
        timezones();

        section("7. Instant — machine timestamps");
        instants();

        section("8. Legacy Date/Calendar interop");
        legacyInterop();

        section("9. Real-world patterns");
        realWorldPatterns();
    }

    // ---------------------------------------------------------------
    // 1. Creating dates and times
    // ---------------------------------------------------------------
    private static void creatingDatesAndTimes() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("Today (LocalDate)      : " + today);
        System.out.println("Now (LocalTime)         : " + now);
        System.out.println("Now (LocalDateTime)     : " + dateTime);

        // Explicit construction
        LocalDate specificDate = LocalDate.of(2026, Month.AUGUST, 2);
        LocalTime specificTime = LocalTime.of(14, 30, 15);
        LocalDateTime combined = LocalDateTime.of(specificDate, specificTime);

        System.out.println("Specific date           : " + specificDate);
        System.out.println("Specific time            : " + specificTime);
        System.out.println("Combined date-time       : " + combined);

        // Parsing from ISO string
        LocalDate parsedDate = LocalDate.parse("2026-12-25");
        System.out.println("Parsed date (Christmas)  : " + parsedDate);
    }

    // ---------------------------------------------------------------
    // 2. Manipulating dates — everything returns a NEW object
    // ---------------------------------------------------------------
    private static void manipulatingDates() {
        LocalDate today = LocalDate.now();

        LocalDate tomorrow = today.plusDays(1);
        LocalDate lastWeek = today.minusWeeks(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate nextYear = today.plusYears(1);

        System.out.println("Today       : " + today);
        System.out.println("Tomorrow    : " + tomorrow);
        System.out.println("Last week   : " + lastWeek);
        System.out.println("Next month  : " + nextMonth);
        System.out.println("Next year   : " + nextYear);

        // Common gotcha: forgetting to reassign does nothing (immutability)
        LocalDate unchanged = today;
        unchanged.plusDays(5); // result discarded — unchanged stays the same
        System.out.println("Forgot to reassign (bug): " + unchanged + " (still today!)");

        // TemporalAdjusters — useful calendar navigation
        LocalDate firstOfMonth = today.withDayOfMonth(1);
        LocalDate lastOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        System.out.println("First of month           : " + firstOfMonth);
        System.out.println("Last of month             : " + lastOfMonth);
        System.out.println("Next Monday                : " + nextMonday);
        System.out.println("Is leap year?               : " + today.isLeapYear());
        System.out.println("Day of week                 : " + today.getDayOfWeek());
    }

    // ---------------------------------------------------------------
    // 3. Comparing dates
    // ---------------------------------------------------------------
    private static void comparingDates() {
        LocalDate d1 = LocalDate.of(2026, 8, 2);
        LocalDate d2 = LocalDate.of(2026, 12, 25);

        System.out.println(d1 + " isBefore " + d2 + " -> " + d1.isBefore(d2));
        System.out.println(d1 + " isAfter  " + d2 + " -> " + d1.isAfter(d2));
        System.out.println(d1 + " isEqual  " + d2 + " -> " + d1.isEqual(d2));
        System.out.println(d1 + " compareTo " + d2 + " -> " + d1.compareTo(d2));
    }

    // ---------------------------------------------------------------
    // 4. Period (date-based) vs Duration (time-based)
    // ---------------------------------------------------------------
    private static void periodAndDuration() {
        LocalDate start = LocalDate.of(2026, 1, 1);
        LocalDate end = LocalDate.of(2026, 8, 2);

        Period period = Period.between(start, end);
        System.out.println("Period between " + start + " and " + end + " -> "
                + period.getMonths() + " months, " + period.getDays() + " days");

        long daysBetween = ChronoUnit.DAYS.between(start, end);
        System.out.println("Total days between        : " + daysBetween);

        LocalTime open = LocalTime.of(9, 0);
        LocalTime close = LocalTime.of(17, 30);
        Duration duration = Duration.between(open, close);
        System.out.println("Business hours duration    : "
                + duration.toHours() + "h " + (duration.toMinutes() % 60) + "m");
    }

    // ---------------------------------------------------------------
    // 5. Formatting and parsing
    // ---------------------------------------------------------------
    private static void formattingAndParsing() {
        LocalDateTime dt = LocalDateTime.of(2026, 8, 2, 14, 30, 15);

        DateTimeFormatter customFmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = dt.format(customFmt);
        System.out.println("Custom format             : " + formatted);

        LocalDateTime reparsed = LocalDateTime.parse(formatted, customFmt);
        System.out.println("Re-parsed back             : " + reparsed);

        System.out.println("ISO format                 : "
                + dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        DateTimeFormatter niceFmt = DateTimeFormatter.ofPattern("dd MMM yyyy, EEEE");
        System.out.println("Readable format             : " + dt.format(niceFmt));
    }

    // ---------------------------------------------------------------
    // 6. Timezones
    // ---------------------------------------------------------------
    private static void timezones() {
        ZoneId indiaZone = ZoneId.of("Asia/Kolkata");
        ZoneId nyZone = ZoneId.of("America/New_York");

        ZonedDateTime meetingIST = ZonedDateTime.of(
                LocalDateTime.of(2026, 8, 5, 15, 0), indiaZone);
        ZonedDateTime meetingNY = meetingIST.withZoneSameInstant(nyZone);

        System.out.println("Meeting time (IST)          : " + meetingIST);
        System.out.println("Same instant (New York)     : " + meetingNY);

        OffsetDateTime offsetTime = OffsetDateTime.now(ZoneOffset.of("+05:30"));
        System.out.println("Fixed-offset time            : " + offsetTime);
    }

    // ---------------------------------------------------------------
    // 7. Instant — UTC epoch-based machine timestamps
    // ---------------------------------------------------------------
    private static void instants() {
        Instant now = Instant.now();
        long epochMillis = now.toEpochMilli();

        System.out.println("Instant.now()                : " + now);
        System.out.println("Epoch millis                  : " + epochMillis);

        Instant oneHourLater = now.plus(1, ChronoUnit.HOURS);
        System.out.println("One hour later                : " + oneHourLater);

        ZonedDateTime asIST = now.atZone(ZoneId.of("Asia/Kolkata"));
        System.out.println("Displayed in IST               : " + asIST);
    }

    // ---------------------------------------------------------------
    // 8. Legacy java.util.Date / Calendar interop
    // ---------------------------------------------------------------
    private static void legacyInterop() {
        Date legacyDate = new Date();
        Instant fromLegacy = legacyDate.toInstant();
        LocalDateTime modernFromLegacy = LocalDateTime.ofInstant(fromLegacy, ZoneId.systemDefault());
        System.out.println("Legacy Date -> LocalDateTime  : " + modernFromLegacy);

        Date backToLegacy = Date.from(modernFromLegacy.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("LocalDateTime -> legacy Date   : " + backToLegacy);

        Calendar cal = Calendar.getInstance();
        ZonedDateTime fromCalendar = ZonedDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId());
        System.out.println("Calendar -> ZonedDateTime       : " + fromCalendar);
    }

    // ---------------------------------------------------------------
    // 9. Real-world patterns
    // ---------------------------------------------------------------
    private static void realWorldPatterns() {
        // Age calculation
        LocalDate birthDate = LocalDate.of(1995, 6, 15);
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        System.out.println("Age (born " + birthDate + ")      : " + age);

        // Weekend check
        LocalDate today = LocalDate.now();
        System.out.println(today + " is weekend?              : " + isWeekend(today));

        // Days until a deadline
        LocalDate deadline = LocalDate.of(2026, 12, 31);
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
        System.out.println("Days until " + deadline + "        : " + daysLeft);
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    // ---------------------------------------------------------------
    // helper
    // ---------------------------------------------------------------
    private static void section(String title) {
        System.out.println();
        System.out.println("== " + title + " ==");
    }
}