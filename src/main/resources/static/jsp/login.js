async function sendUserData(){
    const formEl = document.getElementById("form");
    const formData = new FormData(formEl);
    const username = formData.get("username");
    const password = formData.get("password");

    const data = Object.fromEntries(formData.entries());
    try {
        const response = await fetch("", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });
        location.reload();
    } catch (error) {
        console.error("Error submitting form:", error);
        alert("Failed to add task. Try again later.");
    }
}