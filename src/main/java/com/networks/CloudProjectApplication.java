package com.networks;

import com.networks.CloudExamples.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudProjectApplication {

	public static void main(String[] args) {
		// Lista de exemplos do CloudSim Plus
		Class<?>[] cloudSimPlusExamples = {
				CloudSimPlusExample1.class,
				CloudSimPlusExample2.class,
				CloudSimPlusExample3.class,
				CloudSimPlusExample4.class,
				CloudSimPlusExample5.class,
				CloudSimPlusExample6.class,
				CloudSimPlusExample7.class,
				CloudSimPlusExample8.class,
				// Adicione mais exemplos conforme necessário
				// MultipleHostsAndVMsExample.class,
				NetworkSimulationExample.class
		};

		// Executando exemplos do CloudSim Plus
		for (Class<?> exampleClass : cloudSimPlusExamples) {
			runExample(exampleClass, args);
		}
	}

	private static void runExample(Class<?> exampleClass, String[] args) {
		try {
			// Obter o método estático "run" da classe de exemplo e invocá-lo
			exampleClass.getMethod("run", String[].class).invoke(null, (Object) args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
