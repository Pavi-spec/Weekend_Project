console.log("API Integration JS Loaded");
document.addEventListener("DOMContentLoaded", () => {

    const container = document.getElementById("api-data-container");

    fetch("https://jsonplaceholder.typicode.com/users")

        .then(response => response.json())

        .then(data => {

            let output = "";

            data.forEach(user => {

                output += `

                    <div class="user-card">

                        <h3>${user.name}</h3>

                        <p><strong>Email:</strong> ${user.email}</p>

                        <p><strong>Phone:</strong> ${user.phone}</p>

                        <p><strong>Company:</strong> ${user.company.name}</p>

                    </div>

                `;

            });

            container.innerHTML = output;

        })

        .catch(error => {

            container.innerHTML = "Failed to load API data";

            console.error(error);

        });

});
