package io.socket;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

class WebsocketTransport extends WebSocketClient implements IOTransport {
	private final static Pattern PATTERN_HTTP = Pattern.compile("^http");
	public static final String TRANSPORT_NAME = "websocket";
	private IOConnection connection;

	public static IOTransport create(URL url, IOConnection connection) {
		URI uri = URI.create(PATTERN_HTTP.matcher(url.toString()).replaceFirst("ws") + IOConnection.SOCKET_IO_1 + TRANSPORT_NAME + "/" + connection.getSessionId());
		return new WebsocketTransport(uri, connection);
	}

	public WebsocketTransport(URI uri, IOConnection connection) {
		super(uri);
		this.connection = connection;
		SSLContext context = IOConnection.getSslContext();
		if ("wss".equals(uri.getScheme()) && context != null) {
			try {
				this.setSocket(context.getSocketFactory().createSocket());
			} catch (IOException e) {
				throw new RuntimeException("can not create ssl.", e);
			}
		}
	}

	@Override
	public void disconnect() {
		try {
			this.close();
		} catch (Exception e) {
			connection.transportError(e);
		}
	}

	@Override
	public boolean canSendBulk() {
		return false;
	}

	@Override
	public void sendBulk(String[] texts) throws IOException {
		throw new RuntimeException("Cannot send Bulk!");
	}

	@Override
	public void invalidate() {
		connection = null;
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		if (connection != null)
			connection.transportDisconnected();
	}

	@Override
	public void onMessage(String text) {
		if (connection != null)
			connection.transportMessage(text);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		if (connection != null)
			connection.transportConnected();
	}

	@Override
	public String getName() {
		return TRANSPORT_NAME;
	}

	@Override
	public void onError(Exception ex) {
		connection.transportError(ex);
	}

}