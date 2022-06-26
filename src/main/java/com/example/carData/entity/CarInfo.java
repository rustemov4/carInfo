package com.example.carData.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromUnixtimeTimestamp;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fromUnixtimeCreationTime;

    private String accountID;
    private String deviceID;
    private String timestamp;
    private String statusCode;
    private String latitude;
    private String longitude;
    private String gpsAge;
    private String speedKPH;
    private String heading;
    private String altitude;
    private String transportID;
    private String inputMask;
    private String outputMask;
    private String seatbeltMask;
    private String address;
    private String dataSource;
    private String rawData;
    private String distanceKM;
    private String odometerKM;
    private String odometerOffsetKM;
    private String geozoneIndex;
    private String geozoneID;
    private String creationTime;
    private String satelliteCount;
    private String batteryLevel;
    private String batteryVolts;

}
