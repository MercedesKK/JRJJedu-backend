if (typeof jQuery === "undefined") {
    throw new Error("jQuery plugins need to be before this file");
}
$(function() {
    "use strict"; 
    
    $(document).ready(function() {
        $('#myProjectTable')
        .addClass( 'nowrap' )
        .dataTable( {
            responsive: true,
            columnDefs: [
                { targets: [-1, -3], className: 'dt-body-right' }
            ]
        });
    });

     // Income Analytics
    $(document).ready(function() {
        var options = {
            align: 'center',
            series: [1242, 1047, 1352, 1458, 1465, 1889],
            responsive: [{
                breakpoint: 420,
                options: {
                    chart: {
                        width: 200,
                        align: 'center',
                    },
                    legend: {
                        position: 'bottom',
                        markers: {
                            fillColors:'var(--chart-color1)'
                        },
                        labels: {
                            colors: 'var(--chart-color1)'
                        },
                    }
                }
            }],
            chart: {
                height: 336,
                type: 'polarArea',
                toolbar: {
                    show: false,
                },
            },
            labels: ['surgery', 'psychiatry', 'paediatrics', 'pathology', 'medicine' , 'anaesthetics'],
            fill: {
                opacity: 1,
                colors: ['var(--chart-color1)', 'var(--chart-color2)', 'var(--chart-color3)', 'var(--chart-color4)', 'var(--chart-color5)', 'var(--chart-color6)'],
            },
            stroke: {
                width: 1,
                colors: undefined
            },
            yaxis: {
                show: false
            },
            legend: {
                position: 'bottom', // left, right, top, bottom
                horizontalAlign: 'center',  // left, right, center
            },
            plotOptions: {
                polarArea: {
                    rings: {
                        strokeWidth: 0
                    }
                }
            },
            theme: {
                monochrome: {
                enabled: true,
                shadeTo: 'light',
                shadeIntensity: 0.6
                }
            }
        };

        var chart = new ApexCharts(document.querySelector("#incomeanalytics"), options);
        chart.render();
    }); 

    // Health Survey
    $(document).ready(function() { 
        var options = {
            series: [{
                name: 'Available',
                data: [4, 19, 7, 35, 14, 27, 9, 12],
            }],
                chart: {
                height: 140,
                type: 'line',
                toolbar: {
                    show: false,
                }
            },
            grid: {
                show: false,
                xaxis: {
                    lines: {
                        show: false
                    }
                },   
                yaxis: { 
                    lines: {
                        show: false
                    }
                }, 
            },
            stroke: {
                width: 4,
                curve: 'smooth',
                colors: ['var(--chart-color1)'],
            },
            xaxis: {
                type: 'datetime',
                categories: ['1/11/2021', '2/11/2021', '3/11/2021', '4/11/2021', '5/11/2021', '6/11/2021', '7/11/2021', '8/11/2021'],
                tickAmount: 10,
                labels: {
                    formatter: function(value, timestamp, opts) {
                        return opts.dateFormatter(new Date(timestamp), 'dd MMM')
                    }
                }
            },
            fill: {
                type: 'gradient',
                gradient: {
                    shade: 'dark',
                    gradientToColors: [ "var(--chart-color2)" ],
                    shadeIntensity: 1,
                    type: 'horizontal',
                    opacityFrom: 1,
                    opacityTo: 1,
                    stops: [0, 100, 100, 100],
                },
            },
            markers: {
                size: 3,
                colors: ["#FFA41B"],
                strokeColors: "#ffffff",
                strokeWidth: 2,
                hover: {
                    size: 7,
                }
            },
            yaxis: {
                show: false,
                min: -10,
                max: 50,
            }
        };

        var chart = new ApexCharts(document.querySelector("#apex-emplyoeeAnalytics"), options);
        chart.render();
    }); 

     // patient Survey
     $(document).ready(function() { 
        var options = {
            series: [{
                name: 'Available',
                data: [4, 19, 7, 35, 14, 27, 9, 12],
            }],
                chart: {
                height: 140,
                type: 'line',
                toolbar: {
                    show: false,
                }
            },
            grid: {
                show: false,
                xaxis: {
                    lines: {
                        show: false
                    }
                },   
                yaxis: { 
                    lines: {
                        show: false
                    }
                }, 
            },
            stroke: {
                width: 4,
                curve: 'smooth',
                colors: ['var(--chart-color4)'],
            },
            xaxis: {
                type: 'datetime',
                categories: ['1/11/2021', '2/11/2021', '3/11/2021', '4/11/2021', '5/11/2021', '6/11/2021', '7/11/2021', '8/11/2021'],
                tickAmount: 10,
                labels: {
                    formatter: function(value, timestamp, opts) {
                        return opts.dateFormatter(new Date(timestamp), 'dd MMM')
                    }
                }
            },
            fill: {
                type: 'gradient',
                gradient: {
                    shade: 'dark',
                    gradientToColors: [ "var(--chart-color5)" ],
                    shadeIntensity: 1,
                    type: 'horizontal',
                    opacityFrom: 1,
                    opacityTo: 1,
                    stops: [0, 100, 100, 100],
                },
            },
            markers: {
                size: 3,
                colors: ["#FFA41B"],
                strokeColors: "#ffffff",
                strokeWidth: 2,
                hover: {
                    size: 7,
                }
            },
            yaxis: {
                show: false,
                min: -10,
                max: 50,
            }
        };

        var chart = new ApexCharts(document.querySelector("#apex-patient"), options);
        chart.render();
    });

});


