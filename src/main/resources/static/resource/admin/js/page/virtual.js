if (typeof jQuery === "undefined") {
    throw new Error("jQuery plugins need to be before this file");
}
$(function() {
    "use strict";

    /*****Owl Slider************/
    $(document).ready(function(){
        $('.owl-carouselthree').owlCarousel({
            loop:true,
            margin:15,
            autoplay:true,
            autoplayTimeout:3000,
            autoplayHoverPause:true,
            responsive:{
                0:{
                    items:1
                },
                400:{
                    items:2
                },
                480:{
                    items:3
                },
                600:{
                    items:4
                },
                1000:{
                    items:5
                },
                1100:{
                    items:6
                },
                1400:{
                    items:6
                },
                1501:{
                    items:8
                }
            }
        })
    });
    
    // Heartbeat
    $(document).ready(function() {
        let slideBox = document.querySelector('.slide-box');
        let solid = document.querySelector('.solid');
        let width = 100;
        let slide = setInterval(() => {
            solid.style.width = `${width}%`;
            width -= 1;
        }, 20)
        
        setTimeout(() => {
            clearInterval(slide);
            solid.style.width = `100%`;
        }, 2050);
        
        setInterval(() => {
        
            width = 100;
            let slide = setInterval(() => {
                solid.style.width = `${width}%`;
                width -= 1;
            }, 20)
        
            setTimeout(() => {
                clearInterval(slide);
            }, 2050);
        
        }, 2050);
    }); 
   
});

