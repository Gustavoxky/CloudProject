package com.networks.CloudProject;

import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.core.Simulation;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.Host;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.vms.VmSimple;

import java.util.ArrayList;
import java.util.List;

public class CloudSimPlusExample4 {
    public static void run(String[] args) {
        CloudSimPlus simulation = new CloudSimPlus();

        // Criação dos Datacenters
        DatacenterSimple datacenter1 = createDatacenter("Datacenter1", simulation);
        DatacenterSimple datacenter2 = createDatacenter("Datacenter2", simulation);

        // Criação do Broker
        DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);

        // Criação das VMs
        VmSimple vm1 = new VmSimple(1000, 1);
        VmSimple vm2 = new VmSimple(1500, 1);

        List<VmSimple> vmList = new ArrayList<>();
        vmList.add(vm1);
        vmList.add(vm2);

        broker.submitVmList(vmList, 0);

        // Criação das Cloudlets
        CloudletSimple cloudlet1 = new CloudletSimple(4000, 1);
        CloudletSimple cloudlet2 = new CloudletSimple(6000, 1);

        // Submissão das Cloudlets ao Broker
        broker.submitCloudlet(cloudlet1);
        broker.submitCloudlet(cloudlet2);

        // Início da simulação
        simulation.start();
    }

    private static DatacenterSimple createDatacenter(String name, CloudSimPlus simulation) {
        Host host = new HostSimple(8000, 100000, 8000, List.of(new PeSimple(1600)));
        DatacenterSimple datacenter = new DatacenterSimple(simulation, List.of(host));
        datacenter.setName(name);
        return datacenter;
    }
}
