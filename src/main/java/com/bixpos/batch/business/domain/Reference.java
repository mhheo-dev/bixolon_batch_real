package com.bixpos.batch.business.domain;

import lombok.Data;

@Data
public class Reference {

    @Data
    public static class DemoReference {
        private Integer id;
        private String demoColumn1;
        private String demoColumn2;
        private Integer demoValue;

    }
}
