
var conn = new WebSocket('ws://localhost:8080/socket');

function send(message) {
    conn.send(JSON.stringify(message));
}

configuration = null;
var peerConnection = new RTCPeerConnection(configuration);

var dataChannel = peerConnection.createDataChannel("dataChannel", { reliable: true });