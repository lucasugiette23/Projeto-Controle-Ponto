package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Ponto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Bater ponto - Controller")
@RunWith(SpringRunner.class)
public class PointControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test()
    @DisplayName("Deve ser concluído com sucesso")
    public void test() throws Exception {
        this.mockMvc.perform(get("/ponto/bater-ponto/1/FIRSTOUTYPE")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(ResponseEntity.status(HttpStatus.CREATED))))
                .andExpect((ResultMatcher) ResponseEntity.status(HttpStatus.CREATED));
    }

}
