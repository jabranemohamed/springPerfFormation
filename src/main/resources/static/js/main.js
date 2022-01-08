function displayMovieDetailsResult(result) {
		//stats calculation
		var totalSum=0;
		var totalReviews = result.movie.reviews.length;
		$.each(result.movie.reviews, function(key,value) {
		  totalSum += value.rating;
		}); 
		var rating = totalSum / totalReviews;
		var stars = (totalSum / totalReviews)/2;
		
		//add stats to the json result
		result.stats =  { "rating":rating.toFixed(1), "reviews":totalReviews, stars:stars.toFixed(1) };

		//create modal div based on dedicated template + json result		
		var template = $('#movieTemplate').html();
		Mustache.parse(template);
		var $modal = Mustache.render(template, result);
		$($modal).appendTo('body');
		$("#movie-details").css("display", "block");
		$(".close").click(function(e) {
			$("#movie-details").css("display", "none");
			$("#movie-details").remove();
		});
}

function displayMoviesResult(result) {
	var $grid = $('.grid');
	$('.grid-item').hide();
	$grid.masonry('remove', $('.grid').find('.grid-item')).masonry('layout');
	$grid.masonry('once', 'removeComplete', function() {
		$grid.masonry('reloadItems');
		$grid.masonry('layout');
	});

	$grid.masonry('once', 'layoutComplete', function() {
		$('.grid-item').show();
	});

	var template = $('#movieThumbTemplate').html();
	var $items = Mustache.render(template, result);
	$('.grid').append($items);

	$(".info").click(function() {
		var movieId = $(this).attr('movie-id');
		$.ajax({
			url: "movie/"+movieId,
			type: "GET",
			success: function(result) {
				displayMovieDetailsResult(result);
			}
		})
	});

	$grid
		.imagesLoaded()
		.always(function() {
			$grid.find('.grid-item').show();
			$grid.masonry('appended', $('.grid-item'));
			$grid.masonry('layout');
		});
}