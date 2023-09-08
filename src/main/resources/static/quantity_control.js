&(document).ready(function() {
    $(".minusButton").on("click", function(evt) {
        evt.preventDefault();
        itemId = $(this).attr("pid");
        qtyInput = $("#qnt" + itemId);

        newQnt = parseInt(qtyInput.val()) - 1;
        if (newQnt > 0) qtyInput.val(newQnt);
    });

    $(".plusButton").on("click", function(evt) {
            evt.preventDefault();
            itemId = $(this).attr("pid");
            qtyInput = $("#qnt" + itemId);

            newQnt = parseInt(qtyInput.val()) + 1;
            if (newQnt < 999) qtyInput.val(newQnt);
        });
});