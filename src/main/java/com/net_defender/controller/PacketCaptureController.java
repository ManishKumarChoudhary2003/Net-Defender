package com.net_defender.controller;

import com.net_defender.service.PacketCaptureService;
import com.net_defender.utils.PcapFileGenerator;
import org.pcap4j.core.NotOpenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PacketCaptureController {

    @Autowired
    private PacketCaptureService packetCaptureService;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/read")
    public String readFromPcap() {
        String pcapFilePath = "src/main/resources/pcap/sample.pcap"; // Path to the generated PCAP file
        packetCaptureService.readFromPcapFile(pcapFilePath);
        return "Packet capture started. Check the logs for captured packets.";
    }


    private String getPcapFilePath(String relativePath) {
        Resource resource = resourceLoader.getResource("classpath:pcap/" + relativePath); // Modify to only include pcap folder
        try {
            return resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping("/generate-pcap")
    public String generatePcap() {
        String pcapFilePath = "src/main/resources/pcap/sample.pcap";
        try {
            PcapFileGenerator.createSamplePcap(pcapFilePath);
            return "Sample PCAP file created at: " + pcapFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error creating PCAP file: " + e.getMessage();
        }
    }

    @PostMapping("/create")
    public String createCustomPacket() {
        packetCaptureService.createCustomPacket();
        return "Custom packet created. Check the logs for details.";
    }
}
