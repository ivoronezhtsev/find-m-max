package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class QuickSelectServiceTest {
	@Mock
	private ExcelNumbersReader excelNumbersReader;

	@InjectMocks
	private QuickSelectService quickSelectService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindNthMax_ReturnsCorrectValue() {
		String filePath = "dummy/path";
		int[] numbers = {10, 20, 30, 40, 50, 60, 70}; // 70, 60, 50, 40, 30, 20, 10

		// Mocking the behavior of the ExcelNumbersReader
		when(excelNumbersReader.readNumbers(filePath)).thenReturn(numbers);

		// Test for the 1st max (largest element)
		int result = quickSelectService.findNthMax(filePath, 1);
		assertEquals(70, result);

		// Test for the 2nd max
		result = quickSelectService.findNthMax(filePath, 2);
		assertEquals(60, result);

		// Test for the 3rd max
		result = quickSelectService.findNthMax(filePath, 3);
		assertEquals(50, result);

		// Test for the 4th max
		result = quickSelectService.findNthMax(filePath, 4);
		assertEquals(40, result);

		// Test for the 5th max
		result = quickSelectService.findNthMax(filePath, 5);
		assertEquals(30, result);

		// Test for the 6th max
		result = quickSelectService.findNthMax(filePath, 6);
		assertEquals(20, result);

		// Test for the 7th max
		result = quickSelectService.findNthMax(filePath, 7);
		assertEquals(10, result);
	}

	@Test
	public void testFindNthMax_ThrowsException_WhenNIsGreaterThanArraySize() {
		String filePath = "dummy/path";
		int[] numbers = {5, 15, 25, 35, 45}; // 45, 35, 25, 15, 5
		when(excelNumbersReader.readNumbers(filePath)).thenReturn(numbers);

		// Test for an invalid case where n is greater than the size of the array
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			quickSelectService.findNthMax(filePath, 6);
		});
		assertEquals("N must be between 1 and the size of the array.", exception.getMessage());
	}

	@Test
	public void testFindNthMax_ThrowsException_WhenNIsNegative() {
		String filePath = "dummy/path";
		int[] numbers = {100, 200, 300}; // 300, 200, 100
		when(excelNumbersReader.readNumbers(filePath)).thenReturn(numbers);

		// Test for an invalid case where n is negative
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			quickSelectService.findNthMax(filePath, -1);
		});
		assertEquals("N must be between 1 and the size of the array.", exception.getMessage());
	}

	@Test
	public void testFindNthMax_ThrowsException_WhenArrayIsEmpty() {
		String filePath = "dummy/path";
		int[] numbers = {}; // Empty array
		when(excelNumbersReader.readNumbers(filePath)).thenReturn(numbers);

		// Test for an invalid case where the array is empty
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			quickSelectService.findNthMax(filePath, 1);
		});
		assertEquals("N must be between 1 and the size of the array.", exception.getMessage());
	}
}