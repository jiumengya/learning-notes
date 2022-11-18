## 1.1、static 的使用

当我们编写一个类时，其实就是在描述其对象的属性和行为，而并没有产生实质上的对象，只有通过 new 关键字才会产生出对象，这时系统才会分配内存空间给对象，其方法才可以供外部调用。

我们有时候希望无论是否产生了对象或无论产生了多少对象的情况下，**某些特定的数据在内存空间里只有一份。**

例如所有的中国人都有个国家名称，每一个中国人都共享这个国家名称，不必在每一个中国人的实例对象中都单独分配一个用于代表国家名称的变量。



![img](./assets/image.png)

```java
/*
 * static 关键字的使用
 * 
 * 1.static:静态的。
 * 2.static 可以用来修饰:属性、方法、代码块、内部类。
 * 
 * 3.使用 static 修饰属性:静态变量(或类变量)。
 *     3.1  属性:是否使用 static 修饰，又分为:静态属性 VS 非静态属性(实例变量)
 *        实例变量:我们创建了类的多个对象，每个对象都独立的拥有了一套类中的非静态属性。
 *         当修改其中一个非静态属性时，不会导致其他对象中同样的属性值的修饰。
 *        静态变量:我们创建了类的多个对象，多个对象共享同一个静态变量。当通过静态变量去修改某一个变量时，
 *         会导致其他对象调用此静态变量时，是修改过的。
 *     3.2 static 修饰属性的其他说明:
 *       ① 静态变量随着类的加载而加载。可以通过"类.静态变量"的方式进行调用。
 *       ② 静态变量的加载要早于对象的创建。
 *       ③ 由于类只会加载一次，则静态变量在内存中也只会存在一次。存在方法区的静态域中。
 * 
 *       ④     类变量    实例变量
 *       类    yes      no
 *       对象    yes      yes
 * 
 *     3.3 静态属性举例:System.out.Math.PI;
 *  
 */
public class StaticTest { 
  public static void main(String[] args) { 

    Chinese.nation = "中国";

    Chinese c1 = new Chinese();
    c1.name = "姚明";
    c1.age = 40;
    c1.nation = "CHN";

    Chinese c2 = new Chinese();
    c2.name = "马龙";
    c2.age = 30;
    c2.nation = "CHINA";

    System.out.println(c1.nation); 

    //编译不通过
//    Chinese.name = "张继科";

  }
}
//中国人
class Chinese{ 

  String name;
  int age;
  static String nation;
}
```

## 1.2、类变量 vs 实例变量内存解析



![img](./assets/image-1668757142046-1.png)

## 1.3、static 修饰方法

```java
/* 
 * 4.使用 static 修饰方法:静态方法
 *     ① 随着类的加载而加载，可以通过"类.静态方法"的方式调用
 *     ②       静态方法    非静态方法
 *       类    yes      no
 *       对象    yes      yes
 *     ③ 静态方法中，只能调用静态的方法或属性
 *       非静态的方法中，可以调用所有的方法或属性
 * 
 * 5.static 注意点:
 *    5.1  在静态的方法内，不能使用 this 关键字、super 关键字
 *   5.2 关于静态属性和静态方法的使用，大家从生命周期的角度去理解。
 *   
 * 6.开发中，如何确定一个属性是否需要声明 static 的？
 *    》 属性是可以被多个对象所共享的，不会随着对象的不同而不同的。
 *    》 类中的常量也常常声明为 static
 *   
 *   开发中，如何确定一个方法是否要声明为 static 的？
 *   》 操作静态属性的方法，通常设置为 static 的
 *   》 工具类中的方法，习惯上声明为 static 的。比如：Math、Arrays、Collections
 *    
 */
public class StaticTest { 
  public static void main(String[] args) { 

    Chinese.nation = "中国";

    Chinese c1 = new Chinese();

    //编译不通过
//    Chinese.name = "张继科";

    c1.eat();

    Chinese.show();
    //编译不通过
//    chinese.eat();
//    Chinese.info();
  }
}
//中国人
class Chinese{ 

  String name;
  int age;
  static String nation;

  public void eat(){ 
    System.out.println("中国人吃中餐");
    //调用非静态结构
    this.info();
    System.out.println("name : " + name);
    //调用静态结构
    walk();
    System.out.println("nation : " + Chinese.nation);
  }

  public static void show(){ 
    System.out.println("我是一个中国人！");
//    eat();
//    name = "Tom";
    //可以调用静态的结构
    System.out.println(Chinese.nation);
    walk();
  }

  public void info(){ 
    System.out.println("name : " + name + ",age : " + age);
  }

  public static void walk(){ 

  }
}
```

## 1.4、自定义 ArrayUtil 的优化

```java
/*
 * 自定义数组工具类
 */
public class ArrayUtil { 

  // 求数组的最大值
  public static int getMax(int[] arr) { 
    int maxValue = arr[0];
    for (int i = 1; i < arr.length; i++) { 
      if (maxValue < arr[i]) { 
        maxValue = arr[i];
      }
    }
    return maxValue;
  }

  // 求数组的最小值
  public static int getMin(int[] arr) { 
    int minValue = arr[0];
    for (int i = 1; i < arr.length; i++) { 
      if (minValue > arr[i]) { 
        minValue = arr[i];
      }
    }
    return minValue;
  }

  // 求数组总和
  public static int getSum(int[] arr) { 
    int sum = 0;
    for (int i = 0; i < arr.length; i++) { 
      sum += arr[i];
    }
    return sum;
  }

  // 求数组平均值
  public static int getAvg(int[] arr) { 
    int avgValue = getSum(arr) / arr.length;
    return avgValue;
  }

  //如下两个同名方法构成重载
  // 反转数组
  public static void reverse(int[] arr) { 
    for (int i = 0; i < arr.length / 2; i++) { 
      int temp = arr[i];
      arr[i] = arr[arr.length - i - 1];
      arr[arr.length - i - 1] = temp;
    }
  }

  public void reverse(String[] arr){ 

  }

  // 复制数组
  public static int[] copy(int[] arr) { 
    int[] arr1 = new int[arr.length];
    for (int i = 0; i < arr1.length; i++) { 
      arr1[i] = arr[i];
    }
    return null;
  }

  // 数组排序
  public static void sort(int[] arr) { 
    for (int i = 0; i < arr.length - 1; i++) { 
      for (int j = 0; j < arr.length - 1 - i; j++) { 
        if (arr[j] > arr[j + 1]) { 
//          int temp = arr[j];
//          arr[j] = arr[j + 1];
//          arr[j + 1] = temp;
          //错误的：
//          swap(arr[j],arr[j+1]);
    
          swap(arr,j ,j+1);
        }
      }
    }
  }

  //错误的:交换数组中两个指定位置元素的值
//  public void swap(int i,int j){ 
//    int temp = i;
//    i = j;
//    j = temp;
//  }

  //正确的：
  private static void swap(int[] arr,int i,int j){ 
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  // 遍历数组
  public static void print(int[] arr) { 
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) { 
      System.out.print(arr[i] + ",");
    }
    System.out.println("]");
  }

  // 查找指定元素
  public static int getIndex(int[] arr, int dest) { 
    //线性查找
    for (int i = 0; i < arr.length; i++) { 

      if (dest==arr[i]) { 
        return i;
      }

    }
    return -1;
  }

}
```

> 测试类

```java
public class ArrayUtilTest { 

  public static void main(String[] args) { 
//    ArrayUtil util = new ArrayUtil();
    int[] arr = new int[]{ 32,5,26,74,0,96,14,-98,25};
    int max = ArrayUtil.getMax(arr);
    System.out.println("最大值为:" + max);

    System.out.print("排序前:");
    ArrayUtil.print(arr);

    ArrayUtil.sort(arr);
    System.out.print("排序后:");
    ArrayUtil.print(arr);

//    System.out.println("查找:");
//    int index = util.getIndex(arr, 5);
//    if(index > 0){ 
//      System.out.println("找到了，索引地址:" + index);
//    }else{ 
//      System.out.println("没找到");
//    }
  }
}
```

## 1.5、static 的应用举例

```java
//static 关键字的应用
public class CircleTest { 
  public static void main(String[] args) { 

    Circle c1 = new Circle();

    Circle c2 = new Circle();

    Circle c3 = new Circle();

    System.out.println("c1 的 ID:" + c1.getId());
    System.out.println("c2 的 ID:" + c2.getId());
    System.out.println("c3 的 ID:" + c3.getId());

    System.out.println("创建圆的个数: " + Circle.getTotal());

  }

}

class Circle{ 

  private double radius;
  private int id;  //需要自动赋值

  public Circle(){ 
    id = init++;
    total++;
  }

  public Circle(double radius){ 
    this();
    //或
//    id = init++;
//    total++;
    this.radius = radius;
  }

  private static int total;//记录创建圆的个数
  private static int init = 1001;//static 声明的属性被所有对象所共享

  public double findArea(){ 
    return 3.14 * radius * radius;
  }

  public double getRadius() { 
    return radius;
  }

  public void setRadius(double radius) { 
    this.radius = radius;
  }

  public int getId() { 
    return id;
  }

  public static int getTotal() { 
    return total;
  }

}
```

## 1.6、static 的练习

```java
/*
 * 编写一个类实现银行账户的概念，包含的属性有“帐号”、“密码”、“存款余额”、
 * “利率”、“最小余额”，定义封装这些属性的方法。
 * 账号要自动生成。编写主类，使用银行账户类，输入、输出 3 个储户的上述信息。
 * 考虑：哪些属性可以设计成 static 属性。
 * 
 */
public class Account { 

  private int id;  //账号
  private String pwd = "000000";  //密码
  private double balance; //存款余额

  private static double interestRate; //利率
  private static double minMoney = 1.0;  //最小余额
  private static int init = 1001;  //用于自动生成 id

  public Account(){   //账号自动生成
    id = init++;
  }

  public Account(String pwd,double balance){ 
    id = init++;
    this.pwd = pwd;
    this.balance = balance;
  }

  public String getPwd() { 
    return pwd;
  }

  public void setPwd(String pwd) { 
    this.pwd = pwd;
  }

  public static double getInterestRate() { 
    return interestRate;
  }

  public static void setInterestRate(double interestRate) { 
    Account.interestRate = interestRate;
  }

  public static double getMinMoney() { 
    return minMoney;
  }

  public static void setMinMoney(double minMoney) { 
    Account.minMoney = minMoney;
  }

  public int getId() { 
    return id;
  }

  public double getBalance() { 
    return balance;
  }

  @Override
  public String toString() { 
    return "Account [id=" + id + ", pwd=" + pwd + ", balance=" + balance + "]";
  }

}
```

> **测试类**

```java
public class AccountTest { 
  public static void main(String[] args) { 

    Account acct1 = new Account();
    Account acct2 = new Account("qwerty",2000);

    Account.setInterestRate(0.012); 
    Account.setMinMoney(100);

    System.out.println(acct1);
    System.out.println(acct2);

    System.out.println(acct1.getInterestRate()); 
    System.out.println(acct1.getMinMoney());
  }
}
```

## 1.7、单例([Singleton](https://so.csdn.net/so/search?q=Singleton&spm=1001.2101.3001.7020))设计模式

[设计模式](https://so.csdn.net/so/search?q=设计模式&spm=1001.2101.3001.7020)是**在大量的实践中总结和理论化之后优选的代码结构、编程风格、以及解决问题的思考方式。** 设计模免去我们自己再思考和摸索。就像是经典的棋谱，不同的棋局，我们用不同的棋谱。”套路”

所谓类的单例设计模式，就是采取一定的方法保证在整个的软件系统中，对 **某个类只能存在一个对象实例** 。并且该类只提供一个取得其对象实例的方法。如果我们要让类在一个虚拟机中只能产生一个对象，我们首先必须将类的 **构造器的访问权限设置为 private** ，这样，就不能用 new 操作符在类的外部产生类的对象了，但在类内部仍可以产生该类的对象。因为在类的外部开始还无法得到类的对象，**只能调用该类的某个静态方法以返回类内部创建的对象，静态方法只能访问类中的静态成员变量，所以，指向类内部产生的该类对象的变量也必须定义成静态的。**

> 1、**单例模式的饿汉式**

```java
/*
 * 单例设计模式:
 * 1.所谓类的单例设计模式，就是采取一定的方法保证在整个的软件系统中，对某个类只能存在一个对象实例
 *  
 * 2.如何实现？
 *   饿汉式  VS  懒汉式
 * 
 * 3.区分饿汉式和懒汉式。
 *      饿汉式：坏处:对象加载时间过长。
 *             好处:饿汉式是线程安全的。
 * 
 *   懒汉式：好处:延迟对象的创建。
 *            坏处:目前的写法，会线程不安全。---》到多线程内容时，再修改
 */
public class SingletonTest { 
  public static void main(String[] args) { 
//    Bank bank1 = new Bank(); 
//    Bank bank2 = new Bank(); 

    Bank bank1 = Bank.getInstance();
    Bank bank2 = Bank.getInstance();

    System.out.println(bank1 == bank2);

  }
}

//单例的饿汉式
class Bank{ 

  //1.私有化类的构造器
  private Bank(){ 

  }

  //2.内部创见类的对象
  //4.要求此对象也必须声明为静态的
  private static Bank instance = new Bank();

  //3.提供公共的静态的方法，返回类的对象。
  public static Bank getInstance(){ 
    return instance;
  }
}
```

> 2、**单例模式的懒汉式**

```java
/*
 * 单例的懒汉式实现
 * 
 */
public class SingletonTest2 { 
  public static void main(String[] args) { 

    Order order1 = Order.getInstance();
    Order order2 = Order.getInstance();

    System.out.println(order1 == order2);
  }
}
class Order{ 
  //1.私有化类的构造器
  private Order(){ 

  }

  //2.声明当前类对象，没有初始化。
  //此对象也必须声明为 static 的
  private static Order instance = null;

  //3.声明 public、static 的返回当前类对象的方法
  public static Order getInstance(){ 
    if(instance == null){ 
      instance = new Order();
    }
    return instance;
  }
}
```

> 3、**单例模式的优点**

由于单例模式只生成一个实例， **减少了系统性能开销** ，当一个对象的产生需要比较多的资源时，如读取配置、产生其他依赖对象时，则可以通过在应用启动时直接产生一个单例对象，然后永久驻留内存的方式来解决。

![img](./assets/image-1668757142046-2.png)

> 4、单例(Singleton)设计模式-应用场景

- **网站的计数器** ，一般也是单例模式实现，否则难以同步。
- **应用程序的日志应用** ，一般都使用单例模式实现，这一般是由于共享的日志文件一直处于打开状态，因为只能有一个实例去操作，否则内容不好追加。
- **数据库连接池** 的设计一般也是采用单例模式，因为数据库连接是一种数据库资源。
- 项目中， **读取配置文件的类** ，一般也只有一个对象。没有必要每次使用配置文件数据，都生成一个对象去读取。
- **Application**也是单例的典型应用
- Windows 的 **Task Manager (任务管理器)** 就是很典型的单例模式
- Windows 的 **Recycle Bin(回收站)** 也是典型的单例应用。在整个系统运行过程中，回收站一直维护着仅有的一个实例。