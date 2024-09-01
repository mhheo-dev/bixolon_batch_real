package com.bixpos.batch.business.mapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillingMapper {
    /* 무료체험 마감 매장 이용료 신규->이용중 일괄변경 */
    int updateBillingStatus();
}
