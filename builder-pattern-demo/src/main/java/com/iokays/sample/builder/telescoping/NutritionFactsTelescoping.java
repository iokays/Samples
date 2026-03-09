package com.iokays.sample.builder.telescoping;

/**
 * 可伸缩构造方法模式示例
 * 
 * 这种模式在参数很多时很难使用：
 * 1. 客户端代码难以阅读，不知道参数的含义
 * 2. 容易出错，相同类型的参数可能写反
 * 3. 编译器无法检测参数顺序错误
 */
public class NutritionFactsTelescoping {
    private final int servingSize;      // (mL)            required
    private final int servings;         // (per container) required
    private final int calories;         // (per serving)   optional
    private final int fat;              // (g/serving)     optional
    private final int sodium;           // (mg/serving)    optional
    private final int carbohydrate;     // (g/serving)     optional

    public NutritionFactsTelescoping(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFactsTelescoping(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFactsTelescoping(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFactsTelescoping(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFactsTelescoping(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    @Override
    public String toString() {
        return String.format("NutritionFactsTelescoping{servingSize=%d, servings=%d, calories=%d, fat=%d, sodium=%d, carbohydrate=%d}",
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
