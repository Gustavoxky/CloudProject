package com.networks.CloudProject;

import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.vms.VmSimple;

import java.util.List;

public class CloudSimPlusExample2 {
    public static void run(String[] args) {
        CloudSimPlus simulation = new CloudSimPlus();

        DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);

        HostSimple host = new HostSimple(10000, 100000, 10000, List.of(new PeSimple(2000)));
        DatacenterSimple datacenter = new DatacenterSimple(simulation, List.of(host));

        VmSimple vm = new VmSimple(1000, 1);
        broker.submitVm(vm);

        CloudletSimple cloudlet1 = new CloudletSimple(10000, 1);
        CloudletSimple cloudlet2 = new CloudletSimple(10000, 1);

        broker.submitCloudlet(cloudlet1);
        broker.submitCloudlet(cloudlet2);

        simulation.start();
    }
}
