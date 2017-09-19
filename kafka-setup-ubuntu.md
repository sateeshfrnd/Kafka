
# Setup Kafka

## Prerequisites
- Install/Setup Java 
- Install/Setup Zookeeper

## Setup a Kafka Cluster
- Use wget to download the Kafka binaries.
``` 
$ wget https://www.apache.org/dyn/closer.cgi?path=/kafka/0.8.1.1/kafka_2.10-0.8.1.1.tgz
```
	
- Create a folder Kafka
``` 
$ sudo mkdir /opt/Kafka
```
	
- Untar the downloded file and copy to Kafka folder 
```
$ tar xvf kafka_2.10-0.8.1.1.tgz -C /opt/Kafka 
```

- Create two servers/brokers
```
opt/kafka$ mkdir kafka-log-1
opt/kafka$ mkdir kafka-log-2
```

- Create/Update Configurations for server1 in 'config/server.properties' and update the broker id, port, host name, log directory, partitions and zookeeper.
```
Broker.id=0
Port=9092
num.partitions=2
zookeeper.connect=localhost:2181
log.dirs=/opt/Kafka/kafka-logs-1

Note: id should be unique.
```

- Create/Update Configurations for server2 log 'config/server1.properties' and update the broker id as 1, port as 9091 and log.dir
```	
Broker.id=1
Port=9091
num.partitions=2
zookeeper.connect=localhost:2181
log.dirs=/opt/Kafka/kafka-logs-2
```
- Start embedded zookeeper
``` 
./bin/zookeeper-server-start.sh config/zookeeper.properties
```

- Start kafka broker1/server1
```
./bin/kafka-server-start.sh config/server.properties & 
```

- Start kafka server2
``` 
./bin/kafka-server-start.sh config/server1.properties & 
```

- Check servers are running or not
``` 
$ps–ef | grep kafka 
```

- Create a ‘Topic’ using utility bin/kafka-topics.sh
``` 
$bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic first --partitions 2 --replication-factor 2
```

- Check topic is created or not
```
$bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic first
```

- Open new terminal and start Producer
``` 
$bin/kafka-console-producer.sh --broker-list localhost:9092 --topic first
```

- Open new terminal and start Consumer
``` 
$bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic first 
```

- If you want to see from messages from beginning of any existing topics
``` 
$bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic first --from-beginning
```

#### Note: 
- If you are planning to use kafka with scala then we have to check the support version.
- By default, Kafka doesn't allow you to delete topics. To be able to delete topics, add the following line at the end of the file `server.properties`:
```
vi ~/kafka/config/server.properties
delete.topic.enable = true
```


