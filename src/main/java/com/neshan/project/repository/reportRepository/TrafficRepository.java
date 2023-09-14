package com.neshan.project.repository.reportRepository;

import com.neshan.project.domain.reportType.Traffic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic,Long> {
}
