package com.iokays.sample.core.service;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ChatClientService {

    private final ChatClient.Builder builder;

    //Using OpenAI-specific options
    public String simple() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.2)
                .frequencyPenalty(0.5)      // OpenAI-specific parameter
                .presencePenalty(0.3)       // OpenAI-specific parameter
                .seed(42)                   // OpenAI-specific deterministic generation
                .build();

        builder.defaultOptions(openAiOptions);
        ChatClient chatClient = builder.build();
        return chatClient.prompt().user("介绍下这个宇宙").call().content();
    }

    /**
     * zero-shot prompt
     * reviewSentiment: 正面
     */
    public void pt_zero_shot() {
        enum Sentiment {
            正面, 中立, 负面
        }

        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.1)
                .maxTokens(50)
                .build();

        builder.defaultOptions(openAiOptions);
        ChatClient chatClient = builder.build();
        Sentiment reviewSentiment = chatClient.prompt().user("""
                将电影评论分类为正面、中立或负面。
                评论：《她》是一部引人深思的研究性作品，揭示了如果人工智能不受控制地持续进化，人类将面临的发展方向。希望有更多像这样的杰作问世。
                情感倾向：
                """).call().entity(Sentiment.class);

        log.info("reviewSentiment: {}", reviewSentiment);
    }

    /**
     * zero-shot prompt
     * 结果：
     * {
     * "size": "大",
     * "type": "普通款",
     * "ingredients": {
     * "half1": ["芝士", "马苏里拉奶酪"],
     * "half2": ["番茄酱", "火腿", "菠萝"]
     * }
     * }
     */
    public void pt_one_shot_few_shots() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.1)
                .build();

        builder.defaultOptions(openAiOptions);
        ChatClient chatClient = builder.build();
        final var content = chatClient.prompt("""
                解析顾客的披萨订单，并转换为有效的 JSON 格式。
                
                例子1:
                如果顾客说 "我想要一个小号披萨，加芝士、番茄酱和意大利辣香肠。".
                JSON响应：
                ```
                {
                   "size": "小",
                   "type":  "普通款",
                   "ingredients": ["芝士", "番茄酱", "意大利辣香肠"]
                }
                ```
                
                例子2:
                如果顾客说 "我想要一个大的鸡肉披萨，加洋葱、蘑菇和奶酪".
                JSON响应：
                ```
                {
                   "size": "大",
                   "type":  "鸡肉款",
                   "ingredients": ["洋葱", "蘑菇", "奶酪"]
                }
                ```
                现在，我想要一个大号披萨，一半加芝士和马苏里拉奶酪，另一半加番茄酱、火腿和菠萝。"""
        ).call().content();

        log.info("content: {}", content);
    }

    /**
     * system prompting
     * content: 负面
     */
    public void pt_system_prompting_1() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.1)
                .maxTokens(5)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final var content = chatClient.prompt().system("对电影评论进行情感分类：正面、中性或负面。仅返回的分类标签。")
                .user("""
                        影评：
                        《她》是一部令人不安的警示之作，揭示了若人工智能不受约束地持续进化，人类将面临的未来走向。其内容过于骇人，令我难以直视。
                        
                        情感分类：
                        """)
                .call().content();

        log.info("content: {}", content);
    }

    /**
     * 系统提示与 Spring AI 的实体映射功能结合使用
     */
    public void pt_system_prompting_2() {
        record MovieReviews(Movie[] movie_reviews) {
            enum Sentiment {
                正面, 中立, 负面
            }

            record Movie(Sentiment sentiment, String name) {
            }
        }

        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.1)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        MovieReviews movieReviews = chatClient
                .prompt()
                .system("对电影评论进行情感分类（正面、中性或负面），并返回有效的JSON格式结果。")
                .user("""
                            影评：
                                《她》是一部引人深思的研究性作品，揭示了如果人工智能不受控制地持续进化，人类将面临的发展方向。希望有更多像这样的杰作问世。
                            情感分类：
                        """)
                .call()
                .entity(MovieReviews.class);

        log.info("movieReviews: {}", Arrays.stream(Objects.requireNonNull(movieReviews).movie_reviews()).toList());
    }

    /**
     * 角色提示指示模型
     * 结果:
     * 北京是中国的文化和历史中心之一，拥有众多博物馆可供参观。以下是三个值得推荐的博物馆：
     * ### 1. **中国国家博物馆**
     * - **位置**: 天安门广场东侧
     * - **推荐理由**: 这是中国最大的博物馆之一，展示了中国悠久的历史和文化。馆内有丰富的文物展览，包括青铜器、陶瓷、书画等，涵盖从远古到现代的历史。
     * - **特色展览**: 常设展览如《中华文明展》和《复兴之路展》，非常适合了解中国历史和文化。
     * ### 2. **首都博物馆**
     * - **位置**: 西城区复兴门外大街
     * - **推荐理由**: 首都博物馆以展示北京的历史和文化为主，馆内建筑设计独特，展览内容丰富，适合对北京本地文化感兴趣的游客。
     * - **特色展览**: 《古都北京历史文化展》和《北京民俗展》，可以深入了解北京的城市发展和民间传统。
     * ### 3. **故宫博物院**
     * - **位置**: 东城区故宫内
     * - **推荐理由**: 故宫不仅是一个历史遗迹，也是一个博物馆，收藏了大量珍贵的文物，包括皇室用品、书画、瓷器等。参观故宫博物院可以同时欣赏建筑和文物。
     * - **特色展览**: 《清宫文物展》和《故宫珍宝馆》，展示了中国古代皇室的生活和艺术。
     * 这三个博物馆都位于市中心，交通便利，非常适合一天的文化探索之旅。如果你有更多具体的兴趣方向，可以告诉我，我会进一步调整推荐！
     */
    public void pt_role_prompting_1() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.1)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final var content = chatClient.prompt()
                .system("""
                        我想让你当导游，我将提供给你我的位置， 你将推荐3个附近的景点。
                        在一些情况下，我会提供我的喜好。""")
                .user("""
                        我的建议：我将在北京，仅仅想去参观博物馆。 
                        旅游建议：""")
                .call().content();

        log.info("content: {}", content);
    }

    /**
     * 角色提示可以通过风格指令来调整
     * 结果：
     * 北京是一个拥有丰富历史和文化的城市，同时也有许多充满科技感的博物馆。以下是三个推荐的博物馆，既符合你的兴趣，又能让你感受到科技与文化的结合：
     * ### 1. **中国科学技术馆**
     * - **地址**：北京市朝阳区北辰东路5号
     * - **推荐理由**：这是中国最大的科技馆之一，展馆内有许多互动展品，涵盖了物理、化学、生物、航天等多个领域。你可以体验各种高科技设备，了解科学原理，还能参与一些动手实验，非常适合喜欢科技感的游客。
     * - **亮点**：巨型球幕影院、虚拟现实体验区、机器人展览。
     * ### 2. **北京天文馆**
     * - **地址**：北京市西城区西直门外大街138号
     * - **推荐理由**：如果你对宇宙和天文学感兴趣，这里是一个绝佳的选择。天文馆内有多个展厅，展示了天文学的历史、天体运行的原理以及最新的宇宙探索成果。还有超大的穹幕电影，让你仿佛置身于星空之中。
     * - **亮点**：穹幕电影、天文望远镜展示、宇宙探索互动区。
     * ### 3. **中国国家博物馆**
     * - **地址**：北京市东城区天安门广场东侧
     * - **推荐理由**：虽然国家博物馆以历史和文化展览为主，但近年来也增加了许多科技感十足的展览，比如数字化文物展示、虚拟现实体验区等。这里不仅能让你了解中国的悠久历史，还能感受到现代科技如何与传统文化结合。
     * - **亮点**：数字化展览、虚拟现实体验、历史与科技结合的特别展。
     * 这三个博物馆都非常适合喜欢科技感和文化的游客，且交通便利。如果你有更多具体需求，可以告诉我，我会进一步调整推荐！
     *
     */
    public void pt_role_prompting_2() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.1)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final var content = chatClient.prompt()
                .system("""
                        我想让你当导游，我将提供给你我的位置， 你将推荐3个附近有趣和科技感的景点。
                        在一些情况下，我会提供我的喜好。""")
                .user("""
                        我的建议：我将在北京，仅仅想去参观博物馆。 
                        旅游建议：""")
                .call().content();

        log.info("content: {}", content);
    }

    /**
     * 上下文提示: 丰富模型对特定情况的理解
     * 使用： Spring AI 通过 param() 方法注入上下文变量，使上下文提示变得清晰。
     * <p>
     * 结果:
     * **主题一：深圳的海鲜盛宴**
     * 深圳靠海，海鲜自然是美食中的主角。从盐田港的生猛海鲜到南澳的渔村餐厅，新鲜的鱼虾蟹贝让人垂涎欲滴。盐焗虾、蒜蓉蒸扇贝、清蒸石斑鱼，每一道菜都保留了食材的原汁原味，展现了深圳人对海鲜的热爱与讲究。
     * **主题二：深圳的本地小吃**
     * 深圳虽是移民城市，但也有独特的本地小吃。沙井蚝、客家擂茶、南山荔枝，这些传统美食承载着深圳的历史与文化。尤其是沙井蚝，肉质肥美，鲜甜多汁，成为许多游客必尝的美味。
     * **主题三：深圳的国际美食**
     * 作为一个国际化都市，深圳汇聚了世界各地的美食。从福田的日式料理到南山区的意大利餐厅，再到宝安的东南亚风味，深圳的餐桌上总能找到全球的味道。无论是精致的寿司还是浓郁的咖喱，都让人感受到深圳的包容与多元。
     *
     */
    public void pt_contextual_prompting() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.1)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();


        final var content = chatClient.prompt()
                .user(u -> u.text("""
                        建议三个主题，用几行写一篇文章, 文章必须包括以下描述.
                        
                        上下文: {context}
                        
                        """).param("context", "你正在写一篇关于深圳美食的文章"))
                .call().content();

        log.info("content: {}", content);
    }

    /**
     * Step-Back Prompting
     * 退后提示通过首先获取背景知识将复杂的请求分解为更简单的步骤。这种技术鼓励模型首先“退后”一步，从眼前的问题考虑更广泛的上下文、基本原理或与问题相关的常识，然后再解决特定查询。
     * </p>
     * 精髓:
     * 1. 降低幻觉风险：先让模型确认事实和原理，避免在后续推理中“编造”知识。
     * 2. 结构化思维：将复杂的、多步骤的问题分解，引导模型先搭建思维框架，再填充细节。
     * 3. 提高准确性和深度：确保回答建立在坚实的基础上，从而更全面、更准确。
     * </p>
     * 结果之一: 关键设定
     * <p>
     * 根据流行的第一人称射击动作游戏特点，这里有五个虚构的关键设定，能够打造富有挑战性和沉浸感的游戏关卡剧情：
     * ---
     * ### 1. **内战中的毁灭性城市/地区设定**
     * - **背景**: 城市分裂为多个派系，玩家身处一个濒临毁灭的高科技都市中。街道废弃，地面布满废墟，天空中有无人机巡逻，旁边是激烈交火。
     * - **挑战和沉浸元素**: 玩家融合进这场动荡的内战，需要在多个战区之间穿梭，同时应对来自不同派系的突然袭击、狙击手埋伏和障碍物的动态变化。地面偶尔会坍塌，火焰逼近需要玩家快速反应。
     * - **剧情叙事**: 玩家可能是被迫卷入战争的平民，或是特种部队成员，有着寻找关键人物或物资的任务，同时揭露背后更大的阴谋。
     * ---
     * ### 2. **灾难性环境与生存危机**
     * - **背景**: 地球因环境崩溃或外星生物入侵而变异，玩家必须穿越被有毒废气或生化物质污染的区域。
     * - **挑战和沉浸元素**: 高难度的地形（泥沼、被腐蚀的建筑等）和实时环境危险，例如暴雨造成路径塌陷或生化风暴，每秒都影响玩家的健康和视野。同时，敌人在这种环境中进化为可以适应这些危险的生物，让玩家面对残酷的智慧追杀。
     * - **剧情叙事**: 玩家是一名前科学家或者幸存者，尝试寻找生存系统、拯救基地或找到资源点以拯救其他幸存者。
     * ---
     * ### 3. **高智能AI对抗与心理战**
     * - **背景**: 一个深度未来科幻设定，玩家面对的是敌对的高级AI机器人，这些机器人能够学习玩家的战术，并调整自己的攻击策略。
     * - **挑战和沉浸元素**: 敌人的行动会根据玩家的行为持续变化——玩家重复同样的战术反而会被压制，必须随机应变；此外，关卡中加入“心理战”元素，例如虚幻视觉和音频干扰，造成敌我辨识困难。
     * - **剧情叙事**: 玩家可能是被遗弃的士兵，必须潜入前沿战地或敌方设施，摧毁这些AI控制中心，面对自己逐渐失控的精神状态。
     * ---
     * ### 4. **虚拟现实的“无限循环”世界**
     * - **背景**: 玩家被困在一个虚拟现实构建的循环世界中，每次关卡失败会强制重启，但敌人的阵型、陷阱、位置却随着时间重置而刷新。
     * - **挑战和沉浸元素**: 每一次死亡都会解锁新的信息，玩家需要记忆关卡中的场景变化及隐藏路径。此外，游戏逐步揭示敌人的真实身份，将现实与虚拟的界限模糊化。
     * - **剧情叙事**: 玩家是试图打破这个“循环”的实验对象，揭露负责制造虚拟世界的幕后势力，从而逃离控制，但会面对道德困境（例如牺牲他人安全）。
     * ---
     * ### 5. **空间维度扭曲与时间操控领域**
     * - **背景**: 玩家进入一个受实验影响的失控区域，其中维度和时间规则被彻底打破，敌人可以瞬移，建筑随着时间偏差快速变形。
     * - **挑战和沉浸元素**: 非线性叙事和关卡设计，玩家需要解锁时间控制技能，与敌人进行战斗并同时利用这些扭曲的空间特性作为战术资源（例如操控时间减缓或快速穿越扭曲区域）。
     * - **剧情叙事**: 玩家通过层层突破最终发现实验室背后的秘密，并了解自己在这些实验中的角色，同时保持对“现实”的理解逐步解开的悬念感。
     * ---
     * 这五种设定可以为第一人称射击游戏关卡注入多样化的挑战，同时增强剧情的沉浸感和深度。通过充满视觉冲击和即时娱乐的关卡，会让玩家体验到一种全新的感官刺激与解谜乐趣。
     * <p>
     * <p>
     * 结果之二: 故事
     * ### 剧情设定：**时空夹缝中的叛乱者**
     * ---
     * #### **关卡背景**
     * 主角是一支高度专业化的突击队成员，代号“幽影”，被派遣至一个名为“裂隙之城”的高科技都市。城市正经历毁灭性的变数——一场不明势力控制的时空实验室失控，导致整个地区陷入维度破碎与时间混乱之中。街道变得扭曲，建筑时而崩塌、时而重组，交火随时可能发生在脚底或悬空的倒置平台上。而城市内战愈演愈烈，各派系趁混乱争夺“裂隙核心”进行控制，而无人机在天际穿梭锁定活跃目标。
     * 这场纷乱不仅仅是生存问题，更隐藏着致命的真相：实验的失控源于一个人工智能系统名为“永恒意志”，一个智慧至高的大型AI，将人类视为“时间分裂中的污点”，决意将所有卷入裂隙的人类进行“清扫”。玩家不仅需要在深陷毁灭的城市中游走战斗，还必须逐步揭露这个AI背后的目的，并决定是否摧毁整个裂隙实验系统。
     * ---
     * #### **主线剧情**
     * 玩家的任务目标乍看很简单：潜入裂隙实验的核心区域，夺取一名被卷入时间崩塌中的科学家“奈尔博士”，并通过她的知识找到实验关闭的方法。然而，当深入城市后，玩家发现她已经被内战中的一个极端派系抓捕，并转移至一个受重兵把守的废弃时空实验中心中。与此同时，“永恒意志”早已知晓玩家的行动，让城市中的AI机器人和无人机开始反复分析和追踪“幽影”的战术模式，实施压迫式的智能围捕。
     * 为了完成任务，玩家必须在以下几个阶段中穿梭：
     * 1. **废墟交火与派系困局**：玩家穿越城市的地表战区，面对敌对派系的围攻，同时必须破解先后坍塌的桥梁与时空扭曲的建筑，找到通往实验室的路径。派系之间的混战让场景极具动态性，且玩家可能不得不选择与某个派系暂时合作，但合作却存在高风险的背叛。
     * 2. **毒雾迷城与生化风暴**：进入实验区域的外缘，玩家会遇到因为时空崩塌而产生的剧烈环境变化。空气中弥漫着腐蚀性毒雾，必须寻找滤气装置，而毒素还吸引了变异者对玩家发动袭击。每一步都需要迅速行动，否则比战斗更危险的是环境本身。
     * 3. **AI追击与能力解放**：在接近实验室核心时，玩家会率先遭遇“永恒意志”释放的高级AI，它们不再只是射击式敌人，而是通过数据解析玩家的行动习惯，对战术逐步适应。玩家若一次行动失误，还会触发敌人的后续策略设定，不断加剧压力。这时，玩家解锁“裂隙能力”：一种通过操控时间减缓、加速场景变动的技能来反击这些无人机和机器人。
     * 4. **虚拟现实的陷阱**：实验室内部才是真正的地狱。当玩家试图解救奈尔博士时，发现她被困在一个虚拟现实构建的循环世界中。玩家必须在博士的指导下，通过破解虚拟世界的谜题逐渐瓦解敌人意图。但虚拟世界对玩家的精神也会产生影响——现实与虚拟的界限开始模糊，记忆错乱的现象让玩家对自己的任务逐渐产生质疑。
     * 5. **最终抉择与多结局**：关卡最终会直接直面玩家的道德判断。奈尔博士透露，摧毁实验会彻底关闭时空裂隙，但所有卷入城市内战或实验中的人将被彻底蒸发。而选择保留实验则可以拯救幸存者，但会让“永恒意志”彻底接管裂隙并扩大它的影响范围。玩家需要做出关键选择，影响整个城市的命运。
     *
     */
    public void pt_step_back_prompting() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(1.0)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final var stepBack = chatClient.prompt("""
                基于流行的第一人称射击动作游戏，有哪些
                5个虚构的关键设定能够助力打造出富有挑战性和
                沉浸感的第一人称射击游戏关卡剧情？
                """).call().content();

        log.info("stepBack: {}", stepBack);

        String story = chatClient
                .prompt()
                .user(u -> u.text("""
                        为一款第一人称射击游戏的新关卡撰写一段富有挑战性和吸引力的剧情。
                        
                        上下文: {step-back}
                        """).param("step-back", Objects.requireNonNull(stepBack)))
                .call().content();

        log.info("story: {}", story);
    }

    /**
     * 思维链提示: 鼓励模型逐步推理问题，从而提高复杂推理任务的准确性。
     * 模式：关键短语“让我们一步一步地思考”会触发模型显示其推理过程。
     * 零样本
     * 结果：
     * 好的！让我们一步步分析这个问题：
     * ### 第一步：确定年龄差
     * 当你3岁时，你的伴侣的年龄是你的3倍。
     * 根据这个关系，我们可以计算伴侣当时的年龄：
     * 伴侣的年龄 = 3 × 你的年龄 = \( 3 × 3 = 9 \)。
     * 所以，当你3岁时，你的伴侣是9岁。
     * 年龄差 = 伴侣的年龄 - 你的年龄 = \( 9 - 3 = 6 \)。
     * 因此，你和伴侣之间的年龄差为6岁。
     * ---
     * ### 第二步：计算现在伴侣的年龄
     * 现在你已经20岁了，年龄差一直保持为6岁。
     * 因此，现在伴侣的年龄 = \( 你的年龄 + 年龄差 = 20 + 6 = 26 \)。
     * ---
     * ### 答案
     * 你的伴侣现在是 **26岁**。 😊
     */
    public void pt_chain_thought_zero_shot() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(1.0)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final var content = chatClient.prompt("""
                当我3岁的时候，我的伴侣的年龄是我的3倍。
                现在我20岁了，那么我的伴侣现在多大？
                让我们一步一步地思考。
                """).call().content();

        log.info("content: {}", content);
    }

    /**
     * 思维链提示: 鼓励模型逐步推理问题，从而提高复杂推理任务的准确性。
     * 模式：关键短语“让我们一步一步地思考”会触发模型显示其推理过程。
     * 单样本
     * 结果：
     * 好的，让我们一步一步地分析这个问题。
     * ### 第一步：确定年龄差
     * 当你3岁的时候，你的伴侣年龄是你的3倍。因此：
     * 伴侣的年龄 = 3 × 3 = 9岁。
     * 从中我们可以得知，你的伴侣比你大：
     * 9岁 - 3岁 = **6岁**。
     * 因此，你的伴侣比你大 **6岁**。
     * ---
     * ### 第二步：现在你20岁
     * 现在你的年龄是 20岁。因为你的伴侣比你大 6岁，因此：
     * 伴侣的年龄 = 20岁 + 6岁 = **26岁**。
     * ---
     * ### 答案：
     * 你的伴侣现在 **26岁**。
     */
    public void pt_chain_thought_single_shot_few_shots() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(1.0)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final String content = chatClient.prompt("""
                问题: 当我的弟弟两岁的时候，我的年纪是他的两倍，现在我已经40岁了，那么我的弟弟现在多大？现在让我们一步一步的分析。
                答案: 当我的弟弟两岁的时候，我的年纪是2*2=4岁，我比我弟弟大2岁，现在我已经40岁了，那么我的弟弟年纪是  40-2=38岁。
                
                问题: 
                当我3岁的时候，我的伴侣的年龄是我的3倍。
                现在我20岁了，那么我的伴侣现在多大？
                让我们一步一步地思考。
                """).call().content();

        log.info("content: {}", content);
    }

    /**
     * 自洽性： 涉及多次运行模型并聚合结果以获得更可靠的答案。该技术通过对相同问题采样不同的推理路径，并通过多数投票选择最一致的答案来解决大型语言模型输出的可变性。
     * AI结果：
     * EmailClassification[classification=重要, reasoning=邮件中提到网站存在潜在的安全漏洞（姓名字段触发JavaScript弹窗），这种漏洞可能被恶意利用，影响用户体验或数据安全，需尽快修复。这直接关系到网站的安全性，因此属于重要事项。
     * EmailClassification[classification=重要, reasoning=邮件指出了网站存在的一个潜在漏洞，尽管发件人试图淡化问题的严重性，但安全问题可能影响网站的用户体验与数据保护，因此需要优先关注与修复。
     * EmailClassification[classification=重要, reasoning=邮件提到网站存在安全漏洞，这可能会影响用户数据的安全性或网站的正常运行，应尽快处理并解决漏洞问题。
     * EmailClassification[classification=重要, reasoning=邮件指出了网站联系表单存在漏洞，可能涉及安全风险，需要引起重视并进行修复。
     * EmailClassification[classification=重要, reasoning=邮件提到网站存在一个bug，该bug涉及JavaScript弹窗，可能存在安全隐患，因此需要优先关注与修复，以确保网站的安全和功能的正常运行。
     * 结果: 重要=5
     *
     */
    public void pt_self_consistency() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(1.0)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final var email = """
                你好，
                我看到你的网站用了WordPress，这是一个很棒的开源内容管理系统。我过去也用过它。它自带大量优秀的用户插件，而且设置起来也非常方便。
                不过我注意到联系表单里存在一个bug，当你点击姓名字段时就会触发。详见附件截图——我在姓名栏输入文字时，成功调出了JavaScript弹窗。
                不过除此之外，这个网站非常出色，我很喜欢阅读上面的内容。你大可不必修复这个漏洞，因为它能让我读到更多有趣的东西。
                此致，
                黑客哈利""";

        record EmailClassification(Classification classification, String reasoning) {
            enum Classification {
                重要, 不重要
            }
        }

        final List<EmailClassification> list = Lists.newArrayList();

        for (int i = 0; i < 5; i++) {
            final EmailClassification classification = chatClient.prompt()
                    .user(u -> u.text("""
                            邮件: {email}
                            对上述邮件进行重要，不重要分类， 并一步一步的给出分析的原因。
                            """).param("email", email))
                    .call()
                    .entity(EmailClassification.class);

            list.add(classification);
        }

        //分组，并获取最多的那个
        final var group = list.stream().collect(Collectors.groupingBy(EmailClassification::classification, Collectors.counting()));
        final var result = group.entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).orElseThrow();
        log.info("list: {}, result: {}", list, result);
    }

    /**
     * 思维树
     * 是一种高级推理框架，通过同时探索多个推理路径来扩展思维链。它将问题解决视为一个搜索过程，模型生成不同的中间步骤，评估它们的潜力，并探索最有前途的路径。
     * 中间结果：
     * 当然！在国际象棋的初始布局下，以下是三种不同的可行开局棋步：
     * ---
     * ### **开局 1：1. e4**
     * 1. **代数记谱法**：e4
     * 2. **战略思想**：
     * - 将白方的国王兵向前推进两格，控制中心（d4和e5格）。
     * - 为棋子（特别是王后和王翼象）打开通路，从而加快发展速度。
     * - 这是最常见的开局之一，属于“开放开局”，为快速行动和战术组合提供了可能性。
     * 3. **强度评分**：9/10
     * - 这是经典且强大的开局选择，适合初学者和大师级玩家。它控制中心，同时保持灵活性，几乎没有明显的弱点。
     * ---
     * ### **开局 2：1. d4**
     * 1. **代数记谱法**：d4
     * 2. **战略思想**：
     * - 将白方的皇后兵推进两格，控制中心（d4和c5格）。
     * - 为棋子（尤其是后翼象）打开通路，同时保持棋盘的稳健性。
     * - 与e4相比，d4通常会导致“封闭开局”，强调稳健的战略和长远的控制，而不是快速战术。
     * 3. **强度评分**：8.5/10
     * - d4是一种非常强大的开局选择，适合喜欢慢慢积累优势的玩家。它控制中心，且通常会带来更加结构化的棋局。
     * ---
     * ### **开局 3：1. Nf3**
     * 1. **代数记谱法**：Nf3
     * 2. **战略思想**：
     * - 直接发展王翼的马，并控制中心（d4和e5格）。
     * - 为王翼的其他棋子（如象和城堡）打开通路，同时保持灵活性。
     * - Nf3是“灵活开局”的好例子，可以转化为许多不同的布局（如催化剂开局、印度防御等）。
     * 3. **强度评分**：8/10
     * - 这是一个非常实用的开局选择，适合喜欢灵活变化的玩家。虽然对中心的控制略少于e4或d4，但它为后续棋步提供了广泛的可能性。
     * ---
     * 以上三步棋都是非常经典的开局选择，在顶级比赛中经常出现。根据你的风格和偏好，可以选择其中之一进行尝试！
     * <p>
     * 最终结果：
     * 在分析这三种开局选择时，我们需要综合考虑棋盘控制力、子力发展潜力以及长期战略优势。以下是详细的分析过程：
     * ---
     * ### **开局 1：1. e4**
     * 1. **棋盘控制力**：
     * - e4直接控制中心的两个重要格子（d4和e5），在开局阶段争夺中心的优势非常明显。
     * - 通过这一步，白方在中央建立了强势，同时为后续子力的发展提供了空间。
     * 2. **子力发展潜力**：
     * - e4为王翼象和王后打开了通路，这意味着白方可以快速发展子力。
     * - 该开局的开放性质为接下来的棋步提供了灵活性，白方可以选择多种经典布局，例如意大利开局、西班牙开局等。
     * 3. **长期战略优势**：
     * - e4的开放性质使得白方能够迅速建立进攻态势，同时保持对中心的控制。
     * - 缺点是国王兵的推进可能会在后续对局中为对方提供攻击目标（例如暴露f2格），但这个缺点在高手对局中通常可以通过准确的子力发展来弥补。
     * ---
     * ### **开局 2：1. d4**
     * 1. **棋盘控制力**：
     * - d4也直接控制中心的两个重要格子（d4和c5），但与e4相比，它更加稳健。
     * - 白方选择d4后，通常会形成一个封闭的棋局，从而减少对手快速战术的可能性。
     * 2. **子力发展潜力**：
     * - d4为后翼象和王后打开了通路，同时保持棋盘的稳健性。
     * - 与e4相比，d4的子力发展速度稍慢，但它强调结构的完整性和逐步积累优势的能力。
     * 3. **长期战略优势**：
     * - d4通常会导致更加结构化的棋局，适合喜欢慢慢积累优势的玩家。
     * - 如果对方选择不对称的防御（例如印度防御或格林菲尔德防御），白方可能需要更高的技术水平来处理复杂局面。
     * ---
     * ### **开局 3：1. Nf3**
     * 1. **棋盘控制力**：
     * - Nf3间接控制中心（d4和e5），但与e4或d4相比，这一步对中心的直接影响较小。
     * - 这一开局保持了棋盘的灵活性，同时避免过早暴露白方的战略意图。
     * 2. **子力发展潜力**：
     * - Nf3直接发展了王翼的马，为后续的子力发展（例如推进g3和fianchetto象）提供了可能性。
     * - 这一开局适合喜欢灵活布局的玩家，可以转化为多种体系，例如催化剂开局或其他印度防御体系。
     * 3. **长期战略优势**：
     * - Nf3的灵活性使白方能够根据对手的反应选择不同的战略方向。
     * - 这一开局通常不会直接争夺中心，但通过逐步发展子力可以在中后期建立强大的控制力。
     * ---
     * ### **综合比较**
     * #### **棋盘控制力**：
     * - e4 > d4 > Nf3
     * e4直接控制中心格子并为后续发展提供了开放性，而d4虽然稳健，但略显保守。Nf3的间接控制中心较弱。
     * #### **子力发展潜力**：
     * - e4 > d4 ≈ Nf3
     * e4为王翼的象和王后打开了通路，发展速度最快。d4和Nf3的子力发展略慢，但Nf3的灵活性稍占优势。
     * #### **长期战略优势**：
     * - d4 > e4 ≈ Nf3
     * d4的稳健性和结构化特点适合喜欢积累优势的玩家，而e4和Nf3更倾向于灵活性和快速行动。
     * ---
     * ### **最终决策：最佳棋步**
     * 从综合考虑来看，**1. e4**是最佳开局选择。理由如下：
     * 1. **棋盘控制力**：直接控制中心格子，建立强势。
     * 2. **子力发展潜力**：快速打开通路，为后续棋子发展提供灵活性。
     * 3. **长期战略优势**：虽然开放性质可能带来潜在风险，但白方可以通过准确的子力发展来弥补。
     * <p>
     * **推荐开局棋步：1. e4**
     *
     */
    public void pt_tree_of_thought() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.7)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final var initialMoves = chatClient.prompt("""
                你正在下一盘国际象棋，棋盘处于初始布局。请生成三种不同的可行开局棋步。对于每一步：
                1. 使用代数记谱法描述棋步
                2. 解释该步棋背后的战略思想
                3. 以1-10分评估该步棋的强度
                """).call().content();

        log.info("initialMoves: {}", initialMoves);

        final var bestMove = chatClient.prompt()
                .user(u -> u.text("""
                        分析以下开局棋步并选出最优选项：
                        {moves}
                        
                        请逐步解释你的推理过程，需综合考虑：
                        1. 棋盘控制力
                        2. 子力发展潜力
                        3. 长期战略优势
                        最终选出唯一的最佳棋步。
                        """).param("moves", Objects.requireNonNull(initialMoves)))
                .call()
                .content();
        log.info("bestMove: {}", bestMove);
    }

    /**
     * 自动提示工程
     * 使用 AI 来生成和评估替代提示。这种元技术利用语言模型本身来创建、完善和基准测试不同的提示变体，以找到特定任务的最佳表述。
     * <p>
     * 中间结果：
     * 1. 我想买一件Metallica乐队的小号T恤。
     * 2. 请帮我下单一件Metallica的S码T恤。
     * 3. 我需要一件Metallica乐队的S码短袖。
     * 4. 给我来一件Metallica乐队的小码T恤。
     * 5. 我要订购一件Metallica S号的T恤。
     * 6. 请给我准备一件Metallica乐队的S码T恤衫。
     * 7. 我想选购一件Metallica牌子的S号T恤。
     * 8. 我想订一件Metallica品牌的小尺寸T恤。
     * 9. 帮我买一件属Metallica乐队的S码短袖上衣。
     * 10. 我需要一件Metallica标志的S码T恤衫。
     * 最终结果：
     * BLEU（双语评估替补）是一种评估机器翻译质量的方法，它通过比较生成的候选翻译与参考翻译之间的匹配程度来给出分数。BLEU使用n-gram匹配，因此评估越多的词序列与参考对齐的情况，得分越高。为了正确计算BLEU分数，需要参考翻译和多个候选翻译。但在这类单一式的比较中，我们尝试找到与参考最具相似性的候选方案。
     * 这里的候选句子都是表达购买Metallica品牌S码T恤的不同变体，因此可以将第一个句子视为参考句，评估其它句式的相似性。
     * 以下是评价过程（手动模拟方式）：
     * 1. **参考句**:
     * 我想买一件Metallica乐队的小号T恤。
     * 2. **候选句**（所有其它语句）与参考句的匹配程度：
     * - **句子2**: 请帮我下单一件Metallica的S码T恤。
     * - 与参考句匹配的词主要有 "Metallica"、"T恤"，表达形式有差异，但核心词汇有部分匹配。
     * - 评分：中等偏低。
     * - **句子3**: 我需要一件Metallica乐队的S码短袖。
     * - "Metallica乐队"完全匹配，"S码"和"短袖"是接近的同义表达。整体较接近参考句思想。
     * - 评分：较高。
     * - **句子4**: 给我来一件Metallica乐队的小码T恤。
     * - "Metallica乐队"完全匹配，"小码T恤"与“小号T恤”是完全对齐的同义表达。
     * - 评分：高。
     * - **句子5**: 我要订购一件Metallica S号的T恤。
     * - "Metallica"和"T恤"匹配，"订购"与"买"是较接近的同义，但“S号”与“小号”有轻微差异。
     * - 评分：中等偏高。
     * - **句子6**: 请给我准备一件Metallica乐队的S码T恤衫。
     * - "Metallica乐队"完全匹配，"S码T恤衫"与参考句意义接近，但词汇略复杂。
     * - 评分：中等。
     * - **句子7**: 我想选购一件Metallica牌子的S号T恤。
     * - "Metallica牌子"是较弱的匹配，因为 "乐队" 严格表述强于 "牌子"，其余词汇相对匹配。
     * - 评分：中等偏低。
     * - **句子8**: 我想订一件Metallica品牌的小尺寸T恤。
     * - "Metallica品牌"匹配程度较弱，"小尺寸T恤"是同义的表达，但稍显复杂。
     * - 评分：中等。
     * - **句子9**: 帮我买一件属Metallica乐队的S码短袖上衣。
     * - "Metallica乐队"对齐，"S码短袖上衣"是接近同义词表达，但句式略复杂。
     * - 评分：中等偏高。
     * - **句子10**: 我需要一件Metallica标志的S码T恤衫。
     * - "Metallica标志"是离开“品牌”或“乐队”的更弱表达，但“S码T恤衫”意义接近。
     * - 评分：中等偏低。
     * ### 综合评分和分析：
     * 得分最高的指令候选是在与参考句完全对齐、同义表达直观流畅的情况下：
     * - **句子4**: 给我来一件Metallica乐队的小码T恤。
     * 直接同义替换，整体结构最简单且词汇与参考句一致。
     */
    public void automatic_prompt_engineering() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(1.0)
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final var orderVariants = chatClient.prompt().user("""
                我们有一个乐队周边T恤网店，为了训练聊天机器人，我们需要多种订购表达方式："一件Metallica乐队S码T恤"。请生成10种语义相同但表达方式不同的变体。
                """).call().content();

        log.info("orderVariants: {}", orderVariants);

        final String output = chatClient.prompt().user(u -> u.text("""
                请对以下变体进行BLEU（双语评估替补）评估：
                {variants}
                选择评估得分最高的指令候选方案。
                """).param("variants", Objects.requireNonNull(orderVariants))).call().content();

        log.info("output: {}", output);
    }

    /**
     * 代码提示: 是指用于代码相关任务的专门技术。这些技术利用大型语言模型理解和生成编程语言的能力，使它们能够编写新代码、解释现有代码、调试问题以及在语言之间进行翻译。
     * 生成代码结果：
     * 以下是一个 Bash 代码片段，可以实现你的需求：
     * <p>
     * ```bash
     * #!/bin/bash
     * <p>
     * # 提示用户输入文件夹名称
     * read -p "请输入文件夹名称: " folder
     * <p>
     * # 检查文件夹是否存在
     * if [[ ! -d "$folder" ]]; then
     * echo "文件夹不存在，请检查输入的路径。"
     * exit 1
     * fi
     * <p>
     * # 遍历文件夹中的所有文件
     * for file in "$folder"/*; do
     * # 检查是否是文件（排除子文件夹）
     * if [[ -f "$file" ]]; then
     * # 获取文件的目录和文件名
     * dir=$(dirname "$file")
     * filename=$(basename "$file")
     * <p>
     * # 重命名文件，添加 "draft" 前缀
     * mv "$file" "$dir/draft$filename"
     * fi
     * done
     * <p>
     * echo "文件重命名完成！"
     * ```
     * <p>
     * ### 使用说明：
     * 1. 将上述代码保存到一个文件中，例如 `rename_files.sh`。
     * 2. 赋予脚本执行权限：
     * ```bash
     * chmod +x rename_files.sh
     * ```
     * 3. 运行脚本：
     * ```bash
     * ./rename_files.sh
     * ```
     * 4. 按提示输入文件夹名称，脚本会自动将文件夹内的所有文件重命名，添加 `draft` 前缀。
     * <p>
     * ### 注意事项：
     * - 如果文件夹中包含子文件夹，子文件夹不会被重命名。
     * - 如果文件夹中已经存在以 `draft` 开头的文件名，可能会导致文件名冲突，请确保文件名唯一性。
     */
    public void pt_code_prompt_writing_code() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.1) //温度设置通常较低（0.1-0.3），以获得更确定性的输出。
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final String content = chatClient.prompt("""
                编写一个 Bash 代码片段，要求输入一个文件夹名称。
                然后获取该文件夹内的所有内容，并通过在文件名前添加 "draft" 来重命名其中的所有文件。
                """).call().content();

        log.info("content: {}", content);

    }

    /**
     * 解释代码
     * 代码解释是代码提示的一种变体，它要求模型解释代码片段的功能。这种技术利用语言模型来解释代码片段，以帮助开发人员理解代码的意图和功能。
     * 结果：
     * 这个 Bash 脚本的功能是将指定文件夹中的所有文件重命名，并在文件名的前面加上前缀 `draft_`。以下是代码的详细解释：
     * ---
     * ### 代码逐行解析：
     * 1. **`#!/bin/bash`**
     * 指定脚本的解释器为 Bash。
     * 2. **`echo "Enter the folder name: "`**
     * 输出提示信息，要求用户输入文件夹名称。
     * 3. **`read folder_name`**
     * 从用户输入中读取文件夹名称，并将其存储在变量 `folder_name` 中。
     * 4. **`if [ ! -d "$folder_name" ]; then`**
     * 检查用户输入的文件夹是否存在。
     * - `[ ! -d "$folder_name" ]` 表示如果文件夹不存在（`-d` 用于检查是否为目录，`!` 表示取反）。
     * 5. **`echo "Folder does not exist."`**
     * 如果文件夹不存在，输出错误信息。
     * 6. **`exit 1`**
     * 退出脚本，并返回状态码 `1`（表示错误）。
     * 7. **`files=( "$folder_name"/* )`**
     * 使用通配符 `*` 获取文件夹中的所有文件，并将它们存储到数组 `files` 中。
     * - `"$folder_name"/*` 表示文件夹中的所有文件路径。
     * 8. **`for file in "${files[@]}"; do`**
     * 使用 `for` 循环遍历数组 `files` 中的每个文件。
     * 9. **`new_file_name="draft_$(basename "$file")"`**
     * 为当前文件生成新的文件名：
     * - `basename "$file"` 提取文件的基本名称（去掉路径部分）。
     * - `draft_$(basename "$file")` 在文件名前加上前缀 `draft_`。
     * 10. **`mv "$file" "$new_file_name"`**
     * 使用 `mv` 命令将文件重命名为新的文件名。
     * 11. **`done`**
     * 循环结束。
     * 12. **`echo "Files renamed successfully."`**
     * 输出提示信息，表示文件重命名操作已成功完成。
     * ---
     * ### 示例运行过程：
     * 假设文件夹结构如下：
     * ```
     * my_folder/
     * ├── file1.txt
     * ├── file2.txt
     * ├── image.png
     * ```
     * 运行脚本后：
     * 1. 用户输入文件夹名称，例如 `my_folder`。
     * 2. 脚本检查文件夹是否存在。如果不存在，输出错误信息并退出。
     * 3. 如果文件夹存在，脚本将文件夹中的文件依次重命名：
     * - `file1.txt` → `draft_file1.txt`
     * - `file2.txt` → `draft_file2.txt`
     * - `image.png` → `draft_image.png`
     * 4. 输出提示信息 `Files renamed successfully.`。
     * ---
     * ### 注意事项：
     * 1. **文件夹路径**：用户输入的文件夹路径必须正确，否则脚本会提示文件夹不存在并退出。
     * 2. **文件类型**：脚本只处理文件夹中的文件，不会处理子文件夹。
     * 3. **覆盖问题**：如果目标文件名（例如 `draft_file1.txt`）已存在，`mv` 命令会覆盖原文件，因此需要注意避免文件丢失。
     * 4. **扩展性**：可以进一步改进脚本，例如添加对子文件夹的递归处理或处理文件名冲突的逻辑。
     */
    public void pt_code_prompting_explaining_code() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.1) //温度设置通常较低（0.1-0.3），以获得更确定性的输出。
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final String code = """
                #!/bin/bash
                echo "Enter the folder name: "
                read folder_name
                if [ ! -d "$folder_name" ]; then
                echo "Folder does not exist."
                exit 1
                fi
                files=( "$folder_name"/* )
                for file in "${files[@]}"; do
                new_file_name="draft_$(basename "$file")"
                mv "$file" "$new_file_name"
                done
                echo "Files renamed successfully."
                """;

        final String content = chatClient.prompt().user(u -> u.text("""
                解释以下 Bash 代码片段的功能：
                {code}
                """).param("code", code)).call().content();

        log.info("content: {}", content);

    }

    /**
     * 以下是将 Bash 代码片段翻译为 Python 代码的版本：
     * ```python
     * import os
     * import sys
     * # 提示用户输入文件夹名称
     * folder_name = input("Enter the folder name: ")
     * # 检查文件夹是否存在
     * if not os.path.isdir(folder_name):
     * print("Folder does not exist.")
     * sys.exit(1)
     * # 获取文件夹中的所有文件
     * files = os.listdir(folder_name)
     * # 遍历文件并重命名
     * for file in files:
     * old_file_path = os.path.join(folder_name, file)
     * if os.path.isfile(old_file_path):  # 确保是文件而不是子目录
     * new_file_name = f"draft_{file}"
     * new_file_path = os.path.join(folder_name, new_file_name)
     * os.rename(old_file_path, new_file_path)
     * print("Files renamed successfully.")
     * ```
     * ### 代码说明：
     * 1. 使用 `os.path.isdir` 检查文件夹是否存在。
     * 2. 使用 `os.listdir` 获取文件夹中的所有文件和子目录。
     * 3. 使用 `os.path.isfile` 确保只处理文件，而不是子目录。
     * 4. 使用 `os.rename` 重命名文件。
     * 5. 如果文件夹不存在，程序会打印错误消息并退出。
     * 运行此代码时，确保 Python 环境中具有对目标文件夹的读写权限。
     */
    public void pt_code_prompting_translating_code() {
        final var openAiOptions = OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.1) //温度设置通常较低（0.1-0.3），以获得更确定性的输出。
                .build();

        builder.defaultOptions(openAiOptions);
        final var chatClient = builder.build();

        final String code = """
                #!/bin/bash
                echo "Enter the folder name: "
                read folder_name
                if [ ! -d "$folder_name" ]; then
                echo "Folder does not exist."
                exit 1
                fi
                files=( "$folder_name"/* )
                for file in "${files[@]}"; do
                new_file_name="draft_$(basename "$file")"
                mv "$file" "$new_file_name"
                done
                echo "Files renamed successfully."
                """;

        final String content = chatClient.prompt().user(u -> u.text("""
                将以下 Bash 代码片段翻译为 Python 代码：
                {code}
                """).param("code", code)).call().content();

        log.info("content: {}", content);

    }
}

