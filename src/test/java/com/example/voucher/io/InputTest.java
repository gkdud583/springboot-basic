package com.example.voucher.io;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import static com.example.voucher.exception.ErrorMessage.INVALID_INPUT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Input 인터페이스의")
public class InputTest {

	private final Input input = new ConsoleReader();
	private Field scanner;

	@Nested
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class getDiscountAmount메서드는 {

		@BeforeEach
		void 메서드_테스트를_위한_Scanner_설정() throws Exception {
			scanner = input.getClass().getDeclaredField("sc");
			scanner.setAccessible(true);
		}

		@Nested
		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
		class 정수로_변환_가능한_값이_주어진다면 {

			@BeforeEach
			public void 정수로_변환_가능한_값_설정() throws Exception {
				scanner.set(input, setMockInputStream("1000"));
			}

			@Test
			@DisplayName("정수_값을_반환한다")
			void 정수_값을_반환한다() {
				assertThat(input.getDiscountAmount()).isEqualTo(1000);
			}
		}

		@Nested
		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
		class 정수로_변환_가능하지_않은_값이_주어진다면 {

			@BeforeEach
			void 정수로_변환_가능하지_않은_값_설정() throws Exception {
				scanner.set(input, setMockInputStream("isNotIntType"));
			}

			@Test
			@DisplayName("예외를 던진다")
			void 예외를_던진다() {
				assertThatThrownBy(() -> input.getDiscountAmount())
						.isInstanceOf(IllegalArgumentException.class)
						.hasMessage(INVALID_INPUT.name());
			}
		}
	}
	private Scanner setMockInputStream(String input) {
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		return new Scanner(in);
	}
}