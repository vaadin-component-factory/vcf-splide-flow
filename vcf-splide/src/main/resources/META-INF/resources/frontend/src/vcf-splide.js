import Splide from '@splidejs/splide';
import { Video } from '@splidejs/splide-extension-video';

window.vcfsplide = {

	create: function (container) {

		var main = new Splide("#main-slider", {
			type: 'loop',
			width: '100%',
			pagination: false,
			arrows: false,
			cover: true,			
		});

		var thumbnails = new Splide("#thumbnails-slider", {
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

		main.options.height = container.clientHeight - thumbnails.options.fixedHeight - 30;

		main.sync(thumbnails);
		main.mount({ Video });
		thumbnails.mount({ Video });

		const bar = document.createElement('div');
		bar.id = "lightbox-bar";
		bar.style.width = '95%';
		bar.style.height = '20px';
		bar.style.display = 'none';
		bar.innerHTML = "<span class='lightbox_close'>&times;</span>";
		container.insertBefore(bar, document.querySelector('#main-slider'));

		container.main = main;
		container.thumbnails = thumbnails;			
	},

	showLightbox: function (container) {

		const containerOriginalHeight = container.style.height;
		const containerOriginalWidth = container.style.width;

		container.classList.add('lightbox');
		container.style.height = '100%';
		container.style.width = '100%';
		
		const bar = document.querySelector('#lightbox-bar');
		bar.style.display = 'block';	
		
		var mainOriginalHeight = container.main.options.height;
		var mainOriginalWidth = container.main.options.width;

		var thumbnailsOriginalWidth = container.thumbnails.options.width;

		container.main.options = {
			height: '80vh',
			width: '95vw',
		};

		container.thumbnails.options = {
			width: '95vw',
		}
		const btnClose = document.querySelector('.lightbox_close');

		const closeLightbox = () => {
            bar.style.display = "none";
			container.style.height = containerOriginalHeight;
			container.style.width = containerOriginalWidth;
            container.classList.remove('lightbox');
			container.main.options = {
				height: mainOriginalHeight,
				width: mainOriginalWidth,
			};
			container.thumbnails.options = {
				width: thumbnailsOriginalWidth,
			}
        };
		btnClose.addEventListener("click", () => {
            closeLightbox();
        });		
	},
}