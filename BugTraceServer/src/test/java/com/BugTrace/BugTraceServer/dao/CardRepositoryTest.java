package com.BugTrace.BugTraceServer.dao;

import com.BugTrace.BugTraceServer.model.Card;
import com.BugTrace.BugTraceServer.model.Impact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CardRepositoryTest {
    @Autowired
    private CardRepository cardRepository;

    @AfterEach
    void tearDown()
    {
        cardRepository.deleteAll();
    }
    @Test
    void cardExist()
    {
        //UUID cardId, String title, String creator, String dateCreated, Impact impact
        UUID cardId = UUID.fromString("2d35b93d-cde4-4745-8d39-c8fa61b60a3a");
        String title = "testCard";
        String creator = "Kuraido";
        String dataCreated = java.time.LocalDate.now().toString();
        Impact impact = Impact.HIGH;
        Card testCard=new Card(cardId,title,creator,dataCreated,impact);

        cardRepository.save(testCard);
        boolean exist = cardRepository.existsById(cardId);

        assertThat(exist).isTrue();
    }

    @Test
    void cardDoesNotExist()
    {

        UUID cardId = UUID.fromString("2d35b93d-cde4-4745-8d39-c8fa61b60a3a");
        boolean exist = cardRepository.existsById(cardId);

        assertThat(exist).isFalse();
    }

}