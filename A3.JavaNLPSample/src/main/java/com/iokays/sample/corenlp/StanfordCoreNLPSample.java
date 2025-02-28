package com.iokays.sample.corenlp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Sets;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;
import org.apache.commons.lang3.StringUtils;

import static io.vavr.API.printf;
import static io.vavr.API.println;

public class StanfordCoreNLPSample {

    public static void main(String[] args) throws IOException {
        // 设置Stanford CoreNLP属性
        Properties props = new Properties();
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // 示例文本
        final var allLine = Files.readAllLines(Paths.get("src/main/resources/new-concept-english-2.txt"));

        final var counter = new AtomicInteger(0);

        final var set = Sets.newHashSet();

        allLine.stream().filter(StringUtils::isNotBlank).forEach(text -> {
            if (text.startsWith("Lesson ")) {
                println("--------------------------------");
                println(text);
            }
            // 创建一个Annotation对象
            Annotation document = new Annotation(text);

            // 运行Stanford CoreNLP管道
            pipeline.annotate(document);

            final List<CoreMap> coreMaps = document.get(SentencesAnnotation.class);

            // 从注释中获取句子列表
            List<CoreLabel> tokens = coreMaps.stream().flatMap(coreMap -> coreMap.get(TokensAnnotation.class).stream()).toList();

            // 输出原形化的单词
            for (CoreLabel token : tokens) {
                String lemma = token.get(LemmaAnnotation.class);
//                lemma = token.originalText();
                if (edu.stanford.nlp.util.StringUtils.isAlpha(lemma) && !set.contains(lemma)) {
                    printf("%s.%s\n", counter.getAndIncrement(), lemma);
                    set.add(lemma);
                }
            }
        });



    }

}

