$(function() {
    "use strict";
    var itemsSchedule = [
        {
          keyfrom: "sf",
          keyto: "sff",
          text: "Breckfast",
          bg: "red",
          days:['sunday']
        }
        ,{
          keyfrom: "st",
          keyto: "eff",
          text: "Family Time ",
          bg: "blue",
          days:['sunday']
        }
        ,{
          keyfrom: "nt",
          keyto: "nff",
          text: "Watching Movie",
          bg: "green",
          days:['sunday']
        }
        ,{
          keyfrom: "s",
          keyto: "sf",
          text: "Waking",
          bg: "green",
          days:['monday', 'tuesday', 'wednesday', 'thursday','friday']
        }  
        ,{
          keyfrom: "sf",
          keyto: "sff",
          text: "Patient CheckUp",
          bg: "yellow",
          days:['monday']
        }
        ,{
          keyfrom: "eff",
          keyto: "nf",
          text: "Operation Surgery",
          bg: "purple",
          days:['monday']
        }
        ,{
          keyfrom: "n",
          keyto: "nff",
          text: "Patient CheckUp",
          bg: "blue",
          days:['wednesday']
        }
        ,{
          keyfrom: "sf",
          keyto: "st",
          text: "Operation Surgery",
          bg: "red",
          days:['wednesday']
        }
        ,{
          keyfrom: "st",
          keyto: "e",
          text: "Patient CheckUp",
          bg: "yellow",
          days:['thursday']
        }
        ,{
          keyfrom: "eff",
          keyto: "n",
          text: "Patient CheckUp",
          bg: "yellow",
          days:['thursday']
        }
        ,{
          keyfrom: "e",
          keyto: "eff",
          text: "Patient CheckUp",
          bg: "purple",
          days:['friday']
        }
        ,{
          keyfrom: "n",
          keyto: "nff",
          text: "Patient CheckUp",
          bg: "red",
          days:['saturday']
        }
    ]
      
    itemsSchedule.forEach(function(sitem,index){
    // console.log(index,sitem)
        sitem.days.forEach(function(sday, dindex){
            // console.log(sday, dindex)
            $(".schedule").append(
            '<div class="schedule-item'
            +' schedule-'+ sday
            +' time-from-'+ sitem.keyfrom
            +' time-to-'+sitem.keyto
            +' nt bg-'+sitem.bg+'">'
                +sitem.text
            +'</div>'
            )
    })
    })
    
});