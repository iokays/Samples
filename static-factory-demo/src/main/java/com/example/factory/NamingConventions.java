package com.example.factory;

/**
 * 静态工厂方法命名规范示例
 * 
 * 演示常用的静态工厂方法命名模式
 */
public class NamingConventions {
    
    /**
     * from - 类型转换方法
     * 接受单个参数并返回此类型的相应实例
     * 
     * 示例：Date.from(instant)
     */
    public static NamingConventions from(String input) {
        return new NamingConventions(input);
    }
    
    /**
     * of - 聚合方法
     * 接受多个参数并返回该类型的实例，将它们合并在一起
     * 
     * 示例：Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
     */
    public static NamingConventions of(String value1, String value2) {
        return new NamingConventions(value1 + value2);
    }
    
    /**
     * valueOf - from 和 of 更为详细的替代方式
     * 
     * 示例：BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
     */
    public static NamingConventions valueOf(int number) {
        return new NamingConventions(String.valueOf(number));
    }
    
    /**
     * getInstance / instance
     * 返回一个由其参数描述的实例
     * 但不能说它具有相同的值
     * 
     * 示例：StackWalker luke = StackWalker.getInstance(options);
     */
    private static NamingConventions instance;
    
    public static NamingConventions getInstance() {
        if (instance == null) {
            instance = new NamingConventions("singleton");
        }
        return instance;
    }
    
    /**
     * create / newInstance
     * 与 instance 或 getInstance 类似
     * 但保证每次调用返回一个新的实例
     * 
     * 示例：Object newArray = Array.newInstance(classObject, arrayLen);
     */
    public static NamingConventions newInstance() {
        return new NamingConventions("new instance");
    }
    
    /**
     * getType - 与 getInstance 类似
     * 但工厂方法处于不同的类中
     * 
     * 示例：FileStore fs = Files.getFileStore(path);
     */
    public static NamingConventions getType() {
        return new NamingConventions("type from factory");
    }
    
    /**
     * newType - 与 newInstance 类似
     * 但工厂方法处于不同的类中
     * 
     * 示例：BufferedReader br = Files.newBufferedReader(path);
     */
    public static NamingConventions newType() {
        return new NamingConventions("new type from factory");
    }
    
    /**
     * type - getType 和 newType 简洁的替代方式
     * 
     * 示例：List<Complaint> litany = Collections.list(legacyLitany);
     */
    public static NamingConventions type() {
        return new NamingConventions("type");
    }
    
    private final String data;
    
    private NamingConventions(String data) {
        this.data = data;
    }
    
    public String getData() {
        return data;
    }
    
    /**
     * 演示各种命名规范
     */
    public static void demonstrateNamingConventions() {
        System.out.println("=== 静态工厂方法命名规范 ===\n");
        
        System.out.println("1. from - 类型转换");
        NamingConventions from = NamingConventions.from("hello");
        System.out.println("NamingConventions.from(\"hello\")");
        System.out.println("结果: " + from.getData() + "\n");
        
        System.out.println("2. of - 聚合");
        NamingConventions of = NamingConventions.of("hello", "world");
        System.out.println("NamingConventions.of(\"hello\", \"world\")");
        System.out.println("结果: " + of.getData() + "\n");
        
        System.out.println("3. valueOf - 详细描述");
        NamingConventions valueOf = NamingConventions.valueOf(42);
        System.out.println("NamingConventions.valueOf(42)");
        System.out.println("结果: " + valueOf.getData() + "\n");
        
        System.out.println("4. getInstance - 返回单例");
        NamingConventions instance1 = NamingConventions.getInstance();
        NamingConventions instance2 = NamingConventions.getInstance();
        System.out.println("NamingConventions.getInstance()");
        System.out.println("instance1 == instance2: " + (instance1 == instance2));
        System.out.println("结果: " + instance1.getData() + "\n");
        
        System.out.println("5. newInstance - 保证新实例");
        NamingConventions newInstance1 = NamingConventions.newInstance();
        NamingConventions newInstance2 = NamingConventions.newInstance();
        System.out.println("NamingConventions.newInstance()");
        System.out.println("newInstance1 == newInstance2: " + (newInstance1 == newInstance2));
        System.out.println("结果: " + newInstance1.getData() + "\n");
        
        System.out.println("6. getType - 来自其他工厂");
        System.out.println("Files.getFileStore(path)");
        System.out.println("返回 FileStore 实例\n");
        
        System.out.println("7. newType - 来自其他工厂");
        System.out.println("Files.newBufferedReader(path)");
        System.out.println("返回新的 BufferedReader 实例\n");
        
        System.out.println("8. type - 简洁形式");
        System.out.println("Collections.list(enumeration)");
        System.out.println("返回 ArrayList 实例\n");
    }
}
