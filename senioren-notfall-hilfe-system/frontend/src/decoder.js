function Decoder(bytes, port) {
    // Decode an uplink message from a buffer
    // (array) of bytes to an object of fields.
    var decoded = {};
    var i = 0;
    var latitude = 0;
    var longitude = 0;
    var voltage = 0;
    var notfall;
    var n = 0;  // Um richtig shiften zu können!
    var sizelong = 4; // Um richtig shiften zu können! Ansonsten würde falscher Wert entstehen!

    if(bytes[0] == '0x00') {
        decoded.Grund = 0;  // Normaler Grund (Einfach die Zeit abgekaufen)
    }
    else if(bytes[0] == '0xAA') {
        decoded.Grund = 1;  // Akku wird schwach (5% oder weniger!)
    }
    else if(bytes[0] == '0xFF') {
        decoded.Grund = 2;  // Notfall!!!
    }

    for (i = 1; i <= 1 + sizelong-1; i++) {
        latitude = latitude | (bytes[i] << (sizelong-1-n)*8);  // Richtiges Byte an richtige Stelle schieben!
        n++;
    }

    decoded.latitude = latitude / 1000000;

    n = 0;

    for (i; i <= 1 + 2*sizelong-1; i++) {
        longitude = longitude | (bytes[i] << (sizelong-1-n)*8);  // Richtiges Byte an richtige Stelle schieben!
        n++;
    }

    decoded.longitude = longitude / 1000000;

    n = 0;

    for (i; i <= 1 + 3*sizelong-1; i++) {
        voltage = voltage | (bytes[i] << (sizelong-1-n)*8);  // Richtiges Byte an richtige Stelle schieben!
        n++;
    }

    decoded.voltage = voltage / 10000;

    return decoded;
}
