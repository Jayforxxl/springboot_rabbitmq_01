package simple;

import com.rabbitmq.client.*;
import utils.ConnectionUtil;

import java.io.IOException;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/5/16 15:23
 */
public class Recv {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //队列声明
        channel.queueDeclare("simple_queue",false,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println(msg);
            }
        };
        //监听队列
        channel.basicConsume("simple_queue",true,consumer);
    }


    public void oldMethod()  throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume("simple_queue",true,consumer);
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            java.lang.String msg = new String(delivery.getBody());
            System.out.println(msg);
        }
    }

}
