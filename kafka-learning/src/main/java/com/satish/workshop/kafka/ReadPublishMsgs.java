package com.satish.workshop.kafka;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ReadPublishMsgs {

	public static void main(String[] args) {
//		String topic = args[0].toString();
		String topic = "test_topic";
		String brokers = "localhost:9092";
		String groupId = "groupId1";

		// Create consumer with configurations
		Properties props = new Properties();
		props.put("zookeeper.connect", "localhost:2181");
		props.put("bootstrap.servers", brokers);
		props.put("group.id", groupId);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		// Subscribe Consumer with list of topics
		consumer.subscribe(Collections.singletonList(topic));
		boolean isRunning = true;
		System.out.println("Waiting for Messages");
		while (isRunning) {
			ConsumerRecords<String, String> records = consumer.poll(1000);
			 for (ConsumerRecord<String, String> record : records) {
				 System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
			 }
		}
		System.out.println("Done");
		consumer.close();
	}

}
