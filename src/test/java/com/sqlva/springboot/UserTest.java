//package com.sqlva.springboot;
//
//import com.sqlva.springboot.controller.MainController;
//import com.sqlva.springboot.controller.UserController;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.hamcrest.core.IsEqual.equalTo;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserTest {
//    private MockMvc mvc;
//    @Autowired
//    private UserController userController;
//    @Autowired
//    private UserService userSerivce;
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(userController).build();
//        // 准备，清空user表
//        userSerivce.deleteAllUsers();
//    }
//   @Test
//   public void testUserJDBC(){
//// 插入5个用户
//       userSerivce.create("a", 1);
//       userSerivce.create("b", 2);
//       userSerivce.create("c", 3);
//       userSerivce.create("d", 4);
//       userSerivce.create("e", 5);
//
//       // 查数据库，应该有5个用户
//       Assert.assertEquals(5, userSerivce.getAllUsers().intValue());
//
//       // 删除两个用户
//       userSerivce.deleteByName("a");
//       userSerivce.deleteByName("e");
//
//       // 查数据库，应该有5个用户
//       Assert.assertEquals(3, userSerivce.getAllUsers().intValue());
//   }
//    @Test
//    public void testUserController() throws Exception {
//        RequestBuilder requestBuilder = null;
//
//        requestBuilder = get("/users/");
//        mvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("[]")));
//
//        requestBuilder = post("/users/")
//                .param("id", "1")
//                .param("name", "qiang")
//                .param("age", "18");
//        mvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("success")));
//
//        requestBuilder = get("/users/");
//        mvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"qiang\",\"age\":18}]")));
//
//        // 4、put修改id为1的user
//        requestBuilder = put("/users/1")
//                .param("name", "测试终极大师")
//                .param("age", "30");
//        mvc.perform(requestBuilder)
//                .andExpect(content().string(equalTo("success")));
//
//        requestBuilder = get("/users/");
//        mvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}]")));
//
//        requestBuilder = delete("/users/1");
//        mvc.perform(requestBuilder)
//                .andExpect(content().string(equalTo("success")));
//
//        requestBuilder = get("/users/");
//        mvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("[]")));
//
//    }
//}
