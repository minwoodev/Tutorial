window.addEventListener("DOMContentLoaded", function () {

    $(".header, .header_sub").on("mouseover", function () {
        $(".header_sub").css({
            display: "table",
            background: "white"
        });
    });

    $(".header, .header_sub").on("mouseout", function () {
        $(".header_sub").css({
            display: "none",
            background: "transparent"
        });
    });
        
    $(document).contextmenu(function() {
    	return false;
    });
});