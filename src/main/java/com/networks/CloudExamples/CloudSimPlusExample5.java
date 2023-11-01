package com.networks.CloudExamples;

import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.Host;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.vms.VmSimple;

import java.util.ArrayList;
import java.util.List;

public class CloudSimPlusExample5 {
    public static void run(String[] args) {
        CloudSimPlus simulation = new CloudSimPlus();

        // Criação dos Datacenters
        DatacenterSimple datacenter1 = createDatacenter("Datacenter1", simulation);
        DatacenterSimple datacenter2 = createDatacenter("Datacenter2", simulation);

        // Criação do Broker
        DatacenterBrokerSimple broker1 = new DatacenterBrokerSimple(simulation);
        DatacenterBrokerSimple broker2 = new DatacenterBrokerSimple(simulation);

        // Criação das VMs
        VmSimple vm1 = new VmSimple(1000, 1);
        VmSimple vm2 = new VmSimple(1500, 1);

        List<VmSimple> vmList1 = new ArrayList<>();
        List<VmSimple> vmList2 = new ArrayList<>();
        vmList1.add(vm1);
        vmList2.add(vm2);

        broker1.submitVmList(vmList1, 0);
        broker2.submitVmList(vmList2, 0);

        // Criação das Cloudlets
        CloudletSimple cloudlet1 = new CloudletSimple(4000, 1);
        CloudletSimple cloudlet2 = new CloudletSimple(6000, 1);
        CloudletSimple cloudlet3 = new CloudletSimple(3000, 1);
        CloudletSimple cloudlet4 = new CloudletSimple(5000, 1);

        // Submissão das Cloudlets aos Brokers
        broker1.submitCloudlet(cloudlet1);
        broker1.submitCloudlet(cloudlet2);
        broker2.submitCloudlet(cloudlet3);
        broker2.submitCloudlet(cloudlet4);

        // Início da simulação
        simulation.start();

        // Resultados do Broker 1
        System.out.println("Resultados do Broker 1:");
        List<Cloudlet> finishedCloudlets1 = broker1.getCloudletFinishedList();
        printCloudletList(finishedCloudlets1);

        // Resultados do Broker 2
        System.out.println("\nResultados do Broker 2:");
        List<Cloudlet> finishedCloudlets2 = broker2.getCloudletFinishedList();
        printCloudletList(finishedCloudlets2);
    }

    private static DatacenterSimple createDatacenter(String name, CloudSimPlus simulation) {
        Host host = new HostSimple(8000, 100000, 8000, List.of(new PeSimple(1600)));
        DatacenterSimple datacenter = new DatacenterSimple(simulation, List.of(host));
        datacenter.setName(name);
        return datacenter;
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
