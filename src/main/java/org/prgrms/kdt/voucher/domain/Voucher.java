package org.prgrms.kdt.voucher.domain;

import org.prgrms.kdt.common.Date;
import org.prgrms.kdt.customer.domain.vo.Email;
import org.prgrms.kdt.exception.ErrorMessage;
import org.prgrms.kdt.exception.InvalidArgumentException;
import org.prgrms.kdt.voucher.domain.vo.Type;

import java.time.LocalDateTime;
import java.util.UUID;

public class Voucher extends Date {
    private static final long MIN_VOUCHER_AMOUNT = 0;
    private static final long MAX_VOUCHER_AMOUNT = 10000;
    private final static long MIN_VOUCHER_PERCENT = 0;
    private final static long MAX_VOUCHER_PERCENT = 100;
    private UUID voucherId;
    private Email email;
    private Type type;
    private long value;

    public Voucher(LocalDateTime createdDate, LocalDateTime modifiedDate, UUID voucherId, Email email, Type type, long value) {
        super(createdDate, modifiedDate);
        valid(type, value);
        this.voucherId = voucherId;
        this.email = email;
        this.type = type;
        this.value = value;
    }

    private void valid(Type voucherType, long value) {
        if (voucherType == Type.FIXED) {
            validateAmount(value);
        }
        if (voucherType == Type.PERCENT) {
            validatePercent(value);
        }
    }


    private void validateAmount(long amount) {
        if (amount < MIN_VOUCHER_AMOUNT) {
            throw new InvalidArgumentException(ErrorMessage.MORE_THAN_MIN_VOUCHER_AMOUNT);
        }

        if (amount == MIN_VOUCHER_AMOUNT) {
            throw new InvalidArgumentException(ErrorMessage.NOT_BE_ZERO_VOUCHER_AMOUNT);
        }

        if (amount > MAX_VOUCHER_AMOUNT) {
            throw new InvalidArgumentException(ErrorMessage.LESS_THAN_MAX_VOUCHER_AMOUNT);
        }
    }

    private void validatePercent(long percent) {
        if (percent < MIN_VOUCHER_PERCENT) {
            throw new InvalidArgumentException(ErrorMessage.MORE_THAN_MIN_VOUCHER_PERCENT);
        }

        if (percent == MIN_VOUCHER_PERCENT) {
            throw new InvalidArgumentException(ErrorMessage.NOT_BE_ZERO_VOUCHER_PERCENT);
        }

        if (percent > MAX_VOUCHER_PERCENT) {
            throw new InvalidArgumentException(ErrorMessage.LESS_THAN_MAX_VOUCHER_PERCENT);
        }
    }

    public UUID getVoucherId() {
        return voucherId;
    }

    public Email getEmail() {
        return email;
    }

    public Type getType() {
        return type;
    }

    public long getValue() {
        return value;
    }
}