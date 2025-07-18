package com.iokays.sample.core.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.stream.Stream;

public class JsonDocumentService {

    private final Document document;

    public JsonDocumentService(String html) {
        this.document = Objects.requireNonNull(Jsoup.parse(html));
    }

    /**
     * 获取文档标题
     */
    public String title() {
        return this.document.title();
    }

    /**
     *  获取指定节点的属性值 [默认取content]
     */
    public String metaVal(String name, @Nullable String attr) {
        Validate.notBlank(name, "节点名称不能为空");
        return this.document.select("meta[name=" + name + "]").attr(StringUtils.defaultIfBlank(attr, "content"));
    }

    /**
     * 获取指定节点的属性值 [默认取text]
     */
    public Stream<String> attrVal(String xpath, @Nullable String attr) {
        Validate.notBlank(xpath, "节点路径不能为空");
        return this.document.selectXpath(xpath).stream().map(v -> StringUtils.isNotBlank(attr) ? v.attr(attr): v.text());
    }

}