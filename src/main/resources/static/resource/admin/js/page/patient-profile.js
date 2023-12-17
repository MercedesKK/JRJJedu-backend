if (typeof jQuery === "undefined") {
    throw new Error("jQuery plugins need to be before this file");
}
$(function() {

    "use strict"; 
    // var ROOT_PATH = 'https://echarts.apache.org/examples';
    // var option = {};
    // var organdiagram = getChart("organ_diagram"); 
    
    // $.get(ROOT_PATH + '/data/asset/geo/Veins_Medical_Diagram_clip_art.svg', function (svg) {
    //     echarts.registerMap('organ_diagram', {svg: svg});
    //     option = {
    //         tooltip: {
    //         },
    //         geo: {
    //             left: 10,
    //             right: '50%',
    //             map: 'organ_diagram',
    //             selectedMode: 'multiple',
    //             emphasis: {
    //                 focus: 'self',
    //                 itemStyle: {
    //                     color: null
    //                 },
    //                 label: {
    //                     position: 'bottom',
    //                     distance: 0,
    //                     textBorderColor: '#fff',
    //                     textBorderWidth: 2
    //                 }
    //             },
    //             blur: {
    //             },
    //             select: {
    //                 itemStyle: {
    //                     color: '#b50205'
    //                 },
    //                 label: {
    //                     show: false,
    //                     textBorderColor: '#fff',
    //                     textBorderWidth: 2
    //                 }
    //             }
    //         },
    //         grid: {
    //             left: '60%',
    //             top: '20%',
    //             bottom: '20%'
    //         },
    //         xAxis: {},
    //         yAxis: {
    //             data: ['heart', 'large-intestine', 'small-intestine', 'spleen', 'kidney', 'lung', 'liver']
    //         },
    //         series: [{
    //             type: 'bar',
    //             emphasis: {
    //                 focus: 'self'
    //             },
    //             data: [121, 321, 141, 52, 198, 289, 139]
    //         }]
    //     };
    
    //     myChart.setOption(option);
    
    //     myChart.on('mouseover', { seriesIndex: 0 }, function (event) {
    //         myChart.dispatchAction({
    //             type: 'highlight',
    //             geoIndex: 0,
    //             name: event.name
    //         });
    //     });
    //     myChart.on('mouseout', { seriesIndex: 0 }, function (event) {
    //         myChart.dispatchAction({
    //             type: 'downplay',
    //             geoIndex: 0,
    //             name: event.name
    //         });
    //     });
    
    // });
    // if (option && typeof option === "object") {
    //     organdiagram.setOption(option, true);
    // }  
    // $(window).on('resize', function(){
    //     organdiagram.resize();
    // });

    $(document).ready(function(){
        $('.owl-carouselthree').owlCarousel({
            loop:true,
            margin:15,
            autoplay:true,
            autoplayTimeout:4000,
            autoplayHoverPause:true,
            nav:true,
            navText: ["<img src='../dist/assets/images/stylish-left.png'>","<img src='../dist/assets/images/stylish-right.png'>"],   
            responsive:{
                0:{
                    items:1
                },
                480:{
                    items:2
                },
                600:{
                    items:2
                },
                1000:{
                    items:3
                },
                1100:{
                    items:3
                },
                1400:{
                    items:3
                }
            }
        })
    });

    // circle gradient
     $(document).ready(function() {
        var options = {
            chart: {
                height: 250,
                type: 'radialBar',
                toolbar: {
                    show: false
                }
            },
            colors:  ['var(--chart-color5)'],
            plotOptions: {
                radialBar: {
                    startAngle: -135,
                    endAngle: 225,
                        hollow: {
                        margin: 0,
                        size: '70%',
                        background: '#fff',
                        image: undefined,
                        imageOffsetX: 0,
                        imageOffsetY: 0,
                        position: 'front',

                        dropShadow: {
                            enabled: true,
                            top: 3,
                            left: 0,
                            blur: 4,
                            opacity: 0.24
                        }
                    },
                    track: {
                        background: '#fff',
                        strokeWidth: '67%',
                        margin: 0, // margin is in pixels
                        dropShadow: {
                            enabled: true,
                            top: -3,
                            left: 0,
                            blur: 4,
                            opacity: 0.35
                        }
                    },

                    dataLabels: {
                        showOn: 'always',
                        name: {
                            offsetY: -10,
                            show: true,
                            color: '#888',
                            fontSize: '17px'
                        },
                        value: {
                            formatter: function(val) {
                                return parseInt(val);
                            },
                            color: '#111',
                            fontSize: '36px',
                            show: true,
                        }
                    }
                }
            },
            fill: {
                type: 'gradient',
                gradient: {
                    shade: 'dark',
                    type: 'horizontal',
                    shadeIntensity: 0.5,
                    gradientToColors: ['var(--chart-color4)'],
                    inverseColors: true,
                    opacityFrom: 1,
                    opacityTo: 1,
                    stops: [0, 100]
                }
            },
            series: [75],
            stroke: {
                lineCap: 'round'
            },
            labels: ['Kcal Left'],
        }

        var chart = new ApexCharts(
            document.querySelector("#apex-circle-gradient"),
            options
        );

        chart.render();    
    });
    
        
});

// function getChart(id){ 
//     var dom = document.getElementById(id);
//     return echarts.init(dom);
// }

