package com.xiguo.www.group;

import com.xiguo.www.group.repository.groupBuy.GroupBuyRepository;
import com.xiguo.www.group.repository.product.GroupBuyProductImageRepository;
import com.xiguo.www.group.repository.product.GroupBuyProductRepository;
import com.xiguo.www.group.repository.user.UserRepository;
import com.xiguo.www.group.service.dozer.BeanConvert;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
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

    @PersistenceContext
    EntityManager em;

    @Autowired
    BeanConvert beanConvert;

    @Test
    public void stestsdfsdf() {
        Query nativeQuery = em.createNativeQuery("SELECT " +
                "	gb.id, " +
                "	gb.title, " +
                "gb.create_at, " +
                "	vtpn.totalParticipantNumber, " +
                "	vtpsn.totalSellNumber, " +
                "	vtpsn.totalSellPrice, " +
                "	vtpsn.totalSellPrice / vtpn.totalParticipantNumber as averageConsumePrice " +
                "FROM " +
                "	`user` u " +
                "JOIN group_buy gb ON gb.user_id = u.id, " +
                " v_group_buy_total_participant_number vtpn, " +
                " v_group_buy_total_product_sell_info vtpsn ");
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List resultList = nativeQuery.getResultList();
        System.out.println(resultList.get(0));
    }

}
