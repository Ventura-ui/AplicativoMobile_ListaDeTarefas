package br.edu.ifsp.dm1.gerenciadotarefas_mvvm.data.model

class Task(var description: String, var isCompleted: Boolean) {

    private companion object {
        var lastId: Long = 1L
    }

    var id: Long = 0L

    init {
        id = lastId
        lastId += 1
    }
}