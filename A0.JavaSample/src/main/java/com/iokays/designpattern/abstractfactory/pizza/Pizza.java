package com.iokays.designpattern.abstractfactory.pizza;


/**
 * 披萨
 */
public abstract class Pizza {

    /**
     * 名称
     */
    String name;
    /**
     * 面团
     */
    Dough dough;
    /**
     * 酱料
     */
    Sauce sauce;
    /**
     * 蔬菜
     */
    Veggies veggies[];
    /**
     * 奶酪
     */
    Cheese cheese;
    /**
     * 腊肠
     */
    Pepperoni pepperoni;
    /**
     * 蛤蜊
     */
    Clams clam;

    /**
     * 准备
     */
    abstract void prepare();

    /**
     * 烘烤
     */
    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    /**
     * 切片
     */
    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    /**
     * 装盒
     */
    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    /**
     * 获取名称
     */
    String getName() {
        return name;
    }

    /**
     * 设置名称
     */
    void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("---- ").append(name).append(" ----\n");
        if (dough != null) {
            result.append(dough);
            result.append("\n");
        }
        if (sauce != null) {
            result.append(sauce);
            result.append("\n");
        }
        if (cheese != null) {
            result.append(cheese);
            result.append("\n");
        }
        if (veggies != null) {
            for (Veggies veggie : veggies) {
                result.append(veggie);
                result.append("\n");
            }
        }
        if (clam != null) {
            result.append(clam);
            result.append("\n");
        }
        if (pepperoni != null) {
            result.append(pepperoni);
            result.append("\n");
        }
        return result.toString();
    }


}
