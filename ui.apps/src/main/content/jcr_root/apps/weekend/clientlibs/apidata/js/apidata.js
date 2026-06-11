console.log("JS Loaded");

document.addEventListener("DOMContentLoaded", function () {

    const container = document.getElementById("userContainer");

    console.log("Container:", container);

    if (!container) {
        console.log("Container not found");
        return;
    }

    fetch("/bin/users")
        .then(function (response) {

            console.log("Response Status:", response.status);

            return response.json();
        })
        .then(function (users) {

            console.log("Users Data:", users);

            container.innerHTML = "";

            users.forEach(function (user) {

                const card = document.createElement("div");

                card.classList.add("user-card");

                card.innerHTML =
                    "<h2>" + user.name + "</h2>" +

                    "<p><strong>ID:</strong> " + user.id + "</p>" +
                    "<p><strong>Username:</strong> " + user.username + "</p>" +
                    "<p><strong>Email:</strong> " + user.email + "</p>" +
                    "<p><strong>Phone:</strong> " + user.phone + "</p>" +
                    "<p><strong>Website:</strong> " + user.website + "</p>" +

                    "<h3>Address</h3>" +
                    "<p><strong>Street:</strong> " + user.address.street + "</p>" +
                    "<p><strong>Suite:</strong> " + user.address.suite + "</p>" +
                    "<p><strong>City:</strong> " + user.address.city + "</p>" +
                    "<p><strong>Zipcode:</strong> " + user.address.zipcode + "</p>" +

                    "<h3>Geo Location</h3>" +
                    "<p><strong>Latitude:</strong> " + user.address.geo.lat + "</p>" +
                    "<p><strong>Longitude:</strong> " + user.address.geo.lng + "</p>" +

                    "<h3>Company</h3>" +
                    "<p><strong>Name:</strong> " + user.company.name + "</p>" +
                    "<p><strong>Catch Phrase:</strong> " + user.company.catchPhrase + "</p>" +
                    "<p><strong>Business:</strong> " + user.company.bs + "</p>";

                container.appendChild(card);
            });
        })
        .catch(function (error) {

            console.error("API Error:", error);

            container.innerHTML =
                "<p>Failed to load user data.</p>";
        });

});