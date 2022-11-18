Reflection（反射）是被视为动态语言的关键，反射机制允许程序在执行期间借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法。

加载完类之后，在堆内存的方法区中就产生了一个Class类型的对象（一个类只有一个Class对象），这个对象就包含了完整的类的结构信息。我们可以通过这个对象看到类的结构。这个对象就像一面镜子，透过这个镜子看到类的结构，所以，我们形象的称之为：反射。

框架 = 反射 + 注解 + 设计模式

> 反射的优缺点

**优点**：可以动态的创建和使用对象(也是框架底层核心),比较灵活，没有反射机制，框架技术就失去底层支持。

**缺点**：使用反射基本上是解释执行，对执行速度有影响。



![img](./assets/image.png)

> 1、动态语言

**是一类在运行时可以改变其结构的语言**：例如新的函数、对象、甚至代码可以被引进，已有的函数可以被删除或是其他结构上的变化。通俗点说就是在运行时代码可以根据某些条件改变自身结构。主要动态语言：Object-C、C#、JavaScript、PHP、Python、Erlang。

> 2、静态语言

与动态语言相对应的，**运行时结构不可变的语言就是静态语言**。如Java、C、C++。

- Java不是动态语言，但Java可以称之为“准动态语言”。即Java有一定的动态性，我们可以利用反射机制、字节码操作获得类似动态语言的特性。Java的动态性让编程的时候更加灵活！
- Java反射机制提供的功能
  - 在运行时判断任意一个对象所属的类
  - 在运行时构造任意一个类的对象
  - 在运行时判断任意一个类所具有的成员变量和方法
  - 在运行时调用任意一个对象的成员变量和方法
  - 在运行时获取泛型信息
  - 在运行时处理注解
  - 生成动态代理
  - 可以调用私有成员
- 反射相关的主要API
  - `java.lang.Class`:代表一个类
  - `java.lang.reflect.Method`:代表类的方法
  - `java.lang.reflect.Field`:代表类的成员变量
  - `java.lang.reflect.Constructor`:代表类的构造器

> 测试类

```java
import org.junit.Test;

public class ReflectionTest { 

    //反射之前，对于Person的操作
    @Test
    public void test(){ 

        //1.创建类的对象
        Person p1 = new Person("jay",21);

        //2.调用对象,调用其内部的属性和方法
        p1.age = 15;
        System.out.println(p1.toString());

        p1.show();

        //在Person类的外部，不可以通过Person类的对象调用其内部私有的结构。
        //比如：name、showNation以及私有的构造器。
      
    }
}
```

> Person类

```java
package github;

public class Person { 
    private String name;
    public int age;

    public String getName() { 
        return name;
    }

    public void setName(String name) { 
        this.name = name;
    }

    public int getAge() { 
        return age;
    }

    public void setAge(int age) { 
        this.age = age;
    }

    public Person() { 
    }

    public Person(String name, int age) { 
        this.name = name;
        this.age = age;
    }

    private Person(String name) { 
        this.name = name;
    }

    @Override
    public String toString() { 
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show(){ 
        System.out.println("你好，我是🔔");
    }

    private String showNation(String nation){ 
        System.out.println("喷子实在太多了！！！" + nation);
        return nation;
    }
}
```

## 1.1、使用反射，实现同上的操作

```java
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest { 

    //反射之后 ，堆与Person的操作
    @Test
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException { 
        Class clazz = Person.class;
        //1.通过反射，创建Person类的对象
        //获取构造器
        Constructor cons = clazz.getConstructor(String.class,int.class);
        //创建实例
        Object obj = cons.newInstance("Jon",18);
        Person p = (Person) obj;
        System.out.println(p.toString());
        //2.通过反射，调用对象指定的属性和方法
        //调用公共属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);
        System.out.println(p.toString());

        //调用公共方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

    }
}
```

## 1.2、反射的强大：调用类的私有结构

```text
1.Method、Field、Constructor对象都有setAccessible()方法
2.setAccessible作用是启动和禁用安全检查的开关
3.参数值为true表示反射的对象在使用时取消访问检查，提高反射的效率。
同时也能访问私有结构
4.参数值为false则表示反射的对象执行访问检查。
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest { 
     //反射之后 ，堆与Person的操作
    @Test
    public void test2() throws Exception{ 
       
        System.out.println("+++++++++++++++++++++++++");

        //通过反射，是可以调用Person类的私有结构的。比如：私有的构造器、方法、属性
        //调用私有的构造器
        Constructor cons2 = clazz.getDeclaredConstructor(String.class);
        //取消访问检查
        cons2.setAccessible(true);
        Person p1 = (Person) cons2.newInstance("kalo");
        System.out.println(p1);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"Taoyao");
        System.out.println(p1);

        //调用私有的方法
        Method showNation = clazz.getDeclaredMethod("LiNin", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1,"FaceBook");
        //相当于String nation = p1.showNation("FaceBook")
        System.out.println(nation);
    }

    /**
     * 疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用那个？
     * 建议：直接new的方式。
     * 什么时候会使用：反射的方式。 反射的特征：动态性
     * 即如果我们在编译的时候不确定要创建谁的对象，我们就可以用反射来解决
     * 例如前台传过来一个信息，我们根据判断来确定是登录还是注册
     * 疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
     * 不矛盾。可以理解为封装是不想让人直接调用，如果非要调用，也可以，即反射
     */
}
```