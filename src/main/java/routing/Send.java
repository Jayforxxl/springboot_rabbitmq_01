package routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.ConnectionUtil;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/5/17 22:37
 */
public class Send {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机的时候,类型置位direct
        channel.exchangeDeclare("exchange_direct","direct");
        //交换机和队列之间通过key匹配后再传输
        channel.basicPublish("exchange_direct","routing_key1",null,"HelloWorld".getBytes());
        channel.close();
        connection.close();
    }
}
