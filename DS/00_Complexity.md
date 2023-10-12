# Complexity Dimensions
- expensive algorithm = 효율이 낮음
- scalability 에 대한 measure

## Dimension "Time"
- `Time Complexity`
    - 시간 효율이 낮은 알고리즘은 데이터 양이 늘어날수록 cost가 크게 증가
    - 이때 시간이란, operation의 횟수를 기반으로 실행시간을 정량화한 것
        - 머신 스팩, 작은 규모의 데이터 등에 따라 실제 실행 시간이 달라질 수 있음
- `Big O Notation`
    - 해당 알고리즘 실행 효율의 최악 case $\Rightarrow$ 상한선 표기
    1. `Constant time` : $O(1)$
        - 데이터 양과 관계없이 동일한 실행시간
        ```kt
        fun checkFirst(name: List<String>){
            if(name.firstOrNull() != null)
                println(name.first())
            else
                println("no name")
        }

        fun main(){
            var names: List<String> = listOf("Kevin", "Mila", "Sally")
            checkFirst(names) // 출력 : Kevin
        }
        ```
    1. `Linear time` : $O(n)$
        - 데이터 양이 증가함에 따라 실행시간도 선형 비례 증가
        ```kt
        fun printNames(names: List<String>){
            for(name in names)
                println(name)
        }

        fun main(){
            var names: List<String> = listOf("Kevin", "Mila", "Sally")
            printNames(names) // 출력 : Kevin\nMila\nSally
        }
        ```
    1. `Quadratic time` : $O(n^2)$
        - 데이터 양이 증가함에 따라 실행시간도 제곱(n squared) 비례 증가
        ```kt
        fun multiplicationBoard(size: Int){
            for(number in 1..size){
                print(" | ")
                for(otherNumber in 1..size){
                    print("$number x $otherNumber = ${number * otherNumber} | ")
                }
                println()
            }
        }

        fun main(){
            multiplicationBoard(5) // 출력 : | 1 x 1 = 1 | 1 x 2 = 2 | 1 x 3 = 3 | 1 x 4 = 4 | 1 x 5 = 5 |형식으로 5단까지 출력
        }
        ```
    1. `Logarithm time`
        - 모든 n개의 데이터에 대해 최소한 1번씩 access하지 않음
        - 데이터에 대한 임의의 condition/knowledge/assumption를 바탕으로 데이터 일부만 access하여 반복 횟수 줄임
        - Log의 base는 큰 의미가 없음(줄어듦에 초점)
        - 예. 데이터가 정렬되어 있다는 조건하에 특정 수를 탐색한다면 모든 데이터를 access하지 않고도 가능
        ```kt
        // 모두 access하는 linear complexity
        fun lineraContains(value: Int, numbers: List<Int>): Boolean{
            for(element in numbers){
                if(element == value)
                    return true
            }
            return false
        }

        fun main(){
            val number = listOf(1, 3, 56, 66, 68, 80, 99, 105, 450)
            println(linearContains(99, numbers)) // 출력 : true
        }
        ``` 
        ```kt
        // 데이터 중 일부만 access하는 logarithm complexity
        fun pseudoBinaryContains(value: Int, number: List<Int>): Boolean{
            if(numbers.isEmpty()) return false
            val middleIndex = number.size / 2

            if(value <= number[middleIndex]){
                for(index in 0..middleIndex){
                    if (numbers[index] == value)
                        return true
                }
            }
            else{
                for(index in middleIndex until numbers.size){
                    if(numbers[index] == value)
                        return true
                }
            }
            return false
        }

        fun main(){
            val numbers = listOf(1, 3, 56, 66, 68, 80, 99, 105, 450)
            println(pseudoBinaryContains(99, numbers)) // 출력 : true
        }
        ```
    1. `Quasilinear time` : $O(n*logn)$
        - $O(n) \lt O(n*logn) \lt O(n^2)$
    1. 기타
        - Polynomial time
        - Exponential time
        - Factorial time
- 효율적인 시간 복잡도 구하기
    - 1 ~ n까지의 정수 합 구하는 프로그램
        1. $O(n)$
            ```kt
            fun sumFromOne(n: Int): Int{
                var result = 0
                for(i in 1..n){
                    result += i
                }
                return result
            }
            ```
            ```kt
            fun sumFromOne(n: Int): Int{
                return (1..n).reduce{
                    sum, element -> sum + element
                }
            }
            ```
        1. $O(1)$
            ```kt
            // Fredrick Gauss 알고리즘 사용
            fun sumFromOne(n: Int): Int{
                return n * (n + 1) / 2
            }
            ```

## Dimension "Memory Usage"
- Space Complextiy
    - 알고리즘이 사용하는 메모리
    - 정수 정렬 알고리즘
        1. $O(n)$
            - 인자로 전달된 numbers와 동일한 크기의 List가 메모리에 생성되므로
            ```kt
            fun printSorted(numbers: List<Int>){
                val sorted = numbers.sorted()
                for(element in sorted){
                    println(element)
                }
            }

            fun main(){
                val numbers = listOf(1, -5, 3, 56, 66, 68, 80, 99, 105, 450)
                printSorted(numbers)
            }
            ```
        1. $O(1)$
            - space complexity를 줄이기 위해 time complexity를 희생함
            ```kt
            fun printSorted(numbers: List<Int>){
                if(numbers.isEmpty()) return

                var currentCount = 0
                var minValue = Int.MIN_VALUE
                
                for(value in numbers){
                    if(value == minValue){
                        println(value)
                        currentCount += 1
                    }
                }

                while(currentCount < numbers.size) {
                    var currentValue = numbers.maxOrNull()!!
                    for(value in numbers){
                        if(value < currentValue && value > minValue)
                            currentValue = value
                    }
                    for(value in numbers){
                        if(value == currentValue){
                            println(value)
                            currentCount += 1
                        }
                    }
                    minValue = currentValue
                }
            }

            fun main(){
                val numbers = listOf(1, -5, 3, 56, 66, 68, 80, 99, 105, 450)
                printSorted(numbers)
            }
            ```






