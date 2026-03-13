
export type Status = "TODO" | "INPROGRESS" | "COMPLETED"

export type Priority = "LOW" | "MEDIUM" | "HIGH" | "UNDEFINED"

export interface Task {
    id?: number
    title: string
    description: string
    status: Status
    priority: Priority
}