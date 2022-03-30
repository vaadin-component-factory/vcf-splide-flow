import Splide from '@splidejs/splide';
import { Video } from '@splidejs/splide-extension-video';

window.vcfsplide = {

	create: function (container) {
		// define main slider
		var main = new Splide("#main-slider-" + container.id, {
			type: 'loop',
			width: '100%',
			pagination: false,
			arrows: false,
			cover: true,			
		});

		// define thumbnails slider
		var thumbnails = new Splide("#thumbnails-slider-" + container.id, {
			fixedWidth: 100,
			fixedHeight: 60,
			gap: 10,
			pagination: false,
			rewind: true,
			cover: true,
			isNavigation: true,
			breakpoints: {
				600: {
					fixedWidth: 60,
					fixedHeight: 44,
				},
			},			
		});

		// set main slider 
		main.options = {
			height: container.clientHeight - thumbnails.options.fixedHeight - 30,
		};

		// sync the main slider to thumbnails
		main.sync(thumbnails);
		main.mount({ Video });
		thumbnails.mount({ Video });

		// disable video on thumbnail
		thumbnails.Components.Video.disable(true);

		// create bar for close button on full screen
		const bar = document.createElement('div');
		bar.id = "lightbox-bar-" + container.id;
		bar.style.height = '10px';
		bar.style.display = 'none';
		bar.style.paddingRight = '0.5em';
		bar.style.paddingTop = '1em';
		bar.innerHTML = "<span class='lightbox_close'>&times;</span>";
		container.prepend(bar);

		// save main slider & thumbnails slider values on container
		container.main = main;
		container.thumbnails = thumbnails;			
		container.bar = bar;
	},

	showLightbox: function (container) {
		// save original height and width values of the component
		const containerOriginalHeight = container.style.height;
		const containerOriginalWidth = container.style.width;		

		// set new class and height and width values for full screen
		container.classList.add('lightbox');
		container.style.height = '100%';
		container.style.width = '100%';
		
		// save original main slider height
		const mainOriginalHeight = container.main.options.height;
		
		// show close button bar div
		const bar = document.getElementById(container.bar.id);
		bar.style.display = 'block';			

		// set full screen height for the main slider
		container.main.options = {
			height: '80vh',
		};
		
		// define behavior on full screen close button click
		const closeLightbox = () => {
            bar.style.display = "none";
			container.style.height = containerOriginalHeight;
			container.style.width = containerOriginalWidth;
            container.classList.remove('lightbox');
			container.main.options = {
				height: mainOriginalHeight,
			};
        };

		// add listener to close button	
		const btnClose = bar.querySelector('.lightbox_close');	
		btnClose.addEventListener("click", () => {
            closeLightbox();
        });	
	},
}