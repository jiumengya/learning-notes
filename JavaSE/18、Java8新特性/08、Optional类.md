## 8.1、Optional类的介绍

到目前为止，臭名昭著的空指针异常是导致Java应用程序失败的最常见原因。以前，为了解决空指针异常，Google公司著名的Guava项目引入了Optional类，Guava通过使用检查空值的方式来防止代码污染，它鼓励程序员写更干净的代码。受到Google Guava的启发，Optional类已经成为Java 8类库的一部分。

- `Optional` 类(`java.util.Optional`) 是一个容器类，它可以保存类型T的值，代表这个值存在。或者仅仅保存`null`，表示这个值不存在。原来用`null` 表示一个值不存在，现`在Optional` 可以更好的表达这个概念。并且可以避免空指针异常。
- Optional类的Javadoc描述如下：这是一个可以为null的容器对象。如果值存在则`isPresent()`方法会返回`true`，调用`get()`方法会返回该对象。
- Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
- 创建Optional类对象的方法：
  - `Optional.of(T t)`: 创建一个Optional 实例，t必须非空；
  - `Optional.empty()` : 创建一个空的Optional 实例
  - `Optional.ofNullable(T t`)：t可以为null
- 判断Optional容器中是否包含对象：
  - `boolean isPresent()` : 判断是否包含对象
  - `void ifPresent(Consumer<? super T> consumer)` ：如果有值，就执行Consumer接口的实现代码，并且该值会作为参数传给它。
- 获取Optional容器的对象：
  - `T get()`: 如果调用对象包含值，返回该值，否则抛异常
  - `T orElse(T other)` ：如果有值则将其返回，否则返回指定的other对象。
  - `T orElseGet(Supplier<? extends T> other)` ：如果有值则将其返回，否则返回由Supplier接口实现提供的对象。
  - `T orElseThrow(Supplier<? extends X> exceptionSupplier)` ：如果有值则将其返回，否则抛出由Supplier接口实现提供的异常。

> 1、Boy类

```java
public class Boy { 
    private Girl girl;

    public Boy() { 
    }

    public Boy(Girl girl) { 
        this.girl = girl;
    }

    public Girl getGirl() { 
        return girl;
    }

    public void setGirl(Girl girl) { 
        this.girl = girl;
    }

    @Override
    public String toString() { 
        return "Boy{" +
                "girl=" + girl +
                '}';
    }
}
```

> 2、Girl类

```java
public class Girl { 
    private String name;

    public Girl() { 
    }

    public Girl(String name) { 
        this.name = name;
    }

    public String getName() { 
        return name;
    }

    public void setName(String name) { 
        this.name = name;
    }

    @Override
    public String toString() { 
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

> 3、测试类

```java
import org.junit.Test;
import java.util.Optional;

/**
 * Optional类：为了在程序中避免出现空指针异常而创建的。
 *
 * 常用的方法：ofNullable(T t)
 *           orElse(T t)
 */
public class OptionalTest { 
    /**
     * Optional.of(T t) : 创建一个 Optional 实例，t必须非空；
     * Optional.empty() : 创建一个空的 Optional 实例
     * Optional.ofNullable(T t)：t可以为null
     */
    @Test
    public void test(){ 
        Girl girl = new Girl();
//        girl = null;
        //of(T t):保证t是非空的
        Optional<Girl> optionalGirl = Optional.of(girl);
    }

    @Test
    public void test2(){ 
        Girl girl = new Girl();
//        girl = null;
        //ofNullable(T t)：t可以为null
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
        //orElse(T t1):如果单前的Optional内部封装的t是非空的，则返回内部的t.
        //如果内部的t是空的，则返回orElse()方法中的参数t1.
        Girl girl1 = optionalGirl.orElse(new Girl(""));
        System.out.println(girl1);
    }
}
```

## 8.2、Optional类的使用举例

> 1、测试类

```java
import org.junit.Test;
import java.util.Optional;

/**
 * Optional类：为了在程序中避免出现空指针异常而创建的。
 *
 * 常用的方法：ofNullable(T t)
 *           orElse(T t)
 */
public class OptionalTest { 

    @Test
    public void test3(){ 
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }

    private String getGirlName(Boy boy) { 
        return boy.getGirl().getName();
    }

    //优化以后的getGirlName():
    public String getGirlName1(Boy boy){ 
        if(boy != null){ 
            Girl girl = boy.getGirl();
            if(girl != null){ 
                return girl.getName();
            }
        }
        return null;
    }

    @Test
    public void test4(){ 
        Boy boy = new Boy();
        boy = null;
        String girlName = getGirlName1(boy);
        System.out.println(girlName);
    }

    //使用Optional类的getGirlName():
    public String getGirlName2(Boy boy){ 

        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //此时的boy1一定非空
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("朱淑贞")));

        Girl girl = boy1.getGirl();

        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        //girl1一定非空
        Girl girl1 = girlOptional.orElse(new Girl("阿青"));

        return girl1.getName();
    }

    @Test
    public void test5(){ 
        Boy boy = null;
        boy = new Boy();
        boy = new Boy(new Girl("李清照"));
        String girlName = getGirlName2(boy);
        System.out.println(girlName);
    }
}
```