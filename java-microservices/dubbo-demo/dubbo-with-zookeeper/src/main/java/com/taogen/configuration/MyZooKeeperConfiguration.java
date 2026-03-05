package com.taogen.configuration;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author taogen
 */
@Component
public class MyZooKeeperConfiguration implements CommandLineRunner {
    @Value("${dubbo.config-center.address}")
    private String zookeeperAddress;

    @Value("${dubbo.config-center.namespace}")
    private String namespace;

    @Value("${dubbo.application.name}")
    private String applicationName;

    @Override
    public void run(String... args) throws Exception {
        String connectString = zookeeperAddress.replace("zookeeper://", "");
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                connectString,
                new ExponentialBackoffRetry(1000, 3)
        );
        client.start();
        byte[] data = client.getData().forPath("/" + namespace + "/config/" + applicationName + "/dubbo.properties");
        System.out.println("Get data from ZooKeeper. Dubbo properties: \n" + new String(data));
    }
}
