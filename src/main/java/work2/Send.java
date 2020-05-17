package work2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.ConnectionUtil;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/5/16 16:43
 */
public class Send {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work_queue2",false,false,false,null);
        /**
         *  每个消费者发送确认消息之前,消息队列不发送下一个消息到消费者，一次只处理一个消息
         *  限制发送给同一个消费者不得超过一条消息
         * */
        channel.basicQos(1);
        for (int i = 0;i < 50;i++ ){
            String msg = "我是第"+i+"个消息!";
            channel.basicPublish("","work_queue2",null,msg.getBytes());
            Thread.sleep(i * 20);
        }
        channel.close();
        connection.close();
    }
}
