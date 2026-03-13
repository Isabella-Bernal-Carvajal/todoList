"use client"
import { useEffect, useState } from "react"
import { getTasks } from "@/services/taskService"
import TaskForm from "@/components/TaskForm"
import TaskList from "@/components/TaskList"
import { Task } from "@/types/task"

export default function HomePage() {
  const [tasks, setTasks] = useState<Task[]>([])

  async function loadTasks() {
    const data = await getTasks()
    setTasks(data)
  }

  useEffect(() => {
  async function fetchTasks() {
    const data = await getTasks()
    setTasks(data)
  }
  fetchTasks()

}, [])

  return (
    <main className="bg-gray-50">
      
      <div className="max-w-xl mx-auto p-7">

        <h1 className="text-[29px] font-bold mb-6">
          Todo List
        </h1>

        <TaskForm onTaskCreated={loadTasks} />
        
        <TaskList tasks={tasks} />

      </div>
    </main>
  )
}