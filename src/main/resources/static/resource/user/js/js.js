// JavaScript Document
//判断文本框
function OnEnter( field ) { if( field.value == field.defaultValue ) { field.value = ""; } }
function OnExit( field ) { if( field.value == "" ) { field.value = field.defaultValue; } }


// 焦点图
(function(d,D,v){d.fn.responsiveSlides=function(h){var b=d.extend({auto:!0,speed:1E3,timeout:7E3,pager:!1,nav:!1,random:!1,pause:!1,pauseControls:!1,prevText:"Previous",nextText:"Next",maxwidth:"",controls:"",namespace:"rslides",before:function(){},after:function(){}},h);return this.each(function(){v++;var e=d(this),n,p,i,k,l,m=0,f=e.children(),w=f.size(),q=parseFloat(b.speed),x=parseFloat(b.timeout),r=parseFloat(b.maxwidth),c=b.namespace,g=c+v,y=c+"_nav "+g+"_nav",s=c+"_here",j=g+"_on",z=g+"_s",
o=d("<ul class='"+c+"_tabs "+g+"_tabs' />"),A={"float":"left",position:"relative"},E={"float":"none",position:"absolute"},t=function(a){b.before();f.stop().fadeOut(q,function(){d(this).removeClass(j).css(E)}).eq(a).fadeIn(q,function(){d(this).addClass(j).css(A);b.after();m=a})};b.random&&(f.sort(function(){return Math.round(Math.random())-0.5}),e.empty().append(f));f.each(function(a){this.id=z+a});e.addClass(c+" "+g);h&&h.maxwidth&&e.css("max-width",r);f.hide().eq(0).addClass(j).css(A).show();if(1<
f.size()){if(x<q+100)return;if(b.pager){var u=[];f.each(function(a){a=a+1;u=u+("<li><a href='#' class='"+z+a+"'>"+a+"</a></li>")});o.append(u);l=o.find("a");h.controls?d(b.controls).append(o):e.after(o);n=function(a){l.closest("li").removeClass(s).eq(a).addClass(s)}}b.auto&&(p=function(){k=setInterval(function(){var a=m+1<w?m+1:0;b.pager&&n(a);t(a)},x)},p());i=function(){if(b.auto){clearInterval(k);p()}};b.pause&&e.hover(function(){clearInterval(k)},function(){i()});b.pager&&(l.bind("click",function(a){a.preventDefault();
b.pauseControls||i();a=l.index(this);if(!(m===a||d("."+j+":animated").length)){n(a);t(a)}}).eq(0).closest("li").addClass(s),b.pauseControls&&l.hover(function(){clearInterval(k)},function(){i()}));if(b.nav){c="<a href='#' class='"+y+" prev'>"+b.prevText+"</a><a href='#' class='"+y+" next'>"+b.nextText+"</a>";h.controls?d(b.controls).append(c):e.after(c);var c=d("."+g+"_nav"),B=d("."+g+"_nav.prev");c.bind("click",function(a){a.preventDefault();if(!d("."+j+":animated").length){var c=f.index(d("."+j)),
a=c-1,c=c+1<w?m+1:0;t(d(this)[0]===B[0]?a:c);b.pager&&n(d(this)[0]===B[0]?a:c);b.pauseControls||i()}});b.pauseControls&&c.hover(function(){clearInterval(k)},function(){i()})}}if("undefined"===typeof document.body.style.maxWidth&&h.maxwidth){var C=function(){e.css("width","100%");e.width()>r&&e.css("width",r)};C();d(D).bind("resize",function(){C()})}})}})(jQuery,this,0);
// $(function() {
//     $(".f426x240").responsiveSlides({
//         auto: true,
//         pager: true,
//         nav: true,
//         speed: 700,
//         maxwidth: 750
//     });
//     $(".f160x160").responsiveSlides({
//         auto: true,
//         pager: true,
//         speed: 700,
//         maxwidth: 160
//     });
// });


$(function(){
	//.regEq li
	$(".regEq li:first").addClass("regEqSy");
	$(".regForm:first").show();
	$(".regDl:first").show();
	$(".regEq li").click(function(){
		$(this).addClass("regEqSy").siblings("li").removeClass("regEqSy");
		var regEq=$(this).index();
		$(".regForm").eq(regEq).fadeIn().siblings(".regForm").hide();
		$(".regDl").eq(regEq).fadeIn().siblings(".regDl").hide();
		})
	//.proSelect li
	$(".proSelect li:first").addClass("selStyle");
	$(".proSelect li").click(function(){
		$(this).addClass("selStyle").siblings("li").removeClass("selStyle");
		})	
	//选择支付方式 
	$(".zhiList li:first").addClass("zhistyle");
	$(".zhifufangshi:first").show();
	$(".zhiList li").click(function(){
		$(this).addClass("zhistyle").siblings("li").removeClass("zhistyle");
		var zhi=$(this).index();
		$(".zhifufangshi").eq(zhi).show().siblings(".zhifufangshi").hide();
		})
	//.upd,.add
	$(".upd,.add,.vipUp").click(function(){
		$(".address").stop(true,true).slideDown();
		})
	//luntan
	$(".luntan").click(function(){
		$(this).attr("href","#");
		alert("功能暂未开放，敬请期待！！！！！！！")
		})
	//手机多样选择
	//点击多选时
	$(".duoxuan").click(function(){
		$(this).prev("ul").find("input").show();
		$(this).addClass("danxuan");
		$(this).next(".queen2").fadeIn();
		})
	//点击more
	$(".more").click(function(){
		$(this).hide();
		$(this).next(".shou").show();
		$(this).parents(".proPosition").find("ul").removeClass("moreHeight");
		})		
    $(".shou").click(function(){
		$(this).hide();
		$(this).prev(".more").show();
		$(this).parents(".proPosition").find("ul").addClass("moreHeight");
		})	
	//.xuan2 li
	$(".xuan2 li").hover(function(){
		$(this).find(".chilXuan").show();
		},function(){
			$(this).find(".chilXuan").hide();
			})
	$(".duoxuan").click(function(){
		$(this).parents(".chilXBox").find("input").show();
		$(this).addClass("danxuan");
		$(this).next(".queen2").fadeIn();
		})	
	//.phoneBox d
	$(".phoneBox dl").hover(function(){
		$(this).addClass("dlbor");
		},function(){
			$(this).removeClass("dlbor");
			})
	//.dingLeft dl
	$(".dingLeft dl:first").addClass("dsy")
	$(".dingLeft dl").click(function(){
		$(this).addClass("dsy").siblings("dl").removeClass("dsy");
		})
	//.xingneng li
	$(".xingneng li:first").addClass("xsy");
	$(".xingneng li").click(function(){
		$(this).addClass("xsy").siblings("li").removeClass("xsy");
		})
	//dingRight
	$(".jisy li").click(function(){
		$(this).addClass("lisy").siblings("li").removeClass("lisy");
		})
	//手机正面
	$(".drZheng li").click(function(){
		var drzheng=$(this).index();
		$(".zheng li").eq(drzheng).fadeIn().siblings("li").hide();
		})	
	//手机反面
	$(".drZhong li").click(function(){
		var zhongs=$(this).index();
		$(".zhengzheng li").eq(zhongs).fadeIn().siblings("li").hide();
		})		
	//手机中段
	$(".drZhong li").click(function(){
		var drzheng=$(this).index();
		$(".beiMid li").eq(drzheng).fadeIn().siblings("li").hide();
		})	
	//手机上下
	$(".drSx li").click(function(){
		var drzheng=$(this).index();
		$(".beiTop li").eq(drzheng).fadeIn().siblings("li").hide();
		$(".beiDown li").eq(drzheng).fadeIn().siblings("li").hide();
		})	
	//shangchuan
	$(".shangchuan").click(function(){
		$(".shangchuanBox,.bg100").fadeIn();
		})	
	$(".close,.bg100").click(function(){
		$(".shangchuanBox,.bg100").fadeOut()
		})
	//.buyimgBig img
	$(".buyimgBig img:first").fadeIn();
	$(".buyimgsmall li:first").addClass("bsi");
	$(".buyimgsmall li").click(function(){
		$(this).addClass("bsi").siblings("li").removeClass("bsi");
		var bsiindex=$(this).index();
		$(".buyimgBig img").eq(bsiindex).fadeIn().siblings(".buyimgBig img").hide();
		})	
	$(".buyji li").click(function(){
		$(this).addClass("byj").siblings("li").removeClass("byj");
		})
	$(".buydel2Eq li:first").addClass("bdy2");
	$(".buydlList:first").fadeIn();
	$(".buydel2Eq li").click(function(){
		$(this).addClass("bdy2").siblings("li").removeClass("bdy2");
		var bdy2Index=$(this).index();
		$(".buydlList").eq(bdy2Index).fadeIn().siblings(".buydlList").hide();
		})
	})
	
	
	
	