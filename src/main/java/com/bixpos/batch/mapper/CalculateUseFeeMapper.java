package com.bixpos.batch.mapper;

import com.bixpos.batch.domain.bill.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalculateUseFeeMapper {

    List<Shop.ShopBillingStatus> getBillingStatusList();
    /* 무료체험 마감 매장 이용료 신규->이용중 일괄변경 */
    void updateBillingStatus(String modUsrid);
}
