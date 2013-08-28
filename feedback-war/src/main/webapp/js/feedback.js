$(function() {
	$("#searchForm").submit(
			function(event) {

				event.preventDefault();
				
				var $form = $(this), url = $form.attr('action');
								
				$.ajax({
					type: 'POST',
					url: url,
					data: JSON.stringify({email: $('#email').val(),comment: $('#comment').val(),subscription: $('#subscription').is(':checked')}),
					success: function(data) { 
						alert("Submission Succeeded");
						$('#email').val('');
						$('#comment').val('');
						$('#subscription').prop("checked", false);
					},
					error: function(data) { alert("Submission Failed"); },
					contentType: "application/json",
				});
			});
});