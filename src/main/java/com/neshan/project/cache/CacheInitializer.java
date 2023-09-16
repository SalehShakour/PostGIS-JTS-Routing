package com.neshan.project.cache;

import com.neshan.project.domain.Report;
import com.neshan.project.repository.ReportRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
@ShellComponent
public class CacheInitializer {
    private final RedissonClient redissonClient;
    private final ReportRepository<Report> reportRepository;
    public static RSet<Report> expireReportIdSet;

    @PostConstruct
    public void init() {
        expireReportIdSet = redissonClient.getSet("expiredData", new TypedJsonJacksonCodec(Report.class));
    }

    @Scheduled(cron = "0 0 03 * * ?")
    @ShellMethod("Manually remove expired report")
    public void deleteExpiredData() {
        removeAndSend();
    }

    private void removeAndSend() {
        reportRepository.deleteAll(expireReportIdSet);
        // send this list to microservice
        expireReportIdSet.clear();
    }

}
