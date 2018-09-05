package com.xiguo.www.group;

import com.xiguo.www.group.dto.TestEntityVo;
import com.xiguo.www.group.dto.UserDto;
import com.xiguo.www.group.entity.*;
import com.xiguo.www.group.repository.groupBuy.GroupBuyProductRepository;
import com.xiguo.www.group.repository.groupBuy.GroupBuyProductImageRepository;
import com.xiguo.www.group.repository.groupBuy.GroupBuyRepository;
import com.xiguo.www.group.repository.user.UserRepository;
import com.xiguo.www.group.service.dozer.BeanConvert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.*;

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


    @Test
    public void contextLoads() {
    }

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new TestRestJsonController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
//    @Transactional
    public void updateDb() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 15; i++) {

            User user = new User();
            user.setImageUrl("setImageUrl" + i);
            user.setNickName("setNickName" + i);
            user.setOpenId("setOpenId" + i);
            user.setWechatName("setWechatName" + i);
            long startuser = System.currentTimeMillis();
            userRepository.save(user);
            long enduser = System.currentTimeMillis();
            System.out.println("总耗时:"+(enduser - startuser));

            for (int j = 0; j < 2; j++) {
                System.out.println(user);
                GroupBuy groupBuy = new GroupBuy();
                groupBuy.setCanDistribution(false);
                groupBuy.setCanNoutoasiakas(true);
                groupBuy.setDescriptor("asd" + j);
                groupBuy.setStatus(0);
                groupBuy.setTitle("asd" + j);
                groupBuy.setTitle("asd" + j);
                groupBuy.setUser(user);
                long startgroupBuy = System.currentTimeMillis();
                groupBuyRepository.save(groupBuy);
                long endgroupBuy = System.currentTimeMillis();
                System.out.println("总耗时:"+(endgroupBuy - startgroupBuy));
            }

        }

        long end = System.currentTimeMillis();
        long date = start-end;
        System.out.println("总耗时:"+date);

//        userRepository.deleteById(281L);

//        long startQuery = System.currentTimeMillis();
//        List<user> all = userRepository.findAll();
//        for (user user : all) {
//            System.out.println(user.getImageUrl());
//            for (GroupBuyDto groupBuy : user.getGroupBuys()) {
//                System.out.println(groupBuy.getId());
//            }
//        }
//        long endQuery = System.currentTimeMillis();
//        System.out.println("总耗时:"+(endQuery - startQuery));

//        Date date = new Date();
//        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
//        String formattedDate = dateFormat.format(date);
//
////        userRepository.save(new user("bb1", "aa123456","aa@126.com", "aa", formattedDate));
////        userRepository.save(new user("bb2", "bb123456","bb@126.com", "bb", formattedDate));
////        userRepository.save(new user("cc3", "cc123456","cc@126.com", "cc", formattedDate));
//        System.out.println(userRepository.findAll().size());
//        // 测试的时候才有效的
////        assert 9 == userRepository.findAll().size(): "被断言后才会抛出去的信息";
////        Assert.assertEquals(9, userRepository.findAll().size());
////        Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
//        userRepository.delete(userRepository.findByUserName("aa1"));
    }

    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void testOneToMand() {
        GroupBuyProduct groupBuyProduct = new GroupBuyProduct();
        groupBuyProduct.setName("测试商品1");

        GroupBuyProductImage image = new GroupBuyProductImage();
        image.setGroupBuyProduct(groupBuyProduct);
        image.setUrl("123456");

        GroupBuyProductImage image2 = new GroupBuyProductImage();
        image2.setGroupBuyProduct(groupBuyProduct);
        image2.setUrl("asd");

        GroupBuyProductImage image3 = new GroupBuyProductImage();
        image3.setGroupBuyProduct(groupBuyProduct);
        image3.setUrl("1asdf6");


        GroupBuyProduct groupBuyProduct2 = new GroupBuyProduct();
        groupBuyProduct2.setName("测试商品1");

        GroupBuyProductImage imagex = new GroupBuyProductImage();
        imagex.setGroupBuyProduct(groupBuyProduct2);
        imagex.setUrl("123456");

        GroupBuyProductImage image2x = new GroupBuyProductImage();
        image2x.setGroupBuyProduct(groupBuyProduct2);
        image2x.setUrl("asd");

        GroupBuyProductImage image3x = new GroupBuyProductImage();
        image3x.setGroupBuyProduct(groupBuyProduct2);
        image3x.setUrl("1asdf6");

        Set<GroupBuyProductImage> images = groupBuyProduct.getGroupBuyProductImages();
        images.add(image);
        images.add(image2);
        images.add(image3);


        Set<GroupBuyProductImage> images2 = groupBuyProduct2.getGroupBuyProductImages();
        images2.add(imagex);
        images2.add(image2x);
        images2.add(image3x);

        List<GroupBuyProduct> groupBuyProductd = new ArrayList<>();
        boolean add = groupBuyProductd.add(groupBuyProduct);
        add = groupBuyProductd.add(groupBuyProduct2);

        groupBuyProductRepository.saveAll(groupBuyProductd);

    }

    @Autowired
    BeanConvert beanConvert;

    @Test
    public void beanConvertTest() {
        User user = new User();
        user.setNickName("ceshi nickname");
        user.setWechatName("ceshi WechatNam");
        user.setCountry("ceshi setCountry");

        Order order = new Order();
        order.setAddress("asdfasdfasdfsadfasdfadfasdfadfasdf");
        HashSet<Order> orders = new HashSet<>();
        orders.add(order);

        GroupBuy groupBuy = new GroupBuy();
        groupBuy.setTitle("asdfasdf");
        groupBuy.setStatus(1);
        groupBuy.setUser(user);
        groupBuy.setOrders(orders);

        HashSet<GroupBuy> groupBuys = new HashSet<>();
        groupBuys.add(groupBuy);
        user.setGroupBuys(groupBuys);
        UserDto convert = beanConvert.convert(user, UserDto.class);
        System.out.println(convert);
    }


    @Test
    public void test() {
        TestEntity testEntity = new TestEntity();
        testEntity.setMyProperty("testEntity.MyProperty");
        testEntity.setTitle("testEntity.title");
        testEntity.setPassword("6asd5f46asd8f746a8df74");
        System.out.println(testEntity);
        TestEntityVo convert = beanConvert.convert(testEntity, TestEntityVo.class);
        System.out.println(convert);
    }

    @Test
    public void test2() {
        // 验证jpa是否可以嵌套保存,  结果: 可以

        // 1
        GroupBuy groupBuy = new GroupBuy();
        groupBuy.setTitle("qwe");

        // 2
        GroupBuyProduct groupBuyProduct = new GroupBuyProduct();
        groupBuyProduct.setName("qwe");

        // 3
        GroupBuyProductImage image = new GroupBuyProductImage();
        image.setUrl("6asdf749");

        groupBuy.setUser(new User(1L));

        // 2 -> 1
        groupBuyProduct.setGroupBuy(groupBuy);
        // 1 -> 2
        HashSet<GroupBuyProduct> groupBuyProducts = new HashSet<>();
        groupBuyProducts.add(groupBuyProduct);
        groupBuy.setGroupBuyProducts(groupBuyProducts);
//        groupBuy.getGroupBuyProducts().add(groupBuyProduct);

        // 3 -> 2
        image.setGroupBuyProduct(groupBuyProduct);
        // 2 -> 3
        HashSet<GroupBuyProductImage> groupBuyProductImage = new HashSet<>();
        groupBuyProductImage.add(image);
        groupBuyProduct.setGroupBuyProductImages(groupBuyProductImage);
//        groupBuyProduct.getGroupBuyProductImages().add(image);

        // 1 <-> 2 <-> 3


        System.out.println(groupBuy);

        groupBuyRepository.save(groupBuy);

    }

    /**
     * 覆盖试验
     * a = [b,c,d]
     *
     * a <- [c,d]
     *
     * a = [c,d]
     *
     * a <- [e]
     *
     * a = e
     */
    @Test
    public void cover() {
//        GroupBuy groupBuy = new GroupBuy();
//        groupBuy.setTitle("coverObject");
//        groupBuy.setId(56L);
//        groupBuy.setStatus(0);
//        groupBuy.setUser(new User(1L));



//        GroupBuyProduct groupBuyProduct = new GroupBuyProduct();
//        groupBuyProduct.setName("coverItem");
//        groupBuyProduct.setPrice(new BigDecimal(156.45));
//        groupBuyProduct.setGroupBuy(groupBuy);
//        groupBuyProduct.setId(53L);

//        GroupBuyProduct groupBuyProduct2 = new GroupBuyProduct();
//        groupBuyProduct2.setName("coverItem2");
//        groupBuyProduct2.setGroupBuy(groupBuy);
//        groupBuyProduct2.setId(54L);


//        Set<GroupBuyProduct> groupBuyProducts = groupBuy.getGroupBuyProducts();
//        groupBuyProducts.add(groupBuyProduct);
//        groupBuyProducts.add(groupBuyProduct2);

        GroupBuy groupBuy = groupBuyRepository.findById(56L).get();

//        GroupBuyProduct byId1 = groupBuyProductRepository.findById(53L).get();
        GroupBuyProduct groupBuyProduct = new GroupBuyProduct();
        groupBuyProduct.setName("coverItem");
        groupBuyProduct.setPrice(new BigDecimal(156.45));
        groupBuyProduct.setGroupBuy(groupBuy);
        groupBuyProduct.setId(56L);
        HashSet<GroupBuyProduct> objects = new HashSet<>();
        objects.add(groupBuyProduct);
        groupBuyProductRepository.deleteAll(groupBuy.getGroupBuyProducts());
        groupBuy.setGroupBuyProducts(objects);
        GroupBuy save = groupBuyRepository.save(groupBuy);
    }



}
