package publishsubscribe;

import com.rabbitmq.client.*;
import utils.ConnectionUtil;

import java.io.IOException;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/5/17 21:35
 */
public class Recv2 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        //队列声明
        channel.queueDeclare("queues_fanout_2",false,false,false,null);
        //绑定队列到交换机/转发器
        channel.queueBind("queues_fanout_2","exchange_fanout","");
        channel.basicQos(1);
        Consumer consumer =  new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                try {
                    Thread.sleep(1000);
                    System.out.println("第二个消费者消费消息："+msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        channel.basicConsume("queues_fanout_2",false,consumer);
    }
}
