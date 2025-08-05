package com.iokays.sample.core.domain.sample;


import com.iokays.sample.core.domain.AggregateRoot;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@ToString
@Table(name = "my_entity")
public class MyEntity implements AggregateRoot {

    @Setter
    @MappedCollection(idColumn = "my_entity_id", keyColumn = "name_index") //List,Map 需要指定idColumn和keyColumn, 用于排序.
    List<MySubEntity> names;
    @Id
    private Long id;
    @Setter
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private MyEmbeddedEntity embeddedEntity;

    @Version //只在聚合根上使用版本控制
    private Integer version;

}
