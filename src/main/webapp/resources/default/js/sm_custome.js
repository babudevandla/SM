
$(document).ready(function() {

	
	$('.submitFolder').click(function(event){
		event.preventDefault();
		var	href=$(this).attr('data-href');
		var userid = $(this).attr('data-userid');
		var parentid = $(this).attr('data-currentfolderpath');
		var folderName = $("#folderId").val();
		$.ajax({
			url: href+"?userid="+userid+"&foldername="+folderName+"&currentFolderPath="+parentid,
			type:'GET',
			cache: false,
			success: function(data) {
				$('#myModal').modal('hide');
				location.reload();
			}
		});

	});
	
	$('#openfolder').click(function(event){
		event.preventDefault();
		var	href=$(this).attr('data-href');
		var userid = $(this).attr('data-userid');
		var folderName = $(this).attr("data-folder");
		$.ajax({
			url: href+"?userid="+userid+"&foldername="+folderName,
			type:'GET',
			cache: false,
			success: function(data) {
				
				$("#navigatePath").html(data.foldersFiles);
			}
		});

	});
	
	  
	  $('#viewFileContent').on('click',function(){
		    var filename=$("#viewFileContent").attr("data-file");
		    	$(".filename").html(filename);
		        $('#viewFile').modal({
		        	backdrop: 'static', 
		        	keyboard: false,
		        	show:true,
		        	height:'100%',
		        	width:'100%'
		        });
		    
		});
	  
	
});


