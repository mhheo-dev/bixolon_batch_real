package com.bixpos.batch.business.step.chunk.discount;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class DiscountItemWriter implements ItemWriter<Object> {
    @Override
    public void write(List<? extends Object> items) throws Exception {
        // TODO: Implement logic to update Table A with discounted values
    }
}
