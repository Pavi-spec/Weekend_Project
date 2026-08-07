(function (document, $) {

    "use strict";

    function updateBannerDialog() {

        var $dialog = $(".cq-dialog");

        if (!$dialog.length) {
            return;
        }

        var selectedValue = $dialog
            .find("select[name='./bannerType']")
            .val();

        /*
         * Hide everything first
         */
        $dialog
            .find(".banner-option")
            .each(function () {

                $(this).hide();

            });


        /*
         * Show only Primary
         */
        if (selectedValue === "primary") {

            $dialog
                .find(".banner-primary")
                .show();

        }


        /*
         * Show only Secondary
         */
        else if (selectedValue === "secondary") {

            $dialog
                .find(".banner-secondary")
                .show();

        }


        /*
         * Show only Tertiary
         */
        else if (selectedValue === "tertiary") {

            $dialog
                .find(".banner-tertiary")
                .show();

        }

    }


    /*
     * When dialog opens
     */
    $(document).on(
        "foundation-contentloaded",
        function () {

            setTimeout(function () {

                updateBannerDialog();

            }, 200);

        }
    );


    /*
     * When Select changes
     */
    $(document).on(
        "change",
        "select[name='./bannerType']",
        function () {

            updateBannerDialog();

        }
    );


})(document, Granite.$);