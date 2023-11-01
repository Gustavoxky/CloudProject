package com.networks.CloudProject;

import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.vms.VmSimple;

import java.util.List;

public class CloudSimPlusExample3 {
    public static void run(String[] args) {
        CloudSimPlus simulation = new CloudSimPlus();

        DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);

        HostSimple host1 = new HostSimple(8000, 100000, 8000, List.of(new PeSimple(1600)));
        HostSimple host2 = new HostSimple(10000, 100000, 10000, List.of(new PeSimple(2000)));

        DatacenterSimple datacenter = new DatacenterSimple(simulation, List.of(host1, host2));

        VmSimple vm1 = new VmSimple(1000, 1);
        VmSimple vm2 = new VmSimple(2000, 1);

        broker.submitVmList(List.of(vm1));
        broker.submitVmList(List.of(vm2));


        CloudletSimple cloudlet1 = new CloudletSimple(8000, 1);
        CloudletSimple cloudlet2 = new CloudletSimple(10000, 1);

        broker.submitCloudlet(cloudlet1);
        broker.submitCloudlet(cloudlet2);

        simulation.start();
    }
}
