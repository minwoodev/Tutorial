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
    
    document.onkeydown = function(e) {
        if(e.keyCode === 123 || 
          (e.ctrlKey && e.shiftKey && e.keyCode == 73) || 
          (e.ctrlKey && e.shiftKey && e.keyCode == 74) || 
          (e.ctrlKey && e.shiftKey && e.keyCode == 67)) {
            e.preventDefault();
            e.returnValue = false;
        }
    };
});