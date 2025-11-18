

document.getElementById("form-login").addEventListener("submit", async function (event) {
event.preventDefault(); // Prevent normal form submission

const form = event.target;
const formData = new FormData(form);

// Convert form data to a plain object
const jsonObject = {};
formData.forEach((value, key) => jsonObject[key] = value);

console.log(jsonObject);

// Send JSON to Spring Boot
const response = await fetch("/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(jsonObject)
});

const data = await response.json();

if (response.ok) {
    localStorage.setItem('jwt', data.token);

    console.log("Login successful " + JSON.stringify(data, null, 2));

    // Redirect to dashboard
    window.location.href = data.redirect;
} else {
    alert("Login failed");
    console.log("Login failed " + JSON.stringify(data, null, 2));
}
});