package org.prgms.voucher;

import java.text.MessageFormat;
import java.util.UUID;

public class PercentDiscountVoucher implements Voucher{

    private final UUID voucherId;
    private final long percent;

    public PercentDiscountVoucher(UUID voucherId, long percent) {
        this.voucherId = voucherId;
        this.percent = percent;
    }


    @Override
    public UUID getVoucherId() {
        return voucherId;
    }

    @Override
    public long discount(long beforeDiscount) {
        return beforeDiscount *(percent /100);
    }

    @Override
    public String toString() {
        return MessageFormat.format("[PercentDiscountVoucher] voucherId : {0}, percent : {1}", voucherId, percent) ;
    }
}