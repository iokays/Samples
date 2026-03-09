package com.iokays.sample.builder.javabeans;

/**
 * JavaBeans 模式示例
 * 
 * 缺点：
 * 1. 构造过程中对象可能处于不一致状态
 * 2. 无法保证必需参数被设置
 * 3. 排除了让类不可变的可能性
 * 4. 需要额外的线程安全措施
 */
public class NutritionFactsJavaBeans {
    // Parameters initialized to default values (if any)
    private int servingSize = -1;  // Required; no default value
    private int servings = -1;     // Required; no default value
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public NutritionFactsJavaBeans() { }

    // Setters
    public void setServingSize(int val) { servingSize = val; }
    public void setServings(int val) { servings = val; }
    public void setCalories(int val) { calories = val; }
    public void setFat(int val) { fat = val; }
    public void setSodium(int val) { sodium = val; }
    public void setCarbohydrate(int val) { carbohydrate = val; }

    @Override
    public String toString() {
        return String.format("NutritionFactsJavaBeans{servingSize=%d, servings=%d, calories=%d, fat=%d, sodium=%d, carbohydrate=%d}",
                servingSize, servings, calories, fat, sodium, carbohydrate);
    }

    // Getters
    public int getServingSize() { return servingSize; }
    public int getServings() { return servings; }
    public int getCalories() { return calories; }
    public int getFat() { return fat; }
    public int getSodium() { return sodium; }
    public int getCarbohydrate() { return carbohydrate; }
}
