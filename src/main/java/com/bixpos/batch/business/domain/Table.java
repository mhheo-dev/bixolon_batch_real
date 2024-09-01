package com.bixpos.batch.business.domain;

import lombok.Data;
import lombok.Getter;

@Data
public class Table {

    @Data
    public static class DemoTable {
        private Integer id;
        private String demoColumn1;
        private String demoColumn2;
        private Integer demoValue;
    }
}
