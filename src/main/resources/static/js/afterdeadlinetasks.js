let url = "/afterdeadline";

function openConfirmRemoveDialog(button){
    document.getElementById("confirmRemoveDialog").dataset.taskId = button.dataset.id;
    document.getElementById("confirmRemoveDialog").style.display = "flex";
}

function closeConfirmRemoveDialog(){
    document.getElementById("confirmRemoveDialog").style.display = "none";
}

async function removeTask(){
    try{
        const taskId = document.getElementById("confirmRemoveDialog").dataset.taskId;
        const response = await fetch(url + "/" + taskId, {method: 'DELETE'});

        const html = await response.text();
        document.body.innerHTML = html;
        document.dispatchEvent(new Event("HTMLSwapped"));
    }catch (error) {
        console.error("Error deleting task:", error);
        alert("Failed to delete task. Try again later.");
    }
}