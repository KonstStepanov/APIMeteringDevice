package com.javatpoint.controller;

import com.javatpoint.model.MeteringDevice;
import com.javatpoint.service.MeteringDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddDeviceController {

    @Autowired
    protected MeteringDeviceService meteringDeviceService;

    @GetMapping("/devices")
    public String getDevices(Model model) {
        List<MeteringDevice> meteringDevices = meteringDeviceService.getAllMetering();
        model.addAttribute("devices", meteringDevices);
        model.addAttribute("deviceInfo", new MeteringDevice());
        return "devices";
    }

    @PostMapping("/devices/")
    public String createDevices(@ModelAttribute MeteringDevice meteringDevice) {
        meteringDeviceService.saveOrUpdate(meteringDevice);
        return "redirect:/devices";
    }

}
