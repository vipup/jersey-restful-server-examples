<%@page import="java.util.Date"
%><%@page import="java.util.jar.Attributes"
%><%@page import="java.util.jar.Manifest"%>
<!-- HTML for static distribution bundle build -->
<!DOCTYPE html>
<html lang="en">
  <head>
<% 
	Manifest mf = new Manifest();
	mf.read (  getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF") );
	Attributes atts = mf.getMainAttributes();
%>  
    <meta charset="UTF-8">
    <title> v.<%=atts.getValue("Implementation-Build")%> :: ${project.artifactId}-${project.version} REST-API</title>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/swagger-ui/3.20.5/swagger-ui.css" >
    <link rel="icon" type="image/png" href="./favicon-32x32.png" sizes="32x32" />
    <link rel="icon" type="image/png" href="./favicon-16x16.png" sizes="16x16" />
    <style>
      html
      {
        box-sizing: border-box;
        overflow: -moz-scrollbars-vertical;
        overflow-y: scroll;
      }
      *,
      *:before,
      *:after
      {
        box-sizing: inherit;
      }
      body
      {
        margin:0;
        background: #fafafa;
      }
    </style>
  </head>

  <body>
    <div id="swagger-ui"></div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/swagger-ui/3.20.5/swagger-ui-bundle.js"> </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/swagger-ui/3.20.5/swagger-ui-standalone-preset.js"> </script>
    <script>
    window.onload = function() {
      // Begin Swagger UI call region
      const ui = SwaggerUIBundle({
        url: "swagger.json",
        dom_id: '#swagger-ui',
        deepLinking: true,
        presets: [
          SwaggerUIBundle.presets.apis,
          SwaggerUIStandalonePreset
        ],
        plugins: [
          SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout"
      })
      // End Swagger UI call region
      window.ui = ui
    }
  </script>
  </body>
</html>