package com.neshan.project.controller;

import com.neshan.project.domain.User;
import com.neshan.project.domain.reportType.Accident;
import com.neshan.project.dto.AccidentDTO;
import com.neshan.project.repository.UserRepository;
import com.neshan.project.service.ReportService;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
public class ReportController {
    private final ReportService<Accident> accidentReportService;
    private final UserRepository userRepository;


    @PostMapping("/accident")
    public ResponseEntity<String> addAccident(@AuthenticationPrincipal User currentUser,
                                              @RequestBody AccidentDTO accidentDTO){
        accidentReportService.save(accidentReportService.createAccident(currentUser,accidentDTO));
        return ResponseEntity.status(HttpStatus.OK).body("Thank you for your report.");
    }
}
