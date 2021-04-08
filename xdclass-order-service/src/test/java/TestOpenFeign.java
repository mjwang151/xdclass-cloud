import com.xdclass.OrderApplication;
import com.xdclass.service.VideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Application;

/**
 * @ClassName TestOpenFeign
 * @Author mjwang
 * @Date 2021/4/6 19:46
 * @Description TestOpenFeign
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= OrderApplication.class)
public class TestOpenFeign {

    @Autowired
    private VideoService videoService;

    @Test
    public void test1(){

        videoService.findById(1);

    }
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
