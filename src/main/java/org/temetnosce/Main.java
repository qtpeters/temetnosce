package org.temetnosce;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class Main extends AbstractVerticle {

	public void start() {
		vertx.createHttpServer().requestHandler( req -> {
			req.response()
			.putHeader( "content-type", "text/plain" )
			.end( "Hello, I'm vertexing!" );
		}).listen( 8000 );
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle( m );
	}
}
