# Jrails

In this project I implement a web server that uses a model-view-controller architecture. It uses ideas from Ruby on RailsLinks to an external site.. However, because Java is, well, Java, the framework will be noticeably cruftier than Rails. On the other hand, we'll only implement a tiny fraction of what goes into a real web server framework.

Like most MVC web app frameworks, jrails includes models that represent the database. More specifically, a model is any class that subclasses jrails.Model, which uses reflection to provide primitive database functionality. For example, here is the model from the book app

The views for jrails are HTMLLinks to an external site. pages that are sent back to the client web browser. Ruby on Rails uses HTML with embedded Ruby code, but developing such a system is a bit too complex for this project. Instead, we will create HTML by invoking methods in Java. More specifically, here is an example view from the book project:

When a web request comes in, it's eventually passed to a controller, which handles the request and returns an HTML page in response. For example, here is a controller from our example app:

As we just saw, different HTTP requests are handled by different controllers. Among other things, each HTTP request has a verbLinks to an external site. (e.g., GET, POST) and a path (e.g., /show from the URL localhost:8000/show?id=42). The job of the router is to map such requests to controller methods.

Finally, jrails include a class JServer with a method void start(JRouter r) that starts up an HTTP server on port 8000, listens for requests, and routes any requests received through r, sending the result back to the web browser. We've written this class for you, and you shouldn't need to modify it.
