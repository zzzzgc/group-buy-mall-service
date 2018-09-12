package com.xiguo.www.group;

import com.xiguo.www.group.repository.groupBuy.GroupBuyRepository;
import com.xiguo.www.group.repository.product.GroupBuyProductImageRepository;
import com.xiguo.www.group.repository.product.GroupBuyProductRepository;
import com.xiguo.www.group.repository.user.UserRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class GroupApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupBuyRepository groupBuyRepository;

    @Autowired
    private GroupBuyProductRepository groupBuyProductRepository;

    @Autowired
    private GroupBuyProductImageRepository groupBuyProductsImageRepository;


    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new TestRestJsonController()).build();
    }

}
