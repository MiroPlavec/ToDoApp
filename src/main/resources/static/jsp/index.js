// Show dialog window where user can fill information about task
function showAddDialog() {
    const dialog = document.getElementById("addTaskDialog");
    dialog.querySelector("h2").textContent = "Add new task";
    dialog.querySelector("#dialogSubmitBtn").style.display = "flex";
    dialog.querySelector("#dialogEditBtn").style.display = "none";
    dialog.style.display = "flex";
}

// Close dialog window
function closeAddDialog() {
    document.getElementById("addTaskForm").reset();
    document.getElementById('titleError').style.display = "none";
    document.getElementById('deadlineError').style.display = "none";
    document.getElementById("addTaskDialog").style.display = "none";
}

// Show dialog window where user can edit information about task
// It is the same div element as dialog for adding new task with few changes
// Id(primary key) for specific div in divList is save in data-id property on button
// So when specific button is clicked, i know id of specific task
// This Id is then saved as id of dialog window for editing
function showEditDialog(button){
    const dialog = document.getElementById("addTaskDialog");
    dialog.querySelector("h2").textContent = "Edit task";

    // get information from specific div (specific task)
    const divId = button.dataset.id; // this is primary key in my database
    const taskDiv = document.getElementById(divId);
    const titleText = taskDiv.querySelector("h2").textContent;
    const descriptioText = taskDiv.querySelector("#descriptionId").textContent;
    const deadlineDate = taskDiv.querySelector("#deadlineId").textContent;

    dialog.querySelector('[name="title"]').value = titleText;
    dialog.querySelector("textarea").value = descriptioText;
    dialog.querySelector('[name="deadlineDate"]').value = deadlineDate.split(":")[1].trim();
    dialog.dataset.id = divId;

    //change create button for edit button
    dialog.querySelector("#dialogSubmitBtn").style.display = "none";
    dialog.querySelector("#dialogEditBtn").style.display = "flex";

    dialog.style.display = "flex";

}

async function editTask(){
    // get taskId (primary key in my db) from dialog`s data-id
    const taskId = document.getElementById("addTaskDialog").dataset.id;

    const data = getDataFromForm();
    if(data == null) return;

    data.taskId = taskId;
    data.isCompleted = false;

    try {
        const response = await fetch("http://localhost:8080/home", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });
        document.open();
        document.write(await response.text());
        document.close();

    }catch (error){
        console.error("Error submitting form:", error);
        alert("Failed to edit task. Try again later.");
    }

}

// if title and deadlineDate are not set this function return false, otherwise true
function checkFormData(formData){
    const title = formData.get('title');
    const deadlineDate = formData.get('deadlineDate');

    const titleError = document.getElementById('titleError');
    const deadlineError = document.getElementById('deadlineError');

    // Validate title (must not be empty)
    if (!title) {
        titleError.style.display = "block"; // Show error if title is empty
    } else {
        titleError.style.display = "none"; // Hide error if title is filled
    }

    // Validate deadline date (must not be empty)
    if (!deadlineDate) {
        deadlineError.style.display = "block"; // Show error if deadline is empty
    } else {
        deadlineError.style.display = "none"; // Hide error if deadline is filled
    }

    // If either title or deadline is empty, stop form submission
    if (!title || !deadlineDate) {
        return false; // Prevent form submission if validation fails
    }

    return true;
}

// get information as title, deadline date ... from FORM
function getDataFromForm(){
    const formEl = document.getElementById("addTaskForm");
    if(!formEl){
        console.log("Form element not found");
        return;
    }
    const formData = new FormData(formEl);
    if(!checkFormData(formData)){
        return null;
    }

    return Object.fromEntries(formData.entries());
}

async function submitForm(){

    const data = getDataFromForm();
    if(data == null) return;

    data.isCompleted = false;

    try {
        const response = await fetch("http://localhost:8080/home", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        document.open();
        document.write(await response.text());
        document.close();

    } catch (error) {
        console.error("Error submitting form:", error);
        alert("Failed to add task. Try again later.");
    }
}

// Completed task
async function completeTask(button){
    const taskId = button.dataset.id;
    try{
        const response = await fetch("http://localhost:8080/home/"+taskId, {method: 'PUT'});
        document.open();
        document.write(await response.text());
        document.close();
    }catch (error) {
        console.error("Error submitting form:", error);
        alert("Failed to add task. Try again later.");
    }

}


// taskId for removing task is stored becouse before task is removed a confirm window is showed to the user
let currentTaskId = null;
function openConfirmDialog(button){
    currentTaskId = button.dataset.id;
    document.getElementById("confirmRemoveDialog").style.display = "flex";
}

async function removeTask(){
    try{
        const response = await fetch("http://localhost:8080/home/"+currentTaskId, {method: 'DELETE'});
        document.open();
        document.write(await response.text());
        document.close();
    }catch (error) {
        console.error("Error deleting task:", error);
        alert("Failed to delete task. Try again later.");
    }
}

function closeConfirmDialog(){
    document.getElementById("confirmRemoveDialog").style.display = "none";
}
