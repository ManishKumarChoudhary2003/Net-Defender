package com.net_defender.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PcapFileGenerator {
    private static final int PCAP_GLOBAL_HEADER_LEN = 24;
    private static final int PCAP_RECORD_HEADER_LEN = 16;

    public static void createSamplePcap(String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // Write PCAP Global Header
            byte[] globalHeader = new byte[PCAP_GLOBAL_HEADER_LEN];
            ByteBuffer.wrap(globalHeader)
                .order(ByteOrder.LITTLE_ENDIAN)
                .putInt(0xA1B2C3D4) // Magic number
                .putShort((short) 2) // Major version
                .putShort((short) 4) // Minor version
                .putInt(0) // Time zone
                .putInt(0) // Timestamp accuracy
                .putInt(65535) // Max length of captured packets
                .putInt(1); // Data link type (Ethernet)
            fos.write(globalHeader);

            // Write a sample packet
            byte[] packet = createSamplePacket();
            fos.write(packet);
        }
    }

    private static byte[] createSamplePacket() {
        // Create a simple Ethernet packet (dummy data)
        byte[] packet = new byte[PCAP_RECORD_HEADER_LEN + 60]; // 60 bytes of dummy data
        ByteBuffer.wrap(packet)
            .order(ByteOrder.LITTLE_ENDIAN)
            .putInt(0) // Timestamp seconds (dummy value)
            .putInt(0) // Timestamp microseconds (dummy value)
            .putInt(60) // Number of octets of packet saved in file
            .putInt(60); // Actual length of packet (dummy value)
        
        // Fill the rest with dummy data
        for (int i = PCAP_RECORD_HEADER_LEN; i < packet.length; i++) {
            packet[i] = (byte) (i - PCAP_RECORD_HEADER_LEN);
        }
        return packet;
    }

//    public static void main(String[] args) {
//        String pcapFilePath = "src/main/resources/pcap/sample.pcap"; // Specify the path to save the PCAP file
//        try {
//            createSamplePcap(pcapFilePath);
//            System.out.println("Sample PCAP file created at: " + pcapFilePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
