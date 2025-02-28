package com.iokays.extend.asciidoctorj;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.ast.StructuralNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.println;

public class SplitContent {

    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(new File("./src/main/resources/basics.adoc").toPath());

        split(lines, 50);


    }

    private static void extracted() {
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

    private static List<List<String>> split(final List<String> lines, int size) {
        final List<List<String>> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(lines)) {
            return result;
        }

        result.add(Lists.newArrayList(lines.get(0)));

        for (int i = 1; i < lines.size(); i++) {
            final var line = lines.get(i);
            final List<String> last = result.get(result.size() - 1);

            if (StringUtils.isBlank(line)) {
                if (StringUtils.isBlank(last.get(last.size() - 1))) {
                    last.add(line);
                } else {
                    if (i < lines.size() -1 && StringUtils.isBlank(lines.get(i + 1))) {
                        result.add(Lists.newArrayList(line));
                    } else {
                        last.add(line);
                    }
                }
            } else {
                if (StringUtils.isNotBlank(last.get(last.size() - 1)) || (last.size() > 1 && StringUtils.isNotBlank(last.get(last.size() - 2)))) {
                    last.add(line);
                } else {
                    result.add(Lists.newArrayList(line));
                }
            }
        }
        Validate.isTrue(result.stream().mapToLong(List::size).sum() == lines.size());

        result.stream().filter(v -> !v.stream().allMatch(StringUtils::isBlank)).forEach(v -> println(String.join("\n", v)));

        return result;
    }
}
