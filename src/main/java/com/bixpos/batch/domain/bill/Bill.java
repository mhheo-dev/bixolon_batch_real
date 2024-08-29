package com.bixpos.batch.domain.bill;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Bill {
    @Getter
    @Setter
    public static class ShopBillingStatus {
        private String resultCode;
        private String resultMsg;
        private String payDate;
        private String payTime;
        private String price;
        private String tid;
        private String applNum;
    }

    @Getter
    @Setter
    public static class BillingResult {
        private String resultCode;
        private String resultMsg;
        private String payDate;
        private String payTime;
        private String cardResultCode;
        private String cardKind;
        private String billkey;
        private String price;
        private String tid;
        private String cretUsrId;
        private String applNum;
        private String billType;
    }
}
