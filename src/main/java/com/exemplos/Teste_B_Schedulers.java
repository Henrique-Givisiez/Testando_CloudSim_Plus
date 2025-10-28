package com.exemplos;

import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.Pe;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.vms.VmSimple;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.schedulers.cloudlet.CloudletSchedulerTimeShared;
import org.cloudsimplus.schedulers.cloudlet.CloudletSchedulerSpaceShared;

import java.util.List;

public class Teste_B_Schedulers {
    public static void main(String[] args) {
        runWithScheduler(new CloudletSchedulerTimeShared(),  "TimeShared");
        runWithScheduler(new CloudletSchedulerSpaceShared(), "SpaceShared");
    }

    static void runWithScheduler(org.cloudsimplus.schedulers.cloudlet.CloudletScheduler scheduler, String label){
        var sim = new CloudSimPlus();

        List<Pe> pes = List.of(new PeSimple(30_000));
        var host = new HostSimple(8_000, 10_000, 1_000_000, pes);
        var dc = new DatacenterSimple(sim, List.of(host));
        var broker = new DatacenterBrokerSimple(sim);

        var vm = new VmSimple(1_000, 1).setRam(1024).setBw(1_000).setSize(10_000).setCloudletScheduler(scheduler);

        var c1 = new CloudletSimple(10_000, 1);
        var c2 = new CloudletSimple(10_000, 1);

        broker.submitVm(vm);
        broker.submitCloudlet(c1);
        broker.submitCloudlet(c2);

        sim.start();

        System.out.println("\n=== " + label + " ===");
        broker.getCloudletFinishedList().forEach(cl -> System.out.printf(
                "Cloudlet %d | start=%.2f | finish=%.2f | exec=%.2f%n",
                cl.getId(), cl.getStartTime(), cl.getFinishTime(), cl.getTotalExecutionTime()
        ));
    }
}
