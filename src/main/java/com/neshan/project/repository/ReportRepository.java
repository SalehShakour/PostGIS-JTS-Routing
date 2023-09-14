package com.neshan.project.repository;


import com.neshan.project.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReportRepository<T extends Report> extends JpaRepository<T, Long> {
}

