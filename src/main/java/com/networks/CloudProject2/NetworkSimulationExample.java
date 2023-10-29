package com.networks.CloudProject2;

import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.builders.tables.CloudletsTableBuilder;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.vms.VmSimple;

import java.util.List;

public class NetworkSimulationExample {
    public static void main(String[] args) {
        CloudSimPlus simulation = new CloudSimPlus();
        DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);

        HostSimple host1 = new HostSimple(10000, 100000, 100000, List.of(new PeSimple(2000)));
        HostSimple host2 = new HostSimple(8000, 90000, 90000, List.of(new PeSimple(1800)));

        DatacenterSimple datacenter = new DatacenterSimple(simulation, List.of(host1, host2));

        VmSimple vm1 = new VmSimple(1000, 1);
        VmSimple vm2 = new VmSimple(800, 1);

        broker.submitVmList(List.of(vm1, vm2));

        // Define a network topology and connect VMs to the network
//        datacenter.getPowerModel().addLink(host1, host2, 10.0, 10000); // 10 Mbps, 10ms delay

        broker.submitCloudletList(List.of(new CloudletSimple(10000, 1), new CloudletSimple(8000, 1)));

        simulation.start();

        new CloudletsTableBuilder(broker.getCloudletFinishedList()).build();
    }
}
