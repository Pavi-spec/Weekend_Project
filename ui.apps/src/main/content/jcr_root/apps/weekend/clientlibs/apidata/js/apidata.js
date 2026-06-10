console.log("JS Loaded");

document.addEventListener("DOMContentLoaded", function () {

    const container = document.getElementById("userContainer");

    console.log("Container:", container);

    if (!container) {
        console.log("Container not found");
        return;
    }

    fetch("/bin/users")
        .then(response => {

            console.log("Response Status:", response.status);

            return response.json();
        })
        .then(users => {

            console.log("Users Data:", users);

            container.innerHTML = "";

            users.forEach(user => {

                const card = document.createElement("div");

                card.classList.add("user-card");

                card.innerHTML = `
                    <h3>${user.name}</h3>
                    <p><strong>Email:</strong> ${user.email}</p>
                    <p><strong>Phone:</strong> ${user.phone}</p>
                    <p><strong>Company:</strong> ${user.company.name}</p>
                `;

                container.appendChild(card);
            });
        })
        .catch(error => {
            console.error("API Error:", error);
        });
});