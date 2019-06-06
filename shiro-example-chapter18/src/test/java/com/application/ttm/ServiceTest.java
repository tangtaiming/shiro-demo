package com.application.ttm;

import com.application.ttm.dao.ProductDao;
import com.application.ttm.entity.Authorize;
import com.application.ttm.entity.Product;
import com.application.ttm.entity.Resource;
import com.application.ttm.entity.User;
import com.application.ttm.service.AuthorizeService;
import com.application.ttm.service.DoubanMovieService;
import com.application.ttm.service.ResourceService;
import com.application.ttm.service.UserService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.weaver.Dump;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-28</p>
 * <p>@Version 1.0</p>
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml", "classpath:spring-mvc.xml"})
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizeService authorizeService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ResourceService resourceService;

    @Test
    public void testUpdate() {
//        User usr = userService.findOne(1L);
//        usr.setPassword("123456");
        userService.changePassword(1L, "123456");
    }

    @Autowired
    private DoubanMovieService doubanMovieService;

    @Ignore
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("4");
        user.setPassword("123456");
        user.setOrganizationId(-1L);
        userService.createUser(user);
    }

    @Ignore
    @Test
    public void testAuthoirze() {
        String defaultCreateDate = "YYYY-MM-dd HH:mm:ss";
        Authorize authorize = new Authorize();
        authorize.setUserId(1L);
        authorize.setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(defaultCreateDate)));
        authorizeService.authorize(authorize);
    }

    @Ignore
    @Test
    public void testCount() {
        System.out.println(doubanMovieService.count());
    }

//    @Ignore
    @Test
    public void testProductCreate() {
        Product product = new Product();
        product.setSku("HT120-M");
        product.setSpu("HT120");
        product.setTitle("乐玥桐迷彩短裤男夏天男士大码肥佬多口袋工装中裤个性嘻哈潮牌纯棉胖子沙滩裤 军绿色 31");
        product.setDescription("品牌： 乐玥桐 商品名称：乐玥桐迷彩短裤男夏天男士大码肥佬多口袋工装中裤个性嘻哈潮牌纯棉胖子沙滩裤 军绿色 31商品编号：29429884641店铺： 玥儿弯服饰专营店商品毛重：500.00g商品产地：中国大陆货号：307-2-2292裤型：沙滩裤风格：商务休闲厚度：常规颜色：灰色，绿色，蓝色图案：迷彩弹力：微弹适用场景：其它　尺码：其它主要材质：棉流行元素：简约裤门襟：其他裤长：六分裤腰型：中腰适用季节：夏季工艺：免烫处理适用人群：青少年上市时间：2018夏季");
        product.setStatus(0);
        product.setLength(0.1);
        product.setWidth(0.1);
        product.setHeight(0.1);
        product.setTotalWeight(500.00);
        product.setPrice(128.00);
        product.setCreator(-999L);
        String defaultCreateDate = "YYYY-MM-dd HH:mm:ss";
        product.setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(defaultCreateDate)));
//        System.out.println(product.toString());
        productDao.create(product);
        System.out.println(product.toString());
    }

    @Ignore
    @Test
    public void testProductUpdateSuccess() {
        Product product = productDao.findOne(1L);
        Assert.assertNotNull(product);
        System.out.println(product.toString());
        String defaultCreateDate = "YYYY-MM-dd HH:mm:ss";
        product.setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(defaultCreateDate)));
        Product update = productDao.update(product);
        Assert.assertNotNull(update);
        System.out.println(update.toString());
    }

    @Ignore
    @Test
    public void testProductsSuccess() {
        List<Product> products = productDao.findList(0, 20);
        products.forEach(System.out::println);
    }

    @Ignore
    @Test
    public void testProductCountSuccess() {
        int count = productDao.count();
        System.out.println("count: " + count);
    }

    @Ignore
    @Test
    public void testProductDeleteSuccess() {
        Product product = productDao.findOne(1L);
        Assert.assertNotNull(product);
        Boolean delete = productDao.delete(product);
        System.out.println("Delete: " + delete);
    }

    @Test
    public void testNavSuccess() {
        List<Resource> resources = resourceService.findUserMenus(1L);
        System.out.println(JsonUtils.toJson(resources));
    }

    @Test
    public void testNav2Success() {
        List<Resource> resources = resourceService.findAll();
        List<Resource> all = getDatas(1L, resources);
        System.out.println(JsonUtils.toJson(all));
    }

    public List<Resource> getDatas(Long id, List<Resource> all) {
        List<Resource> childResource = getChild(id, all);
        if (childResource.size() > 0) {
            for (Resource childResourceRow : childResource) {
                childResourceRow.setList(getDatas(childResourceRow.getId(), all));
            }
        }
        return childResource;
    }

    public List<Resource> getChild(Long id, List<Resource> all) {
        List<Resource> newChilds = new ArrayList<>();
        for (Resource resourceRow : all) {
            Long parentId = resourceRow.getParentId();
            if (parentId.equals(id)) {
                newChilds.add(resourceRow);
            }
        }
        return newChilds;
    }


}
