(function ($) {
"use strict";

        /*===========================
        Meanmenu
        ===========================*/

		$('#mobile-menu').meanmenu({
			meanMenuContainer: '.mobile-menu',
			meanScreenWidth: "991"
		});

        /*===========================
        Sticky
        ===========================*/

        var windload = $(window);
        var stickyVisible = $('.header-sticky');
        windload.on('scroll', function () {
            var scroll = windload.scrollTop();
            if (scroll < 100) {
                stickyVisible.removeClass('sticky-menu');
            } else {
                stickyVisible.addClass('sticky-menu');
            }
        });


        /*===========================
        fixed header
        ===========================*/

        windload.on('scroll', function () {
            var scroll = $(window).scrollTop();
            if (scroll < 100) {
                $(".header-sticky").removeClass("sticky-menu animated fadeInDown");
            } else {
                $(".header-sticky").addClass("sticky-menu animated fadeInDown");
            }
		});

		
        /*===========================
        Preloader
        ===========================*/
		
		$(document).ready(function() {
  
			setTimeout(function() {
			  $('#ctn-preloader').addClass('loaded');
			  // Once the preloader has finished, the scroll appears
			  $('#preloader-body').removeClass('no-scroll-y');
		  
			  if ($('#ctn-preloader').hasClass('loaded')) {
				// It is so that once the preloader is gone, the entire preloader section will removed
				$('#preloader').delay(0).queue(function() {
				  $(this).remove();
				});
			  }
			}, 0);
			
		  });



        /*===========================
        Magnific Popup
		===========================*/

		/* MagnificPopup Image View */
		$('.popup-image').magnificPopup({
			type: 'image',
			gallery: {
			enabled: true
			}
		});

		/* MagnificPopup Video View */
		$('.popup-video').magnificPopup({
			type: 'iframe'
		});


		/*===========================
		Single full blog wrap slide Carousel
		===========================*/

		$('.single-full-blog-wrap-slide').owlCarousel({
			loop:true,
			items:1,
			nav:true,
			smartSpeed: 600,
			autoplay:true,
			autoplaySpeed:1000,
			navText:['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],
			dots:true,
			responsive:{
				0:{
					items:1,
					nav:false,
				},
				767:{
					items:1,
					nav:false,
				},
				992:{
					items:1
				}
			}
		});

		/*===========================
		Single full blog wrap slide Carousel
		===========================*/

		$('.blog-carousel').owlCarousel({
			loop:true,
			margin: 30,
			items:3,
			nav:true,
			smartSpeed: 600,
			autoplay:true,
			autoplaySpeed:1000,
			navText:['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],
			dots:true,
			responsive:{
				0:{
					items:1,
					nav:false,
				},
				767:{
					items:2,
					nav:false,
				},
				992:{
					items:3
				}
			}
		});


		/*===========================
		ScrollUp
		===========================*/
		$.scrollUp({
			scrollName: 'scrollUp', // Element ID
			topDistance: '300', // Distance from top before showing element (px)
			topSpeed: 300, // Speed back to top (ms)
			animation: 'fade', // Fade, slide, none
			animationInSpeed: 200, // Animation in speed (ms)
			animationOutSpeed: 200, // Animation out speed (ms)
			scrollText: '<i class="icofont-rounded-up"></i>', // Text for element
			activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
		});

		/*===========================
		Wow
		===========================*/
		new WOW().init();




})(jQuery);