package com.satish.workshop.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class PublishMsgs {

	Properties properties = new Properties();
	Producer<String, String> producer;

	public Producer<String, String> create(String brokers) {
		properties.put("bootstrap.servers", brokers);

		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("acks", "1");

		// how many times to retry when produce request fails?
		properties.put("retries", "3");
		properties.put("linger.ms", 5);

		producer = new KafkaProducer<String, String>(properties);
		return producer;
	}

	public void publishMsg(String topic, String msg) throws InterruptedException, ExecutionException {
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, msg);
		producer.send(record).get();
	}

	public void close() {
		producer.close();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		String brokers = (String) args[0];
//		String topic = (String) args[1];
//		String count = (String) args[2];
		
		String brokers = "localhost:9092";
		String topic = "test_topic";
		String count = "20";
		
		int msgCount = Integer.parseInt(count);

		PublishMsgs publishMsg = new PublishMsgs();
		publishMsg.create(brokers);
		System.out.println("Start pushing messages to topic " + topic);
		for (int i = 0; i < msgCount; i++) {
			publishMsg.publishMsg(topic, "Message " + i);
			Thread.sleep(500);
		}
		System.out.println("Done.");

		publishMsg.close();
	}

}
