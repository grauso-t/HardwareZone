$("div.success div.alert").ready(function() {
    setTimeout(clearDiv, 10000);
});

function clearDiv() {
    $("div.success").fadeOut();
    $("div.alert").fadeOut();
    $("div.warning").fadeOut();
}

window.onbeforeunload = function(e) {
    alert("The Window is closing!");
};