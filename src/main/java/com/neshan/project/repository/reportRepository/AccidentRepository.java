package com.neshan.project.repository.reportRepository;

import com.neshan.project.domain.reportType.Accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentRepository extends JpaRepository<Accident,Long> {
}
