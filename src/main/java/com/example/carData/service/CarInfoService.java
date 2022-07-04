package com.example.carData.service;

import com.example.carData.entity.CarInfo;
import com.example.carData.repository.CarInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CarInfoService {
    @Autowired
    private CarInfoRepository carInfoRepository;

    public Object saveAllData(String csvFilePath) {
        BufferedReader br;
        String line;
        String cvsSplitBy = ";";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            br = new BufferedReader(new FileReader(csvFilePath));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] col = line.split(cvsSplitBy);
                CarInfo carInfo = new CarInfo();
                carInfo.setFromUnixtimeTimestamp(LocalDateTime.parse(col[0], formatter));
                carInfo.setFromUnixtimeCreationTime(LocalDateTime.parse(col[1], formatter));
                carInfo.setAccountID(col[2]);
                carInfo.setDeviceID(col[3]);
                carInfo.setTimestamp(col[4]);
                carInfo.setStatusCode(col[5]);
                carInfo.setLatitude(col[6]);
                carInfo.setLongitude(col[7]);
                carInfo.setGpsAge(col[8]);
                carInfo.setSpeedKPH(col[9]);
                carInfo.setHeading(col[10]);
                carInfo.setAltitude(col[11]);
                carInfo.setTransportID(col[12]);
                carInfo.setInputMask(col[13]);
                carInfo.setOutputMask(col[14]);
                carInfo.setSeatbeltMask(col[15]);
                carInfo.setAddress(col[16]);
                carInfo.setDataSource(col[17]);
                carInfo.setRawData(col[18]);
                carInfo.setDistanceKM(col[19]);
                carInfo.setOdometerKM(col[20]);
                carInfo.setOdometerOffsetKM(col[21]);
                carInfo.setGeozoneIndex(col[22]);
                carInfo.setGeozoneID(col[23]);
                carInfo.setCreationTime(col[24]);
                carInfo.setSatelliteCount(col[38]);
                carInfo.setBatteryLevel(col[39]);
                carInfo.setBatteryVolts(col[40]);
                carInfoRepository.save(carInfo);
            }

        } catch (FileNotFoundException e) {
            return e;
        } catch (IOException e) {
            return e;
        }
        return "File was uploaded to mysql successfully";
    }

    public List<CarInfo> getCarInfoByDate(String deviceId, LocalDateTime start, LocalDateTime end) {
        List<CarInfo> carInfos;
        carInfos = carInfoRepository.getCarInfoByByDate(deviceId, start, end);
        return carInfos;
    }

    public List<CarInfo> getAllData() {
        return this.carInfoRepository.findAll();
    }

    public boolean dataIsEmpty() {
        return this.carInfoRepository.findAll().isEmpty();
    }
}
