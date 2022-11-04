<!doctype html>
 <html>
	<head>
		<meta http-equiv="Content-type" content="text/html;charset=utf-8"/>     
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Newsletter details</title>
	   	<style type="text/css">	  
			body {	    	
	         	background-color: #EBEDEF;           
	         	font-family: Arial, sans-serif, Verdana;
	         	padding: 1em;         
	    	}
	    	.container {        	        
	        	background-color: #FDFEFE ;
	        	border-radius: 0.5em;       
				margin: 2em auto; 
			 	padding: 2em; 
			 	width: 600px;        
	    	}
			 
	   		@media screen and (max-width: 700px) {
	        	.col-sml {
	            	max-width: 50% !important;
	        	}       
	     	}    
    	</style>        	
	</head>
   	<body>
   		<div class="container">
			<div style="text-align:center;">				
        		<a href="https://www.storicard.com/" style="text-decoration:none;">        			
        			<img src="https://blog.storicard.com/wp-content/uploads/2019/07/Stori-horizontal-11.jpg" alt="Logo" title="Logo" width="200" height="80" >
        		</a>    	
    		</div>   
   	   		<div>   	  	
				<p> <b>Hello ${userName}</b> </p>
				<p>We send the <b>Newsletter ${newsletterTypeName}</b> for today.
				<p>You can find more details in the attachment.</p><br>
				<div style="text-align:center;">
					<p>Newsletter summary:</p>
					<p><b> ${newsletterName}:</b> ${newsletterDesc}.</p>	
				</div>			
   	   		</div>  
   			<div> 
   				<br>
   				<p> Thank you for joining our newsletters, regards.</p> 
   				<br><hr>
   				<p>Do you want to stop receiving these emails?
   				<a href="http://localhost:3000/user/unsuscribed?email=${userEmail}"  style="text-decoration:none;">  
   					Unsuscribed
        		</a>    
   				</p>
   			</div>
		</div>
	</body>
 </html>