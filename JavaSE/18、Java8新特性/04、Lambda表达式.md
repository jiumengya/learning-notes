Lambda 是一个匿名函数，我们可以把Lambda [表达式](https://so.csdn.net/so/search?q=表达式&spm=1001.2101.3001.7020)理解为是一段可以传递的代码（将代码像数据一样进行传递）。使用它可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升。

注意：只有接口可以使用`lambda`表达式，且只有一个需要重写的方法体；

## 4.1、Lambda表达式使用举例

```java
import org.junit.Test;

import java.util.Comparator;

/**
 * Lambda表达式的使用举例
 */
public class LambdaTest { 
    @Test
    public void test(){ 
        Runnable r1 = new Runnable() { 
            @Override
            public void run() { 
                System.out.println("长安欢迎您");
            }
        };
        r1.run();

        System.out.println("+++++++++++++++++++++++++|");

        Runnable r2 = () -> System.out.println("长安欢迎您");

        r2.run();
    }

    @Test
    public void test2(){ 
        Comparator<Integer> c1 = new Comparator<Integer>() { 
            @Override
            public int compare(Integer o1, Integer o2) { 
                return Integer.compare(o1,o2);
            }
        };
        int compare1 = c1.compare(8,16);
        System.out.println(compare1);

        System.out.println("+++++++++++++++++++++++");

        //Lambda表达式的写法
        Comparator<Integer> c2 = (o1,o2) -> Integer.compare(o1,o2);

        int compare2 = c2.compare(28,35);
        System.out.println(compare2);

        System.out.println("+++++++++++++++++++++++++++");
        //方法引用
        Comparator<Integer> c3 = Integer :: compare;

        int compare3 = c3.compare(28,35);
        System.out.println(compare3);
    }
}
```

## 4.2、Lambda表达式语法的使用1

```java
import org.junit.Test;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Lambda表达式的使用
 *
 * 1.举例： (o1,o2) -> Integer.compare(o1,o2);
 * 2.格式：
 *      -> :lambda操作符 或 箭头操作符
 *      ->左边：lambda形参列表 （其实就是接口中的抽象方法的形参列表）
 *      ->右边：lambda体 （其实就是重写的抽象方法的方法体）
 *
 * 3.Lambda表达式的使用：（分为6种情况介绍）
 */
public class LambdaTest1 { 

    //语法格式一：无参，无返回值
    @Test
    public void test(){ 
        Runnable r1 = new Runnable() { 
            @Override
            public void run() { 
                System.out.println("长安欢迎您");
            }
        };
        r1.run();

        System.out.println("+++++++++++++++++++++++++|");

        Runnable r2 = () -> System.out.println("长安欢迎您");

        r2.run();
    }

    //语法格式二：Lambda 需要一个参数，但是没有返回值。
    @Test
    public void test2(){ 
        Consumer<String> con = new Consumer<String>() { 
            @Override
            public void accept(String s) { 
                System.out.println(s);
            }
        };
        con.accept("善与恶的区别是什么？");

        System.out.println("+++++++++++++++++++");

        Consumer<String> c1 = (String s) -> { 
            System.out.println(s);
        };
        c1.accept("先天人性无善恶,后天人性有善恶。");
    }

    //语法格式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
    @Test
    public void test3(){ 
        Consumer<String> c1 = (String s) -> { 
            System.out.println(s);
        };
        c1.accept("先天人性无善恶,后天人性有善恶。");

        System.out.println("---------------------");

        Consumer<String> c2 = (s) -> { 
            System.out.println(s);
        };
        c2.accept("如果没有邪恶的话我们怎么会知道人世间的那些善良呢？");
    }

    @Test
    public void test4(){ 
        ArrayList<String> list = new ArrayList<>();//类型推断

        int[] arr = { 1,2,3};//类型推断
    }
}
```

## 4.3、Lambda表达式语法的使用2

```java
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式的使用
 *
 * 1.举例： (o1,o2) -> Integer.compare(o1,o2);
 * 2.格式：
 *      -> :lambda操作符 或 箭头操作符
 *      ->左边：lambda形参列表 （其实就是接口中的抽象方法的形参列表）
 *      ->右边：lambda体 （其实就是重写的抽象方法的方法体）
 *
 * 3.Lambda表达式的使用：（分为6种情况介绍）
 *
 *    总结：
 *    ->左边：lambda形参列表的参数类型可以省略(类型推断)；如果lambda形参列表只有一个参数，其一对()也可以省略
 *    ->右边：lambda体应该使用一对{}包裹；如果lambda体只有一条执行语句（可能是return语句），省略这一对{}和return关键字
 */
public class LambdaTest1 { 

    //语法格式四：Lambda若只需要一个参数时，参数的小括号可以省略
    @Test
    public void test5(){ 
        Consumer<String> c1 = (s) -> { 
            System.out.println(s);
        };
        c1.accept("先天人性无善恶,后天人性有善恶。");

        System.out.println("---------------------");

        Consumer<String> c2 = s -> { 
            System.out.println(s);
        };
        c2.accept("如果没有邪恶的话我们怎么会知道人世间的那些善良呢？");
    }

    //语法格式五：Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test6(){ 
        Comparator<Integer> c1 = new Comparator<Integer>() { 
            @Override
            public int compare(Integer o1, Integer o2) { 
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(c1.compare(15,23));

        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\");

        Comparator<Integer> com2 = (o1,o2) -> { 
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(com2.compare(16,8));
    }

    //语法格式六：当Lambda体只有一条语句时，return与大括号若有，都可以省略
    @Test
    public void test7(){ 
        Comparator<Integer> c1 = (o1,o2) -> { 
            return o1.compareTo(o2);
        };

        System.out.println(c1.compare(16,8));

        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\");

        Comparator<Integer> c2 = (o1,o2) -> o1.compareTo(o2);

        System.out.println(c2.compare(17,24));
    }

    @Test
    public void test8(){ 
        Consumer<String> c1 = s -> { 
            System.out.println(s);
        };
        c1.accept("先天人性无善恶,后天人性有善恶。");

        System.out.println("---------------------");

        Consumer<String> c2 = s -> System.out.println(s);

        c2.accept("如果没有邪恶的话我们怎么会知道人世间的那些善良呢？");
    }
}
```