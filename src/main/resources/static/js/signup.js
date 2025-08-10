

document.getElementById("form-signup").addEventListener("submit", async function (event) {
event.preventDefault(); // Prevent normal form submission

const form = event.target;
const formData = new FormData(form);

// Convert form data to a plain object
const jsonObject = {};
formData.forEach((value, key) => jsonObject[key] = value);

// Send JSON to Spring Boot
const response = await fetch("/signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(jsonObject)
});

const data = await response.json();

if (response.ok) {
    alert("Signup successful");
    console.log("Signup successful " + JSON.stringify(data, null, 2));
} else {
    alert("Signup failed");
    console.log("Signup failed " + JSON.stringify(data, null, 2));
}
});