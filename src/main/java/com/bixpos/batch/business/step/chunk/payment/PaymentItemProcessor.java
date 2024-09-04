package com.bixpos.batch.business.step.chunk.payment;

import org.springframework.batch.item.ItemProcessor;

public class PaymentItemProcessor implements ItemProcessor<Object, Object> {
    @Override
    public Object process(Object item) throws Exception {
        // TODO: Implement payment processing logic
        return item;
    }
}
