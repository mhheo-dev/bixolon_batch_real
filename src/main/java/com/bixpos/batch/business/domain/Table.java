package com.bixpos.batch.business.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Table {

    @Getter
    @Setter
    public static class DemoTable {
        private Integer id;
        private String demoColumn1;
        private String demoColumn2;
        private Integer demoValue;
    }
}
