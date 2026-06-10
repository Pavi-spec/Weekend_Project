document.addEventListener("DOMContentLoaded", () => {

    const tabComponents = document.querySelectorAll(".custom-tabs");

    tabComponents.forEach(component => {

        const buttons = component.querySelectorAll(".tab-button");
        const contents = component.querySelectorAll(".tab-content");

        buttons.forEach(button => {

            button.addEventListener("click", () => {

                const currentIndex = button.dataset.tab;

                // Remove active state
                buttons.forEach(btn =>
                    btn.classList.remove("active")
                );

                contents.forEach(content =>
                    content.classList.remove("active")
                );

                // Add active state
                button.classList.add("active");

                component.querySelector(
                    `.tab-content[data-content="${currentIndex}"]`
                ).classList.add("active");

            });

        });

    });

});