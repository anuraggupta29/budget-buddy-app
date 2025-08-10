

document.getElementById("form-add-exp").addEventListener("submit", async function (event) {
event.preventDefault(); // Prevent normal form submission

const form = event.target;
const formData = new FormData(form);

// Convert form data to a plain object
const jsonObject = {};
formData.forEach((value, key) => jsonObject[key] = value);

// Send JSON to Spring Boot
const jwtToken = localStorage.getItem("jwt");

const response = await fetch("/send-personal-expense", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + jwtToken
    },
    body: JSON.stringify(jsonObject)
});

const data = await response.json();

if (response.ok) {
    alert("Expense save successful");
    console.log("Expense save successful " + JSON.stringify(data, null, 2));
} else {
    alert("Expense save failed");
    console.log("Expense save failed " + JSON.stringify(data, null, 2));
}
});