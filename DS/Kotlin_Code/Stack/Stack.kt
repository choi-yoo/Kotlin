class Stack<Element>(): StackInterface<Element> {
    // ArrayList 타입의 콜렉션 생성
    private val storage = arrayListOf<Element>()

    // buildString : 문자열을 동적으로 빌드 및 구성하는 코틀린 표준 라이브러리에서 제공하는 함수.
    //               문자열을 순차적으로 만들어나갈 수 있으며, 불필요한 문자열 복사를 피할 수 있음.
    // 객체를 문자열로 표현하는 기능을 새로 정의
    override fun toString() = buildString {
        appendLine("----top----")
        // asReversed() : 리스트 또는 배열의 요소 순서를 역순으로 변경하는 함수
        //                Orignal 변경 없이, 그 역순을 가진 새로운 컬렉션 반환
        // forEach : 컬렉션의 각 요소에 대해 주어진 작업 수행하는 함수
        //           람다 함수를 인자로 받아 컬렉션의 각 요소를 이 람다 함수에 전달하여 처리
        storage.asReversed().forEach{
            // it : 현재 순회 중인 요소를 참조하는 식별자
            appendLine("$it")
        }
        appendLine("------------")
    }

    override fun peek(): Element?{
        return storage.lastOrNull()
    }

    override val count: Int
        get() = storage.size

    override fun push(element: Element){
        storage.add(element)
    }

    override fun pop(): Element?{
        if(storage.size == 0){
            return null
        }
        // removeAt(index: Int) : 리스트에서 지정된 인덱스에 있는 요소 삭제
        // remove(element: E) : 리스트에서 특정 요소 삭제
        return storage.removeAt(count - 1)
    }
}