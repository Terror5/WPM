<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" media="screen" type="text/css">
<link href="<c:url value="/resources/js/DatePick/jsDatePick_ltr.css" />" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="<c:url value="/resources/js/tinymce/tinymce.min.js"/>"></script>
<script>
	tinymce.init({
	    selector: "textarea#description",
	    theme: "modern",
	    width: 300,
	    height: 300,
	    plugins: [
	         "advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker",
	         "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
	         "save table contextmenu directionality emoticons template paste textcolor"
	   ],
	   content_css: "content.css",
	   toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | l      ink image | print preview media fullpage | forecolor backcolor emoticons", 
	   style_formats: [
	        {title: 'Bold text', inline: 'b'},
	        {title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
	        {title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
	        {title: 'Example 1', inline: 'span', classes: 'example1'},
	        {title: 'Example 2', inline: 'span', classes: 'example2'},
	        {title: 'Table styles'},
	        {title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
	    ]
	 }); 
</script>
<script type="text/javascript" src="<c:url value="/resources/js/DatePick/jsDatePick.full.1.3.js"/>"></script>
<script>
	window.onload = function(){
		new JsDatePick({
		    useMode:2,
		    target:"penis",        
		    cellColorScheme:"ocean_blue",
		    dateFormat:"%d.%m.%Y",
		});
		
		new JsDatePick({
		    useMode:2,
		    target:"penis2",        
		    cellColorScheme:"ocean_blue",
		    dateFormat:"%d.%m.%Y",
		});
	};
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>
</head>
<body>
<div id="container">
	<header>
          <tiles:insertAttribute name="header" />
	</header>
     <nav>
          <tiles:insertAttribute name="menu" />
	</nav>
	<section id="content">
		<article>
          <tiles:insertAttribute name="body" />
        </article>
     </section>     
	 <aside>
	 	<tiles:insertAttribute name="aside" />
	 </aside>
	 <footer>
          <tiles:insertAttribute name="footer" />
	</footer>
	</div>
</body>
</html>