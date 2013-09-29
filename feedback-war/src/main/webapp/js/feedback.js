$(function() {
	$("#searchForm").submit(
			function(event) {

				event.preventDefault();
				
				var $form = $(this), url = $form.attr('action');

				$('input[type="submit"]').attr('disabled','disabled');
				
				$.ajax({
					type: 'POST',
					url: url,
					data: JSON.stringify({email: $('#email').val(),comment: $('#comment').val(),subscription: $('#subscription').is(':checked')}),
					success: function(data) { 
						$('#message').html('Submission Succeeded!').removeClass().addClass('success').slideDown('slow').delay(3000).slideUp('slow');
						$('#email').val('');
						$('#comment').val('');
						$('#subscription').prop("checked", false);
				        $('input[type="submit"]').removeAttr('disabled');
					},
					error: function(data) { 
						$('#message').html('Submission Failed').removeClass().addClass('error').slideDown('slow').delay(3000).slideUp('slow');
				        $('input[type="submit"]').removeAttr('disabled');
					},
					contentType: "application/json",
				});
			});
});