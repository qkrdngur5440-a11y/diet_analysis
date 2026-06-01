package com.example.diet_analysis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "<html>" +
               "<head><title>Diet Analysis Service</title></head>" +
               "<body style=\"font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; max-width: 600px; color: #333;\">" +
               "  <h1 style=\"color: #0066cc;\">🟢 Diet Analysis Service is Running</h1>" +
               "  <p>식단 분석 서비스가 정상적으로 구동 중입니다.</p>" +
               "  <hr/>" +
               "  <h3>🔌 API Endpoint 안내</h3>" +
               "  <ul>" +
               "    <li><strong>엔드포인트:</strong> <code>POST /api/analysis/photo</code></li>" +
               "    <li><strong>형식 (Content-Type):</strong> <code>multipart/form-data</code></li>" +
               "    <li><strong>파라미터 (Part Name):</strong> <code>image</code> (식단 분석용 이미지 파일)</li>" +
               "  </ul>" +
               "</body>" +
               "</html>";
    }
}
