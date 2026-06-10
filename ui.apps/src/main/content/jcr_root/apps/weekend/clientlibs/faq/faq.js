document.addEventListener("DOMContentLoaded", () => {

    const faqComponents = document.querySelectorAll(".faq-component");

    faqComponents.forEach(component => {

        const questions = component.querySelectorAll(".faq-question");
        const answers = component.querySelectorAll(".faq-answer");

        // Single expand/collapse
        questions.forEach(question => {

            question.addEventListener("click", () => {

                const answer = question.nextElementSibling;

                answer.classList.toggle("active");

            });

        });

        // Expand All
        component.querySelector(".expand-all")
            .addEventListener("click", () => {

                answers.forEach(answer => {
                    answer.classList.add("active");
                });

            });

        // Collapse All
        component.querySelector(".collapse-all")
            .addEventListener("click", () => {

                answers.forEach(answer => {
                    answer.classList.remove("active");
                });

            });

    });

});