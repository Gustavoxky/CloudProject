package com.networks.CloudExamples;

import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.Host;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.vms.Vm;
import org.cloudsimplus.vms.VmSimple;

import java.util.ArrayList;
import java.util.List;

public class CloudSimPlusExample7 {
    public static void run(String[] args) {
        CloudSimPlus simulation = new CloudSimPlus();

        List<DatacenterSimple> datacenters = createMultipleDatacenters(simulation, 3);
        List<DatacenterBrokerSimple> brokers = createMultipleBrokers(simulation, 3);

        for (DatacenterBrokerSimple broker : brokers) {
            List<Vm> vms = createVMs(2);
            broker.submitVmList(vms);
        }

        List<Cloudlet> cloudlets = createCloudlets(12);

        distributeCloudletsToBrokers(brokers, cloudlets);

        // Add an event listener to pause and resume the simulation
        simulation.addOnEventProcessingListener(e -> {
            if (e.getTime() == 50.0) {
                System.out.println("Pausing the simulation at time 50.0");
                simulation.pause();
            } else if (e.getTime() == 100.0) {
                System.out.println("Resuming the simulation at time 100.0");
                simulation.resume();
            }
        });

        // Início da simulação
        simulation.start();

        for (int i = 0; i < brokers.size(); i++) {
            DatacenterBrokerSimple broker = brokers.get(i);
            System.out.println("Resultados do Broker " + i + ":");
            List<Cloudlet> finishedCloudlets = broker.getCloudletFinishedList();
            printCloudletList(finishedCloudlets);
        }
    }

    private static List<DatacenterSimple> createMultipleDatacenters(CloudSimPlus simulation, int numDatacenters) {
        List<DatacenterSimple> datacenters = new ArrayList<>();
        for (int i = 0; i < numDatacenters; i++) {
            DatacenterSimple datacenter = createDatacenter("Datacenter" + i, simulation);
            datacenters.add(datacenter);
        }
        return datacenters;
    }

    private static List<DatacenterBrokerSimple> createMultipleBrokers(CloudSimPlus simulation, int numBrokers) {
        List<DatacenterBrokerSimple> brokers = new ArrayList<>();
        for (int i = 0; i < numBrokers; i++) {
            DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);
            brokers.add(broker);
        }
        return brokers;
    }

    private static DatacenterSimple createDatacenter(String name, CloudSimPlus simulation) {
        Host host = new HostSimple(8000, 100000, 8000, List.of(new PeSimple(1600)));
        DatacenterSimple datacenter = new DatacenterSimple(simulation, List.of(host));
        datacenter.setName(name);
        return datacenter;
    }

    private static List<Vm> createVMs(int num) {
        List<Vm> vms = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Vm vm = new VmSimple(1000, 1);
            vms.add(vm);
        }
        return vms;
    }

    private static List<Cloudlet> createCloudlets(int num) {
        List<Cloudlet> cloudlets = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Cloudlet cloudlet = new CloudletSimple(4000, 1);
            cloudlets.add(cloudlet);
        }
        return cloudlets;
    }

    private static void distributeCloudletsToBrokers(List<DatacenterBrokerSimple> brokers, List<Cloudlet> cloudlets) {
        int currentBrokerIndex = 0;
        for (Cloudlet cloudlet : cloudlets) {
            DatacenterBrokerSimple currentBroker = brokers.get(currentBrokerIndex);
            currentBroker.submitCloudlet(cloudlet);
            currentBrokerIndex = (currentBrokerIndex + 1) % brokers.size();
        }
    }

    private static void printCloudletList(List<Cloudlet> cloudletList) {
        System.out.println("ID\t\tSTATUS\tDatacenter ID\tVM ID\tTime\tStart Time\tFinish Time");
        for (Cloudlet cloudlet : cloudletList) {
            long actualCPUTime = (long) (cloudlet.getFinishTime() - cloudlet.getStartTime());
            System.out.println(cloudlet.getId() + "\t\t" + cloudlet.getStatus() + "\t\t" + cloudlet.getPesNumber() + "\t\t" + cloudlet.getId()
                    + "\t\t" + actualCPUTime + "\t\t" + cloudlet.getStartTime() + "\t\t" + cloudlet.getFinishTime());
        }
    }
}
