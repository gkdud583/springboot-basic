package com.example.voucher.domain.repository;

import com.example.voucher.domain.voucher.FixedAmountVoucher;
import com.example.voucher.domain.voucher.Voucher;
import com.example.voucher.domain.voucher.VoucherType;
import com.example.voucher.domain.voucher.repository.VoucherRepository;
import com.example.voucher.utils.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.IOException;

import static com.example.voucher.domain.voucher.VoucherType.EMPTY;
import static com.example.voucher.exception.ErrorMessage.SERVER_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("VoucherMemoryRepository 클래스의")
public class VoucherFileRepositoryTest {
    private final VoucherRepository voucherRepository = new VoucherFileRepository();

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class save메서드는 {

        @Nested
        @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
        class 바우처가_넘어온다면 {

            private Voucher createdVoucher;

            @BeforeEach
            void 테스트_위한_설정() {
                createdVoucher = new FixedAmountVoucher(null, 1000);
            }

            @Test
            @DisplayName("바우처를 저장하고 저장된 바우처를 반환한다")
            void 바우처를_저장하고_저장된_바우처를_반환한다() {
                try (MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
                    Voucher savedVoucher = voucherRepository.save(createdVoucher);
                    mockedStatic.verify(() -> FileUtils.writeFile(anyString(), anyString()));
                    assertThat(savedVoucher.getDiscountAmount()).isEqualTo(createdVoucher.getDiscountAmount());
                }
            }
        }

        @Nested
        @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
        class 바우처_저장중에_문제가_발생한다면 {

            @Test
            @DisplayName("예외를 던진다")
            void 예외를_던진다() {

                try (MockedStatic<FileUtils> mockedStatic = mockStatic(FileUtils.class)) {
                    mockedStatic.when(() -> FileUtils.writeFile(anyString(), anyString()))
                            .thenThrow(new IOException(FILE_WRITE_ERROR.name()));

                    assertThatThrownBy(() -> voucherRepository.save(new FixedAmountVoucher(null, 100)))
                            .isInstanceOf(RuntimeException.class)
                            .hasMessage(FILE_WRITE_ERROR.name());
                }
            }
        }

        @Nested
        @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
        class 바우처가_null이_넘어온다면 {

            @Test
            @DisplayName("IllegalArgumentException 예외를 던진다")
            void IllegalArgumentException_예외를_던진다() {
                assertThatThrownBy(() -> voucherRepository.save(null))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(SERVER_ERROR.name());

            }
        }

        @Nested
        @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
        class 바우처_타입이_EMPTY라면 {

            @Test
            @DisplayName("IllegalArgumentException 예외를 던진다")
            void IllegalArgumentException_예외를_던진다() {

                try (MockedStatic<VoucherType> voucherType = Mockito.mockStatic(VoucherType.class)) {
                    voucherType.when(() -> VoucherType.of(anyString()))
                            .thenReturn(EMPTY);

                    assertThatThrownBy(() -> voucherRepository.save(new FixedAmountVoucher(null, 1000)))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage(SERVER_ERROR.name());
                }
            }
        }
    }
}


