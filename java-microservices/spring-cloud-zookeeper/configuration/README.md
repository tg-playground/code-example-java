# Configuration

## Create configuration data in ZooKeeper

Node path: `/<root>/<spring.application.name>,<profile>/<key-name>`. For example,
`/configuration/spring-cloud-zookeeper/greeting=Hello ZooKeeper!`

```shell
# Running an interactive shell in the container
$ docker exec -it zookeeper1 sh
# Connect to ZooKeeper
$ bin/zkCli.sh -server 127.0.0.1:2181
# Add configuration data to ZooKeeper
[zk] create /config
[zk] create /config/spring-cloud-zookeeper-configuration
[zk] create /config/spring-cloud-zookeeper-configuration/greeting "Hello World"
[zk] create /config/spring-cloud-zookeeper-configuration,dev
[zk] create /config/spring-cloud-zookeeper-configuration,dev/greeting "Hello Developer"
```
