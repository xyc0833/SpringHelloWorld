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


## utf -8 英文 中文的问题

修改这里的默认编码：Settings->Editor->File Encodings->Default encoding for properties files


## springEL表达式在企业开发中的作用？
你想了解 Spring EL（Spring 表达式语言，简称 SpEL）在企业级开发中的核心作用和实际应用场景，我会从核心价值、典型使用场景、实战示例三个维度，结合企业开发的实际需求来讲解，让你清楚它能解决什么问题、怎么用。

### 1. 核心作用
Spring EL 是一种强大的表达式语言，核心价值是**在 Spring 框架中提供动态求值能力**：
它允许你在配置（注解、XML、配置文件）或代码中，以表达式的形式动态获取/计算值、访问对象属性/方法、调用静态方法、甚至执行逻辑判断，无需硬编码，让配置和代码更灵活、更动态，适配企业开发中“配置与代码解耦”“动态调整逻辑”的核心需求。

简单来说：SpEL 让你可以“用表达式写逻辑”，替代一部分硬编码的判断/取值逻辑，尤其适合需要动态读取配置、动态访问对象的场景。

### 2. 企业开发中的典型使用场景（附实战示例）
以下是 SpEL 最常用的 5 个企业级场景，每个场景都配可直接参考的代码/配置示例：

#### 场景1：动态注入属性（替代硬编码，解耦配置）
这是最基础也最常用的场景：在 `@Value` 注解中使用 SpEL，从配置文件、系统环境、Bean 属性中动态取值，而非写死值。
```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    // 1. 读取配置文件（application.properties）中的值：order.timeout=30
    @Value("#{${order.timeout}}") 
    private Integer orderTimeout;

    // 2. 读取另一个 Bean（userService）的属性值
    @Value("#{userService.defaultPageSize}") 
    private Integer defaultPageSize;

    // 3. 读取系统环境变量（比如服务器端口）
    @Value("#{systemEnvironment['SERVER_PORT']}") 
    private String serverPort;

    // 4. 动态计算值（比如超时时间翻倍）
    @Value("#{${order.timeout} * 2}") 
    private Integer doubleTimeout;
}
```

#### 场景2：在 Spring 注解中动态指定参数
企业开发中，很多注解的参数需要动态决定（比如缓存 key、定时任务 cron 表达式），SpEL 可以满足这个需求。
##### 示例：动态生成缓存 Key（Spring Cache 场景）
```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // SpEL 动态拼接缓存 key：user_用户ID
    // #userId 表示取方法参数 userId 的值
    @Cacheable(value = "userCache", key = "'user_' + #userId")
    public User getUserById(Long userId) {
        // 模拟从数据库查询用户
        return new User(userId, "张三");
    }
}
```

##### 示例：动态指定定时任务 cron 表达式
```java
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    // 从配置文件读取 cron 表达式，动态调整任务执行频率
    @Scheduled(cron = "#{@configProperties.get('task.cron')}")
    public void doTask() {
        System.out.println("定时任务执行...");
    }
}
```

#### 场景3：在 XML 配置中动态取值（兼容老项目）
部分企业老项目仍使用 XML 配置 Spring Bean，SpEL 可在 XML 中动态配置属性：
```xml
<bean id="productService" class="com.example.ProductService">
    <!-- 动态注入另一个 Bean 的属性 -->
    <property name="maxStock" value="#{stockConfig.maxStock}"/>
    <!-- 动态计算值 -->
    <property name="minStock" value="#{stockConfig.maxStock / 10}"/>
</bean>
```

#### 场景4：在代码中手动执行 SpEL（复杂动态逻辑）
对于更复杂的动态逻辑（比如根据用户输入的表达式计算值），可以手动创建 SpEL 解析器执行表达式：
```java
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELDemo {
    public static void main(String[] args) {
        // 1. 创建 SpEL 解析器
        ExpressionParser parser = new SpelExpressionParser();
        
        // 2. 简单表达式求值
        Integer result = parser.parseExpression("100 + 200 * 2").getValue(Integer.class);
        System.out.println(result); // 输出：500
        
        // 3. 访问对象属性（复杂场景）
        User user = new User(1L, "李四");
        StandardEvaluationContext context = new StandardEvaluationContext(user);
        String userName = parser.parseExpression("name").getValue(context, String.class);
        System.out.println(userName); // 输出：李四
    }
}

class User {
    private Long id;
    private String name;
    // 构造方法、getter/setter 省略
}
```

#### 场景5：条件化配置（结合 @Conditional）
企业开发中常需要根据环境/配置动态创建 Bean，SpEL 可配合 `@Conditional` 实现条件判断：
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class DataSourceConfig {
    // 当配置文件中 db.type=mysql 时，才创建 MySQL 数据源 Bean
    @Bean
    @Conditional(MySQLCondition.class)
    public DataSource mysqlDataSource() {
        return new MySQLDataSource();
    }

    // 自定义条件类，使用 SpEL 判断
    static class MySQLCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Environment env = context.getEnvironment();
            // 使用 SpEL 解析配置值并判断
            return "mysql".equals(env.getProperty("db.type"));
        }
    }
}
```

### 3. 企业开发中的优势
- **解耦**：将硬编码的数值、逻辑抽离到表达式/配置中，无需修改代码即可调整；
- **动态**：支持运行时动态求值，适配多环境、多场景的动态配置需求；
- **简洁**：用简洁的表达式替代复杂的 Java 逻辑（比如缓存 key 拼接、数值计算）；
- **统一**：在注解、XML、代码中都能使用，是 Spring 体系内统一的表达式语言。

### 总结
1. SpEL 核心作用：为 Spring 提供**动态求值能力**，实现配置与代码解耦、逻辑动态调整；
2. 核心使用场景：`@Value` 动态注入、缓存/定时任务注解参数、XML 配置、代码中动态求值；
3. 企业价值：减少硬编码、适配多环境、简化动态逻辑，是 Spring 企业开发中提升灵活性的核心工具。

## AOP面向切片

AOP（Aspect Oriented Programming）思想实际上就是：在运行时，动态地将代码切入到类的指定方法、指定位置上。
也就是说，我们可以使用AOP来帮助我们在方法执行前或执行之后，做一些额外的操作，实际上，它就是代理！

在不修改代码的情况下 添加前置操作 或者后置操作


execution：用于匹配方法执行连接点。这是使用Spring AOP时使用的主要点切割指示器。

修饰符 包名.类名.方法名称(方法参数)

修饰符：public、protected、private、包括返回值类型、static等等（使用*代表任意修饰符）
包名：如com.test（* 代表全部，比如com.*代表com包下的全部包）
类名：使用*也可以代表包下的所有类
方法名称：可以使用*代表全部方法
方法参数：填写对应的参数即可，比如(String, String)，也可以使用*来代表任意一个参数，使用..代表所有参数。


## ProceedingJoinPoint

你想了解这段 AOP 代码中 `ProceedingJoinPoint` 的核心作用，我会结合你提供的代码示例，从“是什么、能做什么、怎么用”三个维度讲清楚，让你理解它在 Spring AOP 中的核心价值。

### 1. 核心定位
`ProceedingJoinPoint` 是 Spring AOP 中**专用于环绕通知（@Around）** 的核心接口，它的本质是：**封装了被拦截的目标方法的所有信息，并提供了执行目标方法的核心能力**。

简单来说，在环绕通知中，你可以通过 `ProceedingJoinPoint` 做到：
- 控制目标方法**是否执行**、**何时执行**；
- 修改目标方法的**入参**（就像你代码中把参数改成了 "Rust"）；
- 获取目标方法的信息（方法名、参数、所属类等）；
- 获取目标方法执行后的返回值，并修改它（就像你代码中把返回值从 "Java" 改成 "Rust"）。

### 2. 结合你的代码理解核心方法
先回顾你的代码核心逻辑：拦截目标方法 → 打印提示 → 强行修改入参为 "Rust" 执行目标方法 → 判断返回值，若为 "Java" 则改成 "Rust" → 返回最终值。

下面拆解 `ProceedingJoinPoint` 的关键用法（对应你的代码）：

#### （1）核心方法：`proceed()` / `proceed(Object[] args)`
这是 `ProceedingJoinPoint` 最核心的方法，作用是**执行被拦截的目标方法**：
- `joinPoint.proceed()`：使用原参数执行目标方法；
- `joinPoint.proceed(new Object[]{"Rust"})`：**替换参数后执行目标方法**（你代码中正是这么做的，强行把参数改成了 "Rust"）。

如果不调用 `proceed()` 方法，目标方法就不会执行——这也是环绕通知能“控制目标方法是否执行”的关键。

#### （2）获取目标方法的元信息（扩展）
除了执行方法，`ProceedingJoinPoint` 还能获取目标方法的各类信息，比如：
```java
// 获取目标方法的参数（原参数）
Object[] args = joinPoint.getArgs();
// 获取目标方法的签名（方法名、返回类型等）
Signature signature = joinPoint.getSignature();
String methodName = signature.getName(); // 目标方法名
// 获取目标对象（被代理的原始对象）
Object target = joinPoint.getTarget();
```

### 3. 完整示例（结合你的代码）
为了让你更直观理解，我补全整个 AOP 代码，展示 `ProceedingJoinPoint` 的完整使用场景：

```java
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 1. 定义切面类
@Aspect
@Component
public class StudyAdvice {

    // 2. 环绕通知：拦截指定方法（比如 com.example.service.StudyService.learn 方法）
    @Around("execution(* com.example.service.StudyService.learn(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 打印提示
        System.out.println("我是她的家长，他不能学Java，必须学Rust，这是为他好");
        
        // 3. 核心：替换参数后执行目标方法（原参数被替换为 ["Rust"]）
        Object value = joinPoint.proceed(new Object[]{"Rust"});
        
        // 4. 修改目标方法的返回值
        if(value.equals("Java")) {
            System.out.println("听话，学Rust以后进大厂！");
            value = "Rust";
        }
        
        return value;
    }
}

// 目标服务类
@Component
class StudyService {
    // 被拦截的目标方法：接收一个参数，返回学习的语言
    public String learn(String language) {
        System.out.println("正在学习：" + language);
        return language; // 原逻辑返回传入的语言
    }
}

// 测试类
public class Test {
    public static void main(String[] args) {
        // 初始化 Spring 容器（省略容器创建代码）
        StudyService studyService = context.getBean(StudyService.class);
        // 调用方法，传入 "Java"
        String result = studyService.learn("Java");
        System.out.println("最终学习的语言：" + result);
    }
}
```

**执行结果**：
```
我是她的家长，他不能学Java，必须学Rust，这是为他好
正在学习：Rust
最终学习的语言：Rust
```

可以看到：
- 目标方法原本要接收 "Java"，但通过 `proceed(new Object[]{"Rust"})` 强行替换了参数；
- 目标方法执行由 `proceed()` 触发，没有这个调用，目标方法不会执行。

### 4. 与普通 JoinPoint 的区别（关键）
你可能会见过 `JoinPoint` 接口，这里要明确两者的核心差异：
| 接口                | 适用通知类型                | 核心能力                                  |
|---------------------|-----------------------------|-------------------------------------------|
| `JoinPoint`         | 前置（@Before）、后置（@After）、异常（@AfterThrowing）等 | 只能获取目标方法信息（参数、方法名等），**不能执行目标方法** |
| `ProceedingJoinPoint` | 环绕通知（@Around）         | 继承 `JoinPoint`，新增 `proceed()` 方法，**能控制目标方法执行** |

简单说：环绕通知必须用 `ProceedingJoinPoint`，因为只有它能让目标方法“走起来”；其他通知用普通 `JoinPoint` 就够了。

### 总结
1. `ProceedingJoinPoint` 是 Spring AOP 环绕通知的核心接口，**封装目标方法信息并控制其执行**；
2. 核心能力：通过 `proceed()` 执行目标方法（可替换参数）、获取目标方法元信息、控制目标方法是否执行；
3. 关键区别：比普通 `JoinPoint` 多了 `proceed()` 方法，这是环绕通知能“拦截并控制方法执行”的核心。