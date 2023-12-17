if (typeof jQuery === "undefined") {
    throw new Error("jQuery plugins need to be before this file");
}
$(function() {
    "use strict";
  
   // Stacked Area
    $(document).ready(function() {
        var options = {
            chart: {
                height: 300,
                type: 'area',
                stacked: true,
                toolbar: {
                    show: false,
                },
                events: {
                    selection: function(chart, e) {
                    //console.log(new Date(e.xaxis.min) )
                    }
                },
            },

            colors: ['var(--chart-color3)'],
            dataLabels: {
                enabled: false
            },

            series: [
                {
                    name: 'Patient',
                    data: generateDayWiseTimeSeries(new Date('11 Feb 2017 GMT').getTime(), 20, {
                        min: 10,
                        max: 60
                    })
                }
            ],

            fill: {
                type: 'gradient',
                gradient: {
                    gradientToColors: [ 'var(--chart-color3)'],
                    opacityFrom: 0.6,
                    opacityTo: 0.8,
                }
            },

            legend: {
                position: 'top',
                horizontalAlign: 'right',
                show: true,
            },
            xaxis: {
                type: 'datetime',            
            },
            grid: {
                yaxis: {
                    lines: {
                        show: false,
                    }
                },
                padding: {
                    top: 20,
                    right: 0,
                    bottom: 0,
                    left: 0
                },
            },
            stroke: {
                show: true,
                curve: 'smooth',
                width: 2,
            },
        }

        var chart = new ApexCharts(
            document.querySelector("#apex-stacked-area"),
            options
        );
        chart.render();
        function generateDayWiseTimeSeries(baseval, count, yrange) {
            var i = 0;
            var series = [];
            while (i < count) {
                var x = baseval;
                var y = Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min;

                series.push([x, y]);
                baseval += 86400000;
                i++;
            }
            return series;
        }
    });

    // Addmission by Division
    $(document).ready(function() {
        
        var options = {
            series: [{
                name: 'Cardiologist',
                data: [45, 25, 44, 23, 25, 41, 32, 25, 22, 65, 22, 29]
            }, {
                name: 'Physician',
                data: [45, 12, 25, 22, 19, 22, 29, 23, 23, 25, 41, 32]
            }, {
                name: 'Neurologist',
                data: [45, 25, 32, 25, 22, 65, 44, 23, 25, 41, 22, 29]
            }, {
                name: 'Orthopedic',
                data: [32, 25, 22, 11, 22, 29, 16, 25, 9, 23, 25, 13]
            }],
            chart: {
                type: 'bar',
                height: 300,
                stacked: true,
                toolbar: {
                    show: false
                },
                zoom: {
                    enabled: true
                }
            },
            colors: ['var(--chart-color1)','var(--chart-color2)','var(--chart-color3)','var(--chart-color4)'],
            responsive: [{
                breakpoint: 480,
                options: {
                    legend: {
                        position: 'bottom',
                        offsetX: -10,
                        offsetY: 0
                    }
                }
            }],
            xaxis: {
                categories: ['Jan','Feb','March','Apr','May','Jun','July','Aug','Sept','Oct','Nov','Dec'],
            },
            legend: {
                position: 'top', // top, bottom
                horizontalAlign: 'right', // left, right
            },
            dataLabels: {
                enabled: false,
            },
            fill: {
                opacity: 1
            }
        };

        var chart = new ApexCharts(document.querySelector("#hiringsources"), options);
        chart.render();
    });
    
});

$(document).ready(function () {

    var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ]; 
    var dayNames= [ "Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday" ];
    
    var newDate = new Date();
    newDate.setDate(newDate.getDate());
        
    setInterval( function() {
        var hours = new Date().getHours();
        $(".hour").html(( hours < 10 ? "0" : "" ) + hours);
        var seconds = new Date().getSeconds();
        $(".second").html(( seconds < 10 ? "0" : "" ) + seconds);
        var minutes = new Date().getMinutes();
        $(".minute").html(( minutes < 10 ? "0" : "" ) + minutes);
        
        $(".month span,.month2 span").text(monthNames[newDate.getMonth()]);
        $(".date span,.date2 span").text(newDate.getDate());
        $(".day span,.day2 span").text(dayNames[newDate.getDay()]);
        $(".year span").html(newDate.getFullYear());
    }, 1000);	
    
    
    
    $(".outer").on({
        mousedown:function(){
            $(".dribbble").css("opacity","1");
        },
        mouseup:function(){
            $(".dribbble").css("opacity","0");
        }
    });
    
    
    
});

$(document).ready(function () {
    var time;
    var day;
    var month;
    var year;
    var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    var center;

// remove border if the selected date is today's date
function todayEqualActive(){
  setTimeout(function(){
    if($(".ui-datepicker-current-day").hasClass("ui-datepicker-today")){
      $(".ui-datepicker-today")
        .children(".ui-state-default")
        .css("border-bottom", "0");
    }
    else{
      $(".ui-datepicker-today")
        .children(".ui-state-default")
        .css("border-bottom", "2px solid rgba(53,60,66,0.5)");
    }
  }, 20);
}

// call the above function on document ready
todayEqualActive();

$('#calendar').datepicker({
  inline: true,
  firstDay: 1,
  showOtherMonths: true,
  onChangeMonthYear: function(){
    todayEqualActive();
  },
  onSelect: function(dateText, inst){
    var date = $(this).datepicker('getDate'),
    day  = date.getDate(),
    month = date.getMonth() + 1,
    year =  date.getFullYear();
    
    // display day and month on submit button
    var monthName = months[month - 1];
    $(".request .day").text(monthName + " " + day);
    
    todayEqualActive();    

    $(".request").removeClass("disabled");
    
    var index;
    
    setTimeout(function(){
       $(".ui-datepicker-calendar tbody tr").each(function(){
        if($(this).find(".ui-datepicker-current-day").length){
          index = $(this).index() + 1;
        }
      });
      
      // insert timepiker placeholder after selected row
      $("<tr class='timepicker-cf'></tr>")
          .insertAfter($(".ui-datepicker-calendar tr")
          .eq(index));
          
      if($(".timepicker-cf")){
        var top =$(".timepicker-cf")[0].offsetTop;
        if($(".timepicker").css('height') == '60px'){
          //console.log('in');
          $(".timepicker-cf").animate({
            'height': '0px',
            'position' :'relative'
          }, { duration: 200, queue: false });
          $(".timepicker").animate({
            'top':top
          }, 200);
          $(".timepicker-cf").animate({
            'height': '60px',
            'position' :'relative'
          }, 200);
        }
        else{
        // console.log('out');
          $(".timepicker").css('top',(top));
          $(".timepicker, .timepicker-cf").animate({
            'height': '60px'
          }, 200);
        }
      }
    }, 0);
    
    // display time on submit button
    time = $(".owl-stage .center").text();
    $(".request .time").text(time);
    
    $(".owl-item").removeClass("center-n");
    center = $(".owl-stage").find(".center");
    center.prev("div").addClass("center-n");
    center.next("div").addClass("center-n");
  }
});

// if the inputs arent empty force ":focus state"
$(".form-name input").each(function(){
  $(this).keyup(function() {
    if (this.value) {
      $(this).siblings("label").css({
        'font-size': '0.8em',
        'left': '.15rem',
        'top': '0%'
      });
    }
    // remove force if they're empty
    else{
      $(this).siblings("label").removeAttr("style");
    }
  });
});

$(".timepicker").on('click', '.owl-next', function(){
  time = $(".owl-stage .center").text();
  $(".request .time").text(time);
  
  $(".owl-item").removeClass("center-n");
  center = $(".owl-stage").find(".center");
  center.prev("div").addClass("center-n");
  center.next("div").addClass("center-n");
});

$(".timepicker").on('click', '.owl-prev', function(){
  time = $(".owl-stage .center").text();
  $(".request .time").text(time);
  
  $(".owl-item").removeClass("center-n");
  center = $(".owl-stage").find(".center");
  center.prev("div").addClass("center-n");
  center.next("div").addClass("center-n");
});

$('.owl').owlCarousel({
  center: true,
  loop: true,
  items: 3,
  dots: false,
  nav: true,
  navText: " ",
  mouseDrag: false,
  touchDrag: true,
  responsive: {
    0:{
      items:3
    },
    700:{
      items:3
    },
    1200:{
      items:3
    }
  }
});

$(document).on('click', '.ui-datepicker-next', function(e){
  $(".timepicker-cf").hide(0);
  $(".timepicker").css({
    'height': '0'
  });
  e.preventDefault();
  $(".ui-datepicker").animate({
    "-webkit-transform":"translate(100%,0)"
  }, 200);
});

$(document).on('click', '.ui-datepicker-prev', function(){
  $(".timepicker-cf").hide(0);
  $(".timepicker").css({
    'height': '0'
  });
  $(".ui-datepicker").animate({
    'transform': 'translateX(-100%)'
  }, 200);
});

$(window).on('resize', function(){
  top =$(".timepicker-cf")[0].offsetTop;
  //console.log($(".timepicker-cf")[0].offsetTop);
  $(".timepicker").css('top', ($(".timepicker-cf")[0].offsetTop));
});

});