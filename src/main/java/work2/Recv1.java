package work2;

import com.rabbitmq.client.*;
import utils.ConnectionUtil;

import java.io.IOException;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/5/17 13:02
 */
public class Recv1 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("work_queue2",false,false,false,null);
        channel.basicQos(1);//保证一次只分发一个
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("第一个消费者消费:"+msg);
                try {
                    //模拟消费者消费消息之后的业务处理时间
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("第一个消费者消费完消息!");
                    channel.basicAck(envelope.getDeliveryTag(),false);//手动回值,告知消息队列已处理完消息,可以发送下一条消息了
                }
            }
        };
        //改成手动应答
        channel.basicConsume("work_queue2",false,consumer);


    }

}
