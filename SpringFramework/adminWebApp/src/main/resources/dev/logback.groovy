import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.status.OnConsoleStatusListener

statusListener(OnConsoleStatusListener)

appender('console', ConsoleAppender) {

    encoder(PatternLayoutEncoder) {
        pattern = "%blue(%-5level) %red(%d{yyyy-MM-dd HH:mm:ss.SSS}) [%thread] %green(%logger{36}) - %msg%n"
    }
}

appender('daily', RollingFileAppender) {

    def userHome = System.properties.'user.home'
    file = "${userHome}/logs/app/app-error.log"
    append = true

    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${userHome}/logs/app/app-error.log.%d{yyyy-MM-dd}.gz" // 자동 gz 압축
        maxHistory = 10 // 10일간만 보관
    }

    encoder(PatternLayoutEncoder) {
        pattern = '%-5level %d{yyyy-MM-dd HH:mm:ss} [%thread] %logger{36} - %msg%n'
    }

    // 특정 레벨 이상만 로깅
    filter(ch.qos.logback.classic.filter.ThresholdFilter) {
        level = ERROR // ERROR 이상 레벨만 로깅
    }
}

logger("org.mybatis", DEBUG, ['console'], false) // Mybatis 로그 설정

root(DEBUG, ['console','daily'])