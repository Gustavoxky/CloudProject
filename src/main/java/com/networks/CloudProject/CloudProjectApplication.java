package com.networks.CloudProject;

import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.builders.tables.CloudletsTableBuilder;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.utilizationmodels.UtilizationModelDynamic;
import org.cloudsimplus.vms.VmSimple;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CloudProjectApplication {

	public static void main(String[] args) {
		CloudSimPlusExample1.run(args);
		CloudSimPlusExample2.run(args);
		CloudSimPlusExample3.run(args);
		CloudSimPlusExample4.run(args);
		CloudSimPlusExample5.run(args);
		CloudSimPlusExample6.run(args);
		CloudSimPlusExample7.run(args);
		CloudSimPlusExample8.run(args);

	}
}

