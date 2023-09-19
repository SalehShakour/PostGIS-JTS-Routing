package com.neshan.project.service;

import com.neshan.project.exception.CustomException;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class PointHashService {
    public String hashGeometryPointToString(Point point) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(point.toString().getBytes());
            return Base64.getEncoder().encodeToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException(e.getMessage());
        }
    }
}
