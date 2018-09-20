package  com.netty.cache;

import java.util.List;

import javax.jms.Queue;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.netty.model.PlayMessage;
import com.netty.service.ReportStatusService;



/**
 * 定义生产者
 * 
 * @author 
 * 
 */
@Service
public class Producer {
private static Producer self  ;
	
	private Producer() {
		self = this;
	}
	
	public static Producer getInstance() {
		return self;
	}
	
	
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private Queue que;
	
	@Autowired
	private Queue que1;
	
	@Autowired
	private Queue que2;

	@Autowired
	private Topic topic;

	/**
	 * 每5S执行一次
	 */
	//@Scheduled(fixedRate = 5000, initialDelay = 3000)
	public void send(String playMessage) {
		// 发送队列消息
		
		this.jmsMessagingTemplate.convertAndSend(this.que, playMessage);
		
		// 发送订阅消息
		/*this.jmsMessagingTemplate.convertAndSend(this.topic, "生产者辛苦生产的订阅/发布消息成果");
		
		System.out.println("生产者：辛苦生产的订阅/发布消息成果");*/
	}
	public void sendPlayTime(String playMessage) {
		// 发送队列消息
		
		this.jmsMessagingTemplate.convertAndSend(this.que1, playMessage);
		
		// 发送订阅消息
		/*this.jmsMessagingTemplate.convertAndSend(this.topic, "生产者辛苦生产的订阅/发布消息成果");
		
		System.out.println("生产者：辛苦生产的订阅/发布消息成果");*/
	}
	
	public void sendPlay(String playMessage) {
		// 发送队列消息
		
		this.jmsMessagingTemplate.convertAndSend(this.que2, playMessage);
		
		// 发送订阅消息
		/*this.jmsMessagingTemplate.convertAndSend(this.topic, "生产者辛苦生产的订阅/发布消息成果");
		
		System.out.println("生产者：辛苦生产的订阅/发布消息成果");*/
	}
}