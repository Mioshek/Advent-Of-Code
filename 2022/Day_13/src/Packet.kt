class Packet {
    var packet: String
    var openedBrackets = 0

    constructor(packet: String, openedBrackets: Int){
        this.packet = packet
        this.openedBrackets = openedBrackets
    }
}