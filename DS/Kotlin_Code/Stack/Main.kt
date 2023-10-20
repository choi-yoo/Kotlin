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