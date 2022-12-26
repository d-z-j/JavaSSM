# JavaSSM

## 通过注解配置Bean有两种方式，什么时候该用什么方式：

### 如果要注册为Bean的类是由其他框架提供，我们无法修改其源代码，那么我们就使用第一种方式进行配置。

  创建一个配置类，我取名为MainConfiguration。首先添加注解@Configuration，然后悬浮鼠标在类名上，将该类配置在上下文中。

  创建bean：将要创建的bean写一个方法到配置类中，并将其添加注解@Bean。例如：
  
  
      @Bean
      public Card card(){
          return new Card();
      }

### 如果要注册为Bean的类是我们自己编写的，我们就可以直接在类上添加注解，并在配置中添加扫描。

  创建一个配置类，我取名为MainConfiguration。首先添加注解@Configuration，然后悬浮鼠标在类名上，将该类配置在上下文中。在类上添加注解@ComponentScan("com.test.bean")，然后在bean类中添加注解@Component。
  
  使用方法（以Student为例）：
  
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
      Student student = context.getBean(Student.class);

## 使用注解实现AOP

创建一个配置类，我取名为AopTest，并将它注册为Bean，即添加注解@Component。然后添加注解@Aspect，再在MainConfiguration文件中添加@EnableAspectJAutoProxy。现在就可以在AopTest文件中添加切入代码。例如在say方法之前添加方法：

    @Aspect
    @Component
    public class AopTest {

        @Before("execution(* com.test.bean.Student.say(..))")
        public void before(){
            System.out.println("方法执行之前");
        }
    }
    
## Spring整合mybatis框架

### 使用SqlSessionTemplate

首先需要在resources中创建配置文件mybatis-config.xml。配置数据库的连接信息。

    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <environments default="development">
            <environment id="development">
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql://localhost:3306/study"/>
                    <property name="username" value="root"/>
                    <property name="password" value="123456"/>
                </dataSource>
            </environment>
        </environments>

    </configuration>

然后将SqlSessionTemplate在配置类MainConfiguration中配置为Bean。每次使用SqlSessionTemplate，Ioc容器都会自己生成返回SqlSessionTemplate。

并将@MapperScan("com.test.mapper")注解添加到配置类上，让Spring管理所有的Mapper类。

    @Bean
        public SqlSessionTemplate sqlSessionTemplate() throws IOException {
            return new SqlSessionTemplate(
                    new SqlSessionFactoryBuilder()
                            .build(Resources.getResourceAsReader("mybatis-config.xml")));
        }

然后创建mapper包，并在包中创建想要实现的数据库操作的接口。我以TestMapper接口为例，该接口实现查询数据库中学号为1的学生信息。

    @Mapper
    public interface TestMapper {

        @Select("select * from student where sid = 1")
        Student getStudent();
    }

然后创建service包，并在包中创建业务接口和业务接口的实现类，业务接口的实现类也需要注册为Bean。

业务接口TestService：

    public interface TestService {
        Student getStudent();
    }
    
业务接口实现类TestServiceImpl：

由于在配置类中SqlSessionTemplate被注册为Bean，且我们添加了@MapperScan注解，让Spring管理Mapper，所以我们只需要调用TestMapper接口就可以实现对数据库的操作。
SqlSessionTemplate和Mapper之间的关系就不需要深究，Spring在Ioc容器中就处理了。


    @Component
    public class TestServiceImpl implements TestService{

        @Resource
        TestMapper mapper;

        @Override
        public Student getStudent() {
            return mapper.getStudent();
        }
    }
    
这样就使用SqlSessionTemplate将mybatis整合Spring在一起。使用时只需要用TestService创建对象，就可以使用实现类中的方法。

    public class Main {
        public static void main(String[] args) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
            TestService service = context.getBean(TestService.class);
            System.out.println(service.getStudent());
        }
    }
    
### 使用SqlSessionFactoryBean

使用SqlSessionFactoryBean的优点就是不需要使用配置文件mybatis-config.xml，所有的配置都使用代码和注解。除此之外其他操作和上一方法相同。

在配置类MainConfiguration，创建SqlSessionFactoryBean的Bean及数据源DataSource的Bean即可代替配置文件mybatis-config.xml。

    @Bean
    public DataSource dataSource(){
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/study");
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource source){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(source);
        return bean;
    }

### 使用HikariCP连接池提高DataSource效率

导入HikariCP依赖及自带的slf4j日志（支持Lombok）：

    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
        <version>3.4.5</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-jdk14</artifactId>
        <version>1.7.25</version>
    </dependency>
    
将配置类MainConfiguration中的数据源DataSource的Bean修改为下列代码即可：

    @Bean
    public DataSource dataSource() throws SQLException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/study");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("1607980231..");
        return dataSource;
    }
