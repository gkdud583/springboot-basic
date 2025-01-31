package com.example.voucher.domain.voucher;

import org.junit.jupiter.api.*;
import static com.example.voucher.exception.ErrorMessage.INVALID_INPUT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("PercentDiscountVoucher 클래스의")
public class PercentDiscountVoucherTest {

	@Nested
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class 생성자는 {

		@Nested
		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
		class 음수_할인_값이_넘어오면 {

			@Test
			@DisplayName("IllegalArgumentException 예외를 던진다")
			void IllegalArgumentException_예외를_던진다() {
				assertThatThrownBy(() -> new PercentDiscountVoucher(null, -10))
						.isInstanceOf(IllegalArgumentException.class)
						.hasMessage(INVALID_INPUT.getMessage());
			}
		}

		@Nested
		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
		class 백을_넘는_할인_값이_넘어오면 {

			@Test
			@DisplayName("IllegalArgumentException 예외를 던진다")
			void IllegalArgumentException_예외를_던진다() {
				assertThatThrownBy(() -> new PercentDiscountVoucher(null, 101))
						.isInstanceOf(IllegalArgumentException.class)
						.hasMessage(INVALID_INPUT.getMessage());
			}
		}
	}

	@Nested
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class discount메서드는 {

		private Voucher voucher;

		@BeforeEach
		void 테스트를_위한_바우처_생성_설정() {
			voucher = new PercentDiscountVoucher(null, 10);
		}

		@Nested
		@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
		class 원가가_전달되면 {

			@Test
			@DisplayName("원가에서_할인_값만큼_할인된_금액을_반환한다")
			void 원가에서_할인_값만큼_할인된_금액을_반환한다() {
				int discountedPrice = voucher.discount(2000);
				assertThat(discountedPrice).isEqualTo(1800);
			}
		}

	}


}
