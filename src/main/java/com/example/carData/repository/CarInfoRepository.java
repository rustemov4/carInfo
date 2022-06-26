package com.example.carData.repository;

import com.example.carData.entity.CarInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface CarInfoRepository extends JpaRepository<CarInfo, Long> {
    @Query(value = "SELECT * FROM car_info WHERE deviceid = ?1 AND from_unixtime_timestamp BETWEEN ?2 AND ?3", nativeQuery = true)
    List<CarInfo> getCarInfoByByDate(String deviceId, LocalDateTime start, LocalDateTime end);
}
