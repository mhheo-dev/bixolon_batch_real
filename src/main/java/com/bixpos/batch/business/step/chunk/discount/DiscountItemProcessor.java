package com.bixpos.batch.business.step.chunk.discount;

import org.springframework.batch.item.ItemProcessor;

public class DiscountItemProcessor implements ItemProcessor<Object, Object> {
    @Override
    public Object process(Object item) throws Exception {
        // TODO: Implement logic to check if A's specific value exists in Table B
        // TODO: Apply discount if condition is met
        return item;
    }
}