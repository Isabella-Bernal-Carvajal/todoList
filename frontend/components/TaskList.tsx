import { Task } from "@/types/task"

interface Props {
    tasks: Task[]
}

export default function TaskList({ tasks }: Props) {

    return (
        
        <div className="space-y-6">
        {tasks.map((task) => (
            <div key={task.id} className="border p-4 rounded">
            <h3 className="font-bold">{task.title}</h3>

            <p>{task.description}</p>

            <div className="flex justify-between">
                <div className="flex gap-4">
                    <button className="bg-[#e8effd] border-blue-300 text-blue-700 px-4 py-0.5 rounded-[10px]">
                        {task.status === "COMPLETED"
                        ? "Completed"
                        : "ToDo"}
                    </button>

                    <button className="bg-[#fcece0] border-orange-300 text-orange-700 px-5 py-0.5 rounded-[10px]">
                        {task.priority === "UNDEFINED"
                        ? "LOW" 
                        : "MEDIUM"}
                    </button>   
                </div>
                </div>
            </div>
            
        ))}
        </div>
    )
}