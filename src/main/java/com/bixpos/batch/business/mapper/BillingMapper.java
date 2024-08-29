package com.bixpos.batch.business.mapper;

import com.bixpos.batch.business.domain.bill.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillingMapper {
    /* 무료체험 마감 매장 이용료 신규->이용중 일괄변경 */
    int updateBillingStatus();
}
