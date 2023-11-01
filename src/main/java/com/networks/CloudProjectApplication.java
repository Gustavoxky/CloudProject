package com.networks;

import com.networks.CloudExamples.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//		MultipleHostsAndVMsExample.run(args);
		NetworkSimulationExample.run(args);

	}
}

