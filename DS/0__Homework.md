1. 구구단 2단부터 9단까지를 화면에 출력하려고 한다. 이를 for { ... for { ... } ...} 와 같이, 중첩 for 문으로 구현하였다. '곱셈' operation을 complexity 대상으로 생각했을 때, 이 코드의 Time complexity 는?
    - O(n^2)


1. Linked List 에 대한 설명으로 틀린 것은?
    - 맨 앞에 노드를 1개 추가하는 것은 O(1) 이다.
    - 맨 뒤(tail) 노드가 주어져있을 경우, 이 노드 뒤에 새로운 노드를 1개 추가하는 것은 O(n) 이다.(X)
    - 주어진 임의의 노드의 뒤에 새로운 노드 1개를 추가하는 것은 O(1)이다.
    - 임의의 i 번째에 위치한 노드에 접근하는 것은 O(i) (또는, O(n)) 이다.


1. Linked List와 Array에 대한 설명으로 틀린 것은?
    - (단방향) Linked List의 맨 뒤(tail)에 있는 노드를 삭제하는 것은 O(n) 이다.
    - Array의 맨 앞(head)에 있는 아이템을 삭제하는 것은 O(1)이다.(X)
    - Array의 맨 뒤(tail)에 아이템을 추가하는 것은 O(1)이다.
    - Linked List와 Array가 동일한 개수의 데이터를 저장할 경우, 상대적으로 Linked List가 메모리를 더 많이 소비하게 된다.


1. Array를 사용하여 구현된 Stack에 대한 설명으로 틀린 것은?
- Stack에 새로운 아이템을 추가하는 작업은 O(n) 이다. (X)
- Stack 에서 아이템을 1개 삭제하는 작업은 O(1) 이다.
- Array의 맨 뒤(tail)에 아이템을 추가하는 것은 O(1)이다.
- Linked List와 Array가 동일한 개수의 데이터를 저장할 경우, 상대적으로 Linked List가 메모리를 더 많이 소비하게 된다.
- Stack 은 가장 나중에 push된 아이템이 가장 처음으로 pop된다.


1. (단방향) Linked List 에서 맨 앞노드(head), 맨 뒤 노드(tail)를 직접 접근이 가능한 상황이라고 가정하자. 이러한 Linked List 를 사용해서 Stack 을 구현할 경우, 틀린 것은?
- Linked List의 맨 앞에 push, pop 이 되도록 구현하면 O(n)이 되므로 비효율적이다.(X)
- Push되는 아이템 개수가 갑작스럽게 커지는 경우가 많을 경우에는, Array 기반의 Stack보다는 Linked List 기반의 Stack이 메모리를 재할당하지 않아도 된다는 면에서 장점을 지닐 것이다.
- Linked List의 맨 뒤에 push, pop 이 되도록 구현이 가능할 것이다.
- Array 기반의 Stack 보다 전반적으로 메모리 공간을 더 많이 소비하게 될 것이다.



