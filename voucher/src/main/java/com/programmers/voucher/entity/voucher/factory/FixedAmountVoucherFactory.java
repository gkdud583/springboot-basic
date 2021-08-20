package com.programmers.voucher.entity.voucher.factory;

import com.programmers.voucher.entity.voucher.FixedAmountVoucher;
import com.programmers.voucher.entity.voucher.Voucher;

public class FixedAmountVoucherFactory implements VoucherFactory {
    @Override
    public Voucher create(String name, double value) {
        return new FixedAmountVoucher(name, (int)value);
    }
}