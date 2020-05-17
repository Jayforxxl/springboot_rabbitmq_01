package topic;

import com.rabbitmq.client.*;
import utils.ConnectionUtil;

import java.io.IOException;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/5/17 23:14
 */
public class Recv2 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel =connection.createChannel();
        channel.queueDeclare("queue_name_topic",false,false,false,null);
        channel.basicQos(1);
        channel.queueBind("queue_name_topic","exchange_topic","goods.update");
        channel.queueBind("queue_name_topic","exchange_topic","goods.add");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("消费者2收到消息"+msg);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume("queue_name_topic",false,consumer);
    }
}
