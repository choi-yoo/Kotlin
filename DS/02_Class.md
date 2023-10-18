## Data Class
- src/main/kotlin > 우클릭 > New > Kotlin Class/File
```kt
// TestDataClass.kt
data class TestDataClass(
    // 1개 이상의 변수 선언해야 함
    var a: Int,             // var로 선언된 변수는 getter, setter 자동 생성
    val b: String = "Empty" // val로 선언된 변수는 getter만 자동 생성
){
    
}

// Main.kt
fun main(){
    var o1: TestDataClass = TestDataClass(a: 3)
    var o2: TestDataClass = TestDataClass(5, "hello")
    val o3: TestDataClass = o2.copy()
    
    val eq: Boolean = o1.equals(o2)
    println(eq) // 출력 : false
    println(o2.equals(o3)) // 출력 : true
    
    // componentsN() 함수에 의해 객체 o1의 멤버변수 값들이 순서대로 main() 내의 a와 b에 들어가게 됨
    var (a, b) = o1
    println("$a\t$b") // 출력 : 3  Empty
    // println(o1.toString())과 동일,
    println(o1) // 출력 : TestDataClass(a=3, b=Empty)
    
}
```
- 상속받은 클래스가 존재하지 않지만, 몇 가지 멤버함수들 존재
    - 반복적으로 만들던 함수들을 제공 $\Rightarrow$ 편리성
    - Data Class에 자동 포함되는 멤버함수
        - `hashCode()`
        - `copy()`
        - `equals()`
        - `toString()`
            - toString()를 override를 통해 함수의 역할 변경
            - override : 멤버 함수 재정의할 때 사용하는 키워드
                - 기본 클래스(상위 클래스)에 이미 정의된 함수를 하위 클래스에서 수정하거나 변경할 경우
                ```kt
                // 기존의 toSring() 결과와는 다르다는 점 유의
                // 출력 : Var a = 3, Val b = Empty
                // TestDataClass.kt
                data class TestDataClass(
                    var a: Int,
                    val b: String = "Empty"
                ){
                    override fun toString(): String{
                        return "Var a = $a, Val b = $b"
                    }
                }
                ```
        - `componentsN()`
            - data class의 주 생성자에서 선언된 프로퍼티의 순서에 따라 해당 프로퍼티 반환
            ```kt
            // TestDataClass.kt
            data class Person(val name: String, val age: Int)

            // Main.kt
            val Person = Person("Alice", 30)
            val name = person.component1() // 첫 번째 프로퍼티 반환 함수
            val age = person.component2() // 두 번째 프로퍼티 반환 함수
            ```
- 유의사항
    - 기본 생성자에서 1개 이상의 멤버변수 선언해야 함
        - 생성자에 전달되는 매개변수로 해석X, 멤버변수로 생각
    - 타 클래스를 상속받는 것이 불가능
        - 일부 클래스는 상속 가능(ex. sealed class 상속 가능)
    - 다른 종류의 class(abstract, open, sealed, inner 등)로 만들 수 없음
    - **기본 생성자 부분이 아닌 곳에서 선언한 멤버변수**는 data class에 **자동 포함 함수의 대상에서 제외**
        ```kt
        // TestDataClass.kt
        data class TestDataClass(
            var a: Int,
            var b: String="Empty",
        ){
            var c: Boolean = false
        }

        // Main.kt
        fun main(){
            var o1: TestDataClass = TestDataClass(a: 1, b: "Hi")
            var o2: TestDataClass = TestDataClass(a: 1, b: "Hi")
            o2.c = true

            println(o1.equals(o2)) // 출력 : true(o1과 o2의 멤버변수 c의 값은 다르지만 해당 변수는 기본 생성자가 아닌 곳에서 선언됨)
            println(o2) // 출력 : TestDataClass(a=1, b=Hi)
        }
        ```
        ```kt
        // TestDataClass.kt
        // 출력 : false 
        data class TestDataClass(
            var a: Int,
            val b: String = "Empty"
        ){
            var c: Boolean = false

            // 변수 c까지 체크 대상에 포함하도록 설정
            override fun equals(other: Any?): Boolean{
                // other이 null인지 또는 TestDataClass 타입인지 체크
                // is : 타입을 검사하는 연산자
                if(other == null || other !is TestDataClass)
                    return false
                
                return this.a == other.a && this.b == other.b && this.c == other.c
            }

            override fun toString(): String{
                return "TestDataClass(a=$a, b=$b, c=$c)"
            }
        }
        ```

## Abstract Class
- **객체 생성 불가**
    - 미완성 변수 & 함수가 없더라도 객체 생성 불가
- **미완성** 변수 & 함수 제작 $\Rightarrow$ 상속받아 다른 클래스를 구현
    - 변수/함수 일부는 동일하나, 서로 다른 변수/함수가 존재하는 클래스 만들 경우 용이함
```kt
// TestAbstClass.kt
// a는 TestAbstClass에서 초기화해주면, ChildOfAbstClass에서 별도로 초기화할 필요X
abstract class TestAbstClass(val a: Int = 4){
    // abstract가 아닌 변수도 만들 수는 있음
    // abstract 속성은 하위 클래스에서 구체적인 값으로 초기화되어야 함
    val b: Float = 1.0F
    abstract val c: String

    // fun f1(): Float = a * b
    // 위 한 줄짜리 내용과 동일한 코드
    fun f1(): Float{
        return a * b
    }

    // abstract method로서 구현 내용X
    // 하위 클래스에서 구체적으로 구현해야 함
    abstract fun f2(): Boolean
}

class ChildOfAbstClass(
    override val c: String = "Empty"
): TestAbstClass (6){
    override fun f2(): Boolean{
        return a < b
    }

    override fun toString(): String {
        return "a=$a, b=$b, c=$c"
    }

    fun f3(): String{
        return "f3() func!"
    }
}

// Main.kt
fun main(){
    // 매개 변수가 없어도 실행되는 이유
    // ChildOfAbstClass(자식클래스)가 TestAbstClass(부모클래스)를 상속하면
    // 자식 클래스가 명시적으로 생성자를 정의하지 않을 경우, 부모 클래스의 기본 생성자를 호출
    val o1: TestAbstClass = ChildOfAbstClass()

    println(o1)         // 출력 : a=6, b=1.0 c=Empty
    println(o1.f1())    // 출력 : 6.0
    println(o1.f2())    // 출력 : false

    // Error: Unresolved reference
    // 변수 o1의 타입은 TestAbstClass로 선언
    // TestAbstClass 클래스에는 f3() 메서드가 정의X -> 컴파일러가 메서드를 찾을 수 없음
    println(o1.f3())
}
```

## Interface
- **미완성** 함수와 그렇지 않은 함수 가짐(Abstract class와 유사)
    - 함수들이 기본적으로 **미완성이라는 전제** $\Rightarrow$ `abstract` 키워드 붙이지 않아도 됨
- 생성자 존재X $\Rightarrow$ 미완성 함수가 1개도 없는 경우 객체 생성 불가
```kt
// TestInterface.kt
interface TestInterface {
    fun f1(): String = "Interface: f1 called."
    fun f2(a: String){println("Interface: f2 called.")}
    fun f3(a: String): String{
        return "Interface: f3() called. a = $a"
    }
}

class ChildOfInterface(val x: Int): TestInterface{
    override fun f1(): String = "f1 called"
    override fun f2(a: String) {
        println("f2 called. a = $a")
    }
    // Interface에서 완성된 함수는 call하지 않아도 됨
    // 예시 : f3()
    fun f4(): String = "f4() called. Value x = $x"
}

// Main.kt
fun main(){
    var o1: ChildOfInterface = ChildOfInterface(10)

    // 클래스 이름@객체의 해시코드
    // 출력 : ChildOfInterface@568db2f2
    println(o1)
    // 출력 : f1 called
    println(o1.f1())    
    // f2("A") 메서드 호출하고, 반환값인 kotlin.Unit 출력
    // kotlin.Unit : 함수가 결과 반환X(void와 유사)
    // 출력 : f2 called. a = A
    //        kotlin.Unit
    println(o1.f2("A")) 
    // 출력 : Interface: f3() called. a = B
    println(o1.f3("B")) 
    // 출력 : f4() called. Value x = 10
    println(o1.f4())    


    // o2는 TestInterface에 정의된 메서드만 사용 가능
    var o2: TestInterface = ChildOfInterface(10) 

    // Error: Unresolved reference: f4
    println(o2.f4())


    // Error: Interface TestInterface does not have constructors
    var o3: TestInterface = TestInterface(10) 
}
```

    ### Abstract Class vs Interface
    1. 상속 제한
        - Abstract Class : 단일 상속(다른 클래스와 함께 상속될 수 없음)
        - Interface : 다중 상속(클래스는 여러 인터페이스를 동시에 구현 가능)
    1. 구현 내용
        - Abstract Class : abstract 키워드 사용하지 않은 속성 또는 메서드는 구현 가능
        - Interface : 구현 내용 포함X(모든 메서드는 추상 메서드라는 전제) $\Rightarrow$ 구현 클래스에서 모든 메서드 오버라이드해야 함
    1. 생성자
        - Abstract Class : 생성자 가질 수 있음
        - Interface : 생성자 가질 수 없음
        - 둘 다 객체 생성 불가
    1. 맴버 변수 초기화
        - Abstract Class : 초기화 가능(프로퍼티와 추상 프로퍼티 정의)
        - Interface : 초기화 불가(추상 프로퍼티 정의)
        - 둘 다 속성(멤버 변수)은 정의할 수 있음

## Class 상속
- `일반 class`는 기본적으로 **상속 불가** $\Rightarrow$ `final class`라고 지칭
    - 일반 class : abstract class, Interface가 아닌 class
    - `open` 키워드를 통해 **상속 가능**하도록 만듦
        ```
        // TestClass.kt
        open class Animal(var age: Int)

        class Cat(age: Int = 1, var name: String): Animal(age){
            fun talk(){
                print("${name}: ")
                for(i in 1..age){
                    print("meow")
                }
                println()
            }
        }

        // Main.kt
        fun main(){
            var c1 = Cat(name="Tom") // 출력 : Tom: meow
            c1.talk()
        }
        ```

## Generics with constraints
- 함수 작성 시, 임의의 T에 대하여 제약
```
// TestClass.kt
class TestClass<T>{
    fun fun1(x: T){
        println(x.toString())
    }

    // T가 Closeable 인터페이스를 구현하는 경우에만 호출 가능
    fun <T: Closeable> fun2(x: T){
        x.close()
    }
    
    // T가 Closeable 및 Iterable<T> 인터페이스를 구현하는 경우만 호출 가능
    fun <T> fun3(x: T) where T: Closeable, T: Iterable<T>{
        x.close()
        var it = x.iterator()
    }
}
```

## Getter/Setter
- Getter
    - `field` : 프로퍼티의 현재 값에 대한 참조
- Setter
    - `field` : 프로퍼티의 값 설정하는 데 이용

- 변수 정의
    - () 내에서 정의할 경우
        |클래스 선언|Getter 생성|Setter 생성|유형|
        |:-:|:-:|:-:|:-:|
        |class Person(name: String)|X|X|생성자 매개변수|
        |class Person(var name: String)|O|O|속성|
        |class Person(val name: String)|O|X|속성|
        1. `Constructor Parameter`(생성자 매개변수)
            - 클래스 외부에서 해당 클래스의 **객체**를 생성할 때 전달되는 **초기값을 설정**하는데 사용
            - 객체 생성 시에만 사용
        1. `Property`(속성)
            - 클래스 내에서 **객체의 상태**를 나타냄
            - 객체의 생명주기 동안 사용(필요에 따라 클래스 내부의 여러 함수에서 사용됨)
    - {} 내에서 정의할 경우
        - 해당 변수는 자동으로 getter와 setter 메서드 생성
        - 객체의 생명주기 동안 사용
```kt
// TestClass.kt
class Man(
    // 변수 정의(키워드X vs var vs val)
    name: String,
    var age: Int,
    val job: String
){
    var address: String = "Nowhere"
        get // 있으나마나
        set(v){field = "address: $v"}

    var cursed: Boolean = false
    var speed: Int = 100
        get(){return field - (if(cursed == true) 10 else 0 )}

    var strength: Int = 50
        get() = field
        // Error
        // get() = strength
        // get = "Str: ${this.strength}"
}

// Main.kt
fun main(){
    var tc = Man("Kevin", 23, "Student")

    println(tc.name) // Error: Unresolved reference: name
    println(tc.age) // 출력 : 23
    println(tc.job) // 출력 : Student
    println(tc.address) // 출력 : Nowhere
    println(tc.strength) // 출력 : 50

    tc.address = "Earth"
    println(tc.address) // 출력 : address: Earth

    tc.cursed = true
    println(tc.speed) // 출력 : 90
}
```