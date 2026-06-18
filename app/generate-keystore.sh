#!/bin/bash
# Generate release keystore if not exists
if [ ! -f "release.keystore" ]; then
    keytool -genkey -v \
        -keystore release.keystore \
        -alias allinone \
        -keyalg RSA \
        -keysize 2048 \
        -validity 10000 \
        -storepass allinone123 \
        -keypass allinone123 \
        -dname "CN=AllInOne Calculator, OU=Dev, O=Example, L=Beijing, ST=Beijing, C=CN"
fi
