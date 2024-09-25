package com.net_defender;

import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.util.NifSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class NetDefenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetDefenderApplication.class, args);
	}

}
