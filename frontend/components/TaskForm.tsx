"use client"

import { useState } from "react"
import { createTask } from "@/services/taskService"

interface Props {
    onTaskCreated: () => void
}

export default function TaskForm({ onTaskCreated }: Props) {

    const [title, setTitle] = useState("")
    const [description, setDescription] = useState("")
    const [search, setSearch] = useState("")

    async function handleSubmit(e: React.FormEvent) {
    e.preventDefault()
        await createTask({
        title,
        description,
        status: "TODO",
        priority: "UNDEFINED"
    })

    setTitle("")
    setDescription("")
    setSearch("")

    onTaskCreated()
    }

    return (
        <form onSubmit={handleSubmit} className="space-y-2 mb-6">

        <input
            className="border p-2 w-full rounded-[7px]"
            placeholder="Task title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
        />

        <input
            className="border p-2 w-full rounded-[7px]"
            placeholder="Description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
        />

        <button className="bg-blue-700 align-middle w-64 text-white text-[17px] font-sans px-4 py-2 rounded-[10px]">Add task</button>
        </form>
    )
}