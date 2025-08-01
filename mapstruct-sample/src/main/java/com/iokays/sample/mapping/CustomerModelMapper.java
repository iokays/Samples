package com.iokays.sample.mapping;

import com.iokays.sample.domain.Customer;
import com.iokays.sample.model.CustomerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING, // Spring 环境可以注入到Spring容器中
        imports = {LocalDateTime.class},
        uses = {LongToStrMapper.class}
)
public interface CustomerModelMapper {

    CustomerModelMapper I = Mappers.getMapper(CustomerModelMapper.class);

    @Mapping(target = "customerName", source = "name", defaultValue = "Unknown")
    @Mapping(target = "source", constant = "客户系统")
    @Mapping(target = "now", expression = "java( LocalDateTime.now() )")
    @Mapping(target = "actions", source = "customer", qualifiedByName = "toActions")
    @Mapping(target = "amount", source = "amount", numberFormat = "#.##")
    CustomerModel toCustomerModel(Customer customer);

    @Named("toActions")
    default Set<String> toActions(Customer customer) {
        return Set.of("update", "delete");
    }


}
