package com.neshan.project.controller;

import com.neshan.project.domain.User;
import com.neshan.project.domain.reportType.Accident;
import com.neshan.project.domain.reportType.Bump;
import com.neshan.project.domain.reportType.Camera;
import com.neshan.project.domain.reportType.Police;
import com.neshan.project.dto.AccidentDTO;
import com.neshan.project.dto.BumpDTO;
import com.neshan.project.dto.CameraDTO;
import com.neshan.project.dto.PoliceDTO;
import com.neshan.project.repository.UserRepository;
import com.neshan.project.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
public class ReportController {
    private final ReportService<Accident> accidentService;
    private final ReportService<Bump> bumpService;
    private final ReportService<Camera> cameraService;
    private final ReportService<Police> policeService;
    private final UserRepository userRepository;


    @PostMapping("/accident")
    public ResponseEntity<String> reportAccident(@AuthenticationPrincipal User currentUser,
                                              @RequestBody AccidentDTO accidentDTO){
        accidentService.save(accidentService.createAccidentObject(currentUser,accidentDTO));
        return ResponseEntity.status(HttpStatus.OK).body("Thank you for your accident report.");
    }
    @PostMapping("/bump")
    public ResponseEntity<String> reportBump(@AuthenticationPrincipal User currentUser,
                                              @RequestBody BumpDTO bumpDTO){
        bumpService.save(bumpService.createBumpObject(currentUser, bumpDTO));
        return ResponseEntity.status(HttpStatus.OK).body("Thank you for your bump report.");
    }
    @PostMapping("/camera")
    public ResponseEntity<String> reportCamera(@AuthenticationPrincipal User currentUser,
                                          @RequestBody CameraDTO cameraDTO){
        cameraService.save(cameraService.createCameraObject(currentUser, cameraDTO));
        return ResponseEntity.status(HttpStatus.OK).body("Thank you for your camera report.");
    }
    @PostMapping("/police")
    public ResponseEntity<String> reportPolice(@AuthenticationPrincipal User currentUser,
                                               @RequestBody PoliceDTO policeDTO){
        policeService.save(policeService.createPoliceObject(currentUser, policeDTO));
        return ResponseEntity.status(HttpStatus.OK).body("Thank you for your police report.");
    }
}
