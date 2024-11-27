package br.edu.ifsp.dm1.gerenciadotarefas_mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.dm1.gerenciadotarefas_mvvm.data.dao.TaskDao
import br.edu.ifsp.dm1.gerenciadotarefas_mvvm.data.model.Task

class MainViewModel : ViewModel() {
    private val dao = TaskDao

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() {
            return _tasks
        }

    private val _insertTask = MutableLiveData<Boolean>()
    val insertTask: LiveData<Boolean> = _insertTask

    private val _updateTask = MutableLiveData<Boolean>()
    val updateTask: LiveData<Boolean>
        get() = _updateTask

    init {
        mock()
        load()
    }

    private var spinnerOption = "Todas as tarefas"

    fun updateSpinnerOption(option: String){
        spinnerOption = option
        load()
    }


    fun insertTask(description: String) {
        val task = Task(description, false)
        dao.add(task)
        _insertTask.value = true
        load()
    }

    fun updateTask(position: Int) {
        val task = dao.getAll()[position]
        task.isCompleted = !task.isCompleted
        _updateTask.value = true
        load()
    }

    private fun mock() {
        dao.add(Task("Arrumar a Cama", false))
        dao.add(Task("Retirar o lixo", false))
        dao.add(Task("Fazer trabalho de DMO1", true))
    }

    private fun load() {
        val allTasks = dao.getAll()

        if(spinnerOption == "Todas as tarefas"){
            _tasks.value = allTasks
        }else if(spinnerOption == "Tarefas não concluídas"){
            _tasks.value = allTasks.filter { !it.isCompleted }
        }else if(spinnerOption == "Tarefas concluídas"){
            _tasks.value = allTasks.filter { it.isCompleted }
        }
    }
}