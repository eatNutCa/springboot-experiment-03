package com.experiments.springbootexperiment03;

import com.experiments.springbootexperiment03.Repository.AddressRepository;
import com.experiments.springbootexperiment03.Repository.UserAddressRepository;
import com.experiments.springbootexperiment03.Repository.UserRepository;
import com.experiments.springbootexperiment03.entity.Address;
import com.experiments.springbootexperiment03.entity.User;
import com.experiments.springbootexperiment03.entity.UserAddress;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootExperiment03ApplicationTests {
@Autowired
private UserRepository userRepository;
@Autowired
private AddressRepository addressRepository;
@Autowired
private UserAddressRepository userAddressRepository;
    @Test
    public void contextLoads() {
    }
@Test
public void init() {
    User user = new User("testUser2");
    Address address = new Address("testAddress2");
    UserAddress userAddress=new UserAddress(user,address);
 userRepository.save(user);
 addressRepository.save(address);
 userAddressRepository.save(userAddress);
}
    @Test
    public void userRepTest() {
        User u = userRepository.find(1);
        log.debug(u.getName());
    }
    @Test
    public void addressRepTest() {
        addressRepository.list("testAddress2")
                .forEach(a -> {
                    log.debug("{}", a.getId());
                });
    }

    @Test
    public void userAddressRepTest() {
       userAddressRepository.list("testUser","testAddress").forEach(a->{log.debug(("{}"),a.getInsertTime());});
    }

    @Test
    public void test4() throws ParseException {
        //(5)题1：格式化当前时间：年-月-日 时(24):分:秒
        //TODO 题1代码写在此处
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.format(d);
        //(5)题2：获取2020-10-30 23:59:15的时间戳(毫秒数)
        //TODO 题2代码写在此处
        Date d1 = sdf.parse("2020-10-30 23:59:15");
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        long times= cal.getTimeInMillis();
        System.out.print(times);
    }


    @Data //set get
    @Accessors(chain = true) //链式编程
    private static class Menu {
        private String code;
        private String name;
        private String parentCode;//二级菜单parentCode=父菜单(一级菜单)的code属性。一级菜单parentCode=null
        private List<Menu> subMenus = new ArrayList<>();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Menu menu = (Menu) o;
            return Objects.equals(code, menu.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }
    }
    private List<Menu> data1() {
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu().setCode("m_01").setName("一级菜单01"));
        menus.add(new Menu().setCode("m_01_01").setName("二级菜单01_01").setParentCode("m_01"));
        menus.add(new Menu().setCode("m_01_02").setName("二级菜单01_02").setParentCode("m_01"));
        menus.add(new Menu().setCode("m_01_03").setName("二级菜单01_02").setParentCode("m_01"));
        menus.add(new Menu().setCode("m_02").setName("一级菜单02"));
        menus.add(new Menu().setCode("m_02_01").setName("二级菜单02_01").setParentCode("m_02"));
        return menus;
    }
    private List<Menu> data2() {
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu().setCode("m_02").setName("一级菜单02"));
        menus.add(new Menu().setCode("m_02_01").setName("二级菜单02_01").setParentCode("m_02"));
        menus.add(new Menu().setCode("m_03").setName("一级菜单03"));
        menus.add(new Menu().setCode("m_03_01").setName("二级菜单03_01").setParentCode("m_03"));
        menus.add(new Menu().setCode("m_03_02").setName("二级菜单03_02").setParentCode("m_03"));
        return menus;
    }

    @Test
    public void test1(){
        List<Menu> menus = data1();
        List<Menu> menus1 = new ArrayList<>();
        List<Menu> menus2 = new ArrayList<>();
        //(10)题3. 请将集合menus中的一级菜单数据放入集合menus1中,二级菜单数据放入menus2中
        //TODO 题3代码写在此处
        Iterator<Menu> menuIterator=menus.iterator();
        while (menuIterator.hasNext()){
            Menu menu=menuIterator.next();
            if(menu.getParentCode()==null) {
                menus1.add(menu);
            }
            if(menu.getParentCode()!=null){
                menus2.add(menu);
            }
        }
        //(20)题4. 请将对应的二级菜单放入到对应的一级菜单的subMenus属性中。提示：二级菜单的parentCode是父菜单(一级菜单)的code属性
        //TODO 题4代码写在此处
        Iterator<Menu> menuIterator1=menus1.iterator();
        while(menuIterator1.hasNext()){
            Menu menu=menuIterator1.next();
            Iterator<Menu> menuIterator2=menus2.iterator();
            while (menuIterator2.hasNext()){
                Menu menu1=menuIterator2.next();
                if(menu1.getParentCode().equals(menu.getCode())){
                    menu.getSubMenus().add(menu1);
                }
            }
        }
        System.out.println("ok");
    }
    @Test
    public void test3(){
        List<Menu> data1 = data1();
        List<Menu> data2 = data2();
        //(20)题5：请将集合data1,data2的数据合并到集合result中。并将重复的数据去除。提示：code相同集合判定为重复数据。
        //TODO 题5代码写在此处
        Set<Menu> result=new HashSet<>(data1);
        Set<Menu> data2s= new HashSet<>(data2);
        result.addAll(data2s);
        System.out.println("ok");
}


    /*public String post(Map param) {
        System.out.println("http请求中...");
        return "请求结果";
    }*/

    //(30)附加题：将以上post方法进行异步方法封装，要求调用方式时设置异步处理请求结果的方式
    /*@Async
    public String post(Map param) {
        synchronized {
            System.out.println("http请求中...");
        }
        return "请求结果";
    }*/
}
