package com.neshan.project.controller;

import com.neshan.project.domain.Report;
import com.neshan.project.dto.ReportDTO;
import com.neshan.project.service.RouteService;
import lombok.AllArgsConstructor;
import org.locationtech.jts.io.WKTReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route")
@AllArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<List<ReportDTO>> showRelatedReport(@RequestBody String linestringWkt){
        return ResponseEntity.status(HttpStatus.OK).body(
                routeService.routeAnalysis(routeService.toLinestring(linestringWkt))
        );
    }
}
