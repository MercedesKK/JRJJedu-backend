if (typeof jQuery === "undefined") {
    throw new Error("jQuery plugins need to be before this file");
}
$(function() {

    "use strict"; 
    var option = {};
    var covidinfo = getChart("covidinfo"); 

    var category = [];
    var dottedBase = +new Date();
    var lineData = [];
    var barData = [];

    for (var i = 0; i < 20; i++) {
        var date = new Date(dottedBase += 3600 * 24 * 1000);
        category.push([
            date.getFullYear(),
            date.getMonth() + 1,
            date.getDate()
        ].join('-'));
        var b = Math.random() * 200;
        var d = Math.random() * 200;
        barData.push(b)
        lineData.push(d + b);
    }

    option = {
        backgroundColor: '#0f375f',
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            bottom:"0",
            data: ['Covid-Flow', 'Covid-Cases'],
            textStyle: {
                color: '#ccc'
            }
        },
        xAxis: {
            data: category,
            axisLine: {
                lineStyle: {
                    color: '#ccc'
                }
            }
        },
        yAxis: {
            splitLine: {show: false},
            axisLine: {
                lineStyle: {
                    color: '#ccc'
                }
            }
        },
        series: [{
            name: 'Covid-Flow',
            type: 'line',
            smooth: true,
            showAllSymbol: true,
            symbol: 'emptyCircle',
            symbolSize: 15,
            data: lineData
        }, {
            name: 'Covid-Cases',
            type: 'bar',
            barWidth: 10,
            itemStyle: {
                barBorderRadius: 5,
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#14c8d4'},
                        {offset: 1, color: '#43eec6'}
                    ]
                )
            },
            data: barData
        }, {
            name: 'Covied-NewCases',
            type: 'bar',
            barGap: '-100%',
            barWidth: 10,
            itemStyle: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: 'rgba(20,200,212,0.5)'},
                        {offset: 0.2, color: 'rgba(20,200,212,0.2)'},
                        {offset: 1, color: 'rgba(20,200,212,0)'}
                    ]
                )
            },
            z: -12,
            data: lineData
        }, {
            name: 'Covid-Reached',
            type: 'pictorialBar', 
            symbol: 'rect',
            itemStyle: {
                color: '#0f375f'
            },
            symbolRepeat: true,
            symbolSize: [12, 4],
            symbolMargin: 1,
            z: -10,
            data: lineData
        }]
    };

    if (option && typeof option === "object") {
        covidinfo.setOption(option, true);
    }  
    $(window).on('resize', function(){
        covidinfo.resize();
    });

        

});

$(function() {

    "use strict"; 

    var option = {};
    var covidcountry = getChart("covidcountry"); 

    option = {
        tooltip: {
            trigger: 'item',
        },
        legend: {
            type: 'scroll',
            bottom: 10,
            textStyle: {
                color: "rgba(95, 95, 95, 1)" 
            },
            data: (function (){
                var list = [];
                for (var i = 1; i <=28; i++) {
                    list.push(i + 2000 + '');
                }
                return list;
            })()
        },
        visualMap: {
            top: 'middle',
            right: 10,
            color: ['red', 'yellow'],
            calculable: true,
            textStyle:{
                color:'rgba(95, 95, 95, 1)',
            }
        },
        radar: {
            indicator: [
                { text: 'India', max: 400},
                { text: 'US', max: 400},
                { text: 'China', max: 400},
                { text: 'New Zealand', max: 400},
                { text: 'UAE', max: 400}
            ],
            name: {
                color: "rgba(95, 95, 95, 1)" 
            }
        },
        series: (function (){
            var series = [];
            for (var i = 1; i <= 28; i++) {
                series.push({
                    name: 'Covid-19（Country）',
                    type: 'radar',
                    symbol: 'none',
                    lineStyle: {
                        width: 1
                    },
                    emphasis: {
                        areaStyle: {
                            color: "rgba(95, 95, 95, 1)" 
                        }
                    },
                    data: [{
                        value: [
                            (40 - i) * 10,
                            (38 - i) * 4 + 60,
                            i * 5 + 10,
                            i * 9,
                            i * i /2
                        ],
                        name: i + 2000 + ''
                    }]
                });
            }
            return series;
        })()
    };

    if (option && typeof option === "object") {
        covidcountry.setOption(option, true);
    }  
    $(window).on('resize', function(){
        covidcountry.resize();
    });
});

function getChart(id){ 
    var dom = document.getElementById(id);
    return echarts.init(dom);
}

