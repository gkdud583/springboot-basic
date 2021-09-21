package org.prgrms.kdt.voucher.repository;

import org.prgrms.kdt.voucher.model.FixedAmountVoucher;
import org.prgrms.kdt.voucher.model.PercentDiscountVoucher;
import org.prgrms.kdt.voucher.model.Voucher;
import org.prgrms.kdt.voucher.model.VoucherType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.prgrms.kdt.common.util.JdbcUtil.toLocalDateTime;
import static org.prgrms.kdt.common.util.JdbcUtil.toUUID;
import static org.prgrms.kdt.common.util.VoucherUtil.createVoucherByType;

@Repository
public class JdbcVoucherRepository implements VoucherRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcVoucherRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Map<String, Object> toParamMap(Voucher voucher) {
        return new HashMap<>() {{
            put("voucherId", voucher.getVoucherId().toString().getBytes());
            put("value", voucher.getValue());
            put("type", voucher.getVoucherType().toString());
            put("createdAt", voucher.getCreatedAt());
        }};
    }

    private RowMapper<Voucher> voucherRowMapper = (resultSet, i) -> {
        var voucherId =  toUUID(resultSet.getBytes("voucher_id"));
        var value = resultSet.getLong("value");
        var voucherType = VoucherType.convert(resultSet.getString("type"));
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        return createVoucherByType(voucherId, value, createdAt, voucherType);
    };


    @Override
    public Voucher insert(Voucher voucher) {
        var update = jdbcTemplate.update(
                "insert into vouchers(voucher_id, value, type, created_at) values (UUID_TO_BIN(:voucherId), :value, :type, :createdAt)",
                toParamMap(voucher)
        );
        if (update != 1)
            throw new RuntimeException("Failed insert");
        return voucher;
    }

    @Override
    public List<Voucher> findAll() {
        return jdbcTemplate.query("select * from vouchers", voucherRowMapper);
    }

    @Override
    public void deleteById(UUID voucherId) {
        jdbcTemplate.update(
                "delete from vouchers where voucher_id = UUID_TO_BIN(:voucherId)",
                Collections.singletonMap("voucherId", voucherId.toString().getBytes())
        );
    }


    @Override
    public Optional<Voucher> findById(UUID voucherId) {
        return Optional.empty();
    }

}