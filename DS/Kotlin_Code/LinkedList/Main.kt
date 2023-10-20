fun main(){
    val list = LinkedList<Int>()
    list.append(1)
    list.append(2)
    list.append(3)
    list.append(4)
    list.append(5)

    val other = LinkedList<Int>()
    other.append(-1)
    other.append(-2)
    other.append(-3)
    other.append(-4)
    other.append(-5)

    println("Left: $list")   // 출력 : 1 -> 2 -> 3 -> 4 -> 5
    println("Right: $other") // 출력 : -1 -> -2 -> -3 -> -4 -> -5
    println("Merged: ${list.mergeSorted(other)}")
}

