package com.exemplos;

import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.Pe;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.vms.VmSimple;
import org.cloudsimplus.cloudlets.CloudletSimple;

import java.util.List;

public class Teste_A_2VMs_1CloudLet {
    public static void main(String[] args) {
        var sim = new CloudSimPlus();

        // Host com 2 PEs de 30k MIPS (para comportar 2 VMs simultâneas)
        List<Pe> pes = List.of(new PeSimple(30_000), new PeSimple(30_000));
        var host = new HostSimple(16_000, 10_000, 1_000_000, pes);
        var dc = new DatacenterSimple(sim, List.of(host));

        var broker = new DatacenterBrokerSimple(sim);

        var vmLenta  = new VmSimple(1_000, 1).setRam(1024).setBw(1_000).setSize(10_000);
        var vmRapida = new VmSimple(2_000, 1).setRam(1024).setBw(1_000).setSize(10_000);

        var c1 = new CloudletSimple(10_000, 1);
        var c2 = new CloudletSimple(10_000, 1);

        broker.submitVm(vmLenta);
        broker.submitVm(vmRapida);
        broker.submitCloudlet(c1);
        broker.submitCloudlet(c2);

        sim.start();

        broker.getCloudletFinishedList().forEach(cl -> System.out.printf(
                "VM%2d(%4.0f MIPS) -> Cloudlet %d | start=%.2f | finish=%.2f | exec=%.2f%n",
                cl.getVm().getId(), cl.getVm().getMips(), cl.getId(),
                cl.getStartTime(), cl.getFinishTime(), cl.getTotalExecutionTime()
        ));
    }
}
