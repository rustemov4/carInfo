package com.example.carData.controller;

import com.example.carData.entity.CarInfo;
import com.example.carData.request.CarRequest;
import com.example.carData.response.Response;
import com.example.carData.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarInfoController {
    @Autowired
    CarInfoService carInfoService;

    private final String csvFilePath = "C:\\Users\\azama\\Downloads\\Result_2.csv";

    @GetMapping("/data")
    public ResponseEntity<?> getAllData() {
        if (carInfoService.dataIsEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("No data"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(carInfoService.getAllData()));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> saveAllData() {
        Object result = carInfoService.saveAllData(csvFilePath);
        if (result instanceof IOException || result instanceof FileNotFoundException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(((IOException) result).getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(result));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCarInfoByDate(@RequestBody CarRequest carRequest) {
        String start = carRequest.getDate().concat(" 00:00:00");
        String end = carRequest.getDate().concat(" 23:59:59");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<CarInfo> carInfos = carInfoService.getCarInfoByDate(carRequest.getId(), LocalDateTime.parse(start, formatter), LocalDateTime.parse(end, formatter));
        if (carInfos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("No data"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response((carInfos)));
    }
}
