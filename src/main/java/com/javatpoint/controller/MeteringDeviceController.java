package com.javatpoint.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import com.javatpoint.model.MeteringDevice;
import com.javatpoint.service.MeteringDeviceService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MeteringDeviceController extends AbstractView {
    @Autowired
    protected MeteringDeviceService meteringDeviceService;
    private List<MeteringDevice> meteringDevices;
    @LocalServerPort
    int localServerPort;

    @GetMapping("/api/upd/getall")
    private List<MeteringDevice> getAll() {
        return meteringDeviceService.getAllMetering();
    }

    @GetMapping("/api/upd/getsingle/{id}")
    private MeteringDevice get(@PathVariable("id") int idDevice) {
        return meteringDeviceService.getMeteringById(idDevice);
    }

    @PostMapping("/api/add")
    private MeteringDevice save(@RequestBody MeteringDevice meteringDevice) {
        meteringDeviceService.saveOrUpdate(meteringDevice);
        return meteringDevice;
    }

    @DeleteMapping("/api/del/{id}")
    private void delete(@PathVariable("id") int id) {
        meteringDeviceService.delete(id);
    }

    @PutMapping("/api/put")
    private MeteringDevice update(@RequestBody MeteringDevice meteringDevice) {
        meteringDeviceService.saveOrUpdate(meteringDevice);
        return meteringDevice;
    }

    @GetMapping("api/upd/get/{id}")
    private String formMeteringDeviceList(@PathVariable("id") int idDevice) {
        meteringDevices = new LinkedList<>();
        meteringDevices.add(meteringDeviceService.getMeteringById(idDevice));

        return new RestTemplate().getForObject("http://localhost:" + localServerPort
                + "/api/upd/get/report", String.class);
    }

    @GetMapping("api/upd/get/report")
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception {

        httpServletResponse.setContentType("text/html");
        JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager
                        .compileReport(ResourceUtils
                                .getFile("classpath:report.jrxml").getAbsolutePath()),
                null,
                new JRBeanCollectionDataSource(meteringDevices));

        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(httpServletResponse.getWriter()));
        exporter.exportReport();

    }

}
