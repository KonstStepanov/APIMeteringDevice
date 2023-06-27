package com.javatpoint.repository;

import org.springframework.data.repository.CrudRepository;
import com.javatpoint.model.MeteringDevice;

public interface MeteringDeviceRepository extends CrudRepository<MeteringDevice, Integer> {
}
