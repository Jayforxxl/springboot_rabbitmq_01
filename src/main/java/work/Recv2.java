package work;

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
public class Recv2 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work_queue",false,false,false,null);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("第二个消费者消费:"+msg);
                try {
                    //模拟消费者消费消息之后的业务处理时间
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("第二个消费者 done!");
                }
            }
        };
        channel.basicConsume("work_queue",true,consumer);
    }
}
