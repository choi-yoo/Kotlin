# Stack
- `Last-in First-out`  
![Alt text](image/stack.png)
- 주요 기능
    -`push()` : 가장 위쪽에 item 추가
        - 해당 함수의 시간 복잡도 : $O(1)$
    - `pop()` : 가장 위에 있는 item 제거
        - 해당 함수의 시간 복잡도 : $O(1)$
```kt
// StackInterface.kt
interface StackInterface<Element> {
    fun push(element: Element)
    fun pop(): Element?
}

// Stack.kt
class Stack<Element>(): StackInterface<Element> {
    // ArrayList 타입의 콜렉션 생성
    private val storage = arrayListOf<Element>()

    // buildString : 문자열을 동적으로 빌드 및 구성하는 코틀린 표준 라이브러리에서 제공하는 함수.
    //               문자열을 순차적으로 만들어나갈 수 있으며, 불필요한 문자열 복사를 피할 수 있음.
    // 객체를 문자열로 표현하는 기능을 새로 정의
    override fun toString() = buildString { // this: StringBuilder
        appendLine("----top----")
        // asReversed() : 리스트 또는 배열의 요소 순서를 역순으로 변경하는 함수
        //                Orignal 변경 없이, 그 역순을 가진 새로운 컬렉션 반환
        // forEach : 컬렉션의 각 요소에 대해 주어진 작업 수행하는 함수
        //           람다 함수를 인자로 받아 컬렉션의 각 요소를 이 람다 함수에 전달하여 처리
        storage.asReversed().forEach{ // it: Element
            // it : 현재 순회 중인 요소를 참조하는 식별자
            appendLine("$it")
        }
        appendLine("------------")
    }

    override fun push(element: Element){
        storage.add(element)
    }

    override fun pop(): Element?{
        if(storage.size == 0){
            return null
        }
        // removeAt(index: Int) : 리스트에서 지정된 인덱스에 있는 요소 삭제
        // remove(element: E) : 리스트에서 특정 요소 삭제
        return storage.removeAt(storage.size - 1)
    }
}

// Main.kt
fun main(){
    // apply : 객체 초기화나 설정 작업을 한꺼번에 처리하는 유용한 코틀린 함수
    val stack = Stack<Int>().apply{ // this: Stack<Int>
        push(1)
        push(2)
        push(3)
        push(4)
    }

    // 출력
    // ----top----
    // 4
    // 3
    // 2
    // 1
    // ------------
    print(stack)

    val poppedElement = stack.pop()
    if(poppedElement != null){
        println("Popped: $poppedElement") // 출력 : Popped: 4
    }

    // 출력
    // ----top----
    // 3
    // 2
    // 1
    // ------------
    print(stack)
}
```

## 개량
- `peek()`
    ```kt
    // StackInterface.kt
    interface StackInterface<Element> {
        val count: Int
            get

        fun peek(): Element?
        val isEmpty: Boolean
            get() = count == 0

        fun push(element: Element)
        fun pop(): Element?
    }

    // Stack.kt
    class Stack<Element>(): StackInterface<Element> {
                        .
                        .
                        .

        override fun peek(): Element?{
            // lastOrNull() : 컬렉션의 마지막 요소를 반환하거나 해당 컬렉션이 비어있을 경우 null을 반환하는 Kotlin의 표준 라이브러리 함수
            return storage.lastOrNull()
        }

        override val count: Int
            get() = storage.size

                        .
                        .
                        .

        override fun pop(): Element?{
            if(storage.size == 0){
                return null
            }

            // storage.size를 count로 고침
            return storage.removeAt(count - 1)
        }
    }

    // Main.kt
    fun main(){
        val stack = Stack<Int>().apply{
            push(1)
            push(2)
            push(3)
            push(4)
        }

        // 출력
        // ----top----
        // 4
        // 3
        // 2
        // 1
        // ------------
        print(stack)

        println(stack.peek()) // 출력 : 4

        // 출력
        // ----top----
        // 4
        // 3
        // 2
        // 1
        // ------------
        print(stack)
    }
    ```

- `Iterable`, `Collection` 인터페이스를 상속받으면 더 이상 Stack라고 볼 수 없음
    - 이론상으로는 해당 인터페이스들을 상속받을 수 있음

- `checkParentheses`
    - 괄호 개수가 짝수인지 체크하는 함수
    - 해당 함수의 시간복잡도 : $O(n)$
        - n : 주어진 문자열의 길이
    - String 클래스의 멤버함수를 직접 정의
        - import 없이도 접근/사용 가능한 범위에서는 해당 함수 이용 가능
```kt
// Main.kt
// String : Kotlin 표준 라이브러리에 정의된 문자열을 나타내는 클래스
// checkParentheses() : Extension Function(사용자 정의 함수)
fun String.checkParentheses(): Boolean{
    val stack = Stack<Char>()
    for(character in this){
        when(character){
            '(' -> stack.push(character)
            ')' -> if (stack.isEmpty) return false
            else stack.pop()
        }
    }
    return stack.isEmpty
}

fun main(){
    var s = "h((e))llo(world)()"
    println(s.checkParentheses()) // 출력 : true
    println("(hello world".checkParentheses()) // 출력 : false 
}
```


