package com.neshan.project.controller;

import com.neshan.project.domain.User;
import com.neshan.project.domain.reportType.Accident;
import com.neshan.project.domain.reportType.Bump;
import com.neshan.project.dto.AccidentDTO;
import com.neshan.project.dto.BumpDTO;
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
    private final UserRepository userRepository;


    @PostMapping("/accident")
    public ResponseEntity<String> addAccident(@AuthenticationPrincipal User currentUser,
                                              @RequestBody AccidentDTO accidentDTO){
        accidentService.save(accidentService.createAccident(currentUser,accidentDTO));
        return ResponseEntity.status(HttpStatus.OK).body("Thank you for your report.");
    }
    @PostMapping("/bump")
    public ResponseEntity<String> addBump(@AuthenticationPrincipal User currentUser,
                                              @RequestBody BumpDTO bumpDTO){
        bumpService.save(bumpService.createBump(currentUser, bumpDTO));
        return ResponseEntity.status(HttpStatus.OK).body("Thank you for your report.");
    }
}
