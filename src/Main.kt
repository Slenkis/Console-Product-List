import com.jakewharton.picnic.TextAlignment
import com.jakewharton.picnic.table

var productList = mutableListOf<String>()

fun main(args: Array<String>) {
    while (true) {
        print("> ")
        val input = readLine()?.split(" ", limit = 2) ?: break
        if (input.isEmpty())
            break

        when (input[0]) {
            "add" -> add(input[1])
            "rm" -> removeItem(input[1])
            "l", "list" -> printList()
            "h", "?", "help" -> printHelp()
            "s", "sort" -> sortList()
        }
    }
}

fun removeAllItems() {
    productList.clear()
    println("Список очищен.")
}

fun sortList() {
    productList.sort()
    println("Список отсортирован.")
}

fun printHelp() {
    println(
        """--------------------
[h] or [help] or [?] - print this help
[l] or [list] - print list
[add] - add item to list
[rm] - remove item
--------------------"""
    )
}

fun printList() {
    if (productList.isEmpty()) {
        println("Пустой список!")
        return
    }

    println(table {
        cellStyle {
            border = true
            alignment = TextAlignment.TopCenter
        }
        header {
            row("Список продуктов")
        }
        body {
            cellStyle {
                border = true
                alignment = TextAlignment.TopLeft
            }
            productList.forEachIndexed { i: Int, s: String ->
                row("${i + 1}.$s")
            }
        }
    })
}

fun removeItem(product: String) {
    if (product == "*") {
        removeAllItems()
        return
    }

    val index = product.toIntOrNull()?.minus(1)
    if (index != null && index > 0) {
        if (index < productList.size) {
            productList.removeAt(index)
            println("Продукт удалён.")
        } else
            println("Неправильный номер.")
    } else if (productList.removeIf {
            it.equals(product, true)
        }) println("Продукт удалён.")
    else
        println("Неверное название.")
}

fun add(product: String) {
    productList.add(product)
}

