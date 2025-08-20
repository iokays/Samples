package com.iokays.sample.core.adapter.shell;

import com.google.common.collect.Lists;
import com.iokays.sample.core.service.ChatService;
import com.iokays.sample.core.service.model.ConversationModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Command(description = "AI命令")
public class AISheller {

    private final ChatService chatService;
    private final LinkedHashMap<String, String> map;

    private static List<String> NAMES() {
        return Lists.newArrayList("风筝误",
                "谢道韫", "李清照", "温庭筠", "鱼玄机", "秦观",
                "柳如是", "纳兰性德", "蔡文姬", "薛涛", "李煜",
                "李白", "杜甫", "白居易", "苏轼", "辛弃疾",
                "陆游", "王维", "孟浩然", "李商隐", "杜牧",
                "欧阳修", "王安石", "晏殊", "晏几道", "黄庭坚",
                "贺铸", "周邦彦", "姜夔", "吴文英", "张先",
                "元稹", "刘禹锡", "韦庄", "温庭筠", "冯延巳",
                "范仲淹", "岳飞", "文天祥", "杨万里", "范成大",
                "龚自珍", "袁枚", "郑板桥", "仓央嘉措", "李冶",
                "朱淑真", "严蕊", "顾太清", "吴藻", "徐灿",
                "西施", "王昭君", "赵飞燕", "甄宓", "绿珠",
                "李师师", "陈圆圆", "董小宛", "班昭", "管道升",
                "杨玉环", "貂蝉", "小乔", "大乔", "卫子夫",
                "卓文君", "上官婉儿", "长孙皇后", "太平公主", "武则天",
                "萧皇后", "阴丽华", "樊梨花", "穆桂英", "梁红玉",
                "红拂女", "苏小小", "关盼盼", "马湘兰", "卞玉京",
                "寇白门", "顾横波", "李香君", "王朝云", "琴操",
                "步非烟", "霍小玉", "杜秋娘", "薛素素", "颜令宾",
                "刘采春", "晁采", "张红拂", "崔莺莺", "芸娘",
                "冯小青", "叶小鸾", "贺双卿", "朱柔则", "沈宜修"
        );
    }

    @Command(command = {"q"}, description = "提问")
    public void question(@Option(shortNames = 'm', defaultValue = "提问") String... message) {
        if (map.isEmpty()) {
            this.createNewConversion();
        }
        final Map.Entry<String, String> last = Objects.requireNonNull(map.lastEntry());

        final ConversationModel model = ConversationModel.builder()
                .conversationId(last.getKey())
                .message(String.join(StringUtils.LF, message))
                .build();

        final ConversationModel conversation = chatService.conversation(model);
        System.out.println(last.getValue() + ":");
        System.out.println(conversation.message());
    }

    @Command(command = {"new", "q"}, description = "提问")
    public void newQuestion(@Option(shortNames = 'm', defaultValue = "提问") String... message) {
        this.createNewConversion();
        this.question(message);
    }

    public void createNewConversion() {
        map.put(UUID.randomUUID().toString(), NAMES().get(map.isEmpty() ? 0 : new Random().nextInt(1, NAMES().size())));
    }
}
