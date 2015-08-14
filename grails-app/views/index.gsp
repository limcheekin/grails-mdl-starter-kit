<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Home</title>
        <style>
        #plugins li
        {
            display: inline;
            padding-right: 20px;
        }    
        </style>        
    </head>
    <body>
        <div class="mdl-grid">
          <div class="mdl-card mdl-cell mdl-cell--12-col">
            <div class="mdl-card__supporting-text">
            <h4>Welcome to Grails</h4>
            Congratulations, you have successfully started your first Grails application! At the moment
               this is the default page, feel free to modify it to either redirect to a controller or display whatever
               content you may choose. On the left is a list of controllers that are currently deployed in this application,
               click on each to execute its default action.
             </div>  
          </div>
        </div>          
        <div class="mdl-grid">
          <div class="mdl-card mdl-cell mdl-cell--6-col">
            <div class="mdl-card__supporting-text">           
            <h5>Application Status</h5>
            <ul>
                <li>Environment: ${grails.util.Environment.current.name}</li>
                <li>App profile: ${grailsApplication.config.grails?.profile}</li>
                <li>App version: <g:meta name="info.app.version"/></li>
                <li>Grails version: <g:meta name="info.app.grailsVersion"/></li>
                <li>Groovy version: ${GroovySystem.getVersion()}</li>
                <li>JVM version: ${System.getProperty('java.version')}</li>
                <li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
            </ul>
            </div>
          </div>
          <div class="mdl-card mdl-cell mdl-cell--6-col">
            <div class="mdl-card__supporting-text">            
            <h5>Artefacts</h5>
            <ul>
                <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
                <li>Domains: ${grailsApplication.domainClasses.size()}</li>
                <li>Services: ${grailsApplication.serviceClasses.size()}</li>
                <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>                    
            </ul>
            </div>
          </div>
        </div> 
        <div class="mdl-grid">
          <div class="mdl-card mdl-cell mdl-cell--12-col">
            <div class="mdl-card__supporting-text">
            <h5>Installed Plugins</h5>
            <ul id="plugins">
                <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                    <li>${plugin.name} - ${plugin.version}</li>
                </g:each>
            </ul>
            </div>
          </div>
        </div>                
    </body>
</html>
