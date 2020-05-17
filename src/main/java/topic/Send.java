package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.ConnectionUtil;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/5/17 23:14
 */
public class Send {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("exchange_topic","topic");
        channel.basicPublish("exchange_topic","goods.add",null,"GoodMessage...".getBytes());
        channel.close();
        connection.close();
    }
}
