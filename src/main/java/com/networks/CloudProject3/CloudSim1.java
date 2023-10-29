package com.networks.CloudProject3;

import java.util.ArrayList;
import java.util.List;

// Definição de classe para representar um recurso de processamento.
class Resource {
    int capacity;

    public Resource(int capacity) {
        this.capacity = capacity;
    }
}

// Definição de classe para representar um Host (servidor físico).
class Host {
    int cpuCapacity;
    int ramCapacity;
    int bwCapacity;
    List<Resource> pes;

    public Host(int cpuCapacity, int ramCapacity, int bwCapacity, List<Resource> pes) {
        this.cpuCapacity = cpuCapacity;
        this.ramCapacity = ramCapacity;
        this.bwCapacity = bwCapacity;
        this.pes = pes;
    }
}

// Definição de classe para representar uma VM (Máquina Virtual).
class VM {
    int cpuCapacity;
    int ramCapacity;

    public VM(int cpuCapacity, int ramCapacity) {
        this.cpuCapacity = cpuCapacity;
        this.ramCapacity = ramCapacity;
    }
}

// Definição de classe para representar um Cloudlet (tarefa).
class Cloudlet {
    int length;

    public Cloudlet(int length) {
        this.length = length;
    }
}

// Definição de classe para representar um Datacenter (centro de processamento de dados).
class Datacenter {
    List<Host> hosts;

    public Datacenter(List<Host> hosts) {
        this.hosts = hosts;
    }
}

// Definição de classe para representar um Corretor (broker).
class Broker {
    List<VM> vms;
    List<Cloudlet> cloudlets;

    public Broker() {
        this.vms = new ArrayList<>();
        this.cloudlets = new ArrayList<>();
    }

    // Método para registrar uma VM.
    public void submitVm(VM vm) {
        vms.add(vm);
    }

    // Método para registrar um Cloudlet.
    public void submitCloudlet(Cloudlet cloudlet) {
        cloudlets.add(cloudlet);
    }
}

public class CloudSim1 {
    public static void main(String[] args) {
        // Cria instâncias das classes que representam recursos.
        Resource pe = new Resource(2000);

        // Cria instância da classe Host com recursos.
        List<Resource> pesList = new ArrayList<>();
        pesList.add(pe);
        Host host = new Host(10000, 100000, 10000, pesList);

        // Cria instância da classe Datacenter com hosts.
        List<Host> hostsList = new ArrayList<>();
        hostsList.add(host);
        Datacenter datacenter = new Datacenter(hostsList);

        // Cria instância da classe VM com recursos.
        VM vm = new VM(1000, 1);

        // Cria instância da classe Cloudlet com requisitos de processamento.
        Cloudlet cloudlet = new Cloudlet(10000);

        // Cria instância da classe Broker.
        Broker broker = new Broker();

        // Registra a VM e o Cloudlet com o Broker.
        broker.submitVm(vm);
        broker.submitCloudlet(cloudlet);

        // Inicia a simulação (não implementada na versão explícita).
    }
}
