package com.neshan.project.controller;

import com.neshan.project.domain.Report;
import com.neshan.project.domain.User;
import com.neshan.project.domain.reportType.*;
import com.neshan.project.dto.*;
import com.neshan.project.repository.UserRepository;
import com.neshan.project.service.ReportService;
import com.neshan.project.service.ReportServiceDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
public class ReportController {
    private final ReportService<Report> reportRepo;

    private final ReportServiceDispatcher dispatcher;

    @PostMapping
    public ResponseEntity<String> createReport(@AuthenticationPrincipal User currentUser,
                                              @RequestBody ReportDTO reportDTO){
        reportDTO.setUser(currentUser);
        dispatcher.dispatchAndSave(reportDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                String.format("Thank you for your %s report.",reportDTO.getReportType().name())
        );
    }

    @PutMapping("/like")
    public ResponseEntity<String> likeReport(@AuthenticationPrincipal User currentUser,
                                             @RequestParam Long id){
        reportRepo.updateRating(id,1);
        return ResponseEntity.status(HttpStatus.OK).body("Thank you for sending like.");
    }

    @PutMapping("/dislike")
    public ResponseEntity<String> dislikeReport(@AuthenticationPrincipal User currentUser,
                                                @RequestParam Long id){
        reportRepo.updateRating(id,-1);
        return ResponseEntity.status(HttpStatus.OK).body("Thank you for sending dislike.");
    }

}
