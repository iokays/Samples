package com.iokays.aop.cglib;

import net.sf.cglib.beans.*;
import net.sf.cglib.core.KeyFactory;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.*;
import net.sf.cglib.reflect.*;
import net.sf.cglib.util.ParallelSorter;
import net.sf.cglib.util.StringSwitcher;
import org.objectweb.asm.Type;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CglibSample {

    public String foo() {
        return "foo";
    }

    public String bar() {
        return "bar";
    }

    public static void main(String[] args) throws InvocationTargetException {
        System.out.println("hello world");
    }

    private static void fastClass() throws InvocationTargetException {
        final var fastClass = FastClass.create(SampleBean.class);
        final var fastMethod = fastClass.getMethod("getValue", new Class[0]);
        final SampleBean sampleBean = new SampleBean("foo");

        final var invoke = fastMethod.invoke(sampleBean, new Object[0]);
        System.out.println(invoke);
    }

    private static void parallelSorter() {
        final Integer[][] value = {{4, 3, 9, 0}, {2, 1, 6, 0}};

        ParallelSorter.create(value).mergeSort(0);

        for (Integer[] values : value) {
            for (Integer integer : values) {
                System.out.println(integer);
            }
        }
    }

    private static void constructorDelegate() {
        var constructorDelegate = (SampleBeanConstructorDelegate) ConstructorDelegate.create(SampleBean.class, SampleBeanConstructorDelegate.class);
        SampleBean foo = constructorDelegate.newInstance("foo");
        System.out.println(foo.getValue());
    }

    private static void multicastDelegate() {
        MulticastDelegate multicastDelegate = MulticastDelegate.create(DelegateProvider.class);
        SampleMultiDelegateBean first = new SampleMultiDelegateBean();
        SampleMultiDelegateBean second = new SampleMultiDelegateBean();

        multicastDelegate = multicastDelegate.add(first);
        multicastDelegate = multicastDelegate.add(second);

        DelegateProvider delegateProvider = (DelegateProvider) multicastDelegate;
        delegateProvider.setValue("foo");

        System.out.println(first.getValue());
        System.out.println(second.getValue());
    }

    private static void methodDelegate() {
        SampleBean sampleBean = new SampleBean("boo");
        var foo = (IFoo)MethodDelegate.create(sampleBean, "getValue", IFoo.class);
        System.out.println(foo.foo());
    }

    private static void interfaceMaker() {
        Signature signature = new Signature("foo", Type.DOUBLE_TYPE, new Type[]{Type.INT_TYPE});

        InterfaceMaker interfaceMaker = new InterfaceMaker();
        interfaceMaker.add(signature, new Type[0]);

        Class i = interfaceMaker.create();

        System.out.println(i.getMethods().length);
        System.out.println(i.getMethods()[0].getName());
        System.out.println(i.getMethods()[0].getReturnType());
    }

    private static void stringSwitcher() {
        StringSwitcher ss = StringSwitcher.create(new String[]{"one", "two"}, new int[]{10, 200}, true);
        System.out.println(ss.intValue("one"));
        System.out.println(ss.intValue("two"));
        System.out.println(ss.intValue("three"));
    }

    private static void mixin() {
        Mixin mixin = Mixin.create(new Class[]{IFoo.class, IBar.class, IFB.class}, new Object[]{new Foo(), new Bar()});

        IFB ifb = (IFB) mixin;
        System.out.println(ifb.foo());
        System.out.println(ifb.bar());
    }

    private static void keyFactory() {
        SampleKeyFactory sampleKeyFactory = (SampleKeyFactory) KeyFactory.create(SampleKeyFactory.class);
        Object key = sampleKeyFactory.newInstance("foo");
        Object same_key = sampleKeyFactory.newInstance("foo");
        Object key2 = sampleKeyFactory.newInstance("bar");

        System.out.println(key.equals(same_key));
        System.out.println(key.equals(key2));
    }

    private static void beanMap() {
        BeanMap beanMap = BeanMap.create(new SampleBean("foo"));
        System.out.println(beanMap);
    }

    private static void bulkBean() {
        var bulkBean = BulkBean.create(SampleBean.class, new String[]{"getValue"}, new String[]{"setValue"}, new Class[]{String.class});

        SampleBean bean = new SampleBean("foo");
        System.out.println(bulkBean.getPropertyValues(bean)[0]);
        bulkBean.setPropertyValues(bean, new Object[]{"bar"});
        System.out.println(bulkBean.getPropertyValues(bean)[0]);
    }

    private static void callbackFilter() {
        Enhancer enhancer = new Enhancer();
        CallbackHelper helper = new CallbackHelper(CglibSample.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return (FixedValue) () -> "Hello world";
                } else {
                    return NoOp.INSTANCE;
                }
            }
        };

        enhancer.setSuperclass(CglibSample.class);
        enhancer.setCallbackFilter(helper);
        enhancer.setCallbacks(helper.getCallbacks());

        CglibSample sample = (CglibSample) enhancer.create();
        System.out.println(sample.foo());
        System.out.println(sample.toString());

    }

    private static void immutableBean() {
        var bean = new SampleCopyBean();
        bean.setValue("foo");

        var immutableBean = (SampleCopyBean) ImmutableBean.create(bean);
        bean.setValue("bar");
        //immutableBean.setValue("bar"); //exception
    }

    private static void beanGenerator() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("value", String.class);

        Object bean = beanGenerator.create();
        Method setter = bean.getClass().getMethod("setValue", String.class);
        setter.invoke(bean, "foo");

        Method getter = bean.getClass().getMethod("getValue");

        System.out.println(getter.invoke(bean));
    }

    private static void beanCopier(){
        BeanCopier beanCopier = BeanCopier.create(SampleBean.class, SampleCopyBean.class, false);

        var bean = new SampleBean("foo");

        var copyBean = new SampleCopyBean();

        beanCopier.copy(bean, copyBean, null);
        System.out.println(copyBean.getValue());

    }

}

class SampleMultiDelegateBean implements DelegateProvider {

    private String value;

    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}

class SampleBean {

    private String value;

    public SampleBean() {
    }

    public SampleBean(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

class SampleCopyBean {

    private String value;

    public SampleCopyBean() {
    }

    public SampleCopyBean(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

interface SampleKeyFactory {

    Object newInstance(final String foo);

}

interface IFoo {
    String foo();
}

interface IBar {
    String bar();
}

interface IFB extends IFoo, IBar {

}

class Foo implements IFoo {

    @Override
    public String foo() {
        return "foo";
    }
}

class Bar implements IBar {

    @Override
    public String bar() {
        return "bar";
    }
}

interface SampleBeanConstructorDelegate {
    SampleBean newInstance(final String value);
}
