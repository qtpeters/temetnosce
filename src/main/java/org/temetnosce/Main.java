package org.temetnosce;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class Main extends AbstractVerticle {

	public void start() {
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router( vertx );
		
		router.route( "/webroot/*" ).handler( 
			StaticHandler.create().setCachingEnabled( false ) 
		);
		
		router.route( "/services/*" ).handler( rc -> {
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
