package org.temetnosce;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class Main extends AbstractVerticle {

	public void start() {
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router( vertx );
		
		Route route = router.route();
		route.handler( rc -> {
			HttpServerResponse hsr = rc.response();
			hsr.putHeader( "content-type", "text/plain" );
			hsr.end( "Vert.x is using WEB" );
		});
		
		server.requestHandler( router::accept ).listen( 8000 );
		
		System.out.println( "Started webserver" );
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle( m );
	}
}
