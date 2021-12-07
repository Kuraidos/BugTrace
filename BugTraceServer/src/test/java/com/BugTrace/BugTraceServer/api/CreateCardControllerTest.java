package com.BugTrace.BugTraceServer.api;

import com.BugTrace.BugTraceServer.service.CreateCardService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CreateCardControllerTest {

    @Autowired
    private CreateCardService createCardService;
    private CreateCardController createCardController;
    @BeforeEach
    void setUp()
    {
        createCardController=new CreateCardController(createCardService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCard()
    {
        assertEquals(1,1);
    }
}