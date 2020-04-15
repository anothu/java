package rabbitmqtest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import indi.me.springboot.producer.RabbitSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class springTest {
	@Autowired
	private RabbitSender rabbitSender;

	@Test
	public void contextLoads() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("number", "12345");
		properties.put("send_time", new Date());
		try {
			rabbitSender.send("hello rabbitmq springboot ", properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
