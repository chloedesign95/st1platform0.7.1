package com.st1.platform.pi.repository;

import com.st1.platform.pi.domain.ParkingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Pi 1.9
@RepositoryRestResource
public interface ParkingInfoRepository extends
        JpaRepository<ParkingInfo, Long> {
}
