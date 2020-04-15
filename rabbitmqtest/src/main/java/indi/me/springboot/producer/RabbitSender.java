package indi.me.springboot.producer;

import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/** 生产端 */
@Component
public class RabbitSender {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	final ConfirmCallback confirmCallback = new ConfirmCallback() {
		@Override
		public void confirm(CorrelationData correlationData, boolean ack, String casue) {
			// TODO Auto-generated method stub
			System.out.println("correlationData :" + correlationData);
			System.out.println("ack :" + ack);
			if (!ack) {
				System.out.println("异常处理");
			}
		}
	};

	final ReturnCallback returnCallback = new ReturnCallback() {

		@Override
		public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
				String exchange, String routingKey) {
			// TODO Auto-generated method stub
			System.out.println("message :" + message);
			System.out.println("replyCode :" + replyCode);
			System.out.println("replyText " + replyText);
			System.out.println("exchange " + exchange);
			System.out.println("routingKey " + routingKey);

		}
	};

	public void send(Object message, Map<String, Object> properties) throws Exception {
		MessageHeaders mhs = new MessageHeaders(properties);
		Message msg = MessageBuilder.createMessage(message, mhs);
		rabbitTemplate.setReturnCallback(returnCallback);
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.convertAndSend("exchange-1", "springboot.hello", msg);
	}

}
