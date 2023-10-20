data class Node<T>(
    // 현재 노드의 값
    var value: T,
    // 다음 노드를 가리키는 링크
    var next: Node<T>? = null
){
    override fun toString(): String{
        return if(next != null) {
            // 재귀적으로 호출
            "$value -> ${next.toString()}"
        } else { // 마지막 노드인 경우
            "$value"
        }
    }

    fun printInReverse(){
        this.next?.printInReverse()

        if(this.next != null){
            print(" -> ")
        }

        print(this.value.toString())
    }
}