package com.iokays.extend.asciidoctorj;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.ast.StructuralNode;

import java.io.File;
import java.util.List;

public class SplitContent {

    public static void main(String[] args) {
        // 创建Asciidoctor对象
        final var asciidoctor = Asciidoctor.Factory.create();
        // 设置选项
        final var options = OptionsBuilder.options().toFile(false).asMap();

        final var document = asciidoctor.loadFile(new File("./src/main/resources/basics.adoc"), options);

        System.out.printf("title: %s\n", document.getTitle());

        List<StructuralNode> paragraphs = document.getBlocks();
        paragraphs.stream().filter(v -> v.getContext().equals("paragraph")).forEach(paragraph -> {
            System.out.println("paragraph: " + paragraph.getContent());
        });

        // 关闭Asciidoctor对象
        asciidoctor.shutdown();

    }
}
