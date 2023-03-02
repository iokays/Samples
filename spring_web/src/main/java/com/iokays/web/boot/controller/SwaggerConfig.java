package com.iokays.web.boot.controller;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.Annotations;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.schema.ApiModelProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 关键类
 * 
 * @author linjinjia
 */

@Configuration
public class SwaggerConfig  {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    /**
     * 添加摘要信息(Docket)
     */
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Swagger")
                        .description("Swagger 实体测试")
                        .contact(new Contact("swagger.peng", null, "592721086@qq.com"))
                        .version("V1.0")
                        .licenseUrl("/api-doc")
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

//    @Override
//    public void apply(ModelPropertyContext context) {
//        //如果不支持swagger的话，直接返回
//
//        //获取当前字段的类型
//        final Class fieldType = context.getBeanPropertyDefinition().get().getField().getRawType();
//
//        //为枚举字段设置注释
////        descForEnumFields(context, fieldType);
//    }
//
//    /**
//     * 为枚举字段设置注释
//     */
//    private void descForEnumFields(ModelPropertyContext context, Class fieldType) {
//        Optional<ApiModelProperty> annotation = Optional.absent();
//
//        // 找到 @ApiModelProperty 注解修饰的枚举类
//        if (context.getAnnotatedElement().isPresent()) {
//            annotation = annotation
//                    .or(ApiModelProperties.findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
//        }
//        if (context.getBeanPropertyDefinition().isPresent()) {
//            annotation = annotation.or(Annotations.findPropertyAnnotation(
//                    context.getBeanPropertyDefinition().get(),
//                    ApiModelProperty.class));
//        }
//
//        //没有@ApiModelProperty 或者 notes 属性没有值，直接返回
//        if (!annotation.isPresent() || StringUtils.isEmpty((annotation.get()).notes())) {
//            return;
//        }
//
//        //@ApiModelProperties中的notes指定的class类型
//        Class rawPrimaryType;
//        try {
//            rawPrimaryType = Class.forName((annotation.get()).notes());
//        } catch (ClassNotFoundException e) {
//            //如果指定的类型无法转化，直接忽略
//            return;
//        }
//
//        Object[] subItemRecords = null;
//        SwaggerDisplayEnum swaggerDisplayEnum = AnnotationUtils
//                .findAnnotation(rawPrimaryType, SwaggerDisplayEnum.class);
//        // 判断是否存在 @SwaggerDisplayEnum 注解，并且 rawPrimaryType 是枚举
//        if (null != swaggerDisplayEnum && Enum.class.isAssignableFrom(rawPrimaryType)) {
//            // 拿到枚举的所有的值
//            subItemRecords = rawPrimaryType.getEnumConstants();
//        }
//        if (null == subItemRecords) {
//            return;
//        }
//
//        final List<String> displayValues =
//                Arrays.stream(subItemRecords)
//                        .filter(Objects::nonNull)
//                        // 调用枚举类的 toString 方法
//                        .map(Object::toString)
//                        .filter(Objects::nonNull)
//                        .collect(Collectors.toList());
//
//        String joinText = " (" + String.join("; ", displayValues) + ")";
//        try {
//            // 拿到字段上原先的描述
//            Field mField = ModelPropertyBuilder.class.getDeclaredField("description");
//            mField.setAccessible(true);
//            // context 中的 builder 对象保存了字段的信息
//            joinText = mField.get(context.getBuilder()) + joinText;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//
//        // 设置新的字段说明并且设置字段类型
//        final ResolvedType resolvedType = context.getResolver().resolve(fieldType);
//        context.getBuilder().description(joinText).type(resolvedType);
//    }
//
//    @Override
//    public boolean supports(DocumentationType documentationType) {
//        return true;
//    }
}
