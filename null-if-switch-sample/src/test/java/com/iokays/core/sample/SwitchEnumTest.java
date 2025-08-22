package com.iokays.core.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SwitchEnumTest {

    @Test
    void test() {
        // old start
        double a = 1;
        double b = 2;
        OperationType type = OperationType.ADD;

        final double result =switch (type) {
            case ADD -> a + b;
            case SUBTRACT -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE -> a / b;
        };
        //old end

        //new start
        final double result2 = type.apply(a, b);
        // new end

        Assertions.assertEquals(result, result2);
    }
}

interface Operation {
    double apply(double a, double b);
}


// 这种枚举有多重变体, 这个是最普通的接口实现. 其他实现 1.枚举类定义一个抽象类, 然后再实现. 2. 或者方法参数化,定义为枚举的参数.
enum OperationType implements Operation {
    ADD {
        @Override
        public double apply(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT  {
        @Override
        public double apply(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY {
        @Override
        public double apply(double a, double b) {
            return a * b;
        }

    },
    DIVIDE {
        @Override
        public double apply(double a, double b) {
            return a / b;
        }
    };
   // abstract public double apply(double a, double b); 抽象方法.
}




