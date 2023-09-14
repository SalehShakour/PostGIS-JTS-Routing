package com.neshan.project.repository.subReport;

import com.neshan.project.domain.reportType.Bump;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BumpRepository extends JpaRepository<Bump,Long> {
}
