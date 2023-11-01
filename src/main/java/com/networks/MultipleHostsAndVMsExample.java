package com.networks;

import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.builders.tables.CloudletsTableBuilder;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.utilizationmodels.UtilizationModelDynamic;
import org.cloudsimplus.vms.VmSimple;

import java.util.ArrayList;
import java.util.List;

public class MultipleHostsAndVMsExample {
    public static void run(String[] args) {
        CloudSimPlus simulation = new CloudSimPlus();
        DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);

        // Create and add multiple hosts to a datacenter
        List<HostSimple> hosts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HostSimple host = new HostSimple(10000, 100000, 100000, List.of(new PeSimple(2000)));
            hosts.add(host);
        }

        DatacenterSimple datacenter = new DatacenterSimple(simulation, hosts);

        // Create and add multiple VMs to the broker
        List<VmSimple> vms = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VmSimple vm = new VmSimple(1000, 1);
            vm.setRam(1000).setBw(1000).setSize(1000);
            vms.add(vm);
        }

        broker.submitVmList(vms);

        // Create and add multiple Cloudlets to the broker
        List<CloudletSimple> cloudlets = new ArrayList<>();
        var utilizationModel = new UtilizationModelDynamic(0.5);
        for (int i = 0; i < 5; i++) {
            CloudletSimple cloudlet = new CloudletSimple(10000, 1, utilizationModel);
            cloudlets.add(cloudlet);
        }

        broker.submitCloudletList(cloudlets);

        simulation.start();

        new CloudletsTableBuilder(broker.getCloudletFinishedList()).build();
    }
}
