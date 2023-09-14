package com.neshan.project.service.subReport;

// Import the necessary packages
import com.neshan.project.domain.reportType.Camera;
import com.neshan.project.exception.CustomException;
import com.neshan.project.myEnum.ReportStatus;
import com.neshan.project.repository.subReport.CameraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CameraService {

    private final CameraRepository cameraRepo;


    public Camera reportValidation(Long id) {
        return cameraRepo.findById(id)
                .orElseThrow(() -> new CustomException("Camera report not found"));
    }

    @Transactional
    public void addCameraReport(Camera camera) {
        cameraRepo.save(camera);
    }

    @Transactional(readOnly = true)
    public Camera getCameraReportById(Long id) {
        return reportValidation(id);
    }

    @Transactional
    public Camera updateCameraReportStatus(Long id, ReportStatus status) {
        Camera camera = reportValidation(id);
        camera.setStatus(status);
        return cameraRepo.save(camera);
    }
}

