package com.BugTrace.BugTraceServer.api;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Test must run after the other , as data is dependent on previous test
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BigIntegrationTest
{

    @Autowired
    MockMvc mockMvc;

    private static UUID teamId;
    private String username = "Kuraido";
    private String email= "serelisltu@gmail.com";
    private String password="123123";


    @Test
    @Order(1)
    void register() throws Exception {

        JSONObject user = new JSONObject();
        user.put("username",username);
        user.put("email",email);
        user.put("password",password);

        MvcResult response = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user.toString())).andExpect(status().isOk()).andReturn();
        String content = response.getResponse().getContentAsString();
        assertEquals("1",content);
    }

    @Test
    @Order(2)
    void  login() throws Exception {
        JSONObject user = new JSONObject();
        user.put("email",email);
        user.put("password",password);

        MvcResult response = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user.toString())).andExpect(status().isOk()).andReturn();

        String content = response.getResponse().getContentAsString();
        JSONObject result = new JSONObject(content);
        teamId=UUID.fromString(result.get("teamId").toString());
        assertEquals(username,result.get("username"));
        assertEquals(password,result.get("password"));
        assertEquals(email,result.get("email"));

    }

    @Test
    @Order(3)
    void  mainPageDataTest() throws Exception {
        JSONObject user = new JSONObject();
        user.put("email","serelisltu@gmail.com");
        user.put("password","123123");
        user.put("teamId",teamId.toString());

        MvcResult response = mockMvc.perform(post("/app")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user.toString())).andExpect(status().isOk()).andReturn();

        String content = response.getResponse().getContentAsString();
        JSONObject result = new JSONObject(content);
        assertTrue(result.get("teamMembers").toString().contains("LEADER"));
    }

}