function addProductCard(ID, quantity) {

    $.ajax({
        type: "GET",
        url: "add-to-cart-servlet",
        data: {"productId": ID, "quantity": quantity},
        success: function() {

            let newQuantity = parseInt($("#count").text());
            newQuantity += quantity;
            $("#count").text(newQuantity);
        }
    });
}