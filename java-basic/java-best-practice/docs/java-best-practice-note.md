# best-practice-java
Best practice.

### Content

- Variables
  - [x] Define and Initialize Variables
  - [x] Define Constants
  - [x] Defining Constants With Enum
- Statements
  - Condition Statements (if/else, switch)
    - [x] Using Enumeration for Single Level Conditions Judgement instead of Multiple `if` Statements 
  - Null-Checking
    - [x] Using Optional instead of `if` null-check statement
    - [x] Using Optional instead of multiple levels `if` null-check statements
    - [x] Return Empty Collection Object instead of return Null
    - [x] Return `Optional<Entity>` instead of return entity null object
    - [x] Using Annotations for tip Null Arguments of Functions
  - For Loop
    - [x] Using Stream instead of For-Loop
- Container
  - [x] Define Custom Sort Algorithm Container
- IO
- Utilities Class
- Exceptions Handling
  - [x] Using try with resources instead of close resources in finally block
- Generic Programming
- Functional Programming
  - [x] Stream
  - [x] Method Reference
  - [x] Consumer
  - [x] Function
  - [x] Optional
- Simplify Development
  - [x] Using Spring Framework Check HTTP Parameters
  - Lombok
  - MyBatis Generator



## Variables

### Define and Initialize Variables

```java
int number = 1;
String str = "hello";
int[] my_array = {1, 2, 3};
Object my_object = new Object();
List<String> my_list = new ArrayList<>(Arrays.asList(1, 2, 3));
Map<Integer, String> my_map = new HashMap<Integer, String>()
{
    {
        put(1, "one");
        put(2, "two");
        put(3, "three");
    };
};
```

### Defining Constants

Primitive Constant

```java
public static final int NUM = 1;
public static final int[] MY_ARRAY = {1, 2, 3};
```

Immutable List (Can't add, set, remove list)

```java
// JDK 8 (Don't expose internal_list reference of unmodifiableList(List internale_list)). Arrays.asList() can't increase size, but it can modify its elements.
public static final List UNMODIFY_LIST = Collections.unmodifiableList(Arrays.asList(1, 2, 3));
// JDK 9 (Recommend, less space cost)
public static final List stringList = List.of("a", "b", "c");
```

Immutable Set

```java
// JDK 8
public static final Set<String> stringSet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("a", "b", "c")));
// JDK 9 (Recommend)
public static final Set<String> stringSet2 = Set.of("a", "b", "c");
```

Immutable Map

```java
// Immutable Map, JDK 8
public static final Map<Integer, String> UNMODIFY_MAP = Collections.unmodifiableMap(
    new HashMap<Integer, String>()
    {
        {
            put(1, "one");
            put(2, "two");
            put(3, "three");
        };
    }); 
// java 9, return ImmutableCollections (Recommend)
public static final Map<Integer, String> my_map2 = Map.of(1, "one", 2, "two");
// java 10, return ImmutableCollections (Recommend)
public static final Map<Integer, String> my_map3 = Map.ofEntries(
    entry(1, "One"), 
    entry(2, "two"), 
    entry(3, "three"));
```



### Defining Constants With Enum

```java
public enum WeekDay {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
// bad example
public static final int MONDAY = 0;
public static final int TUESDAY = 1;
public static final int WEDNESDAY = 2;
public static final int THURSDAY = 3;
public static final int FRIDAY = 4;
public static final int SATURDAY = 5;
public static final int SUNDAY = 6;
```





## Statements

### Condition Statements (if/else, switch)

#### Using Enumeration for Single Level Conditions Judgement instead of Multiple `if` Statements

```java
public interface RoleOpetaion {
    String operations();
}
pubilc enum RoleEnum implements RoleOperation {
    ROLE_ROOT_ADMIN {
        @Override
        public String operations(){
            return "ROLE_ROOT_ADMIN: has AAA permission"
        }
    },
    ROLE_ODER_AMDIN {
        @Override
        public String operations(){
            return "ROLE_ROOT_ADMIN: has AAA permission"
        }
    }
}
public class JudgeRole{
    public String judge(String roleName){
        return RoleEnum.valueOf(roleName).operations();
    }
}
```

Multiple Conditions Single Level - Using Factory Method, or Strategy



### Null-Checking



#### Using Optional instead of `if` null-check statement

```java
String str = param;
Optional<String> stringOptional = Optional.ofNullable(str);
String result = stringOptional.orElse("error message");
log.debug("result value is {}", stringOptional.get());
```



#### Using Optional instead of multiple levels `if` null-check statements

```java

public String getStreetName( Province province ) {
    if( province != null ) {
        City city = province.getCity();
        if( city != null ) {
            District district = city.getDistrict();
            if( district != null ) {
                Street street = district.getStreet();
                if( street != null ) {
                    return street.getName();
                }
            }
        }
    }
    return "未找到该道路名";
}
```

to

```java
public String getStreetName(Province province) {
    return Optional.ofNullable(province)
            .map( i -> i.getCity() )
            .map( i -> i.getDistrict() )
            .map( i -> i.getStreet() )
            .map( i -> i.getName() )
            .orElse( "未找到该道路名" );
}
```



#### Return Empty Collection Object instead of Return Null

> Collection return type if not data, return empty collection object rather than return null.

```java
Collections.emptyList();
Collections.emptySet();
Collections.emptyMap();
Collections.emptyEnumeration();
Collections.emptyIterator();
Collections.emptySortSet();
```

Example

```java
public List<User> listUser(){
    List<User> userList = userRepository.selectAll();
    if (user != null){
        return userList;
    }
    return Collections.emptyList();
}
```



#### Return `Optional<Entity>` instead of return entity object

```java
public User getUser(){
    Integer userId = 1;
    Optional<User> userOptional = getOptional(userId);
    if (userOptional.isPresent()){
        return userOptional.get();
    }else{
        throw IllegalArgumentException("userId do not exist!"); 
    }
}
```

to

```java
public Optional<User> getOptional(Integer id){
    return Optional.ofNullale(userRepository.selectByPrimaryKey(id));
}
```



#### Using Annotations for tip Null Arguments of Functions

- JSR 303 Annotations `@NotNull`

- JSR 305 `@Nullable` `@Nonnull` `@CheckForNull`

```java
public inerface UserService{
    User get(@NotNull Integer id);
    Optional<User> getOpetional(@NotNull Integer id);
}
```



### Loop Conditions (for, while)

#### Using Stream instead of For-Loop

```java
String result = stringList.stream()
    .filter(i -> ! isNum(i))
    .filter(i -> i.length() > 5)
    .map(i -> i.toLowerCase())
    .distinct()
    .sorted(Comparator.naturalOrder())
    .collect(Collectors.joining("❤"));
System.out.println(result);
```



## Container

### Define Custom Sort Algorithm Container

Set

```java
/**
 * Sort by character order Set
 */
Set<String> stringSet = new TreeSet<String>(
	new Comparator<String>(){
        @Override
        public int compare(String o1, String o2){
            return o1.compareTo(o2);
        }
    }
);
```



## Utilities Class

Character

```java
if (Character.isDigit(s.charAt(i))){}
```



## Exceptions Handling

#### Using try with resources instead of close resources in finally block

Example

```java
static String readFirstLineFromFileWithFinallyBlock(String path)
                                                     throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(path));
    try {
        return br.readLine();
    } finally {
        if (br != null) br.close();
    }
}
```

to

```java
static String readFirstLineFromFile(String path) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
```



## Generic Programming

## Functional Programming

### Stream

```java
List<String> list = new ArrayList<>();
String result = list.stream()
	.filter(element -> !myCustomCheckFunction(element))
    .filter(element -> element.length() >= 5)
    .map(element -> element.toLowerCase())
    .distinct()
    .sorted(Comparator.naturalOrder())
    .collect(Collectors.joining("❤"))
```

Uasge

- filter()
- map()
- distinct()
- sorted()
- collect()

### Method Reference

Static method reference

```java
(args) -> Class.staticMethod(args) => Class::staticMethod
```

### Consumer

A function have Input parameter, no output value.

Definition format:

- `Consumer<InputType> consumer = inputParamName -> single statement`
- `Consumer<InputType> consumer = inputParamName -> {mulitple statements...}`
- `Consumer<InputType> consumer = inputParamName -> method reference`

```java
System.out.println("hello world");
```

to

```java
Consumer consumer = System.out::println; // or Consumer consumer = i -> System.out.println(i);
consumer.accept("hello world");
consumer.andThen(consumer).andThen(consumer).accept("hello world!")
```

### Function

A function have Input parameter, and have output value. 

Definition format:

- `Function<InputType, ResultType> functionName = inputParamName -> single return statement.`
- `Function<InputType, ResultType> functionName = inputParamName -> {multiple statements...}`
- `Function<InputType, ResultType> functionName = inputParamName -> method reference`

```java
Function<Integer, Integer> function1 = i -> i+i;
Function<Integer, Integer> function2 = i -> i*i;
System.out.println(function1.andThen(function2).apply(2));
```



### Optional

```java
public Integer getScore(Student student){
    if (student != null){
        Subject subject = student.getSubject();
        if (subject != null){
            return subject.getScore();
        }
    }
    return null;
}
```

to

```java
public Integer getScore(Student student){
    return Optional.ofNullable(student)
        .map(Student::getSubject)
        .map(Student::getScore)
        .orElse(null)
}
```



## Simplify Development

#### Using Spring Framework Check HTTP Parameters

Adding JSR-303 and Hibernate Validator Dependency

To use JSR-303 annotations with Spring, you will need to add below dependency in pom.xml.

```xml
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>1.0.0.GA</version>
</dependency>
```

For validation to actually work, you need an implementation as well, such as Hibernate Validator.

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>4.3.1.Final</version>
</dependency>
```

Entity Example

```java
public class Student{
    @NotNull(message = "name can't be null!")
    @NotEmpty(message = "name is invalid!")
    private String name;
}
```

Web Controller Example

```java
@PostMapping("/add")
public String addStudent(@RequestBody @Valid Student student){
	studentService.addStudent(student);
	return SUCCESS;
}
```

Custom Defining Error Message Format

```java
@ControllerAdvice
@ResponseBody
public class GlobalExceptionInterceptor{
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletRequest req, Exception e){
        String errorMessage = null;
        if (e instanceof MethodArgumentNotValidException){
            errorMessage = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        }
        return "error message:" + errorMessage;
    }
}
```



References

[1] [Java 8 Method Reference: How to Use it](https://www.codementor.io/@eh3rrera/using-java-8-method-reference-du10866vx)