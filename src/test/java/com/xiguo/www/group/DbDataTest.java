package com.xiguo.www.group;

import com.xiguo.www.group.dto.GroupBuyDto;
import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.GroupBuyProduct;
import com.xiguo.www.group.entity.TestEntity;
import com.xiguo.www.group.repository.TestEntityRepository;
import com.xiguo.www.group.repository.groupBuy.GroupBuyNoutoasiakasRepository;
import com.xiguo.www.group.repository.groupBuy.GroupBuyProductImageRepository;
import com.xiguo.www.group.repository.groupBuy.GroupBuyProductRepository;
import com.xiguo.www.group.repository.groupBuy.GroupBuyRepository;
import com.xiguo.www.group.repository.order.OrderProductRepository;
import com.xiguo.www.group.repository.order.OrderRepository;
import com.xiguo.www.group.repository.user.*;
import com.xiguo.www.group.service.dozer.BeanConvert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbDataTest {
    @Autowired
    private GroupBuyNoutoasiakasRepository groupBuyNoutoasiakasRepository;
    @Autowired
    private GroupBuyProductRepository groupBuyProductRepository;
    @Autowired
    private GroupBuyProductImageRepository groupBuyProductImagesRepository;
    @Autowired
    private GroupBuyRepository groupBuyRepository;
    @Autowired
    private NoutoasiakasRepository noutoasiakasRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserCareAboutGroupBuyRepository userCareAboutGroupBuyRepository;
    @Autowired
    private UserCustomerDefaultSettingRepository userCustomerDefaultSettingRepository;
    @Autowired
    private UserMerchantDefaultSettingRepository userMerchantDefaultSettingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserShopRepository userShopRepository;
    private MockMvc mvc;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new TestRestJsonController()).build();
    }

    @Test
    public void createNewData() {
        GroupBuy groupBuy = new GroupBuy();
        groupBuy.setId(2L);
        groupBuy.setTitle("更改后的标题");
        groupBuyRepository.save(groupBuy);
    }

    @Autowired
    TestEntityRepository testEntityRepository;

    @Test
    public void save () {
        TestEntity testEntity = new TestEntity();
        testEntity.setTitle("测试标题");
        testEntity.setMyProperty("测试参数");
        testEntity.setPassword("测试密码");
        TestEntity save = testEntityRepository.save(testEntity);
        System.out.println(save);
    }

    @Test
    public void saveTransient () {
       try {
           Optional<GroupBuy> byId = groupBuyRepository.findById(50L);
           GroupBuy groupBuy = byId.get();
           GroupBuyProduct groupBuyProduct = new GroupBuyProduct();
           groupBuyProduct.setName("asdf");
           groupBuyProduct.setGroupBuy(groupBuy);
           groupBuy.getGroupBuyProducts().add(groupBuyProduct);
           groupBuyRepository.save(groupBuy);
       }catch (Exception e) {
           e.printStackTrace();
       }

    }

    @Test
    public void update () {
        TestEntity testEntity = new TestEntity();
        testEntity.setTitle("标题");
        testEntity.setMyProperty("参数");
        testEntity.setPassword("密码");
        testEntity.setId(1L);
        TestEntity save = testEntityRepository.save(testEntity);
        System.out.println(save);
    }

    @Test
    public void delete () {
        // 游离态 删除
        TestEntity testEntity = new TestEntity();
        testEntity.setId(2L);
        testEntityRepository.delete(testEntity);
    }

    @Autowired
    EntityManager em;

    @Autowired
    BeanConvert bc;

    @Test
    public void query () {
        // id查
        System.out.println(testEntityRepository.findById(2L));
    }
}
