package com.emiyaconsulting.dramapopapi;

import com.emiyaconsulting.dramapopapi.controller.DramaController;
import com.emiyaconsulting.dramapopapi.model.Drama;
import com.emiyaconsulting.dramapopapi.service.DramaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DramaController.class)
public class DramapopApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DramaService dramaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveDrama_returnsCreatedAndBody() throws Exception {
        // sample Drama - adjust fields to match your Drama model if needed
        Drama sample = new Drama();
        // If your Drama has setters like setId, setTitle, setYear, set whatever, set them here:
        sample.setId(123456L);
        sample.setTitle("Sample Drama");
        sample.setYear(2025);

        Drama saved = new Drama();
        saved.setId(789012L);
        saved.setTitle("Sample Drama 2");
        saved.setYear(2025);

        when(dramaService.saveDrama(any(Drama.class))).thenReturn(saved);

        mockMvc.perform(post("/drama")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sample)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(saved)));

        verify(dramaService).saveDrama(any(Drama.class));
    }

    @Test
    void getDramas_returnsOkAndList() throws Exception {
        Drama d = new Drama();
        d.setId(1L);
        d.setTitle("ListSample");

        List<Drama> list = List.of(d);
        when(dramaService.getDramas()).thenReturn(list);

        mockMvc.perform(get("/dramas"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));

        verify(dramaService).getDramas();
    }
}