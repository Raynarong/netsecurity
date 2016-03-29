// JavaScript Document 

  function resizeHidth(){
	var mimg = $('.content_img > img');	
	var mmain = $('.main');
	var imgHeight = $(window).height();
	var mflashHeight =700;
     if(imgHeight < mflashHeight)
	       { 
		    imgHeight = 700+"px";
			mimg.css("height", imgHeight);
			mmain.css("height", "630px");
			}
	 if(imgHeight >= mflashHeight)
	       { 
		    imgHeight = "100%";
			mimg.css("height", imgHeight);
			mmain.css("height", "88%");
			}		
	
}


  function resizeWidth(){
	var mimg = $('.content_img > img');
	var mhead = $('.header');
	var imgWidth = $(window).width();
	var mflashWidth =980;
     if(imgWidth < mflashWidth)
	       { 
		    imgWidth = 980+"px";
			mimg.css("width", imgWidth);
			mhead.css("width", imgWidth);
			}
	 if(imgWidth >= mflashWidth)
	       { 
		    imgWidth = "100%";
			mimg.css("width", imgWidth);
			mhead.css("width", imgWidth);
			}		
	
}


window.onresize = function () {resizeWidth(); resizeHidth();}