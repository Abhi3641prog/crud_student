package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.StudentController;
import com.example.demo.model.Student;
import com.example.demo.services.StudentService;

@SpringBootTest
class CrudStudentApplicationTests {

	private MockMvc mockMvc;
	
	@InjectMocks
    private StudentController studentController;
	
	@Mock
    private StudentService studentService;
	
	 @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	    }
	
	@Test
	void contextLoads() {
	}

	
	@Test
    public void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(new Student(1L, "John",  "john@example.com", 100.0));

        Mockito.when(studentService.getAllData()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/student")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
	
	@Test
    public void testGetStudentById() throws Exception {
        Student student = new Student(1L, "John", "john@example.com", 100.0);

        Mockito.when(studentService.getStudentById(1L)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
	
	
	 @Test
	    public void testCreateStudent() throws Exception {
	        Student student = new Student(1L, "John", "john@example.com", 100.0);

	        Mockito.when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(student);

	        mockMvc.perform(MockMvcRequestBuilders.post("/student/save")
	                .content("{\"name\":\"John\",\"email\":\"john@example.com\",\"fee\":200}")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }
	 
	 
	 @Test
	    public void testUpdateStudent() throws Exception {
	        Student student = new Student(1L, "UpdatedName", "updated@example.com", 200.0);

	        Mockito.when(studentService.updateStudentData(1L, student)).thenReturn(student);

	        mockMvc.perform(MockMvcRequestBuilders.put("/student/update/1")
	                .content("{\"name\":\"UpdatedName\",\"email\":\"updated@example.com\",\"fee\":200.0}")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }

	 @Test
	 @Order(1)
	    public void testDeleteStudent() throws Exception {
	        Mockito.when(studentService.deleteStudentById(1L)).thenReturn(true);

	        mockMvc.perform(MockMvcRequestBuilders.delete("/student/delete/1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	    }
}
