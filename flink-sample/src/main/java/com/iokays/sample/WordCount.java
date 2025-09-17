package com.iokays.sample;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

public class WordCount {

    public static void main(String[] args) throws Exception {
        //1. 获取 Flink 流执行环境， 并显示设置为批处理模式
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);

        //2. 从文本文件中读取数据 (input.txt)
        Path path = new Path("file:///flink-data/input.txt");
        final FileSource<String> text = FileSource.forRecordStreamFormat(new TextLineInputFormat(), path).build();
        System.out.println("Resolved path: " + path.toUri());

        //3. 数据转化，分词并统计每个单词出现的次数
        final DataStream<Tuple2<String, Integer>> counts = env
                .fromSource(text, WatermarkStrategy.noWatermarks(), "File Source")
                .flatMap(new LineSplitter())
                .keyBy(value -> value.f0)
                .sum(1);

        //4. 输出结果到控制台
        counts.print();

        //5. 显示执行任务
        env.execute("Word Count");
    }

    // 一个自定义的 FlatMapFunction，用于将每行文本分隔成单词
    public static class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String line, Collector<Tuple2<String, Integer>> out) throws Exception {
            Arrays.stream(StringUtils.defaultString(line)
                            .split("\\W+"))
                    .filter(StringUtils::isNotBlank)
                    .forEach(word -> out.collect(new Tuple2<>(word, 1)));
        }
    }

}
