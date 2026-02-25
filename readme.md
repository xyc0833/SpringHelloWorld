## 学习记录


通过IoC容器进行管理的Bean都是单例模式的

![img.png](img.png)
这里一共有两种作用域，第一种是singleton，默认情况下就是这一种，
当然还有prototype，表示为原型模式（为了方便叫多例模式也行）这种模式每次得到的对象都是一个新的：
```java
Student student1 = context.getBean(Student.class);  //原型模式下，对象不再始终是同一个了
Student student2 = context.getBean(Student.class);
System.out.println(student1 == student2);
```
实际上，当Bean的作用域为单例模式时，那么它会在一开始（容器加载配置时）就被创建，
我们之后拿到的都是这个对象。而处于原型模式下，只有在获取时才会被创建，也就是说，
单例模式下，Bean会被IoC容器存储，只要容器没有被销毁，那么此对象将一直存在，
而原型模式才是相当于在要用的时候直接new了一个对象，并不会被保存。


如果我们需要维护Bean的加载顺序（比如某个Bean必须要在另一个Bean之前创建）
那么我们可以使用depends-on来设定前置加载Bean，这样被依赖的Bean一定会在之前加载，
比如Teacher应该在Student之前加载：

```xml
<bean name="teacher" class="com.test.bean.Teacher"/>
<bean name="student" class="com.test.bean.Student" depends-on="teacher"/>
```

依赖注入：不自己 new，等着别人给

而有了依赖注入之后，Student中的Teacher成员变量，
可以由IoC容器来选择一个合适的Teacher对象进行赋值，也就是说，IoC容器在创建对象时，
需要将我们预先给定的属性注入到对象中，非常简单，我们可以使用property标签来实现，
我们将bean标签展开：

```xml
<bean name="teacher" class="com.test.bean.ProgramTeacher"/>
<bean name="student" class="com.test.bean.Student">
    <property name="teacher" ref="teacher"/>
</bean>
```

## 依赖注入：
可以这样理解吗 比方说 类A里面 需要用到类B 但是我在类A中 不是自己创建 而是通过ioc容器帮我创建 类B

你的理解**已经摸到核心了**，但可以再精准一点点，我帮你把这个比喻补得更完整，确保你完全吃透~

### 先肯定你的核心理解
你说的“类A需要类B，不在A里自己创建，而是让IOC容器帮我创建类B”，这个方向**完全正确**！
核心就是：**把“创建依赖对象”的活儿，从类自己手里，交给了IOC容器** —— 这是依赖注入的基础。

### 再补全一点点细节（让理解更精准）
严格来说，依赖注入（DI）和IOC容器的关系是：
> IOC容器不仅帮你**创建**类B的对象，还会主动把这个创建好的类B对象**“递”到**类A里（注入），类A只需要“伸手接”就行。

用你的例子再拆解：
1. 你告诉IOC容器：“我需要类A、类B的对象，你帮我管着”（通过@Service/@Bean等注解）；
2. IOC容器先创建好类B的对象（把它存在自己的“仓库”里）；
3. 容器创建类A的对象时，发现类A需要类B（通过@Autowired），就从仓库里拿出类B的对象，直接塞到类A里；
4. 类A全程没碰过“new B()”，甚至不用管B在哪，只要用就行。

### 用生活例子再强化（对应你的理解）
你是类A，你需要的“水杯（类B）”：
- 不用依赖注入：你自己去超市买水杯（new B()），水杯坏了/换款式了，你得自己再去买；
- 用依赖注入：你告诉管家（IOC容器）“我需要水杯”，管家先买好水杯（创建B对象），然后直接送到你手里（注入），你只管喝水，水杯坏了管家再换一个就行。

### 代码对应（呼应你之前的示例）
```java
// 类B（水杯）
public class B {}

// 类A（你）
@Service
public class A {
    private B b;

    // 管家（IOC容器）把买好的水杯（B对象）递给你
    @Autowired
    public A(B b) {
        this.b = b; // 你只需要接过来，不用自己买
    }
}
```

### 总结
1. 你的核心理解没错：**类A不自己创建类B，而是由IOC容器负责创建类B**；
2. 补充的关键：**IOC容器不仅创建B，还会把B主动注入到A中**，这才是“注入”的完整含义；
3. 最终效果：类A和类B彻底解耦，A只用B，不用管B的创建/来源，这就是依赖注入的核心价值。

这个理解到位后，你再看Spring里的各种注入方式（构造器/setter/字段），本质都是“管家递东西的不同方式”，就完全不会懵了~