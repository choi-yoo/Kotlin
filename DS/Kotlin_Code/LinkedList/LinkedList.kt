// Collection 인터페이스 : Iterable 포함 -> Iterable<T> 부분은 안 써도 됨
//                       size 변수, isEmpty() 포함 -> 이들 앞에 override 써줘야 함
class LinkedList<T>: Collection<T>{
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override var size = 0 // Collection에 포함되는 변수
        private set // read only

    override fun iterator(): Iterator<T>{
        return LinkedListIterator(this)
    }

    override fun isEmpty(): Boolean{ // Collection에 포함되는 함수
        return size == 0
    }

    // 새롭게 추가된 override 함수들
    // contains의 시간복잡도 : O(n)
    // containsAll의 시간복잡도 : O(n^2)
    override fun contains(element: T): Boolean{
        for (item in this)
            if (item == element) return true
        return false
    }
    override fun containsAll(elements: Collection<T>): Boolean{
        for(searched in elements)
            if(!contains(searched)) return false
        return true
    }


    override fun toString(): String{
        if(isEmpty()){
            return "Empty list"
        }
        return head.toString()
    }

    // Head-first insertion: 리스트의 가장 앞에 node 추가
    fun push(value: T): LinkedList<T>{
        head = Node(value = value, next = head)
        if(tail == null){
            tail = head
        }
        size++
        return this
    }

    // Tail-end insertion: 리스트의 맨 뒤에 node 추가
    fun append(value: T): LinkedList<T>{
        // LinkedList가 비어 있는 경우
        if(isEmpty()){
            push(value)
            return this
        }

        // LinkedList가 비어 있지 않는 경우
        // 새로운 노드 형성하고 현재 tail 노드의 다음 노드로 설정
        tail?.next = Node(value = value)

        // 새로운 노드를 tail 노드로 업데이트
        tail = tail?.next
        size++
        return this
    }

    // 기존에 존재하던 append() 함수와 이름은 동일하지만
    // 전혀 다른 매개변수를 가진 함수를 추가로 정의 가능
    private fun append(
        result: LinkedList<T>,
        node: Node<T>
    ): Node<T>?{
        result.append(node.value)
        return node.next
    }

    // 주어진 index번 째에 위치하는 노드 객체 반환
    fun nodeAt(index: Int): Node<T>?{
        var currentNode = head
        var currentIndex = 0

        while(currentNode != null && currentIndex < index){
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    // value값을 가진 노드를 afterNode 뒤에 삽입
    fun insert(value: T, afterNode: Node<T>):Node<T>{
        //
        if(tail == afterNode){
            append(value)
            return tail!!
        }

        val newNode = Node(value = value, next = afterNode.next)

        afterNode.next = newNode
        size++
        return newNode
    }

    // 맨 앞에 위치한 node 제거 & 해당 값을 리턴
    fun pop(): T?{
        // 연결 리스트가 비어있지 않을 경우
        if(!isEmpty()) size --

        // head의 value을 result에 저장
        val result = head?.value
        // head를 다음 노드로 설정
        head = head?.next

        // 연결 리스트가 비어 있을 경우
        if(isEmpty()){
            tail = null
        }
        return result
    }

    fun removeLast(): T?{
        // head가 null이 아닐 경우, head에 복사
        // head가 null일 경우, null 반환하고 함수 종료
        val head = head ?: return null

        // 노드가 하나만 존재할 경우
        if(head.next == null) return pop()

        size--

        // loop 수행할 세 변수 초기화
        var prev = head
        var current = head
        var next = current.next
        // 리스트의 마지막 노드에 도달할 때까지 반복
        // current가 마지막 노드임
        while(next != null){
            prev = current
            current = next
            next = current.next
        }

        // current를 끊어냄
        prev.next = null
        // prev가 마지막 노드가 됨
        tail = prev
        return current.value
    }

    // 임의의 위치의 node 삭제 & 해당 값 리턴
    fun removeAfter(node: Node<T>): T?{
        val result = node.next?.value
        if(node.next == tail){
            tail = node
        }
        if(node.next != null){
            size--
        }
        node.next = node.next?.next
        return result
    }

    fun printInReverse(){
        this.nodeAt(0)?.printInReverse()
    }

    fun getMiddle(): Node<T>?{
        var slow = this.nodeAt(0)
        var fast = this.nodeAt(0)

        while(fast != null){
            fast = fast.next
            if(fast != null){
                fast = fast.next
                slow = slow?.next
            }
        }
        return slow
    }

    private fun addInReverse(list: LinkedList<T>, node: Node<T>){
        val next = node.next

        if(next != null){
            // 재귀적으로 호출
            addInReverse(list, next)
        }

        // 새로운 리스트에 노드 삽입
        list.append(node.value)
    }

    fun reversed(): LinkedList<T>{
        // 새로운 링크드 리스트 생성
        val result = LinkedList<T>()

        val head = this.nodeAt(0)
        if(head != null){
            addInReverse(result, head)
        }
        return result
    }

    fun mergeSorted(
        otherList: LinkedList<T>
    ): LinkedList<T>{
        // this가 빈 링크드 리스트일 경우
        if(this.isEmpty()) return otherList

        // other이 빈 링크드 리스트일 경우
        if(otherList.isEmpty()) return this

        val result = LinkedList<T>()
        // left는 this의 head를 가리킴
        var left = nodeAt(0)
        // right는 other의 head를 가리킴
        var right = otherList.nodeAt(0)

        while(left != null && right != null){
            if((left.value as Int) < (right.value as Int)){
                left = append(result, left)
            }
            else{
                right = append(result, right)
            }
        }

        while(left != null){
            left = append(result, left)
        }

        while(right != null){
            right = append(result, right)
        }

        return result
    }

}

// Iterator 인터페이스: hasNext() 함수, next() 함수 정의
// hasNext() : 다음 요소가 존재하는지 여부를 확인
// next() : 다음 요소 반환
class LinkedListIterator<K>(
    private val list: LinkedList<K>
): Iterator<K>{
    private var index = 0
    private var lastNode: Node<K>? = null

    override fun next(): K{
        if(index >= list.size) throw IndexOutOfBoundsException()
        lastNode = if(index ==0){
            list.nodeAt(0)
        }else{
            lastNode?.next
        }
        index++
        return lastNode!!.value
    }
    override fun hasNext(): Boolean{
        return index < list.size
    }
}