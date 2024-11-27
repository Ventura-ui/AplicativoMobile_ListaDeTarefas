package br.edu.ifsp.dm1.gerenciadotarefas_mvvm.data.dao

import br.edu.ifsp.dm1.gerenciadotarefas_mvvm.data.model.Task

object TaskDao {

    private var tasks: MutableList<Task> = mutableListOf()


    fun add(task: Task){
        tasks.add(task)
    }

    fun getAll(): List<Task> {
        return tasks.sortedBy { it.isCompleted }
    }

    fun get(id: Long): Task?{
        return tasks.stream()
            .filter{t -> t.id == id}
            .findFirst()
            .orElse(null)
    }
}