package com.javatpoint.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javatpoint.model.MeteringDevice;
import com.javatpoint.repository.MeteringDeviceRepository;

@Service
public class MeteringDeviceService {
    @Autowired
    MeteringDeviceRepository meteringDeviceRepository;

    public List<MeteringDevice> getAllMetering() {
        List<MeteringDevice> meteringDevice = new ArrayList<>();
        meteringDeviceRepository.findAll().forEach(meteringDevice::add);
        return meteringDevice;
    }

    public MeteringDevice getMeteringById(int id) {
        return meteringDeviceRepository.findById(id).get();
    }

    public void saveOrUpdate(MeteringDevice meteringDevice) {
        meteringDeviceRepository.save(meteringDevice);
    }

    public void delete(int id) {
        meteringDeviceRepository.deleteById(id);
    }
}