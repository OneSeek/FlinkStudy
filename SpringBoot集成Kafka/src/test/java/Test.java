import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Arrays;
import java.util.Set;

public class Test {
    @Autowired // adminClien需要自己生成配置bean
    private AdminClient adminClient;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test//自定义手动创建topic和分区
    public void testCreateTopic() throws InterruptedException {
        // 这种是手动创建 //10个分区，一个副本
        // 分区多的好处是能快速的处理并发量，但是也要根据机器的配置
        NewTopic topic = new NewTopic("topic.manual.create", 10, (short) 1);
        adminClient.createTopics(Arrays.asList(topic));
        Thread.sleep(1000);
    }


    /**
     * 获取所有的topic
     * @throws Exception
     */
    @Test
    public void getAllTopic() throws Exception {
        ListTopicsResult listTopics = adminClient.listTopics();
        Set<String> topics = listTopics.names().get();

        for (String topic : topics) {
            System.err.println(topic);

        }
    }
}
