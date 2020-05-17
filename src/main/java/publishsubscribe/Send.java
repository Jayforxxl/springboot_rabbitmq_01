package publishsubscribe;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.ConnectionUtil;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/5/17 21:18
 */
public class Send {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机(分发)
        channel.exchangeDeclare("exchange_fanout","fanout");
        channel.basicPublish("exchange_fanout","",null,"Hello World".getBytes());
        channel.close();
        connection.close();
    }
}
