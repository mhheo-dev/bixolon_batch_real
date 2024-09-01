package com.bixpos.batch.business.mapper;

import com.bixpos.batch.business.domain.Reference;
import com.bixpos.batch.business.domain.Table;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoMapper {
    List<Reference.DemoReference> getDemoReference();
    void insertDemoReference(Table.DemoTable demoTable);
}
