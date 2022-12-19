package com.library.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.payloads.ApiResponse;
import com.library.management.payloads.UserDto;
import com.library.management.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = null;

    UserDto userDto=null;
    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
        userDto=new UserDto();
        userDto.setId(1);
        userDto.setEmail("abc@gmail.com");
        userDto.setName("abc");
        userDto.setAbout("abc about");
        userDto.setPassword("abcPwd");

    }

    @Test
    void createUser() throws Exception {
        when(userService.createUser(userDto)).thenReturn(userDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/create")
                .content(objectMapper.writeValueAsString(userDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateUser() throws Exception {
        Integer userId=1;
        when(userService.updateUser(userDto,userId)).thenReturn(userDto);
        mockMvc.perform(put("/api/users/update/" + userId)
                .content(objectMapper.writeValueAsString(userDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        Integer userId=1;
        String message="Successfully deleted the user";
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage(message);
        apiResponse.setSuccess(true);
        when(userService.deleteUser(userId)).thenReturn(message);
       mockMvc.perform(delete("/api/users/delete/" + userId))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("message").value(message));
    }
}