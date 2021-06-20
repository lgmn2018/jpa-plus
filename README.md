# 简介

## 什么是JPA-Plus

JPA-Plus是对Spring-JPA进行封装，在不影响Spring-JPA原有功能的基础上，对查询方式进行重新定义，旨在通过实体（DTO）来对查询条件进行定义，以尽量减少开发过程中的硬编码，尽量避免因开发人员开发习惯不同带来的代码多样性问题。



## JPA-Plus的由来

为什么会编写JPA-Plus,在日常的增删改查开发中，无论是controller、service、dao都会存在代码“脏乱差”的问题。对代码修养要求不高的开发人员身上问题更为突出，所以作者希望通过相对简单可行的方式对编码方式进行约束，而JPA-Plus就是对dao层的约束。



## 对开发者的要求

要使用JPA-Plus前提条件是要对jpa相对熟悉，因为在设计的时候推荐通过定义Entity映射生成数据表，所以开发者需要熟悉如何建立实体间的关联关系、级联策略。

新手可能在使用过程中会遇到各种问题，欢迎通过issue的方式提出疑问或提供宝贵建议。



## 使用

1、引用依赖

```xml
<dependency>
    <groupId>io.github.lgmn2018.jpa-plus</groupId>
    <artifactId>core</artifactId>
    <version>0.1.2</version>
</dependency>
```



2、修改Application.java，添加@EnableJpaPlusRepository注解

```java
@EnableJpaPlusRepository
@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

}
```



3、实体类继承JpaPlusEntity

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User extends JpaPlusEntity {
    private String name;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
```



4、repository继承JpaPlusRepository

```java
public interface UserRepository extends JpaPlusRepository<User,Long> {
}
```



5、定义查询类（QueryDto)，继承JpaPlusDto

```java
@Data
public class UserEqualsDto extends JpaPlusDto {
    @Condition
    private String name;
}
```



6、测试

```java
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    @Transactional
    public void testAuditing() {
        User user1 = User.builder()
                .name("安琪拉")
                .gender(Gender.FMAIL)
                .age(18)
                .build();
        User user2 = User.builder()
                .name("兰陵王")
                .gender(Gender.MAIL)
                .age(28)
                .build();
        User user3 = User.builder()
                .name("亚瑟")
                .gender(Gender.MAIL)
                .age(30)
                .build();
        List<User> users = new ArrayList<User>(){{add(user1);add(user2);add(user3);}};
        userRepository.saveAll(users);

        UserEqualsDto dto = new UserEqualsDto();
        dto.setName("亚瑟");
        List<User> result = userRepository.findAll(dto);
        System.out.println(result);
        Assertions.assertEquals(result.size(),1);
    }
}
```



## 定义QueryDto

### @condition

| 属性        | 默认值             | 说明                                                         |
| ----------- | ------------------ | ------------------------------------------------------------ |
| field       | 默认与属性名称相同 | 用于指定该条件对应的实体属性名称，<br />例如QueryDto的属性与实体属性名称不相同时可以通过field来设置对应实体属性名称；<br />或者join查询时通过“实体名称.属性名称”的方式来指定对应的是哪个实体的属性 |
| operator    | Operator.EQUAL     | 用于指定查询逻辑                                             |
| logic       | ConditionLogic.AND | 用于指定查询条件间的关系（and/or）                           |
| ignoreEmpty | false              | 是否忽略空集合，忽略后传入空值（集合长度为0/集合为空）该属性不会作为查询条件 |
| ignoreZero  | false              | 是否忽略数字 0 值，忽略后传入0值该属性不会作为查询条件       |
| ignoreBlank | false              | 是否忽略字符空值，忽略后传入空值（字符长度为0/字符为空）值该属性不会作为查询条件 |
| likeInLogic | LikeInLogic.AND    | 当operator=Operator.LIKE_IN/时，用于设置like条件之间的关系   |



### Operator

|                 |                                                              |
| --------------- | ------------------------------------------------------------ |
| EQUAL           | =                                                            |
| NOT_EQUAL       | <>                                                           |
| IN              | in                                                           |
| NOT_IN          | not in                                                       |
| STARTING        | like ('xxx%')                                                |
| NOT_STARTING    | not like ('xxx%')                                            |
| STARTING_IN     | in (like ('xxx%') and like ('xxx%')) / in (like ('xxx%') or like ('xxx%')) |
| NOT_STARTING_IN | not in (like ('xxx%') and like ('xxx%')) / not in (like ('xxx%') or like ('xxx%')) |
| ENDING          | like ('%xxx')                                                |
| NOT_ENDING      | not like ('%xxx')                                            |
| ENDING_IN       | in (like ('%xxx') and like ('%xxx')) / in (like ('%xxx') or like ('%xxx')) |
| NOT_ENDING_IN   | not in (like ('%xxx') and like ('%xxx')) / not in (like ('%xxx') or like ('%xxx')) |
| LIKE            | like ('%xxx%')                                               |
| NOT_LIKE        | not like ('%xxx%')                                           |
| LIKE_IN         | in (like ('%xxx%') and like ('%xxx%')) / in (like ('%xxx%') or like ('%xxx%')) |
| NOT_LIKE_IN     | not in (like ('%xxx%') and like ('%xxx%')) / not in (like ('%xxx%') or like ('%xxx%')) |
| BETWEEN_AND     | between xxx and xxx                                          |
| GT              | >                                                            |
| GT_OR_EQUAL     | >=                                                           |
| LT              | <                                                            |
| LT_OR_EQUAL     | <=                                                           |



