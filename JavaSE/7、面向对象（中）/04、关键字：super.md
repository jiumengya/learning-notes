> Person类

```java
public class Person { 

  String name;
  int age;
  int id = 1003;  //身份证号

  public Person(){ 
    System.out.println("我无处不在");
  }

  public Person(String name){ 
    this.name = name;
  }

  public Person(String name,int age){ 
    this(name);
    this.age = age;
  }

  public void eat(){ 
    System.out.println("人，吃饭");
  }

  public void walk(){ 
    System.out.println("人，走路");
  }
}
```

> Student类

```java
public class Student extends Person{ 

  String major;
  int id = 1002;  //学号

  public Student(){ 

  }

  public Student(String name,int age,String major){ 
//    this.age = age;
//    this.name = name;
    super(name,age);
    this.major = major;
  }

  public Student(String major){ 
    this.major = major;
  }

  public void eat(){ 
    System.out.println("学生多吃有营养的食物");
  }

  public void Study(){ 
    System.out.println("学生，学习知识。");
    this.eat();
    //如果，想调用父类中被重写的，不想调用子类中的方法，可以：
    super.eat();
    super.walk();//子父类中未重写的方法，用"this."或"super."调用都可以
  }
  public void show(){ 
    System.out.println("name = " + this.name + ",age = " + super.age);
    System.out.println("id = " + this.id);
    System.out.println("id = " + super.id);
  }
}
```

> 测试类

```java
/*
 * super关键字的使用
 * 1.super理解为:父类的
 * 2.super可以用来调用:属性、方法、构造器 
 * 
 * 3.super的使用
 *     3.1 我们可以在子类的方法或构造器中，通过"super.属性"或"super.方法"的方式，显式的调用
 *   父类中声明的属性或方法。但是，通常情况下，我们习惯去省略这个"super."
 *     3.2 特殊情况:当子类和父类中定义了同名的属性时，我们要想在子类中调用父类中声明的属性，则必须显式的 
 *  使用"super.属性"的方式，表明调用的是父类中声明的属性。
 *    3.3 特殊情况:当子类重写了父类中的方法后，我们想在子类的方法中调用父类中被重写的方法时，必须显式的
 *  使用"super.方法"的方式，表明调用的是父类中被重写的方法。
 * 
 * 4.super调用构造器
 *     4.1  我们可以在子类的构造器中显式的使用"super(形参列表)"的方式,调用父类中声明的指定的构造器
 *     4.2 "super(形参列表)"的使用，必须声明在子类构造器的首行！
 *    4.3 我们在类的构造器中，针对于"this(形参列表)"或"super(形参列表)"只能二选一，不能同时出现。
 *    4.4 在构造器的首行，既没有显式的声明"this(形参列表)"或"super(形参列表)",则默认的调用的是父类中的空参构造器。super()
 *    4.5 在类的多个构造器中，至少有一个类的构造器使用了"super(形参列表)",调用父类中的构造器。
 *  
 */
public class SuperTest { 
  public static void main(String[] args) { 
  
    Student s = new Student();
    s.show();
  
    s.Study();
  
    Student s1 = new Student("Ton",21,"IT" );
    s1.show();
  
    System.out.println("***********************");
    Student s2 = new Student();
  
  }
}
```