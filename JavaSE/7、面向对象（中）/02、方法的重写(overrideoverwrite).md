> 1、Person类

```java
public class Person { 

  String name;
  int age;

  public Person(){ 
  
  }

  public Person(String name,int age){ 
    this.name = name;
    this.age = age;
  }

  public void eat(){ 
    System.out.println("吃饭");
  }

  public void walk(int distance){ 
    System.out.println("走路，走的距离是：" + distance + "公里");
    show();
  }

  private void show(){ 
    System.out.println("我是一个人。");
  }

  public Object info(){ 
    return null;
  }

  public double info1(){ 
    return 1.0;
  }
}
```

> 2、Student类

```java
public class Student extends Person{ 

  String major;

  public Student(){ 
  
  }

  public Student(String major){ 
    this.major = major;
  }

  public void study(){ 
    System.out.println("学习，专业是:" + major);
  }

  //对父类中的eat()进行了重写
  public void eat(){ 
    System.out.println("学生应该多吃有营养的。");
  }

  public void show(){ 
    System.out.println("我是一个学生。");
  }

  public String info(){ 
    return null;
  }

  //不是一个类型，所以报错。
//  public int info1(){ 
//    return 1;
//  }

  //可以直接将父类的方法的第一行粘过来，直接写方法体
//  public void walk(int distance){ 
//    System.out.println("重写的方法");
//  }

  //直接输入父类的方法名，Alt + /，选择即可生成
  @Override
  public void walk(int distance) { 
    System.out.println("自动生成");
  }
}
```

> 3、测试类

```java
/*
 * 方法的重写(override/overwrite)
 * 
 * 1.重写：子类继承父类以后，可以对父类中的方法进行覆盖操作。
 * 2.应用：重写以后，当创建子类对象以后，通过子类对象去调用子父类中同名同参数方法时，执行的是子类重写父类的方法。
 *   即在程序执行时，子类的方法将覆盖父类的方法。
 * 
 * 面试题：区分方法的重载与重写(有的书也叫做“覆盖”)
 *     答：方法的重写Overriding和重载Overloading是Java多态性的不同表现。
 *     重写Overriding是父类与子类之间多态性的一种表现，重载Overloading是一个类中多态性的一种表现。
 *     如果在子类中定义某方法与其父类有相同的名称和参数，我们说该方法被重写 (Overriding)。
 *     注意：抛出异常范围小于等于父类，返回值类型小于等于父类（这里的返回值类型是指引用数据类型，
 *     基本数据类型的话必须相同）。如父类int，子类必须是int。
 *     子类的对象使用这个方法时，将调用子类中的定义，对它而言，父类中的定义如同被"屏蔽"了。
 *     如果在一个类中定义了多个同名的方法，它们或有不同的参数个数或有不同的参数类型或顺序，则称为方法的重载(Overloading)。
 * 
 */
public class PersonTest { 


  public static void main(String[] args) { 
    Student s = new Student("计算机科学与技术");
    s.eat();
    s.walk(10);
  
    s.study();
  }
}
```

## 2.1、方法重写的细节

> 1、Person类

```java
public class Person { 

  String name;
  int age;

  public Person(){ 
  
  }

  public Person(String name,int age){ 
    this.name = name;
    this.age = age;
  }

//  public void eat(){ 
//    System.out.println("吃饭");
//  }
  static void eat(){ 
    System.out.println("吃饭");
  }

  public void walk(int distance){ 
    System.out.println("走路，走的距离是：" + distance + "公里");
    show();
  }

  private void show(){ 
    System.out.println("我是一个人。");
  }

  public Object info(){ 
    return null;
  }

  public double info1(){ 
    return 1.0;
  }
}
```

> 2、Student类

```java
public class Student extends Person{ 

  String major;

  public Student(){ 
  
  }

  public Student(String major){ 
    this.major = major;
  }

  public void study(){ 
    System.out.println("学习，专业是:" + major);
  }

  //对父类中的eat()进行了重写
//  public void eat(){ 
//    System.out.println("学生应该多吃有营养的。");
//  }

  //这样不会报错，但已经不是重写了！！
  public static void eat(){ 
    System.out.println("学生应该多吃有营养的。");
  }

  public void show(){ 
    System.out.println("我是一个学生。");
  }

  public String info(){ 
    return null;
  }

  //不是一个类型，所以报错。
//  public int info1(){ 
//    return 1;
//  }

  //可以直接将父类的方法的第一行粘过来，直接写方法体
//  public void walk(int distance){ 
//    System.out.println("重写的方法");
//  }

  //直接输入父类的方法名，Alt + /，选择即可生成
  @Override
  public void walk(int distance) { 
    System.out.println("自动生成");
  }
}
```

> 3、测试类

```java
/*
 * 3.重写的规定：
 *     方法的声明：权限修饰符 返回值类型 方法名(形参列表){
 *             //方法体
 *          }
 *     约定俗称:子类中的叫重写的方法，父类中的叫被重写的方法。
 *     ① 子类重写的方法的方法名和形参列表必须和父类被重写的方法的方法名、形参列表相同; 
 *     ② 子类重写的方法使用的访问权限不能小于父类被重写的方法的访问权限,
 *       特殊情况: 子类不能重写父类中声明为private权限的方法;
 *     ③ 返回值类型:
 *       > 父类被重写的方法的返回值类型是void,则子类重写的方法的返回值类型只能是void;
 *       > 父类被重写的方法的返回值类型是A类型，则子类重写的方法的返回值类型可以是A类或A类的子类;
 *       > 父类被重写的方法的返回值类型如果是基本数据类型(比如:double)，则子类重写的方法的返回值类型必须是相同的基本数据类型(必须是:double)。
 *     
 *     ④ 子类方法抛出的异常不能大于父类被重写的方法抛出的异常;
 * 
 * 注意：子类与父类中同名同参数的方法必须同时声明为非static的(即为重写)，或者同时声明为static的（不是重写）。
 *     因为static方法是属于类的，子类无法覆盖父类的方法。
 */
public class PersonTest { 


  public static void main(String[] args) { 
    Student s = new Student("计算机科学与技术");
    s.eat();
    s.walk(10);
    System.out.println("*******************");
  
    s.study();
  
    Person p1 = new Person();
    p1.eat();
  }
}
```

## 2.2、方法的练习

> 1、练习1

```text
1.如果现在父类的一个方法定义成private访问权限，在子类中将此方法声明为default访问权限，那么这样还叫重写吗？(NO)
```

> 2、练习2

```java
/*
 * 2.修改练习1.2中定义的类Kids，在Kids中重新定义employeed()方法，
 *     覆盖父类ManKind中定义的employeed()方法，
 *   输出“Kids should study and no job.”
 */
public class Kids extends ManKind{ 

  private int yearsOld;  //年限

  public Kids() { 

  }

  public Kids(int yearsOld) { 
    this.yearsOld = yearsOld;
  }

  public int getYearsOld() { 
    return yearsOld;
  }

  public void setYearsOld(int yearsOld) { 
    this.yearsOld = yearsOld;
  }

  public void printAge(){ 
    System.out.println("I am " + yearsOld);
  }

  public void employeed(){ 
    System.out.println("Kids should study and no job.");
  }
}
```

MindKids类

```java
public class ManKind { 

  private int sex;  //性别
  private int salary;  //薪资

  public ManKind() { 
  
  }

  public ManKind(int sex, int salary) { 
    this.sex = sex;
    this.salary = salary;
  }

  public void manOrWoman(){ 
    if(sex==1){ 
      System.out.println("man");
    }else if(sex==0){ 
      System.out.println("woman");
    }
  }

  public void employeed(){ 
    if(salary==0){ 
      System.out.println("no job");
    }else if(salary!=0){ 
      System.out.println("job");
    }
  }

  public int getSex() { 
    return sex;
  }

  public void setSex(int sex) { 
    this.sex = sex;
  }

  public int getSalary() { 
    return salary;
  }

  public void setSalary(int salary) { 
    this.salary = salary;
  }

}
```

测试类

```java
public class KidsTest { 
  public static void main(String[] args) { 
  
    Kids someKid = new Kids(12);
  
    someKid.printAge();
  
    someKid.setYearsOld(15);
    someKid.setSalary(0);
    someKid.setSex(1);
  
    someKid.employeed();
    someKid.manOrWoman();
  }
}
```