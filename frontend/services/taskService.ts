import { Task } from "@/types/task"

const API_URL = "http://localhost:8080/task"

export async function getTasks(): Promise<Task[]> {

    const response = await fetch(API_URL)

    if (!response.ok) {
        throw new Error("Error fetching tasks")
    }

    return response.json()
}

export async function createTask(task: Task): Promise<Task> {

    const response = await fetch(API_URL, {
        method: "POST",
        headers: {
        "Content-Type": "application/json"
        },
        body: JSON.stringify(task)
    })

    if (!response.ok) {
        throw new Error("Error creating task")
    }

    return response.json()
}
