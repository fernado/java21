package pr.iceworld.fernando.java21.java8_advanced;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.temporal.ChronoUnit;

@Slf4j
public class TimeApp {

    public static void main(String[] args) {

        doInstant();

        doTemporal();

        doLocal();
        doPeriod();

        doChronoUnit1();
        doChronoUnit2();

        doLocalDate();
        doLocalTime();
        doLocalDateTime();
    }

    /**
     * java.time.Instant 类是对计算机时间建模的方式，以 Unix 元年时间（传统的设定为UTC时区1970-01-01T00:00:00Z）开始所经历的秒数进行计算。
     * 它可测量的时间线被限制在 long 类型的存储大小中。它存储的单位为纳秒（nanos，nanos的取值范围是[0,999_999_999]）。如果早于该时间，秒数为负值，晚于该时间，秒数为正数。
     */
    public static void doInstant() {
        // 从第三秒开始的时间点
        Instant instant1 = Instant.ofEpochSecond(3);
        System.out.println(instant1);
        // 从第三秒100211321纳秒开始的时间点
        Instant instant2 = Instant.ofEpochSecond(3, 100_211_321);
        System.out.println(instant2);
        // 从第三毫秒开始的时间点
        Instant instant3 = Instant.ofEpochMilli(3);
        System.out.println(instant3);
    }

    public static void doTemporal() {
        Instant start = Instant.parse("2024-09-03T10:15:30.00Z");
        Instant end = Instant.parse("2024-09-03T10:16:30.12Z");
        Duration duration = Duration.between(start, end);
        // 60
        log.info("doTemporal - {}", duration.getSeconds());
        // 120000000
        log.info("doTemporal - {}", duration.getNano());
        // [Seconds, Nanos]
        log.info("doTemporal - {}", duration.getUnits());


        duration.plusSeconds(60);
        duration.minus(30, ChronoUnit.SECONDS);
        // false
        log.info("doTemporal - {}", duration.isNegative());


        Duration fromDays = Duration.ofDays(1);
        Duration fromMinutes = Duration.ofMinutes(60);
        // PT24H
        log.info("doTemporal - {}", fromDays);
        // PT1H
        log.info("doTemporal - {}", fromMinutes);
    }

    public static void doPeriod() {
        LocalDate startDate = LocalDate.of(2023, 2, 20);
        LocalDate endDate = LocalDate.of(2024, 1, 15);

        Period period = Period.between(startDate, endDate);
        // 26
        log.info("doPeriod - getDays {}", period.getDays());
        // 10
        log.info("doPeriod - getMonths {}", period.getMonths());
        // 0
        log.info("doPeriod - getYears {}", period.getYears());


        Period fromUnits = Period.of(3, 10, 10);
        Period fromDays = Period.ofDays(50);
        Period fromMonths = Period.ofMonths(5);
        Period fromYears = Period.ofYears(10);
        Period fromWeeks = Period.ofWeeks(40);
        // P3Y10M10D
        log.info("doPeriod - {}", fromUnits);
        // P50D
        log.info("doPeriod - {}", fromDays);
        // P5M
        log.info("doPeriod - {}", fromMonths);
        // P10Y
        log.info("doPeriod - {}", fromYears);
        // P280D
        log.info("doPeriod - {}", fromWeeks);

        period.plusDays(50);
        // P10M26D
        log.info("doPeriod - {}", period);
        period.minusMonths(2);
        // P10M26D
        log.info("doPeriod - {}", period);
    }

    public static void doLocal() {
        LocalTime start2 = LocalTime.of(1, 20, 25, 1314);
        LocalTime end2 = LocalTime.of(3, 22, 27, 1516);
        // 7322
        log.info("doLocal() - {}", Duration.between(start2, end2).getSeconds());
    }

    public static void doChronoUnit1() {
        LocalDate startDate = LocalDate.of(2023, 2, 20);
        LocalDate endDate = LocalDate.of(2024, 1, 15);
        long years = ChronoUnit.YEARS.between(startDate, endDate);
        long months = ChronoUnit.MONTHS.between(startDate, endDate);
        long weeks = ChronoUnit.WEEKS.between(startDate, endDate);
        long days = ChronoUnit.DAYS.between(startDate, endDate);

        log.info("doChronoUnit1 - {}", years);
        log.info("doChronoUnit1 - {}", months);
        log.info("doChronoUnit1 - {}", weeks);
        log.info("doChronoUnit1 - {}", days);

    }

    public static void doChronoUnit2() {
        LocalTime start2 = LocalTime.of(1, 20, 25, 1314);
        LocalTime end2 = LocalTime.of(3, 22, 27, 1516);

        long hours = ChronoUnit.HOURS.between(start2, end2);
        long minutes = ChronoUnit.MINUTES.between(start2, end2);
        long seconds = ChronoUnit.SECONDS.between(start2, end2);
        long milis = ChronoUnit.MILLIS.between(start2, end2);
        long nano = ChronoUnit.NANOS.between(start2, end2);

        log.info("doChronoUnit2 - {}", hours);
        log.info("doChronoUnit2 - {}", minutes);
        log.info("doChronoUnit2 - {}", seconds);
        log.info("doChronoUnit2 - {}", milis);
        log.info("doChronoUnit2 - {}", nano);
    }

    public static void doLocalDate() {
        // 获取的是系统默认时区的日期
        LocalDate now = LocalDate.now();
        System.out.println(now);
        // 传入的是亚洲/上海的时区
        LocalDate nowWithZoneId = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(nowWithZoneId);
        // 传入的时钟对象的时区为UTC
        LocalDate nowWithClock = LocalDate.now(Clock.systemUTC());
        System.out.println(nowWithClock);
    }

    public static void doLocalTime() {
        LocalTime timeOfTime = LocalTime.now();
        System.out.println(timeOfTime);
        // 获取指定时区的当前LocalTime实例
        LocalTime timeOfZone = LocalTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(timeOfZone);
        // 获取指定时钟的当前LocalTime实例
        LocalTime timeOfClock = LocalTime.now(Clock.systemUTC());
        System.out.println(timeOfClock);
    }

    private static void doLocalDateTime() {
        // 使用时钟的默认时区获取LocalDateTime实例
        LocalDateTime dateTimeOfNow = LocalDateTime.now();
        System.out.println(dateTimeOfNow);
        // 获取指定时区的当前LocalDateTime实例
        LocalDateTime dateTimeOfZone = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(dateTimeOfZone);
        // 获取指定时钟的当前LocalDateTime实例
        LocalDateTime dateTimeOfClock = LocalDateTime.now(Clock.systemUTC());
        System.out.println(dateTimeOfClock);


        LocalDateTime dateTimeOfYearMonthDay = LocalDateTime.of(2000, Month.AUGUST, 27, 18, 50, 52, 999999999);
        System.out.println(dateTimeOfYearMonthDay);


        LocalDateTime dateTimeOfInstant = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
        // 2024-09-13 18:41 UTC+8
        // 2024-09-13T10:41:39.916016200
        System.out.println(dateTimeOfInstant);
        LocalDateTime dateTimeOfEpoch = LocalDateTime.ofEpochSecond(2000, 999999, ZoneOffset.UTC);
        // 1970-01-01T00:33:20.000999999
        System.out.println(dateTimeOfEpoch);


        // 2024-09-13T18:43:15.457101700
        LocalDateTime dateTime = LocalDateTime.from(ZonedDateTime.now());
        System.out.println(dateTime);

        // 2012-10-01T10:10:10.921710211
        LocalDateTime dateTimeOfDateAndTime = LocalDateTime.of(LocalDate.of(2012, 10, 1), LocalTime.of(10, 10, 10, 921710211));
        System.out.println(dateTimeOfDateAndTime);


    }
}
