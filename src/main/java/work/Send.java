package work;

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
        channel.queueDeclare("work_queue",false,false,false,null);
        for (int i = 0;i < 50;i++ ){
            String msg = "我是第"+i+"个消息!";
            channel.basicPublish("","work_queue",null,msg.getBytes());
            Thread.sleep(i * 20);
        }
        channel.close();
        connection.close();
    }
}
