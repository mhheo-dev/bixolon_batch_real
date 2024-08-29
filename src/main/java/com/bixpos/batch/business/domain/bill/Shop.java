package com.bixpos.batch.business.domain.bill;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Shop {

    private String hId;

    private String sId;

    private String shopId;

    private String sbPayStartMm;

    @Getter
    @Setter
    public static class ShopBillingStatus {
        private String hId;
        private String sId;
        private String shopId;
        private String sbSt;
        private String sbPayStartMm;
    }

    @Getter
    @Setter
    public static class ShopBillingInfo {
        private String hId;
        private String sId;
        private String shopId;
        private String chargeAmount;
        private String chargeResult;
    }

    @Getter
    @Setter
    public static class BillSuccess {

    }
}
