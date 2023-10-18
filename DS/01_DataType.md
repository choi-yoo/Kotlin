# Abstract Data Type
- abstract data type : 데이터 + 연산(기능)에 대해 명기한 개념
    - 실체화하는 각 기능의 구현으로 다른 연산함
    - 기능에 대한 시간 복잡도 정의X

- abstract data structure : 자료구조 & 기능 명시
    - 기능에 대한 시간 복잡도 표현O
        - 동일한 기능도 구현방법에 따라 시간 복잡도 상이할 수 있음

# Data Type
- Data Types
    - Number : `Double`, `Float`, `Long`, `Int`, `Short`, `Byte`
    - Character : `Char`, `String`
    - other : `Boolean`, `Array`
        1. Array
            ```kt
            fun main(){
                // arrayOf()의 인수에 따른 타입 지정
                var arr1 = arrayOf(100, 90, 80)

                // arr1과 동일한 형태
                var arr2: Array<Int> = arrayOf(100, 90, 80)
            }
            ```

- 변수 선언
    ```kt
    fun main(){
        var name1 = "우왕이"
        val name2 = "좌왕이"
        name1 = "컴돌이"
        name2 = "컴순이" // 오류. val은 ressign 불가

        var score: Int
        score = 70
        score = 71.0 // 오류. Int형에 타 데이터형을 대입해서                
    }
    ```
    -  `var` VS. `val`
        1. var : 변수 역할
        1. val : 상수 역할 $\Rightarrow$ ressign 불가
    - `implicit declaration` VS. `explicit declaration`
        1. implicit declaration : 변수 선언할 당시 data type을 명시하지 않아도 type inference에 의해 자동으로 지정됨  
        1. explicit declaration  
           
- `NULL`
    - Symbol "?" : 있을 수도 없을 수도 있음을 의미, null이 올 수도 타 데이터형이 올 수도 있음
        - "?"가 없을 경우 null 대입 불가
    ```kt
    fun main(){
        var arr1 = ArrayOf(100, 90, 80)
        // 현재 배열의 형태  조차도 갖추고 있지 않음
        var arr2: Array<Int>? = null

        // arr2이 null이므로 arr2.size는 실행되지 않고 null 출력
        print(arr2?.size)
        // !! : null이 아니라는 가정하에 실행
        print(arr2!!.size) // 에러. arr2가 null이 아니라고 가정했는데 null이여서

        // null 체크해서 선택적 동작 수행
        // arr2가 null일 경우 arr1을 arr3에 대입
        var arr3: Array<Int> = arr2 ?: arr1

        // arr3의 0번째 index에 해당하는 값 출력
        println(arr3.get(0))

        // 배열 내에 null값을 넣기 위해 <Int?> 사용
        var arr4: Array<Int?> = arrayOf(100, 90, null) 
    }
    ```

- Conditional Statement
    1. `if`문
        ```kt
        fun main(){
            val a = 5, b = 12

            if(a > b)
                println("a is greater.")
            else if(a < b)
                println("b is greater.")
            else
                println("a == b")

            val c: Double = b * 1.0 / a
            // 다른 타입 간의 비교 가능
            if(c > 1)
                println("c > 1")
            else
                println("c <= 1")
        }
        ```
    1. `when`문
        - `->` 뒤에는 반드시 1문장 이상(2문장 이상일 경우 중괄호 이용)
        - else는 필요 시에만 작성
        ```kt
        fun main(){
            val groupSize = 2
            
            when(groupSize){
                1 -> println("Single")
                2 -> println("Pair")
                3 -> {
                    println("Trio")
                }
                else -> println("This is either nobody or a big crowd.")
            }
        }
        ```

- Loop
    1. `for in`문
        ```kt
        fun main(){
            // 변수 i가 1~5까지 수행
            for(i in 1..5){
                println(i)
            }  

            // 배열의 원소 전체를 순서대로 수행
            for(i in arrayOf(50, 39, 6, -1)){
                println(i * -1.0)
            }
        }
        ```
    1. `while`문
        ```kt
        fun main(){
            var x = 5
            while(x > 0){
                println(x)
                x-- // x=x-1, x-=1
            } 
        }
        ```
    1. `do ... while`문
        ```kt
        fun main(){
            var y = 3
            do{
                println(y)
                y -= 1
            }while(y > 0)    
        }
        ```

- Function
    1. `caller`(호출자)
        - `pass by value` 방식으로 값 전달
        - 함수의 argument는 `val`이다.(var이 아님)
            - 호출자에서 전달되어 피호출자에 val로서 작동(ressign X)
    1. `callee`(피호출자)
        - 함수의 **parameter**의 type은 **명시**해주어야 함
        - return 값이 존재할 경우, **return type**도 **명시**해주어야 함
            - return type과 실제 return value의 type이 다를 경우 Error 발생
    ```kt
    fun f(k: Int): Int{
        k *= 2 // 오류. k는 변경될 수 없는 값
        var double_k = 2 * k
        return double_k
    }

    fun main(){
        // k@main과 k@f는 서로 다른 존재
        var k: Int = 5
        var ret: Int = f(k)
        println("$k\t$ret")
    }
    ```
- `Class`
    - 임의의 객체에 대한 틀
    - 클래스 이름, 멤버변수, 멤버함수 등으로 구성
        - 멤버변수 : type은 var, val로 직접 기입 가능(function과 달리)
            - 변수 생성시 getter(), setter() 자동 생성
            - 객체 생성시 전달 값 생략할 경우, 기본값 반환
        - `init`(생성자)
            - 객체 생성하자마자 자동 호출됨
            - var / val 여부가 지정되지 않을 경우, val로 취급되며, init 생성자에서만 사용 가능 
    - 접근 제한자
        1. `public` : class 외부에서 접근 가능
        1. `private` : class 외부에서 접근 불가
    ```kt
    class Person(
        private var age: Int,
        // "Noname"은 기본값
        public var name: String="Noname",
        sayhi: String,
    )
    {
        init {
            println("$name ($age): $sayhi")
        }
    }

    fun main(){
        var p1: Person = Person(10, "kevin", "Hi, there!") // init 호출

        println(p1.name)
        println(p1.age)  // 오류. private는 외부에서 접근 불가
    }
    ```

- `Generics`
    - 특정 데이터 타입에 국한하지 않고 구현 가능
     - `this` : 해당 클래스 이름을 대신하는 역할
        - 기능 정의시 같은 이름의 변수가 없을 경우 this를 생략 가능
    - put()의 parameter는 어떠한 타입도 받아들일 수 있다
    1. "Any" 클래스 
        - 모든 클래스의 super-class로서, 임의의 객체를 담을 수 있는 클래스 구현할 수 있음
        - 임의의 객체를 담는 Box 클래스 구현
        ```kt
        class Box{
            var content: Any? = null
            fun put(content: Any?){ // item 넣기
                // this = Box
                this.content = content
            }
            fun retrieve(): Any?{ // item 열기(박스를 비우지는 않음)
                return content
            }
            fun isEmpty(): Boolean{
                return content == null
            }
        }

        fun main(){
            var b1: Box = Box()
            b1.put("apple")

            var b2: Box = Box()
            b2.put(3)

            println(b1.retrieve()) // 출력 : apple
            println(b2.retrieve()) // 출력 : 3

            // b1에 서로 다른 클래스의 객체가 들어가게 된 것에 주목!
            b1.put(5)
            println(b1.retrieve()) // 출력 : 5
        }
        ```
    1. `Template`
        - 객체 생성시 `val` 키워드 사용함
            - 생성 후에는 클래스 자체에는 변화 없음
        - <> 안의 문자는 일관성 있게만 써주면 됨
            - 꼭 T로 쓸 필요없음
        - 특정 클래스의 객체 담기 위한 Box 클래스 구현
            - Any 클래스를 사용하는 것은 어떤 클래스 객체가 들어갔는지 알기 어려움
        ```kt
        class Box<T>{
            var content: T? = null
            fun put(content: T?){
                this.content = content
            }
            fun retrieve(): T?{
                return content
            }
            fun isEmpty(): Boolean{
                return content == null
            }
        }

        fun main(){
            val intBox = Box<Int>()
            intBox.put(4)
            val boolBox = Box<Boolean>()
            boolBox.put(true)
            boolBox.isEmpty()
        }
        ```