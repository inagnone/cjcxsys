document.oncontextmenu = function(){
		return false;
		};
		document.onkeydown = function(){
		if (event.ctrlKey && window.event.keyCode==67){
		return false;
		}
		};
		document.body.oncopy = function (){
		return false;
		};
		document.onselectstart = function(){
		//return false;
		};
		$(document).ready(function(e) {
		time = window.setInterval(function(){
			$('.og_next').click();	
		},5000);
		var linum = $('.mainlist li').length,
			licount = 4,
			total_width = linum * 220,
			width = 880;
		$('.piclist').css('width', total_width + 'px');
		$('.og_next').click(function(){
			if($('.mainlist').is(':animated')){
				$('.mainlist').stop(true,true);
			}
			if(linum > 4){
				ml = parseInt($('.mainlist').css('left'));
				if(licount < linum) {
					licount += 4;
					$('.mainlist').animate({left: ml - width + 'px'},'slow');
				}else{
					$('.mainlist').animate({left: 0 + 'px'});
					licount = 4;
				}
			}
		});
		$('.og_prev').click(function(){
			
			if($('.mainlist').is(':animated')){
				$('.mainlist').stop(true,true);
			}
			if(linum > 4){
				ml = parseInt($('.mainlist').css('left'));
				console.log(licount);
				if(licount > 4) {
					licount -= 4;
					$('.mainlist').animate({left: ml + width + 'px'},'slow');
				}
			}
		});    
	});

$(document).ready(function(){
	$('.og_prev,.og_next').hover(function(){
			$(this).fadeTo('fast',1);
		},function(){
			$(this).fadeTo('fast',0.7);
	}); 
});
$(".content-body").oncontextmenu=function(){return false;};
$(".content-body").ondragstart=function(){return false;};
$(".content-body").onselectstart=function(){return false;};
$(".content-body").onselect=function(){document.selection.empty();};
$(".content-body").oncopy=function(){document.selection.empty();};
$(".content-body").onbeforecopy=function(){return false;};
$(".content-body").onmouseup=function(){document.selection.empty();};