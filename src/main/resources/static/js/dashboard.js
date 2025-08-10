

const API_URL_ADD_EXP = '/pexp';

document.getElementById('add-expense').addEventListener('click', () => {
  window.location.href = API_URL_ADD_EXP;
});

//-----------------------------------------------

document.getElementById("form-delete").addEventListener("submit", async function (event) {
    const jwtToken = localStorage.getItem("jwt");
    event.preventDefault(); // Prevent normal form submission

    const form = event.target;
    const formData = new FormData(form);

    // Convert form data to a plain object
    const jsonObject = {};
    formData.forEach((value, key) => jsonObject[key] = value);

    // Send JSON to Spring Boot
    const response = await fetch("/delete-expense", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + jwtToken
        },
        body: JSON.stringify(jsonObject)
    });

    const data = await response.json();

    if (response.ok) {
        alert("Delete successful");
        console.log("Delete successful " + JSON.stringify(data, null, 2));
    } else {
        alert("Delete failed");
        console.log("Delete failed " + JSON.stringify(data, null, 2));
    }
});



//-----------------------------------------------


document.getElementById("view-expense").addEventListener("click", async function () {
    const jwtToken = localStorage.getItem("jwt");

    try {
        const response = await fetch("/get-personal-expenses", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + jwtToken
            }
        });

        const data = await response.json();

        if (response.ok) {
            const tbody = document.getElementById("expenses-body");
            tbody.innerHTML = ""; // Clear old data

            data.expenses.forEach(expense => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${expense.id}</td>
                    <td>${expense.description}</td>
                    <td>${expense.amount.toFixed(2)}</td>
                    <td>${expense.expenseDate}</td>
                    <td>${expense.createdAt}</td>
                `;
                tbody.appendChild(row);
            });

            console.log("Expenses fetched " + JSON.stringify(data, null, 2));
        } else {
            alert("Expense fetch failed");
            console.log("Expense fetch failed " + JSON.stringify(data, null, 2));
        }
    } catch (error) {
        alert("Error fetching expenses: " + error.message);
        console.error(error);
    }
});