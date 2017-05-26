[![Build Status](https://travis-ci.org/mitkey/socket.io-realize-xhr-and-websocket.svg?branch=master)](https://travis-ci.org/mitkey/socket.io-realize-xhr-and-websocket)
[![](https://jitpack.io/v/mitkey/socket.io-realize-xhr-and-websocket.svg)](https://jitpack.io/#mitkey/socket.io-realize-xhr-and-websocket)

# socket.io-realize-xhr-and-websocket
Socket.IO Client Implementation in Java. Realization XhrTransport and WebsocketTransport.

This is based on [Gottox  socket.io-java-client](https://github.com/Gottox/socket.io-java-client)

I'm just using the gradle build again, and Use fastjson instead of org.json




# be dependent on

[https://github.com/TooTallNate/Java-WebSocket](https://github.com/TooTallNate/Java-WebSocket)
	
	use jitpack
		compile 'com.github.TooTallNate:Java-WebSocket:Java-WebSocket-1.3.3'		----> maven { url 'https://jitpack.io' }
	replace
		compile "org.java-websocket:java-websocket:1.3.3"							----> maven { url "http://clojars.org/repo" }
