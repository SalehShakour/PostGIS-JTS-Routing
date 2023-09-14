package com.neshan.project.repository.reportRepository;

import com.neshan.project.domain.reportType.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraRepository extends JpaRepository<Camera,Long> {
}
