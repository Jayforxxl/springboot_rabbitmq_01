package simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.ConnectionUtil;

/**
 * Description:
 *
 * @author ZhangJieChao
 * @version 1.0
 * @date 2020/5/16 14:58
 */
public class Send {

    public static void main(String[] args) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare("simple_queue",false,false,false,null);
            channel.basicPublish("","simple_queue",null,"hello 张界超".getBytes());
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
