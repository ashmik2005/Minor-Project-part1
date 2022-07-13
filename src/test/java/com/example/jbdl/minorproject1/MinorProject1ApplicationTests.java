package com.example.jbdl.minorproject1;

import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Genre;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.repositories.TransactionRepository;
import com.example.jbdl.minorproject1.services.BookService;
import com.example.jbdl.minorproject1.services.StudentService;
import com.example.jbdl.minorproject1.services.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class MinorProject1ApplicationTests {


	private static final int studentId = 1;
	private static final int bookId = 1;

	// Mock: Not a real object but kind of dummy object

	// @Mock - Create a mock of transaction service and other dependent mocks will not be injected
	@InjectMocks // Create a real object of transaction service and inject all the dependant mocks
	TransactionService transactionService;

	@Mock
	StudentService studentService;

	@Mock
	BookService bookService;

	@Mock
	TransactionRepository transactionRepository;

	@Test
	public void testIssueBook_HappyCase() throws Exception {

		Student student = Student.builder()
				.id(studentId)
				.name("ABC")
				.age(23)
				.bookList(new ArrayList<>())
				.build();

		Book book = Book.builder()
				.id(bookId)
				.name("Intro to Java")
				.genre(Genre.SCIENCE)
				.build();

		Mockito.when(studentService.getStudentById(studentId)).thenReturn(student);
		Mockito.when(bookService.getBookById(bookId)).thenReturn(Arrays.asList(book));
		Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(null, null);
		Mockito.doNothing().when(bookService).insert(Mockito.any());

		transactionService.issueBook(bookId, studentId);

		// Assertion
		// Verifying how many times the functions have been called
		Mockito.verify(studentService, Mockito.times(1)).getStudentById(studentId);
		Mockito.verify(bookService, Mockito.times(1)).getBookById(bookId);
		Mockito.verify(transactionRepository, Mockito.times(2)).save(Mockito.any());
		Mockito.verify(bookService, Mockito.times(1)).insert(Mockito.any());

		/**
		 * Now, after writing the test case the logic in the main functions(if it is changed)
		 * has to first be adjusted in the test cases too.
		 * Otherwise the test cases will not pass
		 * */

	}

	@Test(expected = Exception.class)
	public void testIssueBook_FailureCase_QuotaReached() throws Exception{
		Student student = Student.builder()
				.id(studentId)
				.name("ABC")
				.age(23)
				.bookList(
						Arrays.asList(
								Book.builder().build(),
								Book.builder().build(),
								Book.builder().build(),
								Book.builder().build()
						)
				)
				.build();


		Mockito.when(studentService.getStudentById(studentId)).thenReturn(student);


		transactionService.issueBook(bookId, studentId);

		// Assertion
		// Verifying how many times the functions have been called
		Mockito.verify(studentService, Mockito.times(1)).getStudentById(studentId);
	}

	@Test(expected = Exception.class)
	public void testIssueBook_FailureCase_BookAlreadyAssigned() throws Exception{
		Student student = Student.builder()
				.id(studentId)
				.name("ABC")
				.age(23)
				.bookList(new ArrayList<>())
				.build();

		Book book = Book.builder()
				.id(bookId)
				.name("Intro to Java")
				.genre(Genre.SCIENCE)
				.student(
						Student.builder().build()
				)
				.build();

		Mockito.when(studentService.getStudentById(studentId)).thenReturn(student);
		Mockito.when(bookService.getBookById(bookId)).thenReturn(Arrays.asList(book));

		transactionService.issueBook(bookId, studentId);

		// Assertion
		// Verifying how many times the functions have been called
		Mockito.verify(studentService, Mockito.times(1)).getStudentById(studentId);
		Mockito.verify(bookService, Mockito.times(1)).getBookById(bookId);

	}

	@Test
	public void testReturnBook(){

	}
}
