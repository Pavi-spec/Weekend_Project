(function (document, $) {

    "use strict";


    function showSelectedOption() {

        var $dialog = $(".cq-dialog");

        var selectedValue =
            $dialog.find("select[name='./bannerType']").val();


        /*
         * Hide ALL options first
         */

        $dialog
            .find(".banner-option")
            .hide();


        /*
         * If nothing is selected,
         * don't show anything.
         */

        if (!selectedValue) {
            return;
        }


        /*
         * Show ONLY selected option
         */

        $dialog
            .find(
                ".banner-option[data-banner-option='" +
                selectedValue +
                "']"
            )
            .show();

    }


    /*
     * When dialog opens
     */

    $(document).on(
        "foundation-contentloaded",
        function () {

            showSelectedOption();

        }
    );


    /*
     * When dropdown changes
     */

    $(document).on(
        "change",
        "select[name='./bannerType']",
        function () {

            showSelectedOption();

        }
    );


})(document, Granite.$);