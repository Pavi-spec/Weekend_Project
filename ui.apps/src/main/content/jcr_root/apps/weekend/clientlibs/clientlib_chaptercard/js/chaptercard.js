(function (document) {

    "use strict";


    /* =====================================================
       INITIALIZE CHAPTER CARDS
       ===================================================== */

    function initializeChapterCards() {

        const chapters =
            document.querySelectorAll(
                "[data-chapter-cards]"
            );


        if (!chapters.length) {
            return;
        }


        chapters.forEach(function (chapter) {

            initializeLinks(chapter);

            initializeImages(chapter);

            initializeKeyboardSupport(chapter);

        });

    }


    /* =====================================================
       LINK HANDLING
       ===================================================== */

    function initializeLinks(chapter) {

        const links =
            chapter.querySelectorAll(
                ".chapter-card__link"
            );


        links.forEach(function (link) {

            link.addEventListener(
                "click",
                function () {

                    link.classList.add(
                        "chapter-card__link--clicked"
                    );

                }
            );

        });

    }


    /* =====================================================
       IMAGE HANDLING
       ===================================================== */

    function initializeImages(chapter) {

        const images =
            chapter.querySelectorAll(
                ".chapter-card__image"
            );


        images.forEach(function (image) {

            image.addEventListener(
                "error",
                function () {

                    image.classList.add(
                        "chapter-card__image--error"
                    );

                }
            );


            image.addEventListener(
                "load",
                function () {

                    image.classList.add(
                        "chapter-card__image--loaded"
                    );

                }
            );

        });

    }


    /* =====================================================
       KEYBOARD SUPPORT
       ===================================================== */

    function initializeKeyboardSupport(chapter) {

        const cards =
            chapter.querySelectorAll(
                ".chapter-card"
            );


        cards.forEach(function (card) {

            const link =
                card.querySelector(
                    ".chapter-card__link"
                );


            if (!link) {
                return;
            }


            card.addEventListener(
                "keydown",
                function (event) {

                    /*
                     * The anchor already handles Enter.
                     * Space is added for a card-like experience.
                     */

                    if (
                        event.key === " " &&
                        document.activeElement === link
                    ) {

                        event.preventDefault();

                        link.click();

                    }

                }
            );

        });

    }


    /* =====================================================
       DOM READY
       ===================================================== */

    if (
        document.readyState === "loading"
    ) {

        document.addEventListener(
            "DOMContentLoaded",
            initializeChapterCards
        );

    } else {

        initializeChapterCards();

    }


})(document);