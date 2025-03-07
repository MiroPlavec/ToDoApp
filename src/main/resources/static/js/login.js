const protocol = window.location.protocol;
const host = window.location.host;


async function sendUserData(){
    const formEl = document.getElementById("form");
    const formData = new FormData(formEl);
    const username = formData.get("username");
    const password = formData.get("password");

    const data = Object.fromEntries(formData.entries());

    try {
        const response = await fetch("/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();
        if(response.ok && result.redirect){
            window.location.href = result.redirect;
        }else{
            alert(result.redirect);
        }
    } catch (error) {
        console.error("Error submitting login information:", error);
    }
}