Java 8 中，你可以为接口添加静态方法和默认方法。从技术角度来说，这是完全合法的，只是它看起来违反了接口作为一个抽象定义的理念。

> 静态方法：

使用 static 关键字修饰。可以通过接口直接调用静态方法，并执行其方法体。我们经常在相互一起使用的类中使用静态方法。你可以在标准库中找到像 Collection/Collections 或者 Path/Paths 这样成对的接口和类。

> 默认方法：

默认方法使用 default 关键字修饰。可以通过实现类对象来调用。我们在已有的接口中提供新方法的同时，还保持了与旧版本代码的兼容性。比如：java 8 API 中对 Collection、List、Comparator 等接口提供了丰富的默认方法。

> 例1

interface 类

```java
/*
 * JDK8:除了全局常量和抽象方法之外，还可以定义静态方法、默认方法(略)。
 * 
 * 
 */
public interface CompareA { 

  //静态方法
  public static void method1() { 
    System.out.println("CompareA:西安");
  }

  //默认方法
  public default void method2(){ 
    System.out.println("CompareA:深圳");
  }

  default void method3(){ 
    System.out.println("CompareA:杭州");
  }
}
```

SubClassTest 类

```java
public class SubClassTest { 

  public static void main(String[] args) { 
    SubClass s = new SubClass();
//    s.method1();
//    SubClass.method1();
//    知识点 1：接口中定义的静态方法，只能通过接口来调用。
    CompareA.method1();
//    知识点 2：通过实现类的对象，可以调用接口中的默认方法。
//    如果实现类重写了接口中的默认方法，调用时，仍然调用的是重写以后的方法
    s.method2();
//    知识点 3：如果子类(或实现类)继承的父类和实现的接口中声明了同名同参数的默认方法，
//    那么子类在没有重写此方法的情况下，默认调用的是父类中的同名同参数的方法。-->类优先原则
//    知识点 4：如果实现类实现了多个接口，而这多个接口中定义了同名同参数的默认方法，
//    那么在实现类没有重写此方法的情况下，报错。-->接口冲突。
//    这就需要我们必须在实现类中重写此方法
    s.method3();

  }
}
class SubClass extends SuperClass implements CompareA,CompareB{ 

  public void method2(){ 
    System.out.println("SubClass：上海");
  }

  public void method3(){ 
    System.out.println("SubClass：深圳");
  }

//  知识点 5：如何在子类(或实现类)的方法中调用父类、接口中被重写的方法
  public void myMethod(){ 
    method3(); //调用自己定义的重写的方法
    super.method3(); //调用的是父类中声明的
//    调用接口中的默认方法
    CompareA.super.method3();
    CompareB.super.method3();
  }
}
```

SuperClass 类

```java
public class SuperClass { 
  public void method3(){ 
    System.out.println("SuperClass:北京");
  }
}
12345
```

CompareB 类

```java
public interface CompareB { 
  default void method3(){ 
    System.out.println("CompareB：上海");
  }
}
12345
```

> 例2

```java
/*
 * 练习：接口冲突的解决方式
 */
interface Filial { // 孝顺的
  default void help() { 
    System.out.println("老妈，我来救你了");
  }
}

interface Spoony { // 痴情的
  default void help() { 
    System.out.println("媳妇，别怕，我来了");
  }
}

class Father{ 
  public void help(){ 
    System.out.println("儿子，救我媳妇！");
  }
}

class Man extends Father implements Filial, Spoony { 

  @Override
  public void help() { 
    System.out.println("我该就谁呢？");
    Filial.super.help();
    Spoony.super.help();
  }

}
```