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
    - 기본 생성자 부분이 아닌 곳에서 선언한 멤버변수는 data class에 자동 포함 함수의 대상에서 제외
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
                  TestDataClass(a=1, b=Hi, c=true)
        data class TestDataClass(
            var a: Int,
            val b: String = "Empty"
        ){
            var c: Boolean = false

            // 변수 c까지 체크 대상에 포함하도록 설정
            overrid fun equals(other: Any?): Boolean{
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
- 객체 생성이 불가능
- **미완성** 변수 & 함수 제작 $\Rightarrow$ 상속받아 다른 클래스를 구현
```kt
// TestAbstClass.kt
abstract class TestAbstClass(val a: Int = 4){
    abstract val b: Float

}
```

## Interface



## Class 상속



## Generics with constraints



## Getter/Setter



