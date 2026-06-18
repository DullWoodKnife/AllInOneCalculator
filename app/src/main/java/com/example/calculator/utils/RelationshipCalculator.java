package com.example.calculator.utils;

import java.util.*;

public class RelationshipCalculator {

    // 关系链中的每个节点
    public static class RelationNode {
        public String relation;      // 关系名称: 父,母,兄,弟,姐,妹,夫,妻,子,女
        public String displayText;   // 显示文本

        public RelationNode(String relation, String displayText) {
            this.relation = relation;
            this.displayText = displayText;
        }
    }

    // 亲戚关系图谱 - 基于中国亲戚称呼系统
    // 使用关系链来推导称呼
    private static final Map<String, Map<String, String>> RELATIONSHIP_MAP = new HashMap<>();

    static {
        // 初始化关系映射
        // 格式: 当前关系 -> {下一步关系 -> 结果称呼}
        initRelationshipMap();
    }

    private static void initRelationshipMap() {
        // 从"我"出发的关系
        Map<String, String> fromMe = new HashMap<>();
        fromMe.put("父", "爸爸");
        fromMe.put("母", "妈妈");
        fromMe.put("兄", "哥哥");
        fromMe.put("弟", "弟弟");
        fromMe.put("姐", "姐姐");
        fromMe.put("妹", "妹妹");
        fromMe.put("夫", "丈夫");
        fromMe.put("妻", "妻子");
        fromMe.put("子", "儿子");
        fromMe.put("女", "女儿");
        RELATIONSHIP_MAP.put("我", fromMe);

        // 爸爸的关系
        Map<String, String> fromFather = new HashMap<>();
        fromFather.put("父", "爷爷");
        fromFather.put("母", "奶奶");
        fromFather.put("兄", "伯父");
        fromFather.put("弟", "叔叔");
        fromFather.put("姐", "姑妈");
        fromFather.put("妹", "姑姑");
        fromFather.put("妻", "妈妈");
        fromFather.put("子", "自己/哥哥/弟弟");
        fromFather.put("女", "自己/姐姐/妹妹");
        RELATIONSHIP_MAP.put("爸爸", fromFather);

        // 妈妈的关系
        Map<String, String> fromMother = new HashMap<>();
        fromMother.put("父", "外公");
        fromMother.put("母", "外婆");
        fromMother.put("兄", "舅舅");
        fromMother.put("弟", "舅舅");
        fromMother.put("姐", "姨妈");
        fromMother.put("妹", "姨妈");
        fromMother.put("夫", "爸爸");
        fromMother.put("子", "自己/哥哥/弟弟");
        fromMother.put("女", "自己/姐姐/妹妹");
        RELATIONSHIP_MAP.put("妈妈", fromMother);

        // 哥哥的关系
        Map<String, String> fromElderBrother = new HashMap<>();
        fromElderBrother.put("父", "爸爸");
        fromElderBrother.put("母", "妈妈");
        fromElderBrother.put("妻", "嫂子");
        fromElderBrother.put("子", "侄子");
        fromElderBrother.put("女", "侄女");
        RELATIONSHIP_MAP.put("哥哥", fromElderBrother);

        // 弟弟的关系
        Map<String, String> fromYoungerBrother = new HashMap<>();
        fromYoungerBrother.put("父", "爸爸");
        fromYoungerBrother.put("母", "妈妈");
        fromYoungerBrother.put("妻", "弟媳");
        fromYoungerBrother.put("子", "侄子");
        fromYoungerBrother.put("女", "侄女");
        RELATIONSHIP_MAP.put("弟弟", fromYoungerBrother);

        // 姐姐的关系
        Map<String, String> fromElderSister = new HashMap<>();
        fromElderSister.put("父", "爸爸");
        fromElderSister.put("母", "妈妈");
        fromElderSister.put("夫", "姐夫");
        fromElderSister.put("子", "外甥");
        fromElderSister.put("女", "外甥女");
        RELATIONSHIP_MAP.put("姐姐", fromElderSister);

        // 妹妹的关系
        Map<String, String> fromYoungerSister = new HashMap<>();
        fromYoungerSister.put("父", "爸爸");
        fromYoungerSister.put("母", "妈妈");
        fromYoungerSister.put("夫", "妹夫");
        fromYoungerSister.put("子", "外甥");
        fromYoungerSister.put("女", "外甥女");
        RELATIONSHIP_MAP.put("妹妹", fromYoungerSister);

        // 丈夫的关系
        Map<String, String> fromHusband = new HashMap<>();
        fromHusband.put("父", "公公");
        fromHusband.put("母", "婆婆");
        fromHusband.put("兄", "大伯子");
        fromHusband.put("弟", "小叔子");
        fromHusband.put("姐", "大姑子");
        fromHusband.put("妹", "小姑子");
        fromHusband.put("妻", "自己");
        fromHusband.put("子", "儿子");
        fromHusband.put("女", "女儿");
        RELATIONSHIP_MAP.put("丈夫", fromHusband);

        // 妻子的关系
        Map<String, String> fromWife = new HashMap<>();
        fromWife.put("父", "岳父");
        fromWife.put("母", "岳母");
        fromWife.put("兄", "大舅子");
        fromWife.put("弟", "小舅子");
        fromWife.put("姐", "大姨子");
        fromWife.put("妹", "小姨子");
        fromWife.put("夫", "自己");
        fromWife.put("子", "儿子");
        fromWife.put("女", "女儿");
        RELATIONSHIP_MAP.put("妻子", fromWife);

        // 儿子的关系
        Map<String, String> fromSon = new HashMap<>();
        fromSon.put("妻", "儿媳");
        fromSon.put("子", "孙子");
        fromSon.put("女", "孙女");
        RELATIONSHIP_MAP.put("儿子", fromSon);

        // 女儿的关系
        Map<String, String> fromDaughter = new HashMap<>();
        fromDaughter.put("夫", "女婿");
        fromDaughter.put("子", "外孙");
        fromDaughter.put("女", "外孙女");
        RELATIONSHIP_MAP.put("女儿", fromDaughter);

        // 爷爷的关系
        Map<String, String> fromGrandfather = new HashMap<>();
        fromGrandfather.put("兄", "伯祖父");
        fromGrandfather.put("弟", "叔祖父");
        RELATIONSHIP_MAP.put("爷爷", fromGrandfather);

        // 奶奶的关系
        Map<String, String> fromGrandmother = new HashMap<>();
        fromGrandmother.put("兄", "舅公");
        fromGrandmother.put("弟", "舅公");
        RELATIONSHIP_MAP.put("奶奶", fromGrandmother);

        // 外公的关系
        Map<String, String> fromMaternalGrandfather = new HashMap<>();
        fromMaternalGrandfather.put("兄", "伯外祖父");
        fromMaternalGrandfather.put("弟", "叔外祖父");
        RELATIONSHIP_MAP.put("外公", fromMaternalGrandfather);

        // 外婆的关系
        Map<String, String> fromMaternalGrandmother = new HashMap<>();
        fromMaternalGrandmother.put("兄", "舅公");
        fromMaternalGrandmother.put("弟", "舅公");
        RELATIONSHIP_MAP.put("外婆", fromMaternalGrandmother);

        // 伯父/叔叔的关系
        Map<String, String> fromUncle = new HashMap<>();
        fromUncle.put("妻", "伯母/婶婶");
        fromUncle.put("子", "堂兄/堂弟");
        fromUncle.put("女", "堂姐/堂妹");
        RELATIONSHIP_MAP.put("伯父", fromUncle);
        RELATIONSHIP_MAP.put("叔叔", fromUncle);

        // 姑妈/姑姑的关系
        Map<String, String> fromAunt = new HashMap<>();
        fromAunt.put("夫", "姑父");
        fromAunt.put("子", "表哥/表弟");
        fromAunt.put("女", "表姐/表妹");
        RELATIONSHIP_MAP.put("姑妈", fromAunt);
        RELATIONSHIP_MAP.put("姑姑", fromAunt);

        // 舅舅的关系
        Map<String, String> fromMaternalUncle = new HashMap<>();
        fromMaternalUncle.put("妻", "舅妈");
        fromMaternalUncle.put("子", "表哥/表弟");
        fromMaternalUncle.put("女", "表姐/表妹");
        RELATIONSHIP_MAP.put("舅舅", fromMaternalUncle);

        // 姨妈的关系
        Map<String, String> fromMaternalAunt = new HashMap<>();
        fromMaternalAunt.put("夫", "姨父");
        fromMaternalAunt.put("子", "表哥/表弟");
        fromMaternalAunt.put("女", "表姐/表妹");
        RELATIONSHIP_MAP.put("姨妈", fromMaternalAunt);

        // 嫂子的关系
        Map<String, String> fromSisterInLaw = new HashMap<>();
        fromSisterInLaw.put("父", "爸爸");
        fromSisterInLaw.put("母", "妈妈");
        RELATIONSHIP_MAP.put("嫂子", fromSisterInLaw);

        // 侄子的关系
        Map<String, String> fromNephew = new HashMap<>();
        fromNephew.put("父", "哥哥/弟弟");
        RELATIONSHIP_MAP.put("侄子", fromNephew);

        // 侄女的关系
        Map<String, String> fromNiece = new HashMap<>();
        fromNiece.put("父", "哥哥/弟弟");
        RELATIONSHIP_MAP.put("侄女", fromNiece);

        // 外甥的关系
        Map<String, String> fromMaternalNephew = new HashMap<>();
        fromMaternalNephew.put("母", "姐姐/妹妹");
        RELATIONSHIP_MAP.put("外甥", fromMaternalNephew);

        // 外甥女的关系
        Map<String, String> fromMaternalNiece = new HashMap<>();
        fromMaternalNiece.put("母", "姐姐/妹妹");
        RELATIONSHIP_MAP.put("外甥女", fromMaternalNiece);

        // 孙子的关系
        Map<String, String> fromGrandson = new HashMap<>();
        fromGrandson.put("父", "儿子");
        RELATIONSHIP_MAP.put("孙子", fromGrandson);

        // 孙女的关系
        Map<String, String> fromGranddaughter = new HashMap<>();
        fromGranddaughter.put("父", "儿子");
        RELATIONSHIP_MAP.put("孙女", fromGranddaughter);

        // 外孙的关系
        Map<String, String> fromMaternalGrandson = new HashMap<>();
        fromMaternalGrandson.put("母", "女儿");
        RELATIONSHIP_MAP.put("外孙", fromMaternalGrandson);

        // 外孙女的关系
        Map<String, String> fromMaternalGranddaughter = new HashMap<>();
        fromMaternalGranddaughter.put("母", "女儿");
        RELATIONSHIP_MAP.put("外孙女", fromMaternalGranddaughter);
    }

    /**
     * 计算亲戚称呼
     * @param relationChain 关系链，如 ["母", "子"] 表示"妈妈的儿子"
     * @return 对应的称呼
     */
    public static String calculateRelationship(List<String> relationChain) {
        if (relationChain == null || relationChain.isEmpty()) {
            return "";
        }

        String current = "我";
        StringBuilder relationPath = new StringBuilder("我的");

        for (int i = 0; i < relationChain.size(); i++) {
            String relation = relationChain.get(i);
            Map<String, String> relations = RELATIONSHIP_MAP.get(current);

            if (relations == null) {
                return "关系太复杂，无法计算";
            }

            String next = relations.get(relation);
            if (next == null) {
                // 尝试反向查找
                next = findReverseRelation(current, relation);
                if (next == null) {
                    return "未知关系";
                }
            }

            // 添加关系路径显示
            String relationName = getRelationDisplayName(relation);
            if (i > 0) {
                relationPath.append("的");
            }
            relationPath.append(relationName);

            current = next;

            // 处理"自己"的情况
            if (current.contains("自己")) {
                if (i == relationChain.size() - 1) {
                    return relationPath.toString() + "\n" + current;
                }
                // 如果还有后续关系，需要特殊处理
                current = handleSelfContinuation(relationChain, i + 1);
                if (current == null) {
                    return relationPath.toString() + "\n" + "关系太复杂";
                }
                break;
            }
        }

        return relationPath.toString() + "\n" + current;
    }

    /**
     * 处理包含"自己"后的延续关系
     */
    private static String handleSelfContinuation(List<String> relationChain, int startIndex) {
        // 简化处理：从自己出发继续计算
        String current = "我";
        for (int i = startIndex; i < relationChain.size(); i++) {
            String relation = relationChain.get(i);
            Map<String, String> relations = RELATIONSHIP_MAP.get(current);
            if (relations == null) return null;

            String next = relations.get(relation);
            if (next == null) return null;
            current = next;
        }
        return current;
    }

    /**
     * 反向查找关系
     */
    private static String findReverseRelation(String current, String relation) {
        // 一些特殊关系的反向查找
        if (current.equals("自己") || current.contains("自己")) {
            Map<String, String> fromMe = RELATIONSHIP_MAP.get("我");
            if (fromMe != null) {
                return fromMe.get(relation);
            }
        }
        return null;
    }

    /**
     * 获取关系的显示名称
     */
    private static String getRelationDisplayName(String relation) {
        switch (relation) {
            case "父": return "爸爸";
            case "母": return "妈妈";
            case "兄": return "哥哥";
            case "弟": return "弟弟";
            case "姐": return "姐姐";
            case "妹": return "妹妹";
            case "夫": return "丈夫";
            case "妻": return "妻子";
            case "子": return "儿子";
            case "女": return "女儿";
            default: return relation;
        }
    }

    /**
     * 获取关系按钮的显示文本
     */
    public static String getButtonText(String relation) {
        return getRelationDisplayName(relation);
    }

    /**
     * 简化版计算 - 直接返回称呼
     */
    public static String getSimpleResult(List<String> relationChain) {
        if (relationChain == null || relationChain.isEmpty()) {
            return "";
        }

        String current = "我";

        for (int i = 0; i < relationChain.size(); i++) {
            String relation = relationChain.get(i);
            Map<String, String> relations = RELATIONSHIP_MAP.get(current);

            if (relations == null) {
                return "关系太复杂";
            }

            String next = relations.get(relation);
            if (next == null) {
                next = findReverseRelation(current, relation);
                if (next == null) {
                    return "未知关系";
                }
            }

            current = next;

            if (current.contains("自己")) {
                if (i == relationChain.size() - 1) {
                    return current;
                }
                current = handleSelfContinuation(relationChain, i + 1);
                if (current == null) {
                    return "关系太复杂";
                }
                break;
            }
        }

        return current;
    }
}
