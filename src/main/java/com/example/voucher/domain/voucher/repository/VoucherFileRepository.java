package com.example.voucher.domain.voucher.repository;

import com.example.voucher.domain.voucher.Voucher;
import com.example.voucher.domain.voucher.VoucherType;
import com.example.voucher.utils.FileUtils;

import java.util.List;

import static com.example.voucher.domain.voucher.VoucherType.EMPTY;
import static com.example.voucher.exception.ErrorMessage.SERVER_ERROR;


public class VoucherFileRepository implements VoucherRepository {

    private static final String PATH = "voucherList.csv";

    @Override
    public Voucher save(Voucher voucher) {
        if (voucher == null) {
            // TODO: 로그 남기기
            throw new IllegalArgumentException(SERVER_ERROR.name());
        }
        if (voucher.getVoucherId() == null) {
            VoucherType voucherType = VoucherType.of(voucher.getClass().getSimpleName());

            if (voucherType == EMPTY) {
                // TODO: 로그 남기기
                throw new IllegalArgumentException(SERVER_ERROR.name());
            }

            voucher = voucherType.create(VoucherIdGenerator.generateVoucherId(), voucher.getDiscountAmount());
        }
        FileUtils.writeFile(PATH, voucher.toString());
        return voucher;
    }

    @Override
    public List<Voucher> findAll() {
        return null;
    }
}
