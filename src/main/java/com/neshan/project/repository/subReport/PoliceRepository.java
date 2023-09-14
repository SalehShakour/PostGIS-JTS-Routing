package com.neshan.project.repository.subReport;

import com.neshan.project.domain.reportType.Police;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceRepository extends JpaRepository<Police,Long> {
}
